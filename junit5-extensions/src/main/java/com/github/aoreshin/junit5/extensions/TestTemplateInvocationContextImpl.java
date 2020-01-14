package com.github.aoreshin.junit5.extensions;

import java.util.List;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;

/** Simple implementation of TestTemplateInvocationContext interface */
public class TestTemplateInvocationContextImpl implements TestTemplateInvocationContext {
  private final List<Extension> extensions;
  private String displayName;

  TestTemplateInvocationContextImpl(String displayName, List<Extension> extensions) {
    this.displayName = displayName;
    this.extensions = extensions;
  }

  @Override
  public String getDisplayName(int invocationIndex) {
    return displayName + " [" + invocationIndex + "]";
  }

  @Override
  public List<Extension> getAdditionalExtensions() {
    return extensions;
  }
}
