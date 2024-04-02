package org.putnam;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DueDateImpl implements DueDate{
  private LocalDateTime startDate;

  public DueDateImpl(LocalDateTime startDate) {
    this.startDate = startDate;
  }

  private LocalDateTime calculateDueDate() {
    LocalDateTime nextDueDate = this.startDate.plusDays(15);

    if (DayOfWeek.SATURDAY.equals(nextDueDate.getDayOfWeek())) {
      return nextDueDate.plusDays(2);
    } else if (DayOfWeek.SUNDAY.equals(nextDueDate.getDayOfWeek())) {
      return nextDueDate.plusDays(1);
    }
    return nextDueDate;
  }

  @Override
  public String getFormattedDueDate() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
    return formatter.format(this.calculateDueDate());
  }
}
