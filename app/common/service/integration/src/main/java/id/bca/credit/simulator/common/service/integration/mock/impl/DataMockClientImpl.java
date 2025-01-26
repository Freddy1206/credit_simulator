/**
 * Alipay.com Inc.
 * Copyright (c) 2004‐2025 All Rights Reserved.
 */
package id.bca.credit.simulator.common.service.integration.mock.impl;

import id.bca.credit.simulator.common.service.integration.mock.DataMockClient;
import id.bca.credit.simulator.common.util.enums.CreditSimulatorErrorCodeEnum;
import id.bca.credit.simulator.common.util.error.CreditSimulatorException;
import id.bca.credit.simulator.core.model.converter.CreditSimulatorConverter;
import id.bca.credit.simulator.core.model.request.CreditSimulatorRequest;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import static id.bca.credit.simulator.common.util.constants.CommonConstant.DATA_EXISTED_URL;

/**
 * @author freddy.michael@dana.id
 * @version $Id: DataMockClientImpl.java, v 0.1 2025‐01‐25 20.08 freddy.michael@dana.id Exp $$
 */
public class DataMockClientImpl implements DataMockClient {
    @Override
    public CreditSimulatorRequest getExistingData() {
        CreditSimulatorRequest creditSimulatorRequest = null;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(DATA_EXISTED_URL).get().build();

        try {
            Response response = client.newCall(request).execute();
            JSONObject jsonObject = new JSONObject(response.body().string());
            creditSimulatorRequest = CreditSimulatorConverter.convertToRequest(jsonObject);
        } catch (Exception e) {
            throw new CreditSimulatorException(CreditSimulatorErrorCodeEnum.THIRD_PARTY_ERROR,
                    "Failed to get existing data", true);
        }

        return creditSimulatorRequest;
    }
}