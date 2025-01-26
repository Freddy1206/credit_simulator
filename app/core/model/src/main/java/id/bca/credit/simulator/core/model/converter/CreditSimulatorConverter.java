/**
 * Alipay.com Inc.
 * Copyright (c) 2004‐2025 All Rights Reserved.
 */
package id.bca.credit.simulator.core.model.converter;

import id.bca.credit.simulator.common.util.enums.VehicleTypeEnum;
import id.bca.credit.simulator.common.util.enums.VihicleConditionEnum;
import id.bca.credit.simulator.core.model.request.CreditSimulatorRequest;
import org.json.JSONObject;

/**
 * @author freddy.michael@dana.id
 * @version $Id: CreditSimulatorConverter.java, v 0.1 2025‐01‐25 21.54 freddy.michael@dana.id Exp $$
 */
public class CreditSimulatorConverter {
    public static CreditSimulatorRequest convertToRequest(JSONObject jsonObject) {
        CreditSimulatorRequest request = new CreditSimulatorRequest();
        request.setVehicleType(VehicleTypeEnum.getEnumFromCode(
                String.valueOf(jsonObject.get("type")).toUpperCase()));
        request.setVehicleCondition(VihicleConditionEnum.getEnumFromCode(
                String.valueOf(jsonObject.get("condition")).toUpperCase()));
        request.setVehicleYear((Integer) jsonObject.get("year"));
        request.setTotalLoanAmount(Double.valueOf((int) jsonObject.get("loan_amount")));
        request.setDownPaymentAmount(Double.valueOf((int) jsonObject.get("down_payment")));
        request.setTenorYear((Integer) jsonObject.get("tenor"));
        return request;

    }
}