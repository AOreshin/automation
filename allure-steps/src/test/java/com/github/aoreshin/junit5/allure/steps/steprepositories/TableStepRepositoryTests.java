package com.github.aoreshin.junit5.allure.steps.steprepositories;

import static com.github.aoreshin.junit5.allure.steps.steprepositories.AssertionUtil.*;

import java.util.HashMap;
import org.junit.jupiter.api.Test;

final class TableStepRepositoryTests {
  private static class TableStepRepositoryImpl
      implements TableStepRepository<TableStepRepositoryImpl> {}

  private final TableStepRepository<?> tableStepRepository = new TableStepRepositoryImpl();

  @Test
  void checkCellContainsValueTest() {
    assertThrowsNotImplemented(() -> tableStepRepository.checkCellContainsValue(NAME, VALUE, 0));
  }

  @Test
  void checkColumnContainsValueTest() {
    assertThrowsNotImplemented(() -> tableStepRepository.checkColumnContainsValue(NAME, VALUE));
  }

  @Test
  void checkColumnDoesntContainValueTest() {
    assertThrowsNotImplemented(
        () -> tableStepRepository.checkColumnDoesntContainValue(NAME, VALUE));
  }

  @Test
  void checkColumnIsEmptyTest() {
    assertThrowsNotImplemented(() -> tableStepRepository.checkColumnIsEmpty(NAME));
  }

  @Test
  void checkColumnIsNotEmptyTest() {
    assertThrowsNotImplemented(() -> tableStepRepository.checkColumnIsNotEmpty(NAME));
  }

  @Test
  void checkColumnSizeTest() {
    assertThrowsNotImplemented(() -> tableStepRepository.checkColumnSize(NAME, 0));
  }

  @Test
  void checkTableIsEmptyTest() {
    assertThrowsNotImplemented(tableStepRepository::checkTableIsEmpty);
  }

  @Test
  void clickOnEntryRowTest() {
    assertThrowsNotImplemented(() -> tableStepRepository.clickOnEntry(NAME, 0));
  }

  @Test
  void clickOnEntryValueTest() {
    assertThrowsNotImplemented(() -> tableStepRepository.clickOnEntry(NAME, VALUE));
  }

  @Test
  void clickOnHeaderTest() {
    assertThrowsNotImplemented(() -> tableStepRepository.clickOnHeader(NAME));
  }

  @Test
  void clickOnHeaderListTest() {
    assertThrowsNotImplemented(() -> tableStepRepository.clickOnHeader(NAME_LIST));
  }

  @Test
  void headerIsPresentTest() {
    assertThrowsNotImplemented(() -> tableStepRepository.headerIsPresent(NAME));
  }

  @Test
  void headerIsPresentListTest() {
    assertThrowsNotImplemented(() -> tableStepRepository.headerIsPresent(NAME_LIST));
  }

  @Test
  void getNonNullValueTest() {
    assertThrowsNotImplemented(() -> tableStepRepository.getNonNullValue(NAME, new HashMap<>()));
  }

  @Test
  void getCellValueAndPutInMapTest() {
    assertThrowsNotImplemented(
        () -> tableStepRepository.getCellValueAndPutInMap(NAME, "blah", 0, new HashMap<>()));
  }
}
