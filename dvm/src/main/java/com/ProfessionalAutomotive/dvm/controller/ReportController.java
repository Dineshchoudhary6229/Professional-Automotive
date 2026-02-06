package com.ProfessionalAutomotive.dvm.controller;
import com.ProfessionalAutomotive.dvm.entity.Vehicle;
import com.ProfessionalAutomotive.dvm.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {
    private final VehicleRepository vehicleRepo;

    @GetMapping("/old-vehicles")
    public List<Vehicle> getOldVehicles() {
        return vehicleRepo.findByManufactureDateBefore(LocalDate.now().minusYears(5));
    }
}