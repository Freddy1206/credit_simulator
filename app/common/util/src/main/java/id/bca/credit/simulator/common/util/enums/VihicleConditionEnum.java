/**
 * Alipay.com Inc.
 * Copyright (c) 2004‐2025 All Rights Reserved.
 */
package id.bca.credit.simulator.common.util.enums;

/**
 * @author freddy.michael@dana.id
 * @version $Id: VihicleConditionEnum.java, v 0.1 2025‐01‐25 20.19 freddy.michael@dana.id Exp $$
 */
public enum VihicleConditionEnum {
    BARU("BARU", "Baru"),
    BEKAS("BEKAS", "Bekas"),
    ;

    private String code;
    private String description;

    VihicleConditionEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static VihicleConditionEnum getEnumFromCode(String code) {
        for (VihicleConditionEnum vihicleConditionEnum : VihicleConditionEnum.values()) {
            if (vihicleConditionEnum.getCode().equals(code)) {
                return vihicleConditionEnum;
            }
        }
        return null;
    }
}