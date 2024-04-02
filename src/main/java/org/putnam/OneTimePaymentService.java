package org.putnam;

import java.time.LocalDateTime;

public class OneTimePaymentService {
  public static double calculateNewBalance(double payment) {
    double currentBalance = 100.00;
    return currentBalance - processMatch(payment);
  }

  static double processMatch(double payment) {
    if (payment < 10.00) {
      return payment + (payment * .01);
    } else if (payment < 50.00) {
      return payment + (payment * .03);
    } else if (payment >= 50.00) {
      return payment + (payment * .05);
    } else {
      return payment;
    }
  }

  public static String formattedDueDate(LocalDateTime date) {
    DueDateImpl dueDate = new DueDateImpl(date);
    return dueDate.getFormattedDueDate();
  }

}
