package com.example.e1.f2;

import org.junit.jupiter.api.Test;
import com.example.shared.infra.Requirements;
import com.example.shared.infra.TestHandler;

class S3_Story3 extends TestHandler {

  @Test
  void t_9_Task9() {
    setTask("SAMPLE-T-9");

    // SAMPLE-C-41: Check 1 (ALL_PASSED)
    Requirements.Check c1 = getTask().checks().get(0);
    check(c1, input -> input);

    // SAMPLE-C-42: Check 2 (ALL_PASSED)
    Requirements.Check c2 = getTask().checks().get(1);
    check(c2, input -> input);

    // SAMPLE-C-43: Check 3 (ALL_PASSED)
    Requirements.Check c3 = getTask().checks().get(2);
    check(c3, input -> input);

    // SAMPLE-C-44: Check 4 (ALL_PASSED)
    Requirements.Check c4 = getTask().checks().get(3);
    check(c4, input -> input);

    // SAMPLE-C-45: Check 5 (ALL_PASSED)
    Requirements.Check c5 = getTask().checks().get(4);
    check(c5, input -> input);

  }

  @Test
  void t_10_Task10() {
    setTask("SAMPLE-T-10");

    // SAMPLE-C-46: Check 1 (ALL_FAILED)
    Requirements.Check c1 = getTask().checks().get(0);
    check(c1, input -> input);

    // SAMPLE-C-47: Check 2 (ALL_FAILED)
    Requirements.Check c2 = getTask().checks().get(1);
    check(c2, input -> input);

    // SAMPLE-C-48: Check 3 (ALL_FAILED)
    Requirements.Check c3 = getTask().checks().get(2);
    check(c3, input -> input);

    // SAMPLE-C-49: Check 4 (ALL_FAILED)
    Requirements.Check c4 = getTask().checks().get(3);
    check(c4, input -> input);

    // SAMPLE-C-50: Check 5 (ALL_FAILED)
    Requirements.Check c5 = getTask().checks().get(4);
    check(c5, input -> input);

  }

  @Test
  void t_11_Task11() {
    setTask("SAMPLE-T-11");

    // SAMPLE-C-51: Check 1 (ALL_BROKEN)
    Requirements.Check c1 = getTask().checks().get(0);
    check(c1, input -> { throw new Error("Critical failure evaluating: " + input); });

    // SAMPLE-C-52: Check 2 (ALL_BROKEN)
    Requirements.Check c2 = getTask().checks().get(1);
    check(c2, input -> { throw new RuntimeException("Function evaluation failed for: " + input); });

    // SAMPLE-C-53: Check 3 (ALL_BROKEN)
    Requirements.Check c3 = getTask().checks().get(2);
    check(c3, input -> { throw new Error("Critical failure evaluating: " + input); });

    // SAMPLE-C-54: Check 4 (ALL_BROKEN)
    Requirements.Check c4 = getTask().checks().get(3);
    check(c4, input -> { throw new RuntimeException("Function evaluation failed for: " + input); });

    // SAMPLE-C-55: Check 5 (ALL_BROKEN)
    Requirements.Check c5 = getTask().checks().get(4);
    check(c5, input -> { throw new Error("Critical failure evaluating: " + input); });

  }

  @Test
  void t_12_Task12() {
    setTask("SAMPLE-T-12");

    // SAMPLE-C-56: Check 1 (MIXED)
    Requirements.Check c1 = getTask().checks().get(0);
    check(c1, input -> input);

    // SAMPLE-C-57: Check 2 (MIXED)
    Requirements.Check c2 = getTask().checks().get(1);
    check(c2, input -> input);

    // SAMPLE-C-58: Check 3 (MIXED)
    Requirements.Check c3 = getTask().checks().get(2);
    check(c3, input -> { throw new RuntimeException("Function evaluation failed for: " + input); });

    // SAMPLE-C-59: Check 4 (MIXED)
    Requirements.Check c4 = getTask().checks().get(3);
    check(c4, input -> { throw new Error("Critical failure evaluating: " + input); });

    // SAMPLE-C-60: Check 5 (MIXED)
    Requirements.Check c5 = getTask().checks().get(4);
    check(c5, input -> input);

  }
}
