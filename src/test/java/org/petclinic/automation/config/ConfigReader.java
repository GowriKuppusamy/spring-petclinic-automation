package org.petclinic.automation.config;

import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {
  private static final String CONFIG_FILE = "/config.properties";
  private static final Properties PROPS = new Properties();

  static {
    try (InputStream is = ConfigReader.class.getResourceAsStream(CONFIG_FILE)) {
      if (is == null) {
        throw new IllegalStateException("Missing config file: " + CONFIG_FILE);
      }
      PROPS.load(is);
    } catch (Exception e) {
      throw new ExceptionInInitializerError(e);
    }
  }

  private ConfigReader() {}

  public static String get(String key) {
    String envKey = "PETCLINIC_" + key.toUpperCase();
    String env = System.getenv(envKey);
    if (env != null && !env.isBlank()) {
      return env.trim();
    }

    String system = System.getProperty(key);
    if (system != null && !system.isBlank()) {
      return system.trim();
    }

    return PROPS.getProperty(key);
  }

  public static String getBaseUrl() {
    String v = get("baseUrl");
    if (v == null || v.isBlank()) {
      throw new IllegalStateException("Missing config key: baseUrl");
    }
    return v.trim();
  }

  public static boolean getHeadless() {
    String v = get("headless");
    return v == null || Boolean.parseBoolean(v);
  }

  public static String getBrowser() {
    String v = get("browser");
    return (v == null || v.isBlank()) ? "chromium" : v.trim();
  }

  public static int getTimeoutMs() {
    String v = get("timeoutMs");
    return (v == null || v.isBlank()) ? 30000 : Integer.parseInt(v.trim());
  }
}
