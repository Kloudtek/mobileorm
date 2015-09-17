package com.kloudtek.mobileorm;

import com.sun.tools.javac.code.Symbol;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

/**
 * Created by yannick on 9/17/15.
 */
@SupportedAnnotationTypes({"com.kloudtek.mobileorm.DbTable"})
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class ORMAnnotationProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        final Set<? extends Element> tableObjs = roundEnv.getElementsAnnotatedWith(DbTable.class);
        for (Element tableObj : tableObjs) {
            String cName = ((Symbol.ClassSymbol) tableObj).getQualifiedName().toString() + "ORMPersister";
            try {
                JavaFileObject jfo = processingEnv.getFiler().createSourceFile(cName);

                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "creating source file: " + jfo.toUri());

                Writer writer = jfo.openWriter();
                writeClass(tableObj,writer);

                writer.close();
            } catch (IOException e) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Error generating "+cName+" : "+e.getMessage());
                return false;
            }
        }
        return true;
    }

    private void writeClass(Element tableObj, Writer writer) {

    }
}
