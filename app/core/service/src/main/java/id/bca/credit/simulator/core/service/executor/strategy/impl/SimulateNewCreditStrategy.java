/**
 * Alipay.com Inc.
 * Copyright (c) 2004‐2025 All Rights Reserved.
 */
package id.bca.credit.simulator.core.service.executor.strategy.impl;

import id.bca.common.dal.dao.SheetDAO;
import id.bca.common.dal.dao.impl.SheetDAOImpl;
import id.bca.credit.simulator.common.util.enums.VehicleTypeEnum;
import id.bca.credit.simulator.common.util.enums.VihicleConditionEnum;
import id.bca.credit.simulator.core.model.request.CreditSimulatorRequest;
import id.bca.credit.simulator.core.model.result.CreditSimulatorResult;
import id.bca.credit.simulator.core.service.calculation.CalculateLoanInstallmentCoreService;
import id.bca.credit.simulator.core.service.calculation.impl.CalculateLoanInstallmentCoreServiceImpl;
import id.bca.credit.simulator.core.service.executor.strategy.CreditSimulatorStrategy;
import id.bca.credit.simulator.core.service.util.DisplayCreditSimulatorUtil;
import id.bca.credit.simulator.core.service.util.ParseUtil;
import id.bca.credit.simulator.core.service.validation.CreditSimulatorValidator;

import java.util.Scanner;

/**
 * @author freddy.michael@dana.id
 * @version $Id: impl.java, v 0.1 2025‐01‐26 10.58 freddy.michael@dana.id Exp $$
 */
public class SimulateNewCreditStrategy implements CreditSimulatorStrategy {
    private CalculateLoanInstallmentCoreService calculateLoanInstallmentCoreService;
    private Scanner                             scanner;
    private CreditSimulatorRequest              request;
    private CreditSimulatorResult               result;

    public SimulateNewCreditStrategy() {
        this.calculateLoanInstallmentCoreService = new CalculateLoanInstallmentCoreServiceImpl();
        this.scanner = new Scanner(System.in);
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public boolean execute() {
        System.out.println("\n===== Simulate New Credit =====\n");

        request = getUserInput();
        CreditSimulatorValidator.validateRequest(request);
        result = calculateLoanInstallmentCoreService.calculateLoanInstallment(request);

        DisplayCreditSimulatorUtil.displayCreditSimulatorResult(result);
        handleSaveToHistory();
        return true;
    }

    public CreditSimulatorRequest getUserInput() {
        CreditSimulatorRequest request = new CreditSimulatorRequest();

        request.setVehicleType(getVehicleType());
        request.setVehicleCondition(getVehicleCondition());
        request.setVehicleYear(getVehicleYear());
        request.setTotalLoanAmount(getTotalLoanAmount());
        request.setTenorYear(getTenorYear());
        request.setDownPaymentAmount(getDownPaymentAmount());
        return request;
    }

    private VehicleTypeEnum getVehicleType() {
        System.out.print("Enter your vehicle type (Mobil|Motor): ");
        String vehicleType = scanner.nextLine();
        CreditSimulatorValidator.validateVehicleType(vehicleType);
        return VehicleTypeEnum.getEnumFromCode(vehicleType.toUpperCase());
    }

    private VihicleConditionEnum getVehicleCondition() {
        System.out.print("Enter your vehicle condition (Baru|Bekas): ");
        String vehicleCondition = scanner.nextLine();
        CreditSimulatorValidator.validateVehicleCondition(vehicleCondition);
        return VihicleConditionEnum.getEnumFromCode(vehicleCondition.toUpperCase());
    }

    private Integer getVehicleYear() {
        System.out.print("Enter your vehicle year: ");
        String vehicleYear = scanner.nextLine();

        return ParseUtil.parseVehicleYear(vehicleYear);
    }

    private Double getTotalLoanAmount() {
        System.out.print("Enter your total credit (Ex, 100000000): ");
        String totalLoanAmount = scanner.nextLine();
        return ParseUtil.parseTotalLoanAmount(totalLoanAmount);
    }

    private Integer getTenorYear() {
        System.out.print("Enter your tenor year (1-6): ");
        String tenorYear = scanner.nextLine();

        return ParseUtil.parseTenorYear(tenorYear);
    }

    private Double getDownPaymentAmount() {
        System.out.print("Enter your down payment (Ex, 3000000): ");
        String downPaymentAmount = scanner.nextLine();

        return ParseUtil.parseDownPaymentAmount(request, downPaymentAmount);
    }

    private void handleSaveToHistory() {
        String save = getSaveToHistoryInput();
        if (save.equalsIgnoreCase("Y")) {
            String inputFileName = getInputFileName();
            // save to history
            SheetDAO sheetDAO = new SheetDAOImpl(request, result);
            sheetDAO.write(inputFileName + ".xlsx");
            System.out.println("Simulation saved to history\n");
        }
    }

    public String getSaveToHistoryInput() {
        System.out.print("Do you want to save this simulation to history? (Y/N): ");
        return scanner.nextLine();
    }

    public String getInputFileName() {
        System.out.print("Enter file name: ");
        return scanner.nextLine();
    }

}