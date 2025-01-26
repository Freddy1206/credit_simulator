/**
 * Alipay.com Inc.
 * Copyright (c) 2004‐2025 All Rights Reserved.
 */
package id.bca.credit.simulator.core.service.calculation.impl;

import id.bca.credit.simulator.common.util.constants.CommonConstant;
import id.bca.credit.simulator.core.model.result.CreditSimulatorInfo;
import id.bca.credit.simulator.core.model.request.CreditSimulatorRequest;
import id.bca.credit.simulator.core.model.result.CreditSimulatorResult;
import id.bca.credit.simulator.core.service.calculation.CalculateLoanInstallmentCoreService;
import id.bca.credit.simulator.core.service.configuration.CreditSimulatorStrategyConfig;

/**
 * @author freddy.michael@dana.id
 * @version $Id: CalculateLoanInstallmentCoreServiceImpl.java, v 0.1 2025‐01‐25 22.24 freddy.michael@dana.id Exp $$
 */
public class CalculateLoanInstallmentCoreServiceImpl
        implements CalculateLoanInstallmentCoreService {
    @Override
    public CreditSimulatorResult calculateLoanInstallment(CreditSimulatorRequest request) {
        if (request == null) {
            return null;
        }
        Double interestRate = CreditSimulatorStrategyConfig.getInterestRate(request.getVehicleType());
        Double remainingLoanAmount = request.getTotalLoanAmount() - request.getDownPaymentAmount();

        CreditSimulatorResult result = new CreditSimulatorResult();
        for (int year = 1; year <= request.getTenorYear(); year++) {
            CreditSimulatorInfo info = new CreditSimulatorInfo();
            info.setYear(year);
            interestRate = decideInterestRate(interestRate, year);
            info.setInterestRate(changeToPercentage(interestRate));

            remainingLoanAmount = calculateInstallmentAmount(info, remainingLoanAmount, year,
                    request.getTenorYear(), interestRate);
            result.addCreditSimulatorInfo(info);
        }
        return result;
    }

    /**
     * Decide interestInterest rate.
     *
     * @param interestRate
     *         the interest rate
     * @param year
     *         the year
     * @return the interest rate
     */
    private Double decideInterestRate(Double interestRate, int year) {
        if (year == 1) {
            return interestRate;
        }
        interestRate = year % 2 == 0 ? interestRate + 0.001 : interestRate + 0.005;

        return interestRate;
    }

    /**
     * Calculate installment amount.
     *
     * @param info
     *         the interest rate
     * @param remainingLoanAmount
     *         the remaining loan amount
     * @param year
     *         the year
     * @param tenorYear
     *         the tenor year
     * @return the double
     */
    private Double calculateInstallmentAmount(CreditSimulatorInfo info, Double remainingLoanAmount,
                                              int year, int tenorYear, Double interestRate) {
        Double interestAmount = remainingLoanAmount * interestRate;
        Double totalInstallmentAmount = interestAmount + remainingLoanAmount;
        Double principalAmount = totalInstallmentAmount / ((tenorYear - year + 1) * 12.0);

        Double totalYearlyInstallmentAmount = principalAmount * 12;
        remainingLoanAmount = totalInstallmentAmount - totalYearlyInstallmentAmount;
        info.setInstallmentAmount(principalAmount);
        return remainingLoanAmount;
    }

    /**
     * Change to percentage.
     *
     * @param interestRate
     *         the interest rate
     * @return the double
     */
    private Double changeToPercentage(Double interestRate) {
        return interestRate * 100;
    }

}