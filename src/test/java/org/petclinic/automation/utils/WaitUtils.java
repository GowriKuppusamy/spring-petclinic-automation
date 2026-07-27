package org.petclinic.automation.utils;

import com.microsoft.playwright.Page;

public final class WaitUtils {
  private WaitUtils() {}

  public static void waitForStability(Page page) {
    try {
      page.waitForLoadState();
    } catch (Exception ignored) {
      // noop
    }
  }
}
