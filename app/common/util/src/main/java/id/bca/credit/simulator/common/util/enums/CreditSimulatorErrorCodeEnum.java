/**
 * Alipay.com Inc.
 * Copyright (c) 2004‐2025 All Rights Reserved.
 */
package id.bca.credit.simulator.common.util.enums;

/**
 * @author freddy.michael@dana.id
 * @version $Id: CreditSimulatorErrorCodeEnum.java, v 0.1 2025‐01‐26 11.39 freddy.michael@dana.id Exp $$
 */
public enum CreditSimulatorErrorCodeEnum {
    SUCCESS("0000", "Success"),
    INVALID_REQUEST("0001", "Invalid Request"),
    INVALID_MANDATORY_FIELD("0004", "Invalid Mandatory Field"),
    INVALID_FIELD_FORMAT("0004", "Invalid Field Format"),
    OUT_OF_RANGE("0004", "Out of Range"),
    EXCEED_MAX_LOAN_AMOUNT("0005", "Exceed Max Loan Amount"),
    EXCEED_MAX_LOAN_TENOR("0006", "Exceed Max Loan Tenor"),
    TENOR_NEGATIVE("0007", "Tenor Negative"),
    DOWN_PAYMENT_LESS_THAN_MIN("0007", "Down Payment Less Than Min"),
    DOWN_PAYMENT_GREATER_THAN_LOAN_AMOUNT("0008", "Down Payment Greater Than Loan Amount"),
    THIRD_PARTY_ERROR("9998", "Third Party Error"),
    FILE_NOT_FOUND("9997", "File Not Found"),
    FAILED_TO_READ_FILE("9996", "Failed to Read File"),
    FAILED_TO_WRITE_FILE("9995", "Failed to Write File"),

    GENERAL_ERROR("9999", "General Error")
    ;

    private String code;
    private String message;

    CreditSimulatorErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static CreditSimulatorErrorCodeEnum getByCode(String code) {
        for (CreditSimulatorErrorCodeEnum errorCode : values()) {
            if (errorCode.getCode().equals(code)) {
                return errorCode;
            }
        }
        return null;
    }
}