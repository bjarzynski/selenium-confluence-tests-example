package configuration;

import enums.EnvironmentVariable;
import java.util.Objects;

public class EnvironmentVariables {

  private EnvironmentVariables() {

  }

  public static String getStringValue(EnvironmentVariable envVarName) {
    String envVarValue = System.getProperty(envVarName.name());
    Objects.requireNonNull(envVarValue,
            envVarName + " environment variable is required in configuration.");
    return envVarValue;
  }

  public static long getLongValue(EnvironmentVariable envVarName) {
    String envVarValue = getStringValue(envVarName);
    return Long.parseLong(envVarValue);
  }
}
