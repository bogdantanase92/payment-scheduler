package com.tanaseb.paymentscheduler.domain.service;

import com.tanaseb.paymentscheduler.domain.model.Period;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeriodService {

    public List<Period> schedule(Period period) {
        DateTime current = period.getStart();
        DateTime next;
        DateTime startForCurrent = period.getStart();

        List<Period> periods = new ArrayList<>();

        while (isLowerOrEqualToEnd(period, current)) {
            next = next(current);
            if (next.getMonthOfYear() > current.getMonthOfYear()
                    || next.getYear() > current.getYear()) {
                addPeriod(current, startForCurrent, periods);
                startForCurrent = next;
            }
            current = next(current);
        }
        if (!isLastDayOfMonth(previous(current))) {
            if (moreThanOneDay(period)) {
                addPeriod(period.getEnd(), startForCurrent, periods);
            }
            if (period.getStart().equals(period.getEnd())) {
                addPeriod(period.getEnd(), period.getStart(), periods);
            }
        }

        return periods;
    }

    private boolean isLowerOrEqualToEnd(Period period, DateTime current) {
        return current.compareTo(period.getEnd()) <= 0;
    }

    private DateTime next(DateTime current) {
        return current.plusDays(1);
    }

    private DateTime previous(DateTime current) {
        return current.minusDays(1);
    }

    private void addPeriod(DateTime end, DateTime start, List<Period> periods) {
        Period currentPeriod = Period.builder()
                .start(start)
                .end(end)
                .build();
        if (currentPeriod.isEndGreaterThanOrEqualToStart()) {
            currentPeriod.calculateNumberOfDays();
        }
        periods.add(currentPeriod);
    }

    private boolean moreThanOneDay(Period period) {
        int noOfDays = Days.daysBetween(period.getStart(), period.getEnd()).getDays() + 1;
        return noOfDays > 1;
    }

    private boolean isLastDayOfMonth(DateTime current) {
        return current.equals(current.dayOfMonth().withMaximumValue());
    }
}
