package com.github.aoreshin.junit5.extensions;

import java.util.Arrays;
import java.util.List;

class DisplayNameExecutionConditionUtil {
  private DisplayNameExecutionConditionUtil() {}

  static List<String> getDisplayNamesForSystemProperty(String property) {
    String displayNames = System.getProperty(property);

    if (displayNames != null) {
      return Arrays.asList(displayNames.split(","));
    } else {
      return List.of();
    }
  }
}
