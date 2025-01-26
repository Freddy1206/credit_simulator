package id.bca.common.dal.dao.impl;

import id.bca.common.dal.dao.SheetDAO;
import id.bca.credit.simulator.common.util.enums.CreditSimulatorErrorCodeEnum;
import id.bca.credit.simulator.common.util.error.CreditSimulatorException;
import id.bca.credit.simulator.core.model.request.CreditSimulatorRequest;
import id.bca.credit.simulator.core.model.result.CreditSimulatorInfo;
import id.bca.credit.simulator.core.model.result.CreditSimulatorResult;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

public class SheetDAOImpl implements SheetDAO {
    // todo : add logger
    private CreditSimulatorRequest request;
    private CreditSimulatorResult  result;

    public SheetDAOImpl(CreditSimulatorRequest request, CreditSimulatorResult result) {
        this.request = request;
        this.result = result;
    }

    @Override
    public void write(String fileName) {
        String directoryPath = "data/";
        String filePath = directoryPath + fileName;

        createDirectoryIfNotExists(directoryPath);

        try (Workbook workbook = new XSSFWorkbook()) {
            writeRequestInfoSheet(workbook);
            writeResultInfoSheet(workbook);
            saveWorkbookToFile(workbook, filePath);
        } catch (IOException e) {
            throw new CreditSimulatorException(CreditSimulatorErrorCodeEnum.FAILED_TO_WRITE_FILE,
                    "Failed to write file",true);
        }
    }

    private void createDirectoryIfNotExists(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    private void writeRequestInfoSheet(Workbook workbook) {
        Sheet sheet = workbook.createSheet("RequestInfo");
        Row headerRow = sheet.createRow(0);
        createCell(headerRow, 0, "Field Name");
        createCell(headerRow, 1, "Value");

        for (Field field : request.getClass().getDeclaredFields()) {
            Row row = sheet.createRow(sheet.getLastRowNum() + 1);
            createCell(row, 0, field.getName());
            field.setAccessible(true);
            try {
                createCell(row, 1, field.get(request).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeResultInfoSheet(Workbook workbook) {
        Sheet sheet = workbook.createSheet("ResultInfo");
        Row headerRow = sheet.createRow(0);
        createCell(headerRow, 0, "Year");
        createCell(headerRow, 1, "Interest Rate");
        createCell(headerRow, 2, "Installment Amount");

        for (CreditSimulatorInfo info : result.getCreditSimulatorInfoList()) {
            Row row = sheet.createRow(sheet.getLastRowNum() + 1);
            createCell(row, 0, info.getYear());
            createCell(row, 1, info.getInterestRate());
            createCell(row, 2, info.getInstallmentAmount());
        }
    }

    private void saveWorkbookToFile(Workbook workbook, String filePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
            System.out.println("Excel file written successfully on path: " + filePath);
        }
    }

    private void createCell(Row row, int column, String value) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value);
    }

    private void createCell(Row row, int column, double value) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value);
    }

    private void createCell(Row row, int column, int value) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value);
    }

    @Override
    public CreditSimulatorResult read(String sheetName) {
        String filePath = "data/" + sheetName;

        try (FileInputStream fis = new FileInputStream(
                filePath); Workbook workbook = new XSSFWorkbook(fis)) {
            readRequestInfoSheet(workbook.getSheetAt(0));
            readResultInfoSheet(workbook.getSheetAt(1));
        } catch (IOException | NoSuchFieldException | IllegalAccessException e) {
            throw new CreditSimulatorException(CreditSimulatorErrorCodeEnum.FAILED_TO_READ_FILE,
                    "Failed to read file", true);
        }

        return result;
    }

    private void readRequestInfoSheet(Sheet sheet)
            throws NoSuchFieldException, IllegalAccessException {
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null)
                continue;

            Cell fieldNameCell = row.getCell(0);
            Cell valueCell = row.getCell(1);

            if (fieldNameCell != null && valueCell != null) {
                String fieldName = fieldNameCell.getStringCellValue();
                Field field = CreditSimulatorRequest.class.getDeclaredField(fieldName);
                field.setAccessible(true);
                setFieldValue(field, valueCell);
            }
        }
    }

    private void readResultInfoSheet(Sheet sheet) {
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null)
                continue;

            Cell yearCell = row.getCell(0);
            Cell interestRateCell = row.getCell(1);
            Cell installmentAmountCell = row.getCell(2);

            if (yearCell != null && interestRateCell != null && installmentAmountCell != null) {
                int year = (int) yearCell.getNumericCellValue();
                double interestRate = interestRateCell.getNumericCellValue();
                double installmentAmount = installmentAmountCell.getNumericCellValue();

                CreditSimulatorInfo info = new CreditSimulatorInfo();
                info.setYear(year);
                info.setInterestRate(interestRate);
                info.setInstallmentAmount(installmentAmount);
                result.addCreditSimulatorInfo(info);
            }
        }
    }

    private void setFieldValue(Field field, Cell valueCell) throws IllegalAccessException {
        switch (valueCell.getCellType()) {
            case STRING:
                if (field.getType().isEnum()) {
                    Object enumValue = Enum.valueOf((Class<Enum>) field.getType(),
                            valueCell.getStringCellValue());
                    field.set(request, enumValue);
                } else if (field.getType() == Integer.class) {
                    field.set(request, Integer.parseInt(valueCell.getStringCellValue()));
                } else if (field.getType() == Double.class) {
                    field.set(request, Double.parseDouble(valueCell.getStringCellValue()));
                } else {
                    field.set(request, valueCell.getStringCellValue());
                }
                break;
            case NUMERIC:
                if (field.getType() == Integer.class) {
                    field.set(request, (int) valueCell.getNumericCellValue());
                } else if (field.getType() == Double.class) {
                    field.set(request, valueCell.getNumericCellValue());
                } else {
                    field.set(request, valueCell.getNumericCellValue());
                }
                break;
            case BOOLEAN:
                field.set(request, valueCell.getBooleanCellValue());
                break;
            default:
                System.out.println("Unknown data type");
        }
    }
}