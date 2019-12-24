package com.github.aoreshin.junit5.extensions.allure;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import org.apache.logging.log4j.ThreadContext;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Extension that processes logs from concurrent test execution
 *
 * <ul>
 *   <li>Reads logs from provided input stream
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
  private final InputStream inputStream;
  private final String removeFishTagRegex;
  private final String removeSensitiveDataRegex;

  public AllureConcurrentLoggerAttachmentsExtension(
      InputStream inputStream, String removeFishTagRegex) {
    this.inputStream = inputStream;
    this.removeFishTagRegex = removeFishTagRegex;
    this.removeSensitiveDataRegex = null;
  }

  public AllureConcurrentLoggerAttachmentsExtension(
      InputStream inputStream, String removeFishTagRegex, String removeSensitiveDataRegex) {
    this.inputStream = inputStream;
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

    lifecycle()
        .addAttachment("Полный лог", "text/plain", ".txt", groupedLogMessages.getBytes(UTF_8));
  }

  private String getMessagesWithId(String uuid) throws IOException {
    return Stream.of(new String(inputStream.readAllBytes()).split("\n"))
        .filter(logMessage -> logMessage.contains(uuid))
        .map(logMessage -> logMessage.replaceAll(removeFishTagRegex, ""))
        .collect(Collectors.joining("\n"));
  }

  /** Only for testing */
  AllureLifecycle lifecycle() {
    return Allure.getLifecycle();
  }

  String getRemoveSensitiveDataRegex() {
    return removeSensitiveDataRegex;
  }
}
