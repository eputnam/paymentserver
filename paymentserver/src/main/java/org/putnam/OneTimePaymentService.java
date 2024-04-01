package org.putnam;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

  private static String formatDate(LocalDateTime date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
    return formatter.format(date);
  }

  public static String calculateNextDueDate(LocalDateTime now) {
    LocalDateTime nextDueDate = now.plusDays(15);

    if (DayOfWeek.SATURDAY.equals(nextDueDate.getDayOfWeek())) {
      return formatDate(nextDueDate.plusDays(2));
    } else if (DayOfWeek.SUNDAY.equals(nextDueDate.getDayOfWeek())) {
      return formatDate(nextDueDate.plusDays(1));
    }
    return formatDate(nextDueDate);
  }
}
