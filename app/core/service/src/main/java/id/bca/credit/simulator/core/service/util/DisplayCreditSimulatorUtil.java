/**
 * Alipay.com Inc.
 * Copyright (c) 2004‐2025 All Rights Reserved.
 */
package id.bca.credit.simulator.core.service.util;

import id.bca.credit.simulator.core.model.request.CreditSimulatorRequest;
import id.bca.credit.simulator.core.model.result.CreditSimulatorInfo;
import id.bca.credit.simulator.core.model.result.CreditSimulatorResult;

/**
 * @author freddy.michael@dana.id
 * @version $Id: DisplayCreditSimulatorResultUtil.java, v 0.1 2025‐01‐26 12.50 freddy.michael@dana.id Exp $$
 */
public class DisplayCreditSimulatorUtil {
    public static void displayCreditSimulatorResult(CreditSimulatorResult creditSimulatorResult) {
        System.out.println("\n===== Credit Simulator Result =====\n");

        for (CreditSimulatorInfo info : creditSimulatorResult.getCreditSimulatorInfoList()) {
            String interestRate = (info.getInterestRate() % 1 == 0) ?
                    String.format("%.0f", info.getInterestRate()) :
                    String.format("%.1f", info.getInterestRate());
            System.out.println(
                    String.format("Tahun %d : Rp. %,.2f/bln , Suku Bunga : %s%%", info.getYear(),
                            info.getInstallmentAmount(), interestRate));
        }
    }

    public static void displayCreditSimulatorRequest(
            CreditSimulatorRequest creditSimulatorRequest) {
        System.out.println("\n===== Credit Simulator Request =====\n");
        System.out.println(String.format("Jenis Kendaraan : %s",
                creditSimulatorRequest.getVehicleType().getDescription()));
        System.out.println(String.format("Kondisi Kendaraan : %s",
                creditSimulatorRequest.getVehicleCondition().getDescription()));
        System.out.println(
                String.format("Tahun Kendaraan : %d", creditSimulatorRequest.getVehicleYear()));
        System.out.println(String.format("Jumlah Pinjaman : Rp. %,.2f",
                creditSimulatorRequest.getTotalLoanAmount()));
        System.out.println(
                String.format("Tenor Pinjaman : %d tahun", creditSimulatorRequest.getTenorYear()));
        System.out.println(String.format("Jumalh DP : Rp. %,.2f",
                creditSimulatorRequest.getDownPaymentAmount()));

    }
}