package com.lbb.customer.statement.service;

import com.lbb.customer.statement.model.CalulatorObject;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.*;
import java.util.HashMap;
import java.util.Map;
import java.time.temporal.ChronoUnit;
@Service
public class TDInterestCalculator {

    public CalulatorObject calculateTDInterestService(
            BigDecimal principal,
            BigDecimal rate,
            long termMonths,
            LocalDate startDate
    ) {
        // 1. maturity date
        LocalDate maturityDate = startDate.plusMonths(termMonths);

        // 2. days per year
        Map<Integer, Integer> daysByYear = new HashMap<>();
        LocalDate current = startDate;

        while (current.isBefore(maturityDate)) {
            int year = current.getYear();

            LocalDate nextYear = LocalDate.of(year + 1, 1, 1);
            if (nextYear.isAfter(maturityDate)) {
                nextYear = maturityDate;
            }

            long days = ChronoUnit.DAYS.between(current, nextYear);
            daysByYear.put(year, daysByYear.getOrDefault(year, 0) + (int) days);

            current = nextYear;
        }

        // 3. calculate interest
        BigDecimal totalInterest = BigDecimal.ZERO;

        for (Map.Entry<Integer, Integer> entry : daysByYear.entrySet()) {
            int year = entry.getKey();
            int days = entry.getValue();

            int yearBasis = Year.isLeap(year) ? 366 : 365;

            BigDecimal periodInterest =
                    principal
                            .multiply(rate)
                            .multiply(BigDecimal.valueOf(days))

                            .divide(
                                    BigDecimal.valueOf(100L * yearBasis),
                                    10,
                                    RoundingMode.HALF_UP
                            );

            totalInterest = totalInterest.add(periodInterest);
        }

        // 4. round 2 decimal
        BigDecimal interestAmount = totalInterest.setScale(2, RoundingMode.HALF_UP);

        // 5. disclaimer
        String disclaimer = "Quoted interest is indicative only and may differ from the actual interest due to rounding and specific day count conventions.";

        return new CalulatorObject(interestAmount, maturityDate, disclaimer);
    }

}
