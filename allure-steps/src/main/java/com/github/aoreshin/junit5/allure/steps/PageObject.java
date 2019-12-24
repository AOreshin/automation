package com.github.aoreshin.junit5.allure.steps;

import java.lang.annotation.*;

/** Annotation to mark classes, that implement interfaces marked with @StepRepository */
@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PageObject {}
