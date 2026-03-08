package com.example.shared.infra;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * Utility class for common test-related operations.
 */
public class TestUtils {

  /**
   * Converts a string value to a specified Java type.
   * Supports String, Integer, Long, Boolean, and Double.
   *
   * @param value The string value to convert.
   * @param type The target type name (e.g., "java.lang.Integer" or "int").
   * @return The converted object, or the original string if conversion fails or type is unsupported.
   */
  public static Object convertToType(String value, String type) {
    if (value == null || type == null || type.equals("java.lang.String")) {
      return value;
    }
    try {
      return switch (type) {
        case "java.lang.Integer", "int" -> Integer.parseInt(value);
        case "java.lang.Long", "long" -> Long.parseLong(value);
        case "java.lang.Boolean", "boolean" -> Boolean.parseBoolean(value);
        case "java.lang.Double", "double" -> Double.parseDouble(value);
        default -> value;
      };
    } catch (Exception e) {
      return value;
    }
  }

  /**
   * Checks if an object is empty.
   * Supports String, Collection, Map, and Arrays.
   *
   * @param obj The object to check.
   * @return true if the object is null or empty, false otherwise.
   */
  public static boolean isEmpty(Object obj) {
    if (obj == null) return true;
    if (obj instanceof String) return ((String) obj).isEmpty();
    if (obj instanceof Collection) return ((Collection<?>) obj).isEmpty();
    if (obj instanceof Map) return ((Map<?, ?>) obj).isEmpty();
    if (obj.getClass().isArray()) return Array.getLength(obj) == 0;
    return false;
  }
}
