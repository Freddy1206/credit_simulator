/**
 * Alipay.com Inc.
 * Copyright (c) 2004‐2025 All Rights Reserved.
 */
package id.bca.credit.simulator.common.util.error;

import id.bca.credit.simulator.common.util.enums.CreditSimulatorErrorCodeEnum;

/**
 * @author freddy.michael@dana.id
 * @version $Id: ParamCheckHelper.java, v 0.1 2025‐01‐26 11.38 freddy.michael@dana.id Exp $$
 */
public class ParamCheckHelper {
    public static void notNull(Object object, CreditSimulatorErrorCodeEnum errorCode,
                               boolean needRetry, String... message) {
        AssertUtil.notNull(object, errorCode, needRetry, message);
    }

    public static void isNull(Object object, CreditSimulatorErrorCodeEnum errorCode,
                              boolean needRetry, String... message) {
        AssertUtil.isNull(object, errorCode, needRetry, message);
    }

    public static void isExpected(Object object, Object expected, CreditSimulatorErrorCodeEnum errorCode,
                                  boolean needRetry, String... message) {
        AssertUtil.isExpected(object, expected, errorCode, needRetry, message);
    }

    public static void isNotExpected(Object object, Object expected, CreditSimulatorErrorCodeEnum errorCode,
                                     boolean needRetry, String... message) {
        AssertUtil.isNotExpected(object, expected, errorCode, needRetry, message);
    }

    public static void isTrue(boolean condition, CreditSimulatorErrorCodeEnum errorCode,
                              boolean needRetry, String... message) {
        AssertUtil.isTrue(condition, errorCode, needRetry, message);
    }

    public static void isFalse(boolean condition, CreditSimulatorErrorCodeEnum errorCode,
                               boolean needRetry, String... message) {
        AssertUtil.isFalse(condition, errorCode, needRetry, message);
    }

    public static void notEmpty(String string, CreditSimulatorErrorCodeEnum errorCode,
                                boolean needRetry, String... message) {
        AssertUtil.notEmpty(string, errorCode, needRetry, message);
    }

}