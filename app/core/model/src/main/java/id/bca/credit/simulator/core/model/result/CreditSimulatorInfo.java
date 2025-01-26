/**
 * Alipay.com Inc.
 * Copyright (c) 2004‐2025 All Rights Reserved.
 */
package id.bca.credit.simulator.core.model.result;

/**
 * @author freddy.michael@dana.id
 * @version $Id: CreditSimulatorInfo.java, v 0.1 2025‐01‐25 20.47 freddy.michael@dana.id Exp $$
 */
public class CreditSimulatorInfo {
    private Integer year;
    private Double installmentAmount;
    private Double interestRate;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(Double installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }
}