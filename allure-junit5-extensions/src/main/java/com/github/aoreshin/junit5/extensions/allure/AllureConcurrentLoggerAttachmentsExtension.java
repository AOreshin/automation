package com.github.aoreshin.junit5.extensions.allure;

import static java.nio.charset.StandardCharsets.UTF_8;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import org.apache.logging.log4j.ThreadContext;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * Extension that processes logs from concurrent test execution
 *
 * <ul>
 *   <li>Reads logs from provided file
 *   <li>Filters lines that contain id, specified in Log4J ThreadContext
 *   <li>Applies regex to filtered lines to remove fish tag
 *   <li>Applies regex to filtered lines to remove all sensitive data (passwords, tokens, accounts
 *       etc)
 *   <li>Adds the result to Allure as text file attachment
 * </ul>
 *
 * <p>Use com.github.aoreshin.junit5.extensions.TestTemplateInvocationContextBuilder addExtension
 * method and pass constructor parameters.
 */
public final class AllureConcurrentLoggerAttachmentsExtension implements AfterEachCallback {
  private final String logPath;
  private final String removeFishTagRegex;
  private final String removeSensitiveDataRegex;
  private AllureLifecycle lifecycle = Allure.getLifecycle();

  public AllureConcurrentLoggerAttachmentsExtension(String logPath, String removeFishTagRegex) {
    this.logPath = logPath;
    this.removeFishTagRegex = removeFishTagRegex;
    this.removeSensitiveDataRegex = null;
  }

  public AllureConcurrentLoggerAttachmentsExtension(
      String logPath, String removeFishTagRegex, String removeSensitiveDataRegex) {
    this.logPath = logPath;
    this.removeFishTagRegex = removeFishTagRegex;
    this.removeSensitiveDataRegex = removeSensitiveDataRegex;
  }

  @Override
  public void afterEach(ExtensionContext context) throws IOException {
    // Grouping logs by id
    String groupedLogMessages = getMessagesWithId(ThreadContext.get("id"));

    if (removeSensitiveDataRegex != null) {
      groupedLogMessages = groupedLogMessages.replaceAll(removeSensitiveDataRegex, "*****");
    }

    lifecycle.addAttachment("Полный лог", "text/plain", ".txt", groupedLogMessages.getBytes(UTF_8));
  }

  private String getMessagesWithId(String uuid) throws IOException {
    return Files.readAllLines(Path.of(logPath), UTF_8).stream()
        .filter(logMessage -> logMessage.contains(uuid))
        .map(logMessage -> logMessage.replaceAll(removeFishTagRegex, ""))
        .collect(Collectors.joining("\n"));
  }

  String getRemoveSensitiveDataRegex() {
    return removeSensitiveDataRegex;
  }

  /** Only for testing */
  void setLifecycle(AllureLifecycle lifecycle) {
    this.lifecycle = lifecycle;
  }
}
