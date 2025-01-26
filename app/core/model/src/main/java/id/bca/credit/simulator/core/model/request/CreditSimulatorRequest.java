/**
 * Alipay.com Inc.
 * Copyright (c) 2004‐2025 All Rights Reserved.
 */
package id.bca.credit.simulator.core.model.request;

import id.bca.credit.simulator.common.util.enums.VehicleTypeEnum;
import id.bca.credit.simulator.common.util.enums.VihicleConditionEnum;

/**
 * @author freddy.michael@dana.id
 * @version $Id: request.java, v 0.1 2025‐01‐25 20.23 freddy.michael@dana.id Exp $$
 */
public class CreditSimulatorRequest {
    private VehicleTypeEnum      vehicleType;
    private VihicleConditionEnum vehicleCondition;
    private Integer              vehicleYear;
    private Double               totalLoanAmount;
    private Integer              tenorYear;
    private Double               downPaymentAmount;

    public VehicleTypeEnum getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleTypeEnum vehicleType) {
        this.vehicleType = vehicleType;
    }

    public VihicleConditionEnum getVehicleCondition() {
        return vehicleCondition;
    }

    public void setVehicleCondition(VihicleConditionEnum vehicleCondition) {
        this.vehicleCondition = vehicleCondition;
    }

    public Integer getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(Integer vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public Double getTotalLoanAmount() {
        return totalLoanAmount;
    }

    public void setTotalLoanAmount(Double totalLoanAmount) {
        this.totalLoanAmount = totalLoanAmount;
    }

    public Integer getTenorYear() {
        return tenorYear;
    }

    public void setTenorYear(Integer tenorYear) {
        this.tenorYear = tenorYear;
    }

    public Double getDownPaymentAmount() {
        return downPaymentAmount;
    }

    public void setDownPaymentAmount(Double downPaymentAmount) {
        this.downPaymentAmount = downPaymentAmount;
    }
}