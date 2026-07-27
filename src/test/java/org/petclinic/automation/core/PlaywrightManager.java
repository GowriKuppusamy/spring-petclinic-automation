package org.petclinic.automation.core;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import org.petclinic.automation.config.ConfigReader;

public final class PlaywrightManager {
  private static ThreadLocal<Playwright> playwright = new ThreadLocal<>();
  private static ThreadLocal<Browser> browser = new ThreadLocal<>();

  private PlaywrightManager() {}

  public static Playwright getPlaywright() {
    if (playwright.get() == null) {
      playwright.set(Playwright.create());
    }
    return playwright.get();
  }

  public static Browser getBrowser() {
    if (browser.get() == null) {
      String browserName = ConfigReader.getBrowser().toLowerCase();
      BrowserType type;
      switch (browserName) {
        case "firefox" -> type = getPlaywright().firefox();
        case "webkit" -> type = getPlaywright().webkit();
        case "chrome" , "chromium" -> type = getPlaywright().chromium();
        default -> throw new IllegalArgumentException("Unsupported browser: " + browserName);
      }

      Browser b = type.launch(new BrowserType.LaunchOptions().setHeadless(ConfigReader.getHeadless()));
      browser.set(b);
    }
    return browser.get();
  }

  public static void closeAll() {
    try {
      if (browser.get() != null) {
        browser.get().close();
        browser.remove();
      }
    } finally {
      if (playwright.get() != null) {
        playwright.get().close();
        playwright.remove();
      }
    }
  }
}
