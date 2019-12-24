package com.github.aoreshin.junit5.extensions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that marks methods that should be excluded from JaCoCo coverage Apply ONLY generated
 * methods
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface Generated {}
