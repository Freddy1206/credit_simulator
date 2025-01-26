/**
 * Alipay.com Inc.
 * Copyright (c) 2004‐2025 All Rights Reserved.
 */
package id.bca.credit.simulator.test.integration;

import id.bca.credit.simulator.common.service.integration.mock.impl.DataMockClientImpl;
import id.bca.credit.simulator.common.util.enums.VehicleTypeEnum;
import id.bca.credit.simulator.common.util.enums.VihicleConditionEnum;
import id.bca.credit.simulator.core.model.request.CreditSimulatorRequest;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * @author freddy.michael@dana.id
 * @version $Id: DataMockClientTest.java, v 0.1 2025‐01‐25 21.43 freddy.michael@dana.id Exp $$
 */
public class DataMockClientTest {
    @Test
    public void testGetExistingData_Success() {
        System.out.println("Test Get Existing Data Success");
        DataMockClientImpl dataMockClient = new DataMockClientImpl();

        CreditSimulatorRequest existingData = dataMockClient.getExistingData();

        Assertions.assertThat(existingData).isNotNull();
        Assertions.assertThat(existingData.getVehicleType()).isEqualTo(VehicleTypeEnum.MOBIL);
        Assertions.assertThat(existingData.getVehicleCondition()).isEqualTo(VihicleConditionEnum.BARU);
        Assertions.assertThat(existingData.getVehicleYear()).isEqualTo(2023);
        Assertions.assertThat(existingData.getTotalLoanAmount()).isEqualTo(500000000);
        Assertions.assertThat(existingData.getDownPaymentAmount()).isEqualTo(175000000);
        Assertions.assertThat(existingData.getTenorYear()).isEqualTo(5);
    }

    // todo : add negative test case
    // todo : add test case for exception
    // todo : add using mock

}