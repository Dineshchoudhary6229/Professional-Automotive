package com.ProfessionalAutomotive.dvm.repository;
import com.ProfessionalAutomotive.dvm.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByVehicleNumber(String vehicleNumber);
    List<Vehicle> findByManufactureDateBefore(LocalDate date);
}