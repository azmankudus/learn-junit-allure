package com.example.shared.infra;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data model for the requirements.yaml file.
 * Represents the 4-layer hierarchy: Epic -> Feature -> Story -> Task -> Check.
 */
public record Requirements(
  Project project,
  List<Epic> epics) {

  /** Project metadata */
  static record Project(
    String id,
    String lead) {
  }

  /** Large functional area representing a primary objective */
  static record Epic(
    String id,
    String name,
    String description,
    @JsonProperty("success_criteria") List<String> successCriteria,
    List<Feature> features) {
  }

  /** Specific business capability within an Epic */
  static record Feature(
    String id,
    String name,
    @JsonProperty("success_criteria") List<String> successCriteria,
    List<Story> stories) {
  }

  /** Narrative describing a specific user interaction */
  static record Story(
    String id,
    String name,
    String description,
    @JsonProperty("success_criteria") List<String> successCriteria,
    List<Task> tasks) {
  }

  /** Technical unit of work representing a single test method */
  public static record Task(
    String id,
    String name,
    String description,
    @JsonProperty("success_criterion") String successCriterion,
    List<Check> checks) {
  }

  /** Individual validation point within a Task */
  public static record Check(
    String id,
    String scenario,
    String input,
    @JsonProperty("input_type") String inputType,
    String operator,
    String expected,
    @JsonProperty("expected_type") String expectedType,
    @JsonProperty("ignore_case") boolean ignoreCase,
    @JsonProperty("failed_message") String failedMessage) {
  }
}
