/**
 * Alipay.com Inc.
 * Copyright (c) 2004‐2025 All Rights Reserved.
 */
package id.bca.credit.simulator.test.core.service.strategy;

import id.bca.credit.simulator.common.util.enums.VehicleTypeEnum;
import id.bca.credit.simulator.common.util.enums.VihicleConditionEnum;
import id.bca.credit.simulator.core.model.request.CreditSimulatorRequest;
import id.bca.credit.simulator.core.service.executor.strategy.impl.SimulateNewCreditStrategy;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author freddy.michael@dana.id
 * @version $Id: SimulateNewCreditStrategy.java, v 0.1 2025‐01‐26 14.24 freddy.michael@dana.id Exp $$
 */
public class SimulateNewCreditStrategyTest {
    private SimulateNewCreditStrategy simulateNewCreditStrategy;

    @Before
    public void setUp() {
        simulateNewCreditStrategy = Mockito.spy(new SimulateNewCreditStrategy());
    }
    @Test
    public void testExecute_Success() {

        // Mock the getUserInput method
        CreditSimulatorRequest mockRequest = new CreditSimulatorRequest();
        mockRequest.setVehicleType(VehicleTypeEnum.MOBIL);
        mockRequest.setVehicleCondition(VihicleConditionEnum.BEKAS);
        mockRequest.setVehicleYear(2021);
        mockRequest.setTenorYear(3);
        mockRequest.setTotalLoanAmount(100_000_000.0);
        mockRequest.setDownPaymentAmount(25_000_000.0);

        Mockito.doReturn(mockRequest).when(simulateNewCreditStrategy).getUserInput();
        // Mock the scanner input
        Mockito.doReturn("Y").when(simulateNewCreditStrategy).getSaveToHistoryInput();
        Mockito.doReturn("result").when(simulateNewCreditStrategy).getInputFileName();
        simulateNewCreditStrategy.execute();

        // Verify the getUserInput method
    }
}