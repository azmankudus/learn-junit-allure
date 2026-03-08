package com.example.shared.infra;

/**
 * Internal container that maps a Task back to its parent Epic, Feature, and Story.
 * Used for detailed Allure labeling and environment metadata.
 */
record TaskInfo(
  Requirements.Epic epic,
  Requirements.Feature feature,
  Requirements.Story story,
  Requirements.Task task) {
}
