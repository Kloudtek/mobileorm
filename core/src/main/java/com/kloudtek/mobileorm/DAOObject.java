package com.kloudtek.mobileorm;

import com.sun.tools.javac.code.Symbol;

import javax.lang.model.element.Element;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by yannick on 9/17/15.
 */
public class DAOObject {
    private final String className;
    private final String daoClassName;
    private final String tableName;
    private final String pkg;
    private ArrayList<Col> columns = new ArrayList<>();

    public DAOObject(Element tableObj) {
        pkg = tableObj.getEnclosingElement().asType().toString();
        className = tableObj.getSimpleName().toString();
        daoClassName = className + "DAO";
        final Table tableAnno = tableObj.getAnnotation(Table.class);
        tableName = tableAnno.value().isEmpty() ? className.toUpperCase() : tableAnno.value();
        for (Element el : tableObj.getEnclosedElements()) {
            final Column dbCol = el.getAnnotation(Column.class);
            if (dbCol != null) {
                String classType = ((Symbol.VarSymbol) el).asType().baseType().asElement().flatName().toString();
                final Type typeAno = el.getAnnotation(Type.class);
                String fieldType;
                String persister = null;
                if (typeAno != null) {
                    fieldType = typeAno.value();
                    if (typeAno.persister() != null && !typeAno.persister().isEmpty()) {
                        persister = typeAno.persister();
                    }
                } else {
                    fieldType = toSQLType(classType);
                }
                columns.add(new Col(el.getSimpleName().toString(), dbCol.value(), fieldType, null, persister));
            }
        }
    }

    private String toSQLType(String classType) {
        return "integer";
    }

    public String getClassName() {
        return className;
    }

    public String getDaoClassName() {
        return daoClassName;
    }

    public void writeSource(Writer w) throws IOException {
        w.write("package " + pkg + ";\n\n");
        w.write("import com.kloudtek.mobileorm.DAO;\n");
        w.write("\npublic class " + daoClassName + " implements DAO<" + className + "> {\n");
        w.write("\tpublic String getCreateSQL() {\n");
        w.write("\t\treturn \"CREATE TABLE " + tableName + " ( ");
        final Iterator<Col> colIt = columns.iterator();
        while (colIt.hasNext()) {
            Col col = colIt.next();
            w.write(col.name+" "+col.fieldType);
            if( colIt.hasNext() ) {
                w.write(", ");
            }
        }
        w.write(" );\";\n");
        w.write("\t}\n");
        w.write("}\n");
    }

    public class Col {
        private String name;
        private String field;
        private String fieldType;
        private String classType;
        private String persister;

        public Col(String name, String field, String fieldType, String classType, String persister) {
            this.name = name;
            this.field = field;
            this.fieldType = fieldType;
            this.classType = classType;
            this.persister = persister;
        }
    }
}
