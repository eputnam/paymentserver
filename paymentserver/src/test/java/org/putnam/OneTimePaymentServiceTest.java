package org.putnam;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Year;

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

  @Test
  public void TestSaturdayDueDate() {
    LocalDateTime friday = LocalDateTime.of(2024,4,5,1,0,0);
    assertEquals("04-22-2024 01:00:00", OneTimePaymentService.calculateNextDueDate(friday));
  }
}
