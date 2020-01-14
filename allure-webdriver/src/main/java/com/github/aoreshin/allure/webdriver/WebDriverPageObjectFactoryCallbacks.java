package com.github.aoreshin.allure.webdriver;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.*;

/**
 * Extension that resolves specified page object classes as test parameters closes factory after
 * test execution, makes screenshot when test fails
 */
public class WebDriverPageObjectFactoryCallbacks
    implements AfterEachCallback, ParameterResolver, TestExecutionExceptionHandler {
  private static final Logger LOGGER = LogManager.getLogger();

  private final Set<Class<? extends WebDriverPageObject<?>>> pageObjectClassSet;
  private final WebDriverPageObjectFactory pageObjectFactory;
  private AllureLifecycle lifecycle = Allure.getLifecycle();

  public WebDriverPageObjectFactoryCallbacks(
      Set<Class<? extends WebDriverPageObject<?>>> pageObjectClassSet,
      WebDriverPageObjectFactory pageObjectFactory) {
    this.pageObjectClassSet = pageObjectClassSet;
    this.pageObjectFactory = pageObjectFactory;
  }

  @Override
  public void afterEach(ExtensionContext context) {
    this.pageObjectFactory.shutdown();
  }

  @Override
  public boolean supportsParameter(
      ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return pageObjectClassSet.stream()
        .anyMatch(
            pageObjectClass -> parameterContext.getParameter().getType().equals(pageObjectClass));
  }

  @SuppressWarnings("unchecked")
  @Override
  public Object resolveParameter(
      ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return pageObjectFactory.createPageObject(
        (Class<? extends WebDriverPageObject>) parameterContext.getParameter().getType());
  }

  @Override
  public void handleTestExecutionException(ExtensionContext context, Throwable throwable)
      throws Throwable {
    LOGGER.debug("Test ended with exception");
    lifecycle.addAttachment("Screenshot", "image/png", "", pageObjectFactory.makeScreenshot());
    throw throwable;
  }

  /** Only for testing */
  void setLifecycle(AllureLifecycle lifecycle) {
    this.lifecycle = lifecycle;
  }
}
