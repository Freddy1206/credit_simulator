/**
 * Alipay.com Inc.
 * Copyright (c) 2004‐2025 All Rights Reserved.
 */
package id.bca.credit.simulator.biz.shared.support;

import id.bca.credit.simulator.common.util.enums.CreditSimulatorErrorCodeEnum;
import id.bca.credit.simulator.common.util.error.CreditSimulatorException;
import id.bca.credit.simulator.core.service.executor.CreditSimulatorExecutor;

/**
 * @author freddy.michael@dana.id
 * @version $Id: CreditSimulatorSupport.java, v 0.1 2025‐01‐26 16.46 freddy.michael@dana.id Exp $$
 */
public class CreditSimulatorSupport {
    public static void process(CreditSimulatorExecutor creditSimulatorExecutor) {
        while (true) {
            try {
                boolean success = creditSimulatorExecutor.execute();
                if (!success) {
                    break;
                }
            } catch (CreditSimulatorException e) {
                System.out.println(
                        "Error: " + String.format("%s, message: %s", e.getErrorCodeMessage(),
                                e.getMessage()));
                if (!e.isNeedRetry()) {
                    break;
                }
            } catch (Exception e) {
                System.out.println(
                        "Error: " + CreditSimulatorErrorCodeEnum.GENERAL_ERROR.getMessage());
                break;
            }
        }
    }

    public static void processFile(CreditSimulatorExecutor creditSimulatorExecutor, String path) {
        try {
            creditSimulatorExecutor.exeucteFile(path);
        } catch (CreditSimulatorException e) {
            System.out.println("Error: " + String.format("%s, message: %s", e.getErrorCodeMessage(),
                    e.getMessage()));
        } catch (Exception e) {
            System.out.println("Error: " + CreditSimulatorErrorCodeEnum.GENERAL_ERROR.getMessage());
            ;
        }
    }

}