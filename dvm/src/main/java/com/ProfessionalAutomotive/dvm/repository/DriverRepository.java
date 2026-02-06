package com.ProfessionalAutomotive.dvm.repository;
import com.ProfessionalAutomotive.dvm.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DriverRepository extends JpaRepository<Driver, Long> {}