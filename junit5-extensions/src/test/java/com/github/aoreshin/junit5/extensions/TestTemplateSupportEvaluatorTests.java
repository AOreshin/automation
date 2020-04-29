package com.github.aoreshin.junit5.extensions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TestTemplateSupportEvaluatorTests {
  class TestClass {}

  class AnotherTestClass {}

  @Test
  void constructorTestClassTest() {
    ExtensionContext context = mock(ExtensionContext.class);
    when(context.getTestClass()).thenReturn(Optional.of(TestClass.class));

    Method method = mock(Method.class);
    String testMethodName = "awesomeTest";
    when(method.getName()).thenReturn(testMethodName);
    when(context.getTestMethod()).thenReturn(Optional.of(method));

    TestTemplateSupportEvaluator evaluator = new TestTemplateSupportEvaluator(context);

    assertEquals(TestClass.class, evaluator.getTestClass());
    assertEquals(TestClass.class.getSimpleName(), evaluator.getTestClassSimpleName());
    assertEquals(testMethodName, evaluator.getTestMethodName());
  }

  @ParameterizedTest
  @MethodSource("argumentsStream")
  void supportTestClassTrue(TestTemplateSupportEvaluator evaluator) {
    boolean result = evaluator.support(TestClass.class).evaluate();

    assertTrue(result);
  }

  @ParameterizedTest
  @MethodSource("argumentsStream")
  void supportTestClassFalse(TestTemplateSupportEvaluator evaluator) {
    boolean result = evaluator.support(AnotherTestClass.class).evaluate();

    assertFalse(result);
  }

  @ParameterizedTest
  @MethodSource("argumentsStream")
  void supportTestClassNameTrue(TestTemplateSupportEvaluator evaluator) {
    boolean result = evaluator.support("TestClass").evaluate();

    assertTrue(result);
  }

  @ParameterizedTest
  @MethodSource("argumentsStream")
  void supportTestClassNameFalse(TestTemplateSupportEvaluator evaluator) {
    boolean result = evaluator.support("Blahblah").evaluate();

    assertFalse(result);
  }

  @ParameterizedTest
  @MethodSource("argumentsStream")
  void supportTestClassAndMethodNamesTrue(
      TestTemplateSupportEvaluator evaluator, String testMethodName) {
    boolean result = evaluator.support(TestClass.class, testMethodName).evaluate();

    assertTrue(result);
  }

  @ParameterizedTest
  @MethodSource("argumentsStream")
  void supportTestClassAndMethodNamesWrongMethodName(TestTemplateSupportEvaluator evaluator) {
    boolean result = evaluator.support(TestClass.class, "someRandomNameWhoCares").evaluate();

    assertFalse(result);
  }

  @ParameterizedTest
  @MethodSource("argumentsStream")
  void supportTestClassAndMethodNamesWrongTestClass(
      TestTemplateSupportEvaluator evaluator, String testMethodName) {
    boolean result = evaluator.support(AnotherTestClass.class, testMethodName).evaluate();

    assertFalse(result);
  }

  @ParameterizedTest
  @MethodSource("argumentsStream")
  void supportTestClassSimpleNameAndMethodNamesTrue(
      TestTemplateSupportEvaluator evaluator, String testMethodName) {
    boolean result = evaluator.support(TestClass.class.getSimpleName(), testMethodName).evaluate();

    assertTrue(result);
  }

  @ParameterizedTest
  @MethodSource("argumentsStream")
  void supportTestClassSimpleNameAndMethodNamesWrongMethodName(
      TestTemplateSupportEvaluator evaluator) {
    boolean result =
        evaluator.support(TestClass.class.getSimpleName(), "someRandomNameWhoCares").evaluate();

    assertFalse(result);
  }

  @ParameterizedTest
  @MethodSource("argumentsStream")
  void supportTestClassSimpleNameAndMethodNamesWrongClassName(
      TestTemplateSupportEvaluator evaluator, String testMethodName) {
    boolean result =
        evaluator.support(AnotherTestClass.class.getSimpleName(), testMethodName).evaluate();

    assertFalse(result);
  }

  @ParameterizedTest
  @MethodSource("argumentsStream")
  void supportTestMethodTrue(TestTemplateSupportEvaluator evaluator, String testMethodName) {
    boolean result = evaluator.supportTestMethod(testMethodName).evaluate();

    assertTrue(result);
  }

  @ParameterizedTest
  @MethodSource("argumentsStream")
  void supportTestMethodFalse(TestTemplateSupportEvaluator evaluator) {
    boolean result = evaluator.supportTestMethod("someRandomNameWhoCares").evaluate();

    assertFalse(result);
  }

  private static Stream<Arguments> argumentsStream() {
    ExtensionContext context = mock(ExtensionContext.class);
    when(context.getTestClass()).thenReturn(Optional.of(TestClass.class));

    Method method = mock(Method.class);
    String testMethodName = "awesomeTest";
    when(method.getName()).thenReturn(testMethodName);
    when(context.getTestMethod()).thenReturn(Optional.of(method));

    TestTemplateSupportEvaluator evaluator = new TestTemplateSupportEvaluator(context);

    return Stream.of(Arguments.of(evaluator, testMethodName));
  }
}
