/**
 * Alipay.com Inc.
 * Copyright (c) 2004‐2025 All Rights Reserved.
 */
package id.bca.credit.simulator.core.service.validation;

import id.bca.credit.simulator.common.util.enums.CreditSimulatorErrorCodeEnum;
import id.bca.credit.simulator.common.util.enums.VehicleTypeEnum;
import id.bca.credit.simulator.common.util.enums.VihicleConditionEnum;
import id.bca.credit.simulator.common.util.error.ParamCheckHelper;
import id.bca.credit.simulator.core.model.request.CreditSimulatorRequest;
import id.bca.credit.simulator.core.service.configuration.CreditSimulatorStrategyConfig;

/**
 * @author freddy.michael@dana.id
 * @version $Id: CrediSimulatorValidator.java, v 0.1 2025‐01‐26 11.33 freddy.michael@dana.id Exp $$
 */
public class CreditSimulatorValidator {
    public static void validateRequest(CreditSimulatorRequest request) {
        ParamCheckHelper.notNull(request.getDownPaymentAmount(),
                CreditSimulatorErrorCodeEnum.INVALID_MANDATORY_FIELD, true,
                "Down payment amount is required");
        ParamCheckHelper.isExpected(request.getDownPaymentAmount() <= request.getTotalLoanAmount(),
                true, CreditSimulatorErrorCodeEnum.DOWN_PAYMENT_GREATER_THAN_LOAN_AMOUNT, true,
                "Down payment amount must be less than total credit");
        ParamCheckHelper.isExpected(calculatePercentageDownPayment(request), true,
                CreditSimulatorErrorCodeEnum.DOWN_PAYMENT_LESS_THAN_MIN, true,
                String.format("Down payment amount must be more than %.0f%% of total credit", CreditSimulatorStrategyConfig.getDownPaymentRate(
                        request.getVehicleCondition()) * 100));
    }

    private static boolean calculatePercentageDownPayment(CreditSimulatorRequest request) {
        return request.getDownPaymentAmount() >= request.getTotalLoanAmount() * CreditSimulatorStrategyConfig.getDownPaymentRate(
                request.getVehicleCondition());
    }

    public static void validateVehicleType(String vehicleType) {
        ParamCheckHelper.notEmpty(vehicleType, CreditSimulatorErrorCodeEnum.INVALID_MANDATORY_FIELD,
                true, "Vehicle type cannot be null and must be Mobil or Motor");
        ParamCheckHelper.notNull(VehicleTypeEnum.getEnumFromCode(vehicleType.toUpperCase()),
                CreditSimulatorErrorCodeEnum.INVALID_MANDATORY_FIELD, true,
                "Vehicle type must be Mobil or Motor");
    }

    public static void validateVehicleCondition(String vehicleCondition) {
        ParamCheckHelper.notEmpty(vehicleCondition,
                CreditSimulatorErrorCodeEnum.INVALID_MANDATORY_FIELD, true,
                "Vehicle condition cannot be null and must be Baru or Bekas");
        ParamCheckHelper.notNull(
                VihicleConditionEnum.getEnumFromCode(vehicleCondition.toUpperCase()),
                CreditSimulatorErrorCodeEnum.INVALID_MANDATORY_FIELD, true,
                "Vehicle condition must be Baru or Bekas");
    }

    public static void validateVehicleYear(Integer vehicleYear) {
        ParamCheckHelper.notNull(vehicleYear, CreditSimulatorErrorCodeEnum.INVALID_MANDATORY_FIELD,
                true, "Vehicle year cannot be null");
        ParamCheckHelper.isExpected(vehicleYear > 1000 && vehicleYear < 10000, true,
                CreditSimulatorErrorCodeEnum.INVALID_FIELD_FORMAT, true,
                "Vehicle year must in 4 digit format");
    }

    public static void validateTotalLoanAmount(Double totalLoanAmount) {
        ParamCheckHelper.notNull(totalLoanAmount,
                CreditSimulatorErrorCodeEnum.INVALID_MANDATORY_FIELD, true,
                "Total credit is required");
        ParamCheckHelper.isExpected(totalLoanAmount > 0, true,
                CreditSimulatorErrorCodeEnum.INVALID_FIELD_FORMAT, true,
                "Total credit must be positive");
        ParamCheckHelper.isExpected(totalLoanAmount <= 1_000_000_000, true,
                CreditSimulatorErrorCodeEnum.EXCEED_MAX_LOAN_AMOUNT, true,
                "Total credit must be less than 1 billion");
    }

    public static void validateTenorYear(Integer tenorYear) {
        ParamCheckHelper.notNull(tenorYear, CreditSimulatorErrorCodeEnum.INVALID_MANDATORY_FIELD,
                true, "Tenor year cannot be null");
        ParamCheckHelper.isExpected(tenorYear > 0, true,
                CreditSimulatorErrorCodeEnum.TENOR_NEGATIVE, true, "Tenor year must be positive");
        ParamCheckHelper.isExpected(tenorYear < 7, true,
                CreditSimulatorErrorCodeEnum.EXCEED_MAX_LOAN_TENOR, true,
                "Tenor year must be less than 7 years");
    }
}