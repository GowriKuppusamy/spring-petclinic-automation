package org.petclinic.automation.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public final class TestDataUtils {
  private static final DateTimeFormatter DNE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  private TestDataUtils() {}

  public static String uniqueString(String prefix) {
    return prefix + "-" + UUID.randomUUID().toString().substring(0, 8);
  }

  public static String today() {
    return LocalDate.now().format(DNE_FORMAT);
  }

  public static String futureDate() {
    return LocalDate.now().plusDays(3).format(DNE_FORMAT);
  }

  public static String pastDate() {
    return LocalDate.now().minusDays(3).format(DNE_FORMAT);
  }
}
