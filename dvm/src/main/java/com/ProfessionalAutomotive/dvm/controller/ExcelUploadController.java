package com.ProfessionalAutomotive.dvm.controller;
import com.ProfessionalAutomotive.dvm.service.ExcelUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class ExcelUploadController {
    private final ExcelUploadService service;

    @PostMapping("/vehicles")
    public String uploadVehicles(@RequestParam("file") MultipartFile file) throws Exception {
        service.uploadVehicles(file);
        return "Vehicles Uploaded Successfully";
    }

    @PostMapping("/drivers")
    public String uploadDrivers(@RequestParam("file") MultipartFile file) throws Exception {
        service.uploadDrivers(file);
        return "Drivers Uploaded and Assigned Successfully";
    }
}