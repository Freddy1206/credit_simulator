package id.bca.credit.simulator.core.service.executor.impl;

import id.bca.credit.simulator.common.util.enums.CreditSimulatorErrorCodeEnum;
import id.bca.credit.simulator.common.util.error.AssertUtil;
import id.bca.credit.simulator.common.util.error.CreditSimulatorException;
import id.bca.credit.simulator.core.service.configuration.CreditSimulatorStrategyConfig;
import id.bca.credit.simulator.core.service.executor.CreditSimulatorExecutor;
import id.bca.credit.simulator.core.service.executor.strategy.CreditSimulatorStrategy;
import id.bca.credit.simulator.core.service.file.ReadFileTxtCoreService;
import id.bca.credit.simulator.core.service.file.impl.ReadFileTxtCoreServiceImpl;

import java.util.Scanner;

public class CreditSimulatorExecutorImpl implements CreditSimulatorExecutor {
    @Override
    public boolean execute() {
        Scanner scanner = new Scanner(System.in);
        displayMenu();
        int option = getUserOption(scanner);

        CreditSimulatorStrategy strategy = CreditSimulatorStrategyConfig.getStrategy(option);
        AssertUtil.notNull(strategy, CreditSimulatorErrorCodeEnum.OUT_OF_RANGE, true,
                "Strategy not found for option: " + option);
        return strategy.execute();
    }

    private void displayMenu() {
        System.out.println("\nAvailable Options:");
        System.out.println("1. Simulate New Credit");
        System.out.println("2. Load Existing Simulation");
        System.out.println("3. Show Your History Simulation");
        System.out.println("4. Exit");
        System.out.print("Select an option: ");
    }

    private int getUserOption(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            throw new CreditSimulatorException(CreditSimulatorErrorCodeEnum.INVALID_FIELD_FORMAT,
                    "Option must be a number", true);
        }
    }

    private void displayInvalidOptionMessage() {
        System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println("xxxxx INVALID OPTION xxxxx");
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxx\n");
    }

    @Override
    public boolean exeucteFile(String path) {
        ReadFileTxtCoreService readFileTxtCoreService = new ReadFileTxtCoreServiceImpl();
         readFileTxtCoreService.readFile(path);
        return true;
    }

    public static void main(String[] args) {
        CreditSimulatorExecutor creditSimulatorExecutor = new CreditSimulatorExecutorImpl();
        creditSimulatorExecutor.exeucteFile("input.txt");
    }
}