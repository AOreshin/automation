package com.github.aoreshin.junit5.allure.steps;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import java.util.Objects;
import java.util.Stack;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** Class that helps to create nested Allure steps directly in test code */
final class StepWrapper {
  private static final Logger LOGGER = LogManager.getLogger();
  private final Stack<String> uuidStack = new Stack<>();
  private AllureLifecycle lifecycle = Allure.getLifecycle();

  StepWrapper() {}

  /**
   * Starts step, pushes step id to stack
   *
   * @param stepName
   */
  void startStep(String stepName) {
    Objects.requireNonNull(stepName, "stepName should not be null");
    String uuid = UUID.randomUUID().toString();
    lifecycle.startStep(uuid, new StepResult().setName(stepName));
    uuidStack.push(uuid);
  }

  /**
   * Closes first step in a stack
   *
   * @param status - status of a closed step
   */
  void stopStep(Status status) {
    if (uuidStack.size() != 0) {
      String uuid = uuidStack.pop();
      lifecycle.updateStep(uuid, update -> update.setStatus(status));
      lifecycle.stopStep(uuid);
    } else {
      LOGGER.warn("No steps to stop!");
    }
  }

  /** Only for testing */
  void setLifecycle(AllureLifecycle lifecycle) {
    this.lifecycle = lifecycle;
  }
}
