package com.example.shared.infra;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Base class for all requirement-driven tests.
 * Manages the Allure reporting lifecycle and provides multi-assertion capabilities via {@link Assertions#assertAll}.
 */
public abstract class TestHandler {

  /**
   * System property 'test.proceed' (default: false).
   * If true, the test will continue executing all checks in a task even if one fails.
   */
  public static final boolean PROCEED_ON_FAIL = Boolean.parseBoolean(System.getProperty("test.proceed", "false"));

  private static final ThreadLocal<List<Executable>> assertsThreadLocal = ThreadLocal.withInitial(ArrayList::new);
  private static final ThreadLocal<Requirements.Task> taskThreadLocal = new ThreadLocal<>();

  /**
   * Retrieves the current task context.
   * @return The active {@link Requirements.Task}.
   * @throws IllegalStateException if setTask(String) has not been called for this test.
   */
  protected Requirements.Task getTask() {
    Requirements.Task task = taskThreadLocal.get();
    if (task == null) {
      throw new IllegalStateException("Task context not initialized. Did you call setTask(taskId)?");
    }
    return task;
  }

  @BeforeEach
  protected void setup() {
    assertsThreadLocal.get().clear();
    taskThreadLocal.remove();
  }

  /**
   * Executes all collected assertions at the end of each test method.
   * Correctively distinguishes between "Failed" and "Broken" statuses in Allure.
   * @throws Throwable aggregated failure if any checks did not pass.
   */
  @AfterEach
  protected void execute() throws Throwable {
    List<Executable> asserts = assertsThreadLocal.get();
    try {
      if (!asserts.isEmpty()) {
        if (PROCEED_ON_FAIL) {
          try {
            Assertions.assertAll(asserts.stream());
          } catch (org.opentest4j.MultipleFailuresError mfe) {
            boolean hasBroken = mfe.getFailures().stream()
                .anyMatch(f -> !(f instanceof AssertionError));
            if (hasBroken) {
              throw new RuntimeException("Broken: contains non-assertion failures", mfe);
            }
            throw mfe;
          }
        } else {
          for (Executable exec : asserts) {
            exec.execute();
          }
        }
      }
    } finally {
      setup(); // Clean up thread locals
    }
  }

  /**
   * Initializes the test context with a specific Task from the requirements.
   * Automatically sets Allure Epic, Feature, and Story labels.
   * @param taskId The ID of the task to load (e.g., "SAMPLE-T-1").
   */
  protected void setTask(String taskId) {
    TaskInfo taskInfo = RequirementsManager.getTaskInfo(taskId);
    if (taskInfo == null) {
      throw new RuntimeException("Task ID '" + taskId + "' not found in requirements.yaml");
    }

    Allure.epic(taskInfo.epic().id() + " - " + taskInfo.epic().name());
    Allure.feature(taskInfo.feature().id() + " - " + taskInfo.feature().name());
    Allure.story(taskInfo.story().id() + " - " + taskInfo.story().name());

    Allure.label("task", taskInfo.task().id() + " - " + taskInfo.task().name());

    String descriptionHtml = """
        %s<br>
        <br>
        Success criterion : %s
        """.formatted(
        taskInfo.task().description(), taskInfo.task().successCriterion());

    Allure.descriptionHtml(descriptionHtml);

    taskThreadLocal.set(taskInfo.task());
  }

  protected void info(String name, String details) {
    Allure.step("Info: " + name, context -> {
      if (!TestUtils.isEmpty(details))
        context.parameter("Details", details);
    });
  }

  /**
   * Functional interface for operations that can throw checked or unchecked exceptions.
   *
   * @param <T> The input type.
   * @param <R> The result type.
   */
  @FunctionalInterface
  public interface ThrowingFunction<T, R> {
    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     * @throws Throwable if the operation fails
     */
    R apply(T t) throws Throwable;
  }

  /**
   * Core assertion method that executes a producer lazily inside an Allure step.
   * Captures any Throwable and reports it to Allure.
   *
   * @param name Name of the check (step).
   * @param input Input value from requirements for documentation.
   * @param expected Expected value from requirements.
   * @param actualProducer Producer to resolve the real actual value (simulation/service call).
   * @param operation The type of comparison to perform.
   * @param failedMessage Custom failure message.
   */
  protected void check(String name, Object input, Object expected,
      org.junit.jupiter.api.function.ThrowingSupplier<Object> actualProducer, AssertType operation,
      String failedMessage) {

    Executable assertExec = () -> Allure.step("Check: " + name, context -> {
      try {
        context.parameter("Operation", String.valueOf(operation));
        context.parameter("Input", Objects.toString(input, "null"));
        context.parameter("Expected", Objects.toString(expected, "null"));

        Object actual = actualProducer.get();
        context.parameter("Actual", Objects.toString(actual, "null"));

        switch (operation) {
          case EQUALS -> Assertions.assertEquals(expected, actual, failedMessage);
          case STARTS_WITH ->
            Assertions.assertTrue(String.valueOf(actual).startsWith(String.valueOf(expected)), failedMessage);
          case ENDS_WITH ->
            Assertions.assertTrue(String.valueOf(actual).endsWith(String.valueOf(expected)), failedMessage);
          case CONTAINS ->
            Assertions.assertTrue(String.valueOf(actual).contains(String.valueOf(expected)), failedMessage);
          case MATCHES_REGEX ->
            Assertions.assertTrue(String.valueOf(actual).matches(String.valueOf(expected)), failedMessage);
          case IS_NULL -> Assertions.assertNull(actual, failedMessage);
          case IS_NOT_NULL -> Assertions.assertNotNull(actual, failedMessage);
          case IS_EMPTY -> Assertions.assertTrue(TestUtils.isEmpty(actual), failedMessage);
          case IS_BLANK -> Assertions.assertTrue(String.valueOf(actual).trim().isEmpty(), failedMessage);
          default -> throw new IllegalArgumentException("Unsupported operation: " + operation);
        }
      } catch (Throwable t) {
        context.parameter("Exception", t.getClass().getSimpleName() + ": " + t.getMessage());
        throw t;
      }
    });

    assertsThreadLocal.get().add(assertExec);
  }

  /**
   * Standard check method for pre-evaluated results.
   */
  public void check(Requirements.Check check, Object actual) {
    check(check, input -> actual);
  }

  /**
   * Enhanced check method for lazy evaluation using a function that maps requirement input to actual result.
   */
  @SuppressWarnings("unchecked")
  public <T, R> void check(Requirements.Check check, ThrowingFunction<T, R> evaluation) {
    String rawInput = check.input();
    String rawExpected = check.expected();

    Object finalInput = TestUtils.convertToType(rawInput, check.inputType());
    Object finalExpectedRaw = TestUtils.convertToType(rawExpected, check.expectedType());

    if (check.ignoreCase() && finalExpectedRaw instanceof String) {
      finalExpectedRaw = ((String) finalExpectedRaw).toLowerCase();
    }
    final Object finalExpected = finalExpectedRaw;

    AssertType operation = AssertType.valueOf(check.operator());
    String failedMessage = check.failedMessage() != null ? check.failedMessage() : "Check Failed";

    check(check.scenario(), finalInput, finalExpected, () -> {
      Object actual = evaluation.apply((T) finalInput);
      if (check.ignoreCase() && actual instanceof String && finalExpected instanceof String) {
        return ((String) actual).toLowerCase();
      }
      return actual;
    }, operation, failedMessage);
  }

  protected void attach(String name, String content) {
    Allure.attachment(name, content);
  }
}
