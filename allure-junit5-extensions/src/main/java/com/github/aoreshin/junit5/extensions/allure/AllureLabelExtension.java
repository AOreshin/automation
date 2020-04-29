package com.github.aoreshin.junit5.extensions.allure;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Label;
import java.util.List;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * Extension that is used to programmatically add Labels to Allure report. To be used with
 * com.github.aoreshin.junit5.extensions.TestTemplateInvocationContextBuilder
 */
public class AllureLabelExtension implements BeforeEachCallback {
  private final List<Label> labelList;

  public AllureLabelExtension(String name, String value) {
    this.labelList = List.of(new Label().setName(name).setValue(value));
  }

  public AllureLabelExtension(Label label) {
    this.labelList = List.of(label);
  }

  public AllureLabelExtension(List<Label> labelList) {
    this.labelList = labelList;
  }

  @Override
  public void beforeEach(ExtensionContext context) {
    Allure.getLifecycle().updateTestCase(testResult -> testResult.getLabels().addAll(labelList));
  }

  /** Only for testing */
  List<Label> getLabelList() {
    return labelList;
  }
}
