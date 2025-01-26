/**
 * Alipay.com Inc.
 * Copyright (c) 2004‐2025 All Rights Reserved.
 */
package id.bca.credit.simulator.core.service.calculation;

import id.bca.credit.simulator.core.model.request.CreditSimulatorRequest;
import id.bca.credit.simulator.core.model.result.CreditSimulatorResult;

/**
 * @author freddy.michael@dana.id
 * @version $Id: CalculateLoanInstallmentCoreService.java, v 0.1 2025‐01‐25 22.23 freddy.michael@dana.id Exp $$
 */
public interface CalculateLoanInstallmentCoreService {
    CreditSimulatorResult calculateLoanInstallment(CreditSimulatorRequest request);
}