/**
 * Alipay.com Inc.
 * Copyright (c) 2004‐2025 All Rights Reserved.
 */
package id.bca.common.dal.dao;

import id.bca.credit.simulator.core.model.request.CreditSimulatorRequest;
import id.bca.credit.simulator.core.model.result.CreditSimulatorResult;

/**
 * @author freddy.michael@dana.id
 * @version $Id: SheetWriter.java, v 0.1 2025‐01‐26 01.31 freddy.michael@dana.id Exp $$
 */
public interface SheetDAO {
    void write(String filePath);

    CreditSimulatorResult read(String sheetName);
}