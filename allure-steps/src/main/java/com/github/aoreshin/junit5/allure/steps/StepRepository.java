package com.github.aoreshin.junit5.allure.steps;

import java.lang.annotation.*;

/** Annotation to mark interfaces that contain reusable test steps */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface StepRepository {}
