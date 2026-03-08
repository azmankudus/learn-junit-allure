package com.example.e1.f1;

import org.junit.jupiter.api.Test;
import com.example.shared.infra.Requirements;
import com.example.shared.infra.TestHandler;

class S1_Story1 extends TestHandler {

  @Test
  void t_1_Task1() {
    setTask("SAMPLE-T-1");

    // SAMPLE-C-1: Check 1 (ALL_PASSED)
    Requirements.Check c1 = getTask().checks().get(0);
    check(c1, input -> input);

    // SAMPLE-C-2: Check 2 (ALL_PASSED)
    Requirements.Check c2 = getTask().checks().get(1);
    check(c2, input -> input);

    // SAMPLE-C-3: Check 3 (ALL_PASSED)
    Requirements.Check c3 = getTask().checks().get(2);
    check(c3, input -> input);

    // SAMPLE-C-4: Check 4 (ALL_PASSED)
    Requirements.Check c4 = getTask().checks().get(3);
    check(c4, input -> input);

    // SAMPLE-C-5: Check 5 (ALL_PASSED)
    Requirements.Check c5 = getTask().checks().get(4);
    check(c5, input -> input);

  }

  @Test
  void t_2_Task2() {
    setTask("SAMPLE-T-2");

    // SAMPLE-C-6: Check 1 (ALL_FAILED)
    Requirements.Check c1 = getTask().checks().get(0);
    check(c1, input -> input);

    // SAMPLE-C-7: Check 2 (ALL_FAILED)
    Requirements.Check c2 = getTask().checks().get(1);
    check(c2, input -> input);

    // SAMPLE-C-8: Check 3 (ALL_FAILED)
    Requirements.Check c3 = getTask().checks().get(2);
    check(c3, input -> input);

    // SAMPLE-C-9: Check 4 (ALL_FAILED)
    Requirements.Check c4 = getTask().checks().get(3);
    check(c4, input -> input);

    // SAMPLE-C-10: Check 5 (ALL_FAILED)
    Requirements.Check c5 = getTask().checks().get(4);
    check(c5, input -> input);

  }

  @Test
  void t_3_Task3() {
    setTask("SAMPLE-T-3");

    // SAMPLE-C-11: Check 1 (ALL_BROKEN)
    Requirements.Check c1 = getTask().checks().get(0);
    check(c1, input -> { throw new Error("Critical failure evaluating: " + input); });

    // SAMPLE-C-12: Check 2 (ALL_BROKEN)
    Requirements.Check c2 = getTask().checks().get(1);
    check(c2, input -> { throw new RuntimeException("Function evaluation failed for: " + input); });

    // SAMPLE-C-13: Check 3 (ALL_BROKEN)
    Requirements.Check c3 = getTask().checks().get(2);
    check(c3, input -> { throw new Error("Critical failure evaluating: " + input); });

    // SAMPLE-C-14: Check 4 (ALL_BROKEN)
    Requirements.Check c4 = getTask().checks().get(3);
    check(c4, input -> { throw new RuntimeException("Function evaluation failed for: " + input); });

    // SAMPLE-C-15: Check 5 (ALL_BROKEN)
    Requirements.Check c5 = getTask().checks().get(4);
    check(c5, input -> { throw new Error("Critical failure evaluating: " + input); });

  }

  @Test
  void t_4_Task4() {
    setTask("SAMPLE-T-4");

    // SAMPLE-C-16: Check 1 (MIXED)
    Requirements.Check c1 = getTask().checks().get(0);
    check(c1, input -> input);

    // SAMPLE-C-17: Check 2 (MIXED)
    Requirements.Check c2 = getTask().checks().get(1);
    check(c2, input -> input);

    // SAMPLE-C-18: Check 3 (MIXED)
    Requirements.Check c3 = getTask().checks().get(2);
    check(c3, input -> { throw new RuntimeException("Function evaluation failed for: " + input); });

    // SAMPLE-C-19: Check 4 (MIXED)
    Requirements.Check c4 = getTask().checks().get(3);
    check(c4, input -> { throw new Error("Critical failure evaluating: " + input); });

    // SAMPLE-C-20: Check 5 (MIXED)
    Requirements.Check c5 = getTask().checks().get(4);
    check(c5, input -> input);

  }
}
