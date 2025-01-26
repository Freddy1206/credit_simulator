/**
 * Alipay.com Inc.
 * Copyright (c) 2004‐2025 All Rights Reserved.
 */
package id.bca.credit.simulator.core.service.configuration;

import id.bca.credit.simulator.common.util.enums.VehicleTypeEnum;
import id.bca.credit.simulator.common.util.enums.VihicleConditionEnum;
import id.bca.credit.simulator.core.service.executor.strategy.CreditSimulatorStrategy;
import id.bca.credit.simulator.core.service.executor.strategy.impl.ExitCreditSimulatorStrategy;
import id.bca.credit.simulator.core.service.executor.strategy.impl.LoadExistedSimulationStrategy;
import id.bca.credit.simulator.core.service.executor.strategy.impl.ShowHistorySimulationStrategy;
import id.bca.credit.simulator.core.service.executor.strategy.impl.SimulateNewCreditStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author freddy.michael@dana.id
 * @version $Id: CreditSimulatorStrategyConfig.java, v 0.1 2025‐01‐26 11.04 freddy.michael@dana.id Exp $$
 */
public class CreditSimulatorStrategyConfig {
    private static Map<Integer, CreditSimulatorStrategy> getStrategyMap() {
        Map<Integer, CreditSimulatorStrategy> strategyMap = new HashMap<>();
        strategyMap.put(1, new SimulateNewCreditStrategy());
        strategyMap.put(2, new LoadExistedSimulationStrategy());
        strategyMap.put(3, new ShowHistorySimulationStrategy());
        strategyMap.put(4, new ExitCreditSimulatorStrategy());
        return strategyMap;
    }


    public static CreditSimulatorStrategy getStrategy(int strategyId) {
        return getStrategyMap().get(strategyId);
    }

    public static Map<VehicleTypeEnum, Double> interestRateMap() {
        Map<VehicleTypeEnum, Double> interestRateMap = new HashMap<>();
        interestRateMap.put(VehicleTypeEnum.MOBIL, 0.08);
        interestRateMap.put(VehicleTypeEnum.MOTOR, 0.09);

        return interestRateMap;
    }

    public static Double getInterestRate(VehicleTypeEnum vehicleTypeEnum) {
        return interestRateMap().get(vehicleTypeEnum);
    }

    public static Map<VihicleConditionEnum, Double> downPaymentRateMap() {
        Map<VihicleConditionEnum, Double> downPaymentRateMap = new HashMap<>();
        downPaymentRateMap.put(VihicleConditionEnum.BARU, 0.35);
        downPaymentRateMap.put(VihicleConditionEnum.BEKAS, 0.25);

        return downPaymentRateMap;
    }

    public static Double getDownPaymentRate(VihicleConditionEnum vihicleConditionEnum) {
        return downPaymentRateMap().get(vihicleConditionEnum);
    }
}