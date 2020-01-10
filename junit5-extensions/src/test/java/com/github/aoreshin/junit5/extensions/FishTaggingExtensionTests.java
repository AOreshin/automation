package com.github.aoreshin.junit5.extensions;

import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.UUID;
import org.apache.logging.log4j.ThreadContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

final class FishTaggingExtensionTests {
  @Test
  void beforeEachTest() {
    String uuid = UUID.randomUUID().toString();

    FishTaggingExtension extension = new FishTaggingExtension();

    extension.setUuid(uuid);

    extension.beforeEach(null);

    Assertions.assertEquals(uuid, ThreadContext.get("id"));
  }

  @Test
  void afterEachTest() {
    FishTaggingExtension extension = new FishTaggingExtension();

    extension.beforeEach(null);
    extension.afterEach(null);

    assertNull(ThreadContext.get("id"));
  }
}
