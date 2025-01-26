/**
 * Alipay.com Inc.
 * Copyright (c) 2004‐2025 All Rights Reserved.
 */
package id.bca.credit.simulator.test.dal;

import id.bca.common.dal.dao.SheetDAO;
import id.bca.common.dal.dao.impl.SheetDAOImpl;
import id.bca.credit.simulator.common.util.enums.VehicleTypeEnum;
import id.bca.credit.simulator.common.util.enums.VihicleConditionEnum;
import id.bca.credit.simulator.core.model.request.CreditSimulatorRequest;
import id.bca.credit.simulator.core.model.result.CreditSimulatorResult;
import id.bca.credit.simulator.core.service.calculation.impl.CalculateLoanInstallmentCoreServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * @author freddy.michael@dana.id
 * @version $Id: SheetDAOImplTest.java, v 0.1 2025‐01‐26 01.46 freddy.michael@dana.id Exp $$
 */
public class SheetDAOImplTest {
    @Test
    public void testRead_Success() {
        System.out.println("Read Sheet");

        // Test logic here
        CreditSimulatorRequest creditSimulatorRequest = new CreditSimulatorRequest();
        CreditSimulatorResult creditSimulatorResult = new CreditSimulatorResult();
        SheetDAO sheetDAO = new SheetDAOImpl(creditSimulatorRequest, creditSimulatorResult);
        sheetDAO.read("example1.xlsx");

        Assertions.assertThat(creditSimulatorResult).isNotNull();
        Assertions.assertThat(creditSimulatorResult.getCreditSimulatorInfoList()).isNotEmpty();
        Assertions.assertThat(creditSimulatorResult.getCreditSimulatorInfoList().size()).isEqualTo(3);

        Assertions.assertThat(creditSimulatorResult.getCreditSimulatorInfoList().get(0).getYear()).isEqualTo(1);

        Assertions.assertThat(creditSimulatorResult.getCreditSimulatorInfoList().get(0).getInterestRate()).isEqualTo(8);
        Assertions.assertThat(creditSimulatorResult.getCreditSimulatorInfoList().get(0).getInstallmentAmount()).isEqualTo(2_250_000.00);

        Assertions.assertThat(creditSimulatorResult.getCreditSimulatorInfoList().get(1).getYear()).isEqualTo(2);
        Assertions.assertThat(creditSimulatorResult.getCreditSimulatorInfoList().get(1).getInterestRate()).isEqualTo(8.1);
        Assertions.assertThat(creditSimulatorResult.getCreditSimulatorInfoList().get(1).getInstallmentAmount()).isEqualTo(2_432_250.00);

        Assertions.assertThat(creditSimulatorResult.getCreditSimulatorInfoList().get(2).getYear()).isEqualTo(3);
        Assertions.assertThat(creditSimulatorResult.getCreditSimulatorInfoList().get(2).getInterestRate()).isCloseTo(8.6, Assertions.within(0.0001));
        Assertions.assertThat(creditSimulatorResult.getCreditSimulatorInfoList().get(2).getInstallmentAmount()).isEqualTo(2_641_423.50);


        Assertions.assertThat(creditSimulatorRequest).isNotNull();
        Assertions.assertThat(creditSimulatorRequest.getTotalLoanAmount()).isEqualTo(100_000_000.00);
        Assertions.assertThat(creditSimulatorRequest.getDownPaymentAmount()).isEqualTo(25_000_000.00);
        Assertions.assertThat(creditSimulatorRequest.getTenorYear()).isEqualTo(3);
        Assertions.assertThat(creditSimulatorRequest.getVehicleType()).isEqualTo(VehicleTypeEnum.MOBIL);
        Assertions.assertThat(creditSimulatorRequest.getVehicleCondition()).isEqualTo(VihicleConditionEnum.BARU);

    }

    @Test
    public void testWrite_Success() {
        System.out.println("Write Sheet");
        // prepare data
        CreditSimulatorRequest creditSimulatorRequest = new CreditSimulatorRequest();
        creditSimulatorRequest.setTotalLoanAmount(100_000_000.00);
        creditSimulatorRequest.setDownPaymentAmount(25_000_000.00);
        creditSimulatorRequest.setTenorYear(3);
        creditSimulatorRequest.setVehicleType(VehicleTypeEnum.MOBIL);
        creditSimulatorRequest.setVehicleCondition(VihicleConditionEnum.BARU);
        creditSimulatorRequest.setVehicleYear(2025);
        CalculateLoanInstallmentCoreServiceImpl calculateLoanInstallmentCoreService = new CalculateLoanInstallmentCoreServiceImpl();
        CreditSimulatorResult creditSimulatorResult = calculateLoanInstallmentCoreService.calculateLoanInstallment(
                creditSimulatorRequest);

        // Test logic here
        SheetDAO sheetDAO = new SheetDAOImpl(creditSimulatorRequest, creditSimulatorResult);
        sheetDAO.write("example1.xlsx");
    }
}