package com.example.shared.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Singleton manager to ensure requirements are loaded and mapped only once.
 * Uses Initialization-on-demand holder idiom for thread-safe lazy initialization.
 */
class RequirementsManager {

  /**
   * Thread-safe lazy initializer holder.
   */
  private static class Holder {
    private static final RequirementsManager INSTANCE = new RequirementsManager();
  }

  private final Map<String, TaskInfo> taskMapper;

  /**
   * Private constructor for singleton pattern.
   * Loads requirements from the specified YAML resource.
   */
  private RequirementsManager() {
    String path = System.getProperty("requirements.path", "requirements.yaml");
    try (InputStream is = getClass().getClassLoader().getResourceAsStream(path)) {
      if (is == null) {
        throw new RuntimeException("Requirements file not found: " + path);
      }
      ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
      Requirements requirements = mapper.readValue(is, Requirements.class);
      if (requirements == null || requirements.epics() == null || requirements.epics().isEmpty()) {
        throw new RuntimeException("requirements.yaml is empty or invalid");
      }

      Map<String, TaskInfo> tempMapper = new HashMap<>();
      for (Requirements.Epic e : requirements.epics()) {
        for (Requirements.Feature f : e.features()) {
          for (Requirements.Story s : f.stories()) {
            for (Requirements.Task t : s.tasks()) {
              tempMapper.put(t.id(), new TaskInfo(e, f, s, t));
            }
          }
        }
      }
      this.taskMapper = Map.copyOf(tempMapper);
    } catch (Exception e) {
      throw new RuntimeException("CRITICAL: Failed to load requirements.yaml", e);
    }
  }

  /**
   * Retrieves the task metadata for a given task ID.
   *
   * @param taskId The ID of the task to retrieve.
   * @return The {@link TaskInfo} containing the task and its hierarchy, or null if not found.
   */
  public static TaskInfo getTaskInfo(String taskId) {
    return Holder.INSTANCE.taskMapper.get(taskId);
  }
}
