/**
 * Alipay.com Inc.
 * Copyright (c) 2004‐2025 All Rights Reserved.
 */
package id.bca.credit.simulator.common.service.integration.mock;

import id.bca.credit.simulator.core.model.request.CreditSimulatorRequest;

/**
 * @author freddy.michael@dana.id
 * @version $Id: DataMockClient.java, v 0.1 2025‐01‐25 20.07 freddy.michael@dana.id Exp $$
 */
public interface DataMockClient {
    CreditSimulatorRequest getExistingData();
}