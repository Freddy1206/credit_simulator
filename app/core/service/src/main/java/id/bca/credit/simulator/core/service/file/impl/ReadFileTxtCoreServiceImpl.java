/**
 * Alipay.com Inc.
 * Copyright (c) 2004‐2025 All Rights Reserved.
 */
package id.bca.credit.simulator.core.service.file.impl;

import id.bca.credit.simulator.common.util.enums.VehicleTypeEnum;
import id.bca.credit.simulator.common.util.enums.VihicleConditionEnum;
import id.bca.credit.simulator.core.model.request.CreditSimulatorRequest;
import id.bca.credit.simulator.core.model.result.CreditSimulatorResult;
import id.bca.credit.simulator.core.service.calculation.CalculateLoanInstallmentCoreService;
import id.bca.credit.simulator.core.service.calculation.impl.CalculateLoanInstallmentCoreServiceImpl;
import id.bca.credit.simulator.core.service.file.ReadFileTxtCoreService;
import id.bca.credit.simulator.core.service.util.DisplayCreditSimulatorUtil;
import id.bca.credit.simulator.core.service.util.ParseUtil;
import id.bca.credit.simulator.core.service.validation.CreditSimulatorValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author freddy.michael@dana.id
 * @version $Id: ReadFileTxtCoreServicee.java, v 0.1 2025‐01‐26 19.32 freddy.michael@dana.id Exp $$
 */
public class ReadFileTxtCoreServiceImpl implements ReadFileTxtCoreService {
    @Override
    public void readFile(String path) {
        System.out.println("Read file from path: " + path);
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            if (lines.size() >= 6) {
                CreditSimulatorRequest creditSimulatorRequest = composeCreditSimulatorRequest(
                        lines);
                CalculateLoanInstallmentCoreService calculateLoanInstallmentCoreService = new CalculateLoanInstallmentCoreServiceImpl();
                CreditSimulatorResult creditSimulatorResult = calculateLoanInstallmentCoreService.calculateLoanInstallment(
                        creditSimulatorRequest);

                DisplayCreditSimulatorUtil.displayCreditSimulatorRequest(creditSimulatorRequest);
                DisplayCreditSimulatorUtil.displayCreditSimulatorResult(creditSimulatorResult);

            } else {
                System.err.println("File does not contain enough lines.");
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing number: " + e.getMessage());
        }
    }

    private CreditSimulatorRequest composeCreditSimulatorRequest(List<String> lines) {
        CreditSimulatorRequest creditSimulatorRequest = new CreditSimulatorRequest();
        String vehicleType = lines.get(0);
        CreditSimulatorValidator.validateVehicleType(vehicleType);
        creditSimulatorRequest.setVehicleType(VehicleTypeEnum.getEnumFromCode(vehicleType.toUpperCase()));

        String vehicleCondition = lines.get(1);
        CreditSimulatorValidator.validateVehicleCondition(vehicleCondition);
        creditSimulatorRequest.setVehicleCondition(
                VihicleConditionEnum.getEnumFromCode(vehicleCondition.toUpperCase()));
        creditSimulatorRequest.setVehicleYear(ParseUtil.parseVehicleYear(lines.get(2)));
        creditSimulatorRequest.setTotalLoanAmount(ParseUtil.parseTotalLoanAmount(lines.get(3)));
        creditSimulatorRequest.setTenorYear(ParseUtil.parseTenorYear(lines.get(4)));
        creditSimulatorRequest.setDownPaymentAmount(
                ParseUtil.parseDownPaymentAmount(creditSimulatorRequest, lines.get(5)));
        return creditSimulatorRequest;
    }
}