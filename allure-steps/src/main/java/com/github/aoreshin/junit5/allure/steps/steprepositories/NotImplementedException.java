package com.github.aoreshin.junit5.allure.steps.steprepositories;

final class NotImplementedException extends RuntimeException {
  private static final String MESSAGE = "Implement this method";

  public NotImplementedException() {
    super(MESSAGE);
  }
}
