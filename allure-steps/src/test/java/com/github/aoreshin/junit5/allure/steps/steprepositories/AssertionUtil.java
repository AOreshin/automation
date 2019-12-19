package com.github.aoreshin.junit5.allure.steps.steprepositories;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.function.Executable;

class AssertionUtil {
  static final String NAME = "BLAH-BLAH";
  static final String VALUE = "BLAH-BLAH-BAAAA";
  static final List<String> NAME_LIST = List.of("blah", "blah-blah");
  static final Map<String, String> NAMES_AND_VALUES = Map.of(NAME, VALUE);

  static void assertThrowsNotImplemented(Executable executable) {
    assertThrows(NotImplementedException.class, executable);
  }
}
