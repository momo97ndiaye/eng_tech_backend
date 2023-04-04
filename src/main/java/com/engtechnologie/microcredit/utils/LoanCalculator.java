package com.engtechnologie.microcredit.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LoanCalculator {
    public static BigDecimal calculateLoanAmount(double loanAmount, double interestRate, int loanDurationInMonths) {
        double t = interestRate / 100.0;
        double n = loanDurationInMonths / 12;
        double numerator = loanAmount * (t / 12.0);
        double denominator = 1 - Math.pow(1 + (t / 12.0), -12 * n);
        return BigDecimal.valueOf(numerator / denominator)
                .setScale(0, RoundingMode.HALF_UP);
    }

    public static Double getAmountPaid(Double montant, Integer nb_month, Double taux) {
        Double t = taux / 100.0;
        Double numerator2 = montant * t * Math.pow(1 + t, nb_month);
        Double denominator = Math.pow(1+t, nb_month) - 1;
        Double echeance = numerator2 / denominator;
        echeance = roundToNearest(echeance, 10.0);
        return echeance;
    }

    public static void main(String[] args) {
        double loanAmount = 100000.0;
        double interestRate = 10.0;
        int loanDurationInMonths = 12;
        System.out.println("Loan amount: $" + calculateLoanAmount(loanAmount, interestRate, loanDurationInMonths));
    }

    private static Double roundToNearest(Double value, Double nearest) {
        return Math.round(value / nearest) * nearest;
    }
}
