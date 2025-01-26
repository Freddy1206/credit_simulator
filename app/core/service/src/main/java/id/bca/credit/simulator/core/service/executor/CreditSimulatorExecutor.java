/**
 * Alipay.com Inc.
 * Copyright (c) 2004‐2025 All Rights Reserved.
 */
package id.bca.credit.simulator.core.service.executor;

/**
 * @author freddy.michael@dana.id
 * @version $Id: CreditServiceExecutor.java, v 0.1 2025‐01‐26 10.22 freddy.michael@dana.id Exp $$
 */
public interface CreditSimulatorExecutor {
    boolean execute();

    boolean exeucteFile(String path);
}