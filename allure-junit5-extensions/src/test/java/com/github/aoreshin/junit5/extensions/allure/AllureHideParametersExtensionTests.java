package com.github.aoreshin.junit5.extensions.allure;

import io.qameta.allure.model.StepResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.github.aoreshin.junit5.extensions.allure.AllureExtensionsTestUtil.*;
import static org.junit.jupiter.api.Assertions.*;

final class AllureHideParametersExtensionTests {
    @Test
    void afterEachTest() {
        //Fixture set up
        Map<String, StepResult> stepResults = Map.of(
                getUuid(), getStepResult("login")
                        .setParameters(List.of(
                                getParameter("password", "1234"),
                                getParameter("login", "t1000")
                        )),
                getUuid(), getStepResult("authorize")
                        .setParameters(List.of(
                                getParameter("token", "ohgeez"),
                                getParameter("name", "morty")
                        )),
                getUuid(), getStepResult("sign in")
                        .setParameters(List.of(
                                getParameter("secret", "iwanttosleep"),
                                getParameter("really", "badly"),
                                getParameter("and", "also"),
                                getParameter("i", "want"),
                                getParameter("to", "eat"),
                                getParameter("seriously", "!")
                        ))
        );

        startSteps(stepResults);

        AllureHideParametersExtension extension = new AllureHideParametersExtension();

        //Executing SUT
        extension.afterEach(null);

        //Verification
        List<StepResult> processedStepResults = getProcessedStepResults(stepResults);

        List<Executable> executables = processedStepResults
                .stream()
                .map(stepResult -> (Executable) () -> assertTrue(stepResult.getParameters().isEmpty()))
                .collect(Collectors.toUnmodifiableList());

        assertFalse(executables.isEmpty());
        assertAll(executables);
    }
}