package com.github.aoreshin.junit5.extensions;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.UUID;
import org.apache.logging.log4j.ThreadContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

final class FishTaggingExtensionTests {
  @Test
  void beforeEachTest() {
    String uuid = UUID.randomUUID().toString();

    FishTaggingExtension extension = Mockito.spy(new FishTaggingExtension());

    when(extension.getUuid()).thenReturn(uuid);

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
