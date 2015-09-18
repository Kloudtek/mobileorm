package com.kloudtek.mobileorm;

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
@SupportedAnnotationTypes({"com.kloudtek.mobileorm.*"})
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class ORMAnnotationProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        final Set<? extends Element> tableObjs = roundEnv.getElementsAnnotatedWith(Table.class);
        for (Element tableObj : tableObjs) {
            final DAOObject daoObject = new DAOObject(tableObj);
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Processing " + daoObject.getClassName());
            try {
                JavaFileObject jfo = processingEnv.getFiler().createSourceFile(daoObject.getDaoClassName());

                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "creating dao file: " + jfo.toUri());

                Writer writer = jfo.openWriter();
                daoObject.writeSource(writer);

                writer.close();
            } catch (IOException e) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Error generating " + daoObject.getDaoClassName() + " : " + e.getMessage());
                return false;
            }
        }
        return true;
    }

}
