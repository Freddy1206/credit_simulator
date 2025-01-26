/**
 * Alipay.com Inc.
 * Copyright (c) 2004‐2025 All Rights Reserved.
 */
package id.bca.credit.simulator.core.model.result;

import java.util.ArrayList;
import java.util.List;

/**
 * @author freddy.michael@dana.id
 * @version $Id: CreditSimulatorResult.java, v 0.1 2025‐01‐25 20.46 freddy.michael@dana.id Exp $$
 */
public class CreditSimulatorResult {
    private List<CreditSimulatorInfo> creditSimulatorInfoList = new ArrayList<>();

    public List<CreditSimulatorInfo> getCreditSimulatorInfoList() {
        return creditSimulatorInfoList;
    }

    public void setCreditSimulatorInfoList(List<CreditSimulatorInfo> creditSimulatorInfoList) {
        this.creditSimulatorInfoList = creditSimulatorInfoList;
    }

    public void addCreditSimulatorInfo(CreditSimulatorInfo creditSimulatorInfo) {
        this.creditSimulatorInfoList.add(creditSimulatorInfo);
    }
}