package com.github.aoreshin.junit5.extensions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import org.junit.jupiter.api.extension.ExtensionContext;

public class TestTemplateSupportEvaluator {
  private final Class<?> testClass;
  private final String testClassSimpleName;
  private final String testMethodName;
  private final List<Supplier<Boolean>> suppliers = new ArrayList<>();

  public TestTemplateSupportEvaluator(ExtensionContext extensionContext) {
    this.testClass = extensionContext.getTestClass().orElseThrow();
    this.testClassSimpleName = extensionContext.getTestClass().orElseThrow().getSimpleName();
    this.testMethodName = extensionContext.getTestMethod().orElseThrow().getName();
  }

  public TestTemplateSupportEvaluator support(Class<?> testClass) {
    suppliers.add(() -> isSameTestClass(testClass));
    return this;
  }

  public TestTemplateSupportEvaluator support(Class<?> testClass, String... testMethods) {
    suppliers.add(
        () -> isSameTestClass(testClass) && containsTestMethod(testMethodName, testMethods));
    return this;
  }

  public TestTemplateSupportEvaluator support(String testClassSimpleName) {
    suppliers.add(() -> isSameTestClass(testClassSimpleName));
    return this;
  }

  public TestTemplateSupportEvaluator support(String testClassSimpleName, String... testMethods) {
    suppliers.add(
        () ->
            isSameTestClass(testClassSimpleName)
                && containsTestMethod(testMethodName, testMethods));
    return this;
  }

  public TestTemplateSupportEvaluator supportTestMethod(String... testMethodNames) {
    suppliers.add(() -> containsTestMethod(testMethodName, testMethodNames));
    return this;
  }

  private boolean isSameTestClass(String testClassName) {
    return this.testClassSimpleName.equals(testClassName);
  }

  private boolean isSameTestClass(Class<?> testClass) {
    return this.testClass.equals(testClass);
  }

  private boolean containsTestMethod(String testMethodName, String... testMethods) {
    return List.of(testMethods).contains(testMethodName);
  }

  public boolean evaluate() {
    return suppliers.stream().anyMatch(Supplier::get);
  }

  /** Only for testing */
  Class<?> getTestClass() {
    return testClass;
  }

  /** Only for testing */
  String getTestClassSimpleName() {
    return testClassSimpleName;
  }

  /** Only for testing */
  String getTestMethodName() {
    return testMethodName;
  }
}
