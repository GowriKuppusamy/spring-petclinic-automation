package org.petclinic.automation.core;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIRequestNewContextOptions;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import org.petclinic.automation.config.ConfigReader;

public final class DriverFactory {
  private static final ThreadLocal<BrowserContext> CONTEXT = new ThreadLocal<>();
  private static final ThreadLocal<Page> PAGE = new ThreadLocal<>();
  private static final ThreadLocal<APIRequestContext> API = new ThreadLocal<>();

  private DriverFactory() {}

  public static Page getPage() {
    return PAGE.get();
  }

  public static APIRequestContext getApi() {
    return API.get();
  }

  public static void createPage() {
    Browser browser = PlaywrightManager.getBrowser();
    BrowserContext context = browser.newContext();
    context.setDefaultTimeout(ConfigReader.getTimeoutMs());
    PAGE.set(context.newPage());
    CONTEXT.set(context);
  }

  public static void createApiContext() {
    APIRequestContext api = PlaywrightManager.getPlaywright().request().newContext(
        new APIRequestNewContextOptions().setBaseURL(ConfigReader.getBaseUrl())
    );
    API.set(api);
  }

  public static void cleanup() {
    try {
      if (PAGE.get() != null) {
        PAGE.get().close();
        PAGE.remove();
      }
      if (CONTEXT.get() != null) {
        CONTEXT.get().close();
        CONTEXT.remove();
      }
      if (API.get() != null) {
        API.get().dispose();
        API.remove();
      }
    } finally {
      PlaywrightManager.closeAll();
    }
  }
}
