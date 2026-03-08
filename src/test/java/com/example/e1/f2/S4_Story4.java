package com.example.e1.f2;

import org.junit.jupiter.api.Test;
import com.example.shared.infra.Requirements;
import com.example.shared.infra.TestHandler;

class S4_Story4 extends TestHandler {

  @Test
  void t_13_Task13() {
    setTask("SAMPLE-T-13");

    // SAMPLE-C-61: Check 1 (ALL_PASSED)
    Requirements.Check c1 = getTask().checks().get(0);
    check(c1, input -> input);

    // SAMPLE-C-62: Check 2 (ALL_PASSED)
    Requirements.Check c2 = getTask().checks().get(1);
    check(c2, input -> input);

    // SAMPLE-C-63: Check 3 (ALL_PASSED)
    Requirements.Check c3 = getTask().checks().get(2);
    check(c3, input -> input);

    // SAMPLE-C-64: Check 4 (ALL_PASSED)
    Requirements.Check c4 = getTask().checks().get(3);
    check(c4, input -> input);

    // SAMPLE-C-65: Check 5 (ALL_PASSED)
    Requirements.Check c5 = getTask().checks().get(4);
    check(c5, input -> input);

  }

  @Test
  void t_14_Task14() {
    setTask("SAMPLE-T-14");

    // SAMPLE-C-66: Check 1 (ALL_FAILED)
    Requirements.Check c1 = getTask().checks().get(0);
    check(c1, input -> input);

    // SAMPLE-C-67: Check 2 (ALL_FAILED)
    Requirements.Check c2 = getTask().checks().get(1);
    check(c2, input -> input);

    // SAMPLE-C-68: Check 3 (ALL_FAILED)
    Requirements.Check c3 = getTask().checks().get(2);
    check(c3, input -> input);

    // SAMPLE-C-69: Check 4 (ALL_FAILED)
    Requirements.Check c4 = getTask().checks().get(3);
    check(c4, input -> input);

    // SAMPLE-C-70: Check 5 (ALL_FAILED)
    Requirements.Check c5 = getTask().checks().get(4);
    check(c5, input -> input);

  }

  @Test
  void t_15_Task15() {
    setTask("SAMPLE-T-15");

    // SAMPLE-C-71: Check 1 (ALL_BROKEN)
    Requirements.Check c1 = getTask().checks().get(0);
    check(c1, input -> { throw new Error("Critical failure evaluating: " + input); });

    // SAMPLE-C-72: Check 2 (ALL_BROKEN)
    Requirements.Check c2 = getTask().checks().get(1);
    check(c2, input -> { throw new RuntimeException("Function evaluation failed for: " + input); });

    // SAMPLE-C-73: Check 3 (ALL_BROKEN)
    Requirements.Check c3 = getTask().checks().get(2);
    check(c3, input -> { throw new Error("Critical failure evaluating: " + input); });

    // SAMPLE-C-74: Check 4 (ALL_BROKEN)
    Requirements.Check c4 = getTask().checks().get(3);
    check(c4, input -> { throw new RuntimeException("Function evaluation failed for: " + input); });

    // SAMPLE-C-75: Check 5 (ALL_BROKEN)
    Requirements.Check c5 = getTask().checks().get(4);
    check(c5, input -> { throw new Error("Critical failure evaluating: " + input); });

  }

  @Test
  void t_16_Task16() {
    setTask("SAMPLE-T-16");

    // SAMPLE-C-76: Check 1 (MIXED)
    Requirements.Check c1 = getTask().checks().get(0);
    check(c1, input -> input);

    // SAMPLE-C-77: Check 2 (MIXED)
    Requirements.Check c2 = getTask().checks().get(1);
    check(c2, input -> input);

    // SAMPLE-C-78: Check 3 (MIXED)
    Requirements.Check c3 = getTask().checks().get(2);
    check(c3, input -> { throw new RuntimeException("Function evaluation failed for: " + input); });

    // SAMPLE-C-79: Check 4 (MIXED)
    Requirements.Check c4 = getTask().checks().get(3);
    check(c4, input -> { throw new Error("Critical failure evaluating: " + input); });

    // SAMPLE-C-80: Check 5 (MIXED)
    Requirements.Check c5 = getTask().checks().get(4);
    check(c5, input -> input);

  }
}
