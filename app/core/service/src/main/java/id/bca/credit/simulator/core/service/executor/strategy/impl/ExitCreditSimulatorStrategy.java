/**
 * Alipay.com Inc.
 * Copyright (c) 2004‐2025 All Rights Reserved.
 */
package id.bca.credit.simulator.core.service.executor.strategy.impl;

import id.bca.credit.simulator.core.service.executor.strategy.CreditSimulatorStrategy;

/**
 * @author freddy.michael@dana.id
 * @version $Id: ExitCreditSimulatorStrategy.java, v 0.1 2025‐01‐26 11.16 freddy.michael@dana.id Exp $$
 */
public class ExitCreditSimulatorStrategy implements CreditSimulatorStrategy {
    @Override
    public boolean execute() {
        System.out.println("\n===== Exit Credit Simulator =====\n");
        return false;
    }
}