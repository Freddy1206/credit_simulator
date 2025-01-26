package id.bca.credit.simulator.application;
/**
 * Alipay.com Inc.
 * Copyright (c) 2004‐2025 All Rights Reserved.
 */

import id.bca.credit.simulator.biz.shared.support.CreditSimulatorSupport;
import id.bca.credit.simulator.core.service.executor.CreditSimulatorExecutor;
import id.bca.credit.simulator.core.service.executor.impl.CreditSimulatorExecutorImpl;

/**
 * @author freddy.michael@dana.id
 * @version $Id: id.bca.credit.simulator.application.CreditSimulatorApplication.java, v 0.1 2025‐01‐25 19.04 freddy.michael@dana.id Exp $$
 */
public class CreditSimulatorApplication {
    public static void main(String[] args) {
        System.out.println("Welcome to Credit Simulator Application");
        CreditSimulatorExecutor creditSimulatorExecutor = new CreditSimulatorExecutorImpl();
        if(args.length > 0) {
            CreditSimulatorSupport.processFile(creditSimulatorExecutor, args[0]);
        } else {
            CreditSimulatorSupport.process(creditSimulatorExecutor);
        }

        System.out.println("\nThank you for using Credit Simulator Application\n");
    }
}