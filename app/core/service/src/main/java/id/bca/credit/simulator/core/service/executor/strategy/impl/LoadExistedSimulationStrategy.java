/**
 * Alipay.com Inc.
 * Copyright (c) 2004‐2025 All Rights Reserved.
 */
package id.bca.credit.simulator.core.service.executor.strategy.impl;

import id.bca.credit.simulator.common.service.integration.mock.DataMockClient;
import id.bca.credit.simulator.common.service.integration.mock.impl.DataMockClientImpl;
import id.bca.credit.simulator.core.model.request.CreditSimulatorRequest;
import id.bca.credit.simulator.core.model.result.CreditSimulatorResult;
import id.bca.credit.simulator.core.service.calculation.CalculateLoanInstallmentCoreService;
import id.bca.credit.simulator.core.service.calculation.impl.CalculateLoanInstallmentCoreServiceImpl;
import id.bca.credit.simulator.core.service.executor.strategy.CreditSimulatorStrategy;
import id.bca.credit.simulator.core.service.util.DisplayCreditSimulatorUtil;

/**
 * @author freddy.michael@dana.id
 * @version $Id: LoadExistedSimulationStrategy.java, v 0.1 2025‐01‐26 11.01 freddy.michael@dana.id Exp $$
 */
public class LoadExistedSimulationStrategy implements CreditSimulatorStrategy {
    @Override
    public boolean execute() {
        System.out.println("\n===== Load Existing Simulation =====\n");
        DataMockClient dataMockClient = new DataMockClientImpl();
        CreditSimulatorRequest existingData = dataMockClient.getExistingData();
        CalculateLoanInstallmentCoreService calculateLoanInstallmentCoreService = new CalculateLoanInstallmentCoreServiceImpl();
        CreditSimulatorResult creditSimulatorResult = calculateLoanInstallmentCoreService.calculateLoanInstallment(
                existingData);

        DisplayCreditSimulatorUtil.displayCreditSimulatorRequest(existingData);
        DisplayCreditSimulatorUtil.displayCreditSimulatorResult(creditSimulatorResult);

        return true;
    }
}