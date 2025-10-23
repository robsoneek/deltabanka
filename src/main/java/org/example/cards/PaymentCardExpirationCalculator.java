package org.example.cards;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class PaymentCardExpirationCalculator {

    private static final DateTimeFormatter MONTH_FORMATTER = DateTimeFormatter.ofPattern("MM");
    private static final DateTimeFormatter YEAR_FORMATTER = DateTimeFormatter.ofPattern("yyyy");

    public String calculateMonthExpire(){
        YearMonth expirationDate = YearMonth.now().plusYears(5);
        return expirationDate.format(MONTH_FORMATTER);
    }

    public String calculateYearExpire(){
        YearMonth expirationDate = YearMonth.now().plusYears(5);
        return expirationDate.format(YEAR_FORMATTER);
    }
}