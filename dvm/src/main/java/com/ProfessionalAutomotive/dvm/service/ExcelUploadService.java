package com.ProfessionalAutomotive.dvm.service;

import com.ProfessionalAutomotive.dvm.entity.*;
import com.ProfessionalAutomotive.dvm.repository.*;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ExcelUploadService {

    private final DriverRepository driverRepo;
    private final VehicleRepository vehicleRepo;
    private final AssignmentService assignmentService;

    public void uploadVehicles(MultipartFile file) throws Exception {
        try (Workbook wb = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = wb.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row r = sheet.getRow(i);
                if (r == null || isRowEmpty(r)) continue;

                Vehicle v = new Vehicle();
                v.setModel(getSafeString(r, 1));
                v.setVehicleNumber(getSafeString(r, 3));

                Cell yearCell = r.getCell(2);
                if (yearCell != null && yearCell.getCellType() == CellType.NUMERIC) {
                    v.setManufactureDate(LocalDate.of((int)yearCell.getNumericCellValue(), 1, 1));
                }
                vehicleRepo.save(v);
            }
        }
    }

    public void uploadDrivers(MultipartFile file) throws Exception {
        try (Workbook wb = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = wb.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row r = sheet.getRow(i);
                if (r == null || isRowEmpty(r)) continue;

                // Get the code and check if it's empty
                String driverCode = getSafeString(r, 1);

                // If Driver Code is empty, skip this row
                if (driverCode == null || driverCode.trim().isEmpty()) {
                    continue;
                }

                Driver d = new Driver();
                d.setDriverCode(driverCode);
                d.setDriverName(getSafeString(r, 2));
                d.setLicenceNumber(getSafeString(r, 3));
                d.setLicenceExpiryDate(getSafeDate(r, 4));

                driverRepo.save(d);

                String vNum = getSafeString(r, 0);
                if (!vNum.isEmpty()) {
                    vehicleRepo.findByVehicleNumber(vNum).ifPresent(v -> {
                        assignmentService.createAssignment(d, v);
                    });
                }
            }
        }
    }

    private String getSafeString(Row r, int index) {
        Cell c = r.getCell(index);
        if (c == null) return "";
        if (c.getCellType() == CellType.NUMERIC) return String.valueOf((long)c.getNumericCellValue());
        return c.getStringCellValue().trim();
    }

    private LocalDate getSafeDate(Row r, int index) {
        Cell c = r.getCell(index);
        if (c != null && c.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(c)) {
            return c.getLocalDateTimeCellValue().toLocalDate();
        }
        return null;
    }

    private boolean isRowEmpty(Row row) {
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != CellType.BLANK) return false;
        }
        return true;
    }
}