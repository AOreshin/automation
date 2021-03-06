package com.github.aoreshin.junit5.extensions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.SAME_THREAD)
final class ExcludeDisplayNameRegexExecutionConditionTests {
  private static final String PROPERTY_NAME = "excludeDisplayNamesRegex";

  @Test
  void nullPropertyEnablesAllTests() {
    System.clearProperty(PROPERTY_NAME);

    ExecutionCondition executionCondition = new ExcludeDisplayNameRegexExecutionCondition();

    ConditionEvaluationResult result = executionCondition.evaluateExecutionCondition(null);

    assertFalse(result.isDisabled());
    assertEquals(PROPERTY_NAME + " property is null", result.getReason().orElseThrow());
  }

  @Test
  void shouldDisableTest() {
    String regex = "excludeMeNow";
    String displayName = "excludeMeNow";

    System.setProperty(PROPERTY_NAME, regex);

    ExtensionContext context = mock(ExtensionContext.class);
    when(context.getDisplayName()).thenReturn(displayName);

    ExecutionCondition executionCondition = new ExcludeDisplayNameRegexExecutionCondition();

    ConditionEvaluationResult result = executionCondition.evaluateExecutionCondition(context);

    assertTrue(result.isDisabled());
    assertEquals(
        displayName + " matches " + PROPERTY_NAME + " pattern " + regex,
        result.getReason().orElseThrow());
  }

  @Test
  void shouldEnableTest() {
    String regex = "excludeMeNow";
    String displayName = "nothingToExcludeHere";

    System.setProperty(PROPERTY_NAME, regex);

    ExtensionContext context = mock(ExtensionContext.class);
    when(context.getDisplayName()).thenReturn(displayName);

    ExecutionCondition executionCondition = new ExcludeDisplayNameRegexExecutionCondition();

    ConditionEvaluationResult result = executionCondition.evaluateExecutionCondition(context);

    assertFalse(result.isDisabled());
    assertEquals(
        displayName + " doesn't match " + PROPERTY_NAME + " pattern " + regex,
        result.getReason().orElseThrow());
  }
}
