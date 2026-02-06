package com.ProfessionalAutomotive.dvm.service;

import com.ProfessionalAutomotive.dvm.entity.*;
import com.ProfessionalAutomotive.dvm.repository.AssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AssignmentService {
    private final AssignmentRepository assignmentRepo;

    public void createAssignment(Driver d, Vehicle v) {
        if (!assignmentRepo.existsByVehicleAndEndDateIsNull(v)) {
            DriverVehicleAssignment a = new DriverVehicleAssignment();
            a.setDriver(d);
            a.setVehicle(v);
            a.setStartDate(LocalDate.now());
            assignmentRepo.save(a);
        }
    }
}