package com.github.aoreshin.junit5.allure.steps;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import java.util.UUID;
import java.util.function.Consumer;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

final class StepWrapperTests {
  @Test
  void nullNameThrows() {
    StepWrapper stepWrapper = new StepWrapper();
    assertThrows(NullPointerException.class, () -> stepWrapper.startStep(null));
  }

  @Test
  void stopWhenNotStartedDoesNotThrow() {
    StepWrapper stepWrapper = new StepWrapper();
    assertDoesNotThrow(() -> stepWrapper.stopStep(Status.PASSED));
  }

  @Test
  void startStepTest() {
    StepWrapper stepWrapper = spy(StepWrapper.class);
    AllureLifecycle allureLifecycle = mock(AllureLifecycle.class);

    when(stepWrapper.lifecycle()).thenReturn(allureLifecycle);

    stepWrapper.startStep(getStepName());

    verify(allureLifecycle, only()).startStep(anyString(), any());
  }

  @Test
  void stoppedStepTest() {
    // Fixture setup
    StepWrapper stepWrapper = spy(StepWrapper.class);

    AllureLifecycle allureLifecycle = mock(AllureLifecycle.class);

    when(stepWrapper.lifecycle()).thenReturn(allureLifecycle);

    Status status = Status.PASSED;

    @SuppressWarnings("unchecked")
    ArgumentCaptor<Consumer<StepResult>> captor = ArgumentCaptor.forClass(Consumer.class);

    // Executing SUT
    stepWrapper.startStep(getStepName());
    stepWrapper.stopStep(status);

    // Verification of AllureLifecycle calls
    verify(allureLifecycle, times(1)).startStep(anyString(), any());
    verify(allureLifecycle, times(1)).updateStep(anyString(), captor.capture());
    verify(allureLifecycle, times(1)).stopStep(anyString());

    // Verification of lambda logic
    StepResult stepResult = new StepResult();
    captor.getValue().accept(stepResult);
    assertEquals(status, stepResult.getStatus());
  }

  private String getStepName() {
    return UUID.randomUUID().toString();
  }
}
