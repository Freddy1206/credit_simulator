/**
 * Alipay.com Inc.
 * Copyright (c) 2004‐2025 All Rights Reserved.
 */
package id.bca.credit.simulator.core.service.executor.strategy.impl;

import id.bca.common.dal.dao.SheetDAO;
import id.bca.common.dal.dao.impl.SheetDAOImpl;
import id.bca.credit.simulator.common.util.enums.CreditSimulatorErrorCodeEnum;
import id.bca.credit.simulator.common.util.error.CreditSimulatorException;
import id.bca.credit.simulator.core.model.request.CreditSimulatorRequest;
import id.bca.credit.simulator.core.model.result.CreditSimulatorResult;
import id.bca.credit.simulator.core.service.executor.strategy.CreditSimulatorStrategy;
import id.bca.credit.simulator.core.service.util.DisplayCreditSimulatorUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author freddy.michael@dana.id
 * @version $Id: ShowHistorySimulationStrategy.java, v 0.1 2025‐01‐26 11.01 freddy.michael@dana.id Exp $$
 */
public class ShowHistorySimulationStrategy implements CreditSimulatorStrategy {
    private Scanner scanner;

    public ShowHistorySimulationStrategy() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public boolean execute() {
        System.out.println("\n===== Show Your History Simulation =====\n");
        System.out.println("1. Load All History Simulation");
        System.out.println("2. Load Specific History Simulation");

        int choice = getChoice();
        switch (choice) {
            case 1:
                loadAllHistorySimulations();
                break;
            case 2:
                List<String> allFileNames = getAllFileNames();
                if (CollectionUtils.isEmpty(allFileNames)) {
                    System.out.println("No history simulation found");
                    break;
                }
                System.out.println("Available history simulations:");
                for (int i = 0; i < allFileNames.size(); i++) {
                    System.out.println(i + 1 + ". " + allFileNames.get(i));
                }
                loadSpecificHistorySimulation(getFileNameWithExtension());
                break;
            default:
                throw new CreditSimulatorException(CreditSimulatorErrorCodeEnum.OUT_OF_RANGE,
                        "out of range choice", true);
        }
        return true;
    }

    private int getChoice() {
        try {
            System.out.print("Enter choice: ");
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new CreditSimulatorException(CreditSimulatorErrorCodeEnum.INVALID_FIELD_FORMAT,
                    "Invalid choice", true);
        }
    }

    private void loadAllHistorySimulations() {
        List<String> fileNames = getAllFileNames();
        if (CollectionUtils.isEmpty(fileNames)) {
            System.out.println("No history simulation found");
        } else {
            fileNames.forEach(this::loadSpecificHistorySimulation);
        }
    }

    private void loadSpecificHistorySimulation(String fileName) {
        Path filePath = Paths.get("data", fileName);

        if(!Files.exists(filePath)) {
            throw new CreditSimulatorException(CreditSimulatorErrorCodeEnum.FILE_NOT_FOUND,
                    "File not found", true);
        }

        System.out.println("\nLoading file: " + filePath.getFileName());
        CreditSimulatorRequest creditSimulatorRequest = new CreditSimulatorRequest();
        CreditSimulatorResult creditSimulatorResult = new CreditSimulatorResult();
        SheetDAO sheetDAO = new SheetDAOImpl(creditSimulatorRequest, creditSimulatorResult);
        creditSimulatorResult = sheetDAO.read(fileName);
        DisplayCreditSimulatorUtil.displayCreditSimulatorRequest(creditSimulatorRequest);
        DisplayCreditSimulatorUtil.displayCreditSimulatorResult(creditSimulatorResult);
    }

    private List<String> getAllFileNames() {
        List<String> fileNames = null;
        try {
            Path dataFolderPath = Paths.get("data");
            fileNames = Files.list(dataFolderPath).map(Path::getFileName).map(Path::toString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Error reading files: " + e.getMessage());
        }

        return fileNames;
    }

    private String getFileNameWithExtension() {
        System.out.print("Enter file name: ");
        String fileName = scanner.nextLine();

        if (!fileName.endsWith(".xlsx")) {
            fileName += ".xlsx";
        }
        return fileName;
    }
}