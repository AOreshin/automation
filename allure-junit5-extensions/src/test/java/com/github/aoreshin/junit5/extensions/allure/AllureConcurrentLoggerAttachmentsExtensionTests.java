package com.github.aoreshin.junit5.extensions.allure;

import com.github.aoreshin.junit5.extensions.FishTaggingExtension;
import io.qameta.allure.AllureLifecycle;
import org.apache.logging.log4j.ThreadContext;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

final class AllureConcurrentLoggerAttachmentsExtensionTests {
  @Test
  void twoArgsConstructorTest() {
    InputStream inputStream =
        new InputStream() {
          @Override
          public int read() throws IOException {
            return 0;
          }
        };

    String removeFishTagRegex = "regex";

    AllureConcurrentLoggerAttachmentsExtension extension =
        new AllureConcurrentLoggerAttachmentsExtension(inputStream, removeFishTagRegex);

    assertNull(extension.getRemoveSensitiveDataRegex());
  }

  @Test
  void threeArgsConstructorTest() {
    InputStream inputStream =
        new InputStream() {
          @Override
          public int read() throws IOException {
            return 0;
          }
        };

    String removeFishTagRegex = "regex";
    String removeSensitiveDataRegex = "anotherRegex";

    AllureConcurrentLoggerAttachmentsExtension extension =
        new AllureConcurrentLoggerAttachmentsExtension(
            inputStream, removeFishTagRegex, removeSensitiveDataRegex);

    assertEquals(removeSensitiveDataRegex, extension.getRemoveSensitiveDataRegex());
  }

  @Test
  void afterEachTestWithoutRemovingSensitiveData() throws IOException {
    // Fixture setup
    String regex = "^\\[\\S*\\] ";

    String messageOne = "Some clever message";
    String messageTwo = "Another clever message";
    String messageThree = "I'm out of clever messages";
    String messageFour = "Blah-blah-blah";

    FishTaggingExtension fishTaggingExtension = new FishTaggingExtension();
    fishTaggingExtension.beforeEach(null);

    String uuid = ThreadContext.get("id");

    String messages =
        getString(uuid, messageOne)
            + getString(uuid, messageTwo)
            + getString("notPresentUuid", messageThree)
            + getString(uuid, messageFour);

    String expected = messageOne + "\n" + messageTwo + "\n" + messageFour;

    InputStream inputStream = new ByteArrayInputStream(messages.getBytes());

    AllureConcurrentLoggerAttachmentsExtension extension =
        spy(new AllureConcurrentLoggerAttachmentsExtension(inputStream, regex));
    AllureLifecycle lifecycle = mock(AllureLifecycle.class);

    when(extension.lifecycle()).thenReturn(lifecycle);
    ArgumentCaptor<byte[]> captor = ArgumentCaptor.forClass(byte[].class);

    // Executing SUT
    extension.afterEach(null);

    // Verification
    verify(lifecycle, times(1))
        .addAttachment(eq("Полный лог"), eq("text/plain"), eq(".txt"), captor.capture());

    String actual = new String(captor.getValue());

    assertEquals(expected, actual);
  }

  @Test
  void afterEachTestWithRemovingSensitiveData() throws IOException {
    // Fixture setup
    String regex = "^\\[\\S*\\] ";
    String sensitiveData = "clever";

    String messageOne = "Some clever message";
    String messageTwo = "Another clever message";
    String messageThree = "I'm out of clever messages";
    String messageFour = "Blah-blah-blah";

    FishTaggingExtension fishTaggingExtension = new FishTaggingExtension();
    fishTaggingExtension.beforeEach(null);

    String uuid = ThreadContext.get("id");

    String messages =
        getString(uuid, messageOne)
            + getString(uuid, messageTwo)
            + getString("notPresentUuid", messageThree)
            + getString(uuid, messageFour);

    InputStream inputStream = new ByteArrayInputStream(messages.getBytes());

    AllureConcurrentLoggerAttachmentsExtension extension =
        spy(new AllureConcurrentLoggerAttachmentsExtension(inputStream, regex, sensitiveData));
    AllureLifecycle lifecycle = mock(AllureLifecycle.class);

    when(extension.lifecycle()).thenReturn(lifecycle);
    ArgumentCaptor<byte[]> captor = ArgumentCaptor.forClass(byte[].class);

    // Executing SUT
    extension.afterEach(null);

    // Verification
    verify(lifecycle, times(1))
        .addAttachment(eq("Полный лог"), eq("text/plain"), eq(".txt"), captor.capture());

    String actual = new String(captor.getValue());

    assertFalse(actual.contains("clever"));
  }

  private String getString(String uuid, String message) {
    String format = "[%s] %s\n";
    return String.format(format, uuid, message);
  }
}
