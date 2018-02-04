package com.test.processor;

import com.test.annotation.TestAnnotation;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

/**
 *
 */
public class DemoProcessor extends AbstractProcessor {

    private Messager messager;

    public DemoProcessor() {}

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        messager = processingEnv.getMessager();

        messager.printMessage(Diagnostic.Kind.MANDATORY_WARNING, "");
        messager.printMessage(Diagnostic.Kind.MANDATORY_WARNING, "********************************");
        messager.printMessage(Diagnostic.Kind.MANDATORY_WARNING, "***** DemoProcessor init ! *****");
        messager.printMessage(Diagnostic.Kind.MANDATORY_WARNING, "********************************");
        messager.printMessage(Diagnostic.Kind.MANDATORY_WARNING, "");
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of(TestAnnotation.class.getName());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element e : roundEnv.getRootElements()) {
//            messager.printMessage(Diagnostic.Kind.MANDATORY_WARNING, e.getSimpleName());

            if (e instanceof TypeElement) { // TODO: Java 9, the first element is NOT an instance of TypeElement (module something ?)
                for (Element ee : ((TypeElement)e).getEnclosedElements()) {
                    if (ee.getAnnotation(TestAnnotation.class) != null) {
                        messager.printMessage(Diagnostic.Kind.MANDATORY_WARNING, ee.getSimpleName());
                    }
                }
            }
        }

        return false;
    }
}
