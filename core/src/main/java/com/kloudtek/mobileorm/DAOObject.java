package com.kloudtek.mobileorm;

import javax.lang.model.element.Element;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by yannick on 9/17/15.
 */
public class DAOObject {
    private final String className;
    private final String daoClassName;
    private final String tableName;

    public DAOObject(Element tableObj) {
        className = tableObj.getSimpleName().toString();
        daoClassName = className+"DAO";
        final DbTable tableAnno = tableObj.getAnnotation(DbTable.class);
        tableName = tableAnno.value().isEmpty() ? className.toUpperCase() : tableAnno.value();
    }

    public String getClassName() {
        return className;
    }

    public String getDaoClassName() {
        return daoClassName;
    }

    public void writeSource(Writer w) throws IOException {
        w.write("import com.kloudtek.mobileorm.DAO;\n");
        w.write("public class " + daoClassName + " implements DAO {\n");
        w.write("\tpublic String getCreateSQL() {\n");
        w.write("\t\treturn \"CREATE TABLE "+tableName+" ( ");
        w.write(" );\";\n");
        w.write("\t}\n");
        w.write("}\n");
    }
}
