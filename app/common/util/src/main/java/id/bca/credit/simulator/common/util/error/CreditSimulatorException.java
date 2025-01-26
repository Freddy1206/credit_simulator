/**
 * Alipay.com Inc.
 * Copyright (c) 2004‐2025 All Rights Reserved.
 */
package id.bca.credit.simulator.common.util.error;

import id.bca.credit.simulator.common.util.enums.CreditSimulatorErrorCodeEnum;

/**
 * @author freddy.michael@dana.id
 * @version $Id: CreditSimulatorException.java, v 0.1 2025‐01‐26 11.54 freddy.michael@dana.id Exp $$
 */
public class CreditSimulatorException extends RuntimeException {
    private boolean needRetry;
    private CreditSimulatorErrorCodeEnum errorCode;


    public CreditSimulatorException(CreditSimulatorErrorCodeEnum errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public CreditSimulatorException(CreditSimulatorErrorCodeEnum errorCode, boolean needRetry) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.needRetry = needRetry;
    }

    public CreditSimulatorException(CreditSimulatorErrorCodeEnum errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public CreditSimulatorException(CreditSimulatorErrorCodeEnum errorCode, String message, boolean needRetry) {
        super(message);
        this.errorCode = errorCode;
        this.needRetry = needRetry;
    }

    public CreditSimulatorErrorCodeEnum getErrorCode() {
        return errorCode;
    }

    public boolean isNeedRetry() {
        return needRetry;
    }

    public void setNeedRetry(boolean needRetry) {
        this.needRetry = needRetry;
    }

    public void setErrorCode(CreditSimulatorErrorCodeEnum errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCodeMessage() {
        return errorCode.getMessage();
    }
}