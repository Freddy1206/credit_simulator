/**
 * Alipay.com Inc.
 * Copyright (c) 2004‐2025 All Rights Reserved.
 */
package id.bca.credit.simulator.common.util.error;

import id.bca.credit.simulator.common.util.enums.CreditSimulatorErrorCodeEnum;

/**
 * @author freddy.michael@dana.id
 * @version $Id: AssertUtil.java, v 0.1 2025‐01‐26 11.56 freddy.michael@dana.id Exp $$
 */
public class AssertUtil {
    public static void notNull(Object object, CreditSimulatorErrorCodeEnum errorCode,
                               boolean needRetry, String... message) {

        if (object == null) {
            throw new CreditSimulatorException(errorCode, getErrorMessage(message), needRetry);
        }
    }

    public static void isNull(Object object, CreditSimulatorErrorCodeEnum errorCode,
                              boolean needRetry, String... message) {
        if (object != null) {
            throw new CreditSimulatorException(errorCode, getErrorMessage(message), needRetry);
        }
    }

    public static void isExpected(Object object, Object expected, CreditSimulatorErrorCodeEnum errorCode,
                                  boolean needRetry, String... message) {
        if (!object.equals(expected)) {
            throw new CreditSimulatorException(errorCode, getErrorMessage(message), needRetry);
        }
    }

    public static void isNotExpected(Object object, Object expected, CreditSimulatorErrorCodeEnum errorCode,
                                     boolean needRetry, String... message) {
        if (object.equals(expected)) {
            throw new CreditSimulatorException(errorCode, getErrorMessage(message), needRetry);
        }
    }

    public static void isTrue(boolean condition, CreditSimulatorErrorCodeEnum errorCode,
                              boolean needRetry, String... message) {
        if (!condition) {
            throw new CreditSimulatorException(errorCode, getErrorMessage(message), needRetry);
        }
    }

    public static void isFalse(boolean condition, CreditSimulatorErrorCodeEnum errorCode,
                               boolean needRetry, String... message) {
        if (condition) {
            throw new CreditSimulatorException(errorCode, getErrorMessage(message), needRetry);
        }
    }

    public static void notEmpty(String string, CreditSimulatorErrorCodeEnum errorCode,
                                boolean needRetry, String... message) {
        if (string == null || string.isEmpty()) {
            throw new CreditSimulatorException(errorCode, getErrorMessage(message), needRetry);
        }
    }



    private static String getErrorMessage(Object... objs) {
        StringBuilder errorMessage = new StringBuilder();
        Object[] arrayOfObject = objs;
        int length = objs.length;

        for (int i = 0; i < length; ++i) {
            Object o = arrayOfObject[i];
            errorMessage.append(o);
        }

        return errorMessage.toString();
    }
}