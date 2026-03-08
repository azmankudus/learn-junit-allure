package com.example.e2.f2;

import org.junit.jupiter.api.Test;
import com.example.shared.infra.Requirements;
import com.example.shared.infra.TestHandler;

class S8_Story8 extends TestHandler {

  @Test
  void t_29_Task29() {
    setTask("SAMPLE-T-29");

    // SAMPLE-C-141: Check 1 (ALL_PASSED)
    Requirements.Check c1 = getTask().checks().get(0);
    check(c1, input -> input);

    // SAMPLE-C-142: Check 2 (ALL_PASSED)
    Requirements.Check c2 = getTask().checks().get(1);
    check(c2, input -> input);

    // SAMPLE-C-143: Check 3 (ALL_PASSED)
    Requirements.Check c3 = getTask().checks().get(2);
    check(c3, input -> input);

    // SAMPLE-C-144: Check 4 (ALL_PASSED)
    Requirements.Check c4 = getTask().checks().get(3);
    check(c4, input -> input);

    // SAMPLE-C-145: Check 5 (ALL_PASSED)
    Requirements.Check c5 = getTask().checks().get(4);
    check(c5, input -> input);

  }

  @Test
  void t_30_Task30() {
    setTask("SAMPLE-T-30");

    // SAMPLE-C-146: Check 1 (ALL_FAILED)
    Requirements.Check c1 = getTask().checks().get(0);
    check(c1, input -> input);

    // SAMPLE-C-147: Check 2 (ALL_FAILED)
    Requirements.Check c2 = getTask().checks().get(1);
    check(c2, input -> input);

    // SAMPLE-C-148: Check 3 (ALL_FAILED)
    Requirements.Check c3 = getTask().checks().get(2);
    check(c3, input -> input);

    // SAMPLE-C-149: Check 4 (ALL_FAILED)
    Requirements.Check c4 = getTask().checks().get(3);
    check(c4, input -> input);

    // SAMPLE-C-150: Check 5 (ALL_FAILED)
    Requirements.Check c5 = getTask().checks().get(4);
    check(c5, input -> input);

  }

  @Test
  void t_31_Task31() {
    setTask("SAMPLE-T-31");

    // SAMPLE-C-151: Check 1 (ALL_BROKEN)
    Requirements.Check c1 = getTask().checks().get(0);
    check(c1, input -> { throw new Error("Critical failure evaluating: " + input); });

    // SAMPLE-C-152: Check 2 (ALL_BROKEN)
    Requirements.Check c2 = getTask().checks().get(1);
    check(c2, input -> { throw new RuntimeException("Function evaluation failed for: " + input); });

    // SAMPLE-C-153: Check 3 (ALL_BROKEN)
    Requirements.Check c3 = getTask().checks().get(2);
    check(c3, input -> { throw new Error("Critical failure evaluating: " + input); });

    // SAMPLE-C-154: Check 4 (ALL_BROKEN)
    Requirements.Check c4 = getTask().checks().get(3);
    check(c4, input -> { throw new RuntimeException("Function evaluation failed for: " + input); });

    // SAMPLE-C-155: Check 5 (ALL_BROKEN)
    Requirements.Check c5 = getTask().checks().get(4);
    check(c5, input -> { throw new Error("Critical failure evaluating: " + input); });

  }

  @Test
  void t_32_Task32() {
    setTask("SAMPLE-T-32");

    // SAMPLE-C-156: Check 1 (MIXED)
    Requirements.Check c1 = getTask().checks().get(0);
    check(c1, input -> input);

    // SAMPLE-C-157: Check 2 (MIXED)
    Requirements.Check c2 = getTask().checks().get(1);
    check(c2, input -> input);

    // SAMPLE-C-158: Check 3 (MIXED)
    Requirements.Check c3 = getTask().checks().get(2);
    check(c3, input -> { throw new RuntimeException("Function evaluation failed for: " + input); });

    // SAMPLE-C-159: Check 4 (MIXED)
    Requirements.Check c4 = getTask().checks().get(3);
    check(c4, input -> { throw new Error("Critical failure evaluating: " + input); });

    // SAMPLE-C-160: Check 5 (MIXED)
    Requirements.Check c5 = getTask().checks().get(4);
    check(c5, input -> input);

  }
}
