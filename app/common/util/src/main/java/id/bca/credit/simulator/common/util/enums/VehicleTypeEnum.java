/**
 * Alipay.com Inc.
 * Copyright (c) 2004‐2025 All Rights Reserved.
 */
package id.bca.credit.simulator.common.util.enums;

import java.util.Objects;

/**
 * @author freddy.michael@dana.id
 * @version $Id: VehicleTypeEnum.java, v 0.1 2025‐01‐25 20.14 freddy.michael@dana.id Exp $$
 */
public enum VehicleTypeEnum {
    MOTOR("MOTOR", "Motor"),
    MOBIL("MOBIL", "Mobil"),
    ;

    private String code;
    private String description;

    VehicleTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static VehicleTypeEnum getEnumFromCode(String code) {
        for (VehicleTypeEnum vehicleTypeEnum : VehicleTypeEnum.values()) {
            if (Objects.equals(vehicleTypeEnum.getCode(), code)) {
                return vehicleTypeEnum;
            }
        }
        return null;
    }
}