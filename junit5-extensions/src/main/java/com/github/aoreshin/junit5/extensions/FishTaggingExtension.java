package com.github.aoreshin.junit5.extensions;

import java.util.UUID;
import org.apache.logging.log4j.ThreadContext;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * Extension that is used to tag all logged messages with tag, which is present in Log4J
 * ThreadContext. Should be highest priority extension, to capture logs from other callbacks
 * https://junit.org/junit5/docs/current/user-guide/#extensions-execution-order-wrapping-behavior
 */
public class FishTaggingExtension implements BeforeEachCallback, AfterEachCallback {
  private String uuid = UUID.randomUUID().toString();

  @Override
  public void beforeEach(ExtensionContext context) {
    ThreadContext.put("id", uuid);
  }

  @Override
  public void afterEach(ExtensionContext context) {
    ThreadContext.clearMap();
  }

  /** Only for testing */
  void setUuid(String uuid) {
    this.uuid = uuid;
  }
}
