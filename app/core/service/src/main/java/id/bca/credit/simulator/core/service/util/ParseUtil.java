/**
 * Alipay.com Inc.
 * Copyright (c) 2004‐2025 All Rights Reserved.
 */
package id.bca.credit.simulator.core.service.util;

import id.bca.credit.simulator.common.util.enums.CreditSimulatorErrorCodeEnum;
import id.bca.credit.simulator.common.util.error.CreditSimulatorException;
import id.bca.credit.simulator.core.model.request.CreditSimulatorRequest;
import id.bca.credit.simulator.core.service.validation.CreditSimulatorValidator;

/**
 * @author freddy.michael@dana.id
 * @version $Id: ParseUtil.java, v 0.1 2025‐01‐26 19.43 freddy.michael@dana.id Exp $$
 */
public class ParseUtil {
    public static int parseVehicleYear(String vehicleYear) {
        try {
            Integer year = Integer.parseInt(vehicleYear);
            CreditSimulatorValidator.validateVehicleYear(year);
            return year;
        } catch (NumberFormatException e) {
            throw new CreditSimulatorException(CreditSimulatorErrorCodeEnum.INVALID_FIELD_FORMAT,
                    "Vehicle year must be a number 4 digits", true);
        }
    }

    public static Double parseTotalLoanAmount(String totalLoanAmount) {
        try {
            double v = Double.parseDouble(totalLoanAmount);
            CreditSimulatorValidator.validateTotalLoanAmount(v);
            return v;
        } catch (NumberFormatException e) {
            throw new CreditSimulatorException(CreditSimulatorErrorCodeEnum.INVALID_FIELD_FORMAT,
                    "Total loan amount must be a number", true);
        }
    }

    public static Double parseDownPaymentAmount(CreditSimulatorRequest request,
                                                String downPaymentAmount) {
        try {
            Double downPaymentAmountDouble = Double.parseDouble(downPaymentAmount);
            request.setDownPaymentAmount(downPaymentAmountDouble);
            CreditSimulatorValidator.validateRequest(request);
            return downPaymentAmountDouble;
        } catch (NumberFormatException e) {
            throw new CreditSimulatorException(CreditSimulatorErrorCodeEnum.INVALID_FIELD_FORMAT,
                    "Down payment amount must be a number", true);
        }
    }

    public static int parseTenorYear(String tenorYear) {
        try {
            Integer year = Integer.parseInt(tenorYear);
            CreditSimulatorValidator.validateTenorYear(year);
            return year;
        } catch (NumberFormatException e) {
            throw new CreditSimulatorException(CreditSimulatorErrorCodeEnum.INVALID_FIELD_FORMAT,
                    "Tenor year must be a number", true);
        }
    }


}