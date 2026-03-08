package com.example.e2.f1;

import org.junit.jupiter.api.Test;
import com.example.shared.infra.Requirements;
import com.example.shared.infra.TestHandler;

class S6_Story6 extends TestHandler {

  @Test
  void t_21_Task21() {
    setTask("SAMPLE-T-21");

    // SAMPLE-C-101: Check 1 (ALL_PASSED)
    Requirements.Check c1 = getTask().checks().get(0);
    check(c1, input -> input);

    // SAMPLE-C-102: Check 2 (ALL_PASSED)
    Requirements.Check c2 = getTask().checks().get(1);
    check(c2, input -> input);

    // SAMPLE-C-103: Check 3 (ALL_PASSED)
    Requirements.Check c3 = getTask().checks().get(2);
    check(c3, input -> input);

    // SAMPLE-C-104: Check 4 (ALL_PASSED)
    Requirements.Check c4 = getTask().checks().get(3);
    check(c4, input -> input);

    // SAMPLE-C-105: Check 5 (ALL_PASSED)
    Requirements.Check c5 = getTask().checks().get(4);
    check(c5, input -> input);

  }

  @Test
  void t_22_Task22() {
    setTask("SAMPLE-T-22");

    // SAMPLE-C-106: Check 1 (ALL_FAILED)
    Requirements.Check c1 = getTask().checks().get(0);
    check(c1, input -> input);

    // SAMPLE-C-107: Check 2 (ALL_FAILED)
    Requirements.Check c2 = getTask().checks().get(1);
    check(c2, input -> input);

    // SAMPLE-C-108: Check 3 (ALL_FAILED)
    Requirements.Check c3 = getTask().checks().get(2);
    check(c3, input -> input);

    // SAMPLE-C-109: Check 4 (ALL_FAILED)
    Requirements.Check c4 = getTask().checks().get(3);
    check(c4, input -> input);

    // SAMPLE-C-110: Check 5 (ALL_FAILED)
    Requirements.Check c5 = getTask().checks().get(4);
    check(c5, input -> input);

  }

  @Test
  void t_23_Task23() {
    setTask("SAMPLE-T-23");

    // SAMPLE-C-111: Check 1 (ALL_BROKEN)
    Requirements.Check c1 = getTask().checks().get(0);
    check(c1, input -> { throw new Error("Critical failure evaluating: " + input); });

    // SAMPLE-C-112: Check 2 (ALL_BROKEN)
    Requirements.Check c2 = getTask().checks().get(1);
    check(c2, input -> { throw new RuntimeException("Function evaluation failed for: " + input); });

    // SAMPLE-C-113: Check 3 (ALL_BROKEN)
    Requirements.Check c3 = getTask().checks().get(2);
    check(c3, input -> { throw new Error("Critical failure evaluating: " + input); });

    // SAMPLE-C-114: Check 4 (ALL_BROKEN)
    Requirements.Check c4 = getTask().checks().get(3);
    check(c4, input -> { throw new RuntimeException("Function evaluation failed for: " + input); });

    // SAMPLE-C-115: Check 5 (ALL_BROKEN)
    Requirements.Check c5 = getTask().checks().get(4);
    check(c5, input -> { throw new Error("Critical failure evaluating: " + input); });

  }

  @Test
  void t_24_Task24() {
    setTask("SAMPLE-T-24");

    // SAMPLE-C-116: Check 1 (MIXED)
    Requirements.Check c1 = getTask().checks().get(0);
    check(c1, input -> input);

    // SAMPLE-C-117: Check 2 (MIXED)
    Requirements.Check c2 = getTask().checks().get(1);
    check(c2, input -> input);

    // SAMPLE-C-118: Check 3 (MIXED)
    Requirements.Check c3 = getTask().checks().get(2);
    check(c3, input -> { throw new RuntimeException("Function evaluation failed for: " + input); });

    // SAMPLE-C-119: Check 4 (MIXED)
    Requirements.Check c4 = getTask().checks().get(3);
    check(c4, input -> { throw new Error("Critical failure evaluating: " + input); });

    // SAMPLE-C-120: Check 5 (MIXED)
    Requirements.Check c5 = getTask().checks().get(4);
    check(c5, input -> input);

  }
}
