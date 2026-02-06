package com.ProfessionalAutomotive.dvm.repository;
import com.ProfessionalAutomotive.dvm.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AssignmentRepository extends JpaRepository<DriverVehicleAssignment, Long> {
    boolean existsByVehicleAndEndDateIsNull(Vehicle vehicle);
}