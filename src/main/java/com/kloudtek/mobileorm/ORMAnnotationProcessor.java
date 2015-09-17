package com.kloudtek.mobileorm;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
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
        System.out.println();
        return false;
    }
}
