package com.fatec.networkmonitoring.repository;


import com.fatec.networkmonitoring.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, String> {
}
