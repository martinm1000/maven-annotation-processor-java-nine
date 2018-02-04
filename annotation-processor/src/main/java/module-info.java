module annotationprocessor {
    requires java.compiler;

    exports com.test.annotation;
    //exports com.test.processor; // still working manually using javac

    //provides javax.annotation.processing.Processor with com.test.processor.DemoProcessor; // may be unnecessary
}