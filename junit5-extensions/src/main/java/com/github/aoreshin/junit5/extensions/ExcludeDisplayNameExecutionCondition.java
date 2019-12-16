package com.github.aoreshin.junit5.extensions;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.List;

import static com.github.aoreshin.junit5.extensions.DisplayNameExecutionConditionUtil.getDisplayNamesForSystemProperty;
import static org.junit.jupiter.api.extension.ConditionEvaluationResult.disabled;
import static org.junit.jupiter.api.extension.ConditionEvaluationResult.enabled;

public final class ExcludeDisplayNameExecutionCondition implements ExecutionCondition {
    private static final String PROPERTY_NAME = "excludeDisplayNames";
    private final List<String> DISPLAY_NAMES_LIST = getDisplayNamesForSystemProperty(PROPERTY_NAME);

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
        if (DISPLAY_NAMES_LIST.size() != 0) {
            String displayName = context.getDisplayName();

            boolean isContained = DISPLAY_NAMES_LIST
                    .stream()
                    .anyMatch(displayName::contains);

            if (isContained) {
                return disabled(displayName + " is present in " + PROPERTY_NAME + " " + DISPLAY_NAMES_LIST);
            }

            return enabled(displayName + " is not present in " + PROPERTY_NAME + " " + DISPLAY_NAMES_LIST);
        }

        return enabled(PROPERTY_NAME + " property is null");
    }
}