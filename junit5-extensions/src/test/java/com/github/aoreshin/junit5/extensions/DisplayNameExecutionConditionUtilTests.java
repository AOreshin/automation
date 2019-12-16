package com.github.aoreshin.junit5.extensions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.List;

import static com.github.aoreshin.junit5.extensions.DisplayNameExecutionConditionUtil.getDisplayNamesForSystemProperty;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Execution(ExecutionMode.SAME_THREAD)
final class DisplayNameExecutionConditionUtilTests {
    @Test
    void nonNullProperty() {
        String propertyName = "randomProp";
        String value = "one,two,three";

        System.setProperty(propertyName, value);

        List<String> expected = List.of(value.split(","));

        List<String> values = getDisplayNamesForSystemProperty(propertyName);

        assertEquals(expected, values);
    }

    @Test
    void nullProperty() {
        String propertyName = "randomProp";

        System.clearProperty(propertyName);

        List<String> expected = List.of();

        List<String> values = getDisplayNamesForSystemProperty(propertyName);

        assertEquals(expected, values);
    }
}
