package com.github.aoreshin.junit5.allure.steps.steprepositories;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

final class ButtonStepRepositoryTests {
    private static class ButtonStepRepositoryImpl implements ButtonStepRepository<ButtonStepRepositoryImpl> {}

    private final ButtonStepRepository<?> buttonStepRepository = new ButtonStepRepositoryImpl();

    @Test
    void clickButtonTest() {
        assertThrows(NotImplementedException.class,
                () -> buttonStepRepository.clickButton("blah"));
    }

    @Test
    void clickButtonListTest() {
        assertThrows(NotImplementedException.class,
                () -> buttonStepRepository.clickButton(List.of("blah")));
    }

    @Test
    void checkButtonIsDisplayedTest() {
        assertThrows(NotImplementedException.class,
                () -> buttonStepRepository.checkButtonIsDisplayed("blah"));
    }

    @Test
    void checkButtonIsDisplayedListTest() {
        assertThrows(NotImplementedException.class,
                () -> buttonStepRepository.checkButtonIsDisplayed(List.of("blah")));
    }
}
