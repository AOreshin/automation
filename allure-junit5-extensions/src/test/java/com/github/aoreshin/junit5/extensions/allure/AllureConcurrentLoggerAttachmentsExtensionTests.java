package com.github.aoreshin.junit5.extensions.allure;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import io.qameta.allure.AllureLifecycle;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;
import org.apache.logging.log4j.ThreadContext;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;

@Execution(ExecutionMode.SAME_THREAD)
final class AllureConcurrentLoggerAttachmentsExtensionTests {
  @ParameterizedTest
  @MethodSource("provider")
  void twoArgsConstructorTest(String logPath, String removeFishTagRegex) {
    AllureConcurrentLoggerAttachmentsExtension extension =
        new AllureConcurrentLoggerAttachmentsExtension(logPath, removeFishTagRegex);

    assertNull(extension.getRemoveSensitiveDataRegex());
  }

  @ParameterizedTest
  @MethodSource("provider")
  void threeArgsConstructorTest(String logPath, String removeFishTagRegex) {
    String removeSensitiveDataRegex = "anotherRegex";

    AllureConcurrentLoggerAttachmentsExtension extension =
        new AllureConcurrentLoggerAttachmentsExtension(
            logPath, removeFishTagRegex, removeSensitiveDataRegex);

    assertEquals(removeSensitiveDataRegex, extension.getRemoveSensitiveDataRegex());
  }

  @ParameterizedTest
  @MethodSource("provider")
  void afterEachTestWithoutRemovingSensitiveData(
      String logPath, String removeFishTagRegex, ArgumentCaptor<byte[]> captor) throws IOException {
    // Fixture setup
    String expected =
        "Some clever message" + "\n" + "Another clever message" + "\n" + "Blah-blah-blah";

    AllureConcurrentLoggerAttachmentsExtension extension =
        new AllureConcurrentLoggerAttachmentsExtension(logPath, removeFishTagRegex);
    AllureLifecycle lifecycle = setUpLifecycleMock(extension);

    // Executing SUT
    extension.afterEach(null);

    // Verification
    verifyResult(captor, expected, lifecycle);
  }

  @ParameterizedTest
  @MethodSource("provider")
  void afterEachTestWithRemovingSensitiveData(
      String logPath, String removeFishTagRegex, ArgumentCaptor<byte[]> captor) throws IOException {
    // Fixture setup
    String sensitiveDataRegex = "clever";

    String expected =
        "Some ***** message" + "\n" + "Another ***** message" + "\n" + "Blah-blah-blah";

    AllureConcurrentLoggerAttachmentsExtension extension =
        new AllureConcurrentLoggerAttachmentsExtension(
            logPath, removeFishTagRegex, sensitiveDataRegex);

    AllureLifecycle lifecycle = setUpLifecycleMock(extension);

    // Executing SUT
    extension.afterEach(null);

    // Verification
    verifyResult(captor, expected, lifecycle);
  }

  private void verifyResult(
      ArgumentCaptor<byte[]> captor, String expected, AllureLifecycle lifecycle) {
    verify(lifecycle, only())
        .addAttachment(eq("Полный лог"), eq("text/plain"), eq(".txt"), captor.capture());
    assertEquals(expected, new String(captor.getValue()));
  }

  private static List<String> getLines(String uuid) {
    String messageOne = "Some clever message";
    String messageTwo = "Another clever message";
    String messageThree = "I'm out of clever messages";
    String messageFour = "Blah-blah-blah";

    return List.of(
        getString(uuid, messageOne),
        getString(uuid, messageTwo),
        getString("blah", messageThree),
        getString(uuid, messageFour));
  }

  private static Stream<Arguments> provider() throws IOException {
    String logPath = System.getProperty("user.dir") + "/build/log.txt";
    String removeFishTagRegex = "^\\[\\S*\\] ";

    String uuid = UUID.randomUUID().toString();
    ThreadContext.put("id", uuid);

    List<String> lines = getLines(uuid);

    writeToFile(logPath, lines);

    ArgumentCaptor<byte[]> captor = ArgumentCaptor.forClass(byte[].class);

    return Stream.of(Arguments.of(logPath, removeFishTagRegex, captor));
  }

  private static String getString(String uuid, String message) {
    String format = "[%s] %s";
    return String.format(format, uuid, message);
  }

  private AllureLifecycle setUpLifecycleMock(AllureConcurrentLoggerAttachmentsExtension extension) {
    AllureLifecycle lifecycle = mock(AllureLifecycle.class);

    extension.setLifecycle(lifecycle);
    return lifecycle;
  }

  private static void writeToFile(String logPath, List<String> lines) throws IOException {
    FileWriter fileWriter = new FileWriter(Path.of(logPath).toString());

    for (String line : lines) {
      fileWriter.write(line + System.lineSeparator());
    }

    fileWriter.close();
  }
}
