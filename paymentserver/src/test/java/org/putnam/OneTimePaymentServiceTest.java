package org.putnam;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OneTimePaymentServiceTest {

  @Test
  public void testOnePercentMatch() {
    double payment = 5.00;
    double paymentWithMatch = OneTimePaymentService.processMatch(payment);
    assertEquals(5.05, paymentWithMatch);
  }

  @Test
  public void testThreePercentMatch() {
    double payment = 20.00;
    double paymentWithMatch = OneTimePaymentService.processMatch(payment);
    assertEquals(20.60, paymentWithMatch);
  }
}
