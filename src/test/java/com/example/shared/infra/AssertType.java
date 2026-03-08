package com.example.shared.infra;

/**
 * Supported assertion operations for the requirements engine.
 */
public enum AssertType {
  /** Strict equality check */
  EQUALS,
  /** Checks if value starts with prefix */
  STARTS_WITH,
  /** Checks if value ends with suffix */
  ENDS_WITH,
  /** Checks for substring existence */
  CONTAINS,
  /** Validates against regex pattern */
  MATCHES_REGEX,
  /** Asserts value is null */
  IS_NULL,
  /** Asserts value is not null */
  IS_NOT_NULL,
  /** Asserts collection/string/map/array is empty */
  IS_EMPTY,
  /** Asserts string is blank */
  IS_BLANK
}
