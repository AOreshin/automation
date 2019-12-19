package com.github.aoreshin.junit5.allure.steps.steprepositories;

import static com.github.aoreshin.junit5.allure.steps.steprepositories.AssertionUtil.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

final class FieldStepRepositoryTests {
  private static class FieldStepRepositoryImpl
      implements FieldStepRepository<FieldStepRepositoryImpl> {}

  private final FieldStepRepository<?> fieldStepRepository = new FieldStepRepositoryImpl();

  @Test
  void checkFieldContainsValueTest() {
    assertThrowsNotImplemented(() -> fieldStepRepository.checkFieldContainsValue("blah", "blah"));
  }

  @Test
  void checkFieldContainsValueMapTest() {
    assertThrowsNotImplemented(
        () -> fieldStepRepository.checkFieldContainsValue(Map.of("blah", "blah")));
  }

  @Test
  void checkFieldDoesntContainValueTest() {
    assertThrowsNotImplemented(
        () -> fieldStepRepository.checkFieldDoesntContainValue("blah", "blah"));
  }

  @Test
  void checkFieldDoesntContainValueMapTest() {
    assertThrowsNotImplemented(
        () -> fieldStepRepository.checkFieldDoesntContainValue(Map.of("blah", "blah")));
  }

  @Test
  void checkFieldIsEmptyTest() {
    assertThrowsNotImplemented(() -> fieldStepRepository.checkFieldIsEmpty(NAME));
  }

  @Test
  void checkFieldIsEmptyListTest() {
    assertThrowsNotImplemented(() -> fieldStepRepository.checkFieldIsEmpty(NAME_LIST));
  }

  @Test
  void checkFieldIsNotEmptyTest() {
    assertThrowsNotImplemented(() -> fieldStepRepository.checkFieldIsNotEmpty(NAME));
  }

  @Test
  void checkFieldIsNotEmptyListTest() {
    assertThrowsNotImplemented(() -> fieldStepRepository.checkFieldIsNotEmpty(NAME_LIST));
  }

  @Test
  void clearFieldTest() {
    assertThrowsNotImplemented(() -> fieldStepRepository.clearField(NAME));
  }

  @Test
  void fillFieldTest() {
    assertThrowsNotImplemented(() -> fieldStepRepository.fillField(NAME, VALUE));
  }

  @Test
  void fillFieldMapTest() {
    assertThrowsNotImplemented(() -> fieldStepRepository.fillField(NAMES_AND_VALUES));
  }

  @Test
  void putFieldValueInMapTest() {
    assertThrowsNotImplemented(
        () -> fieldStepRepository.putFieldValueInMap(NAME, VALUE, new HashMap<>()));
  }
}
