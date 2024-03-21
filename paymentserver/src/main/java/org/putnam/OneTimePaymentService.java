package org.putnam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OneTimePaymentService {
  static public double calculateNewBalance(double payment){
    double currentBalance = 100.00;
    return currentBalance - processMatch(payment);
  }

  static double processMatch(double payment) {
    if(payment < 10.00) {
      return payment + (payment * .01);
    } else if(payment < 50.00) {
      return payment + (payment * .03);
    } else if(payment >= 50.00) {
      return payment + (payment * .05);
    } else {
      return payment;
    }
  }

  static public String calculateNextDueDate() {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime nextDueDate = now.plusDays(15);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
    return formatter.format(nextDueDate);
  }
}
