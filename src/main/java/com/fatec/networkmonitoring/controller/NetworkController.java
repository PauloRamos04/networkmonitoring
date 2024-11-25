package com.fatec.networkmonitoring.controller;

import com.fatec.networkmonitoring.model.Device;
import com.fatec.networkmonitoring.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

@Controller
@RequestMapping("/network")
public class NetworkController {

    @Autowired
    private DeviceRepository deviceRepository;

    // Página inicial com lista de dispositivos
    @GetMapping("/")
    public String listDevices(Model model) {
        List<Device> devices = deviceRepository.findAll();
        model.addAttribute("devices", devices);
        return "index";
    }

    // Cadastro de dispositivo
    @PostMapping("/addDevice")
    public String addDevice(@RequestParam String ipAddress, @RequestParam String macAddress, Model model) {
        boolean isOnline = isDeviceOnline(ipAddress);
        Device device = new Device(ipAddress, macAddress, isOnline);
        deviceRepository.save(device);
        return "redirect:/network/";
    }

    // Descobrir dispositivos na rede
    @PostMapping("/discoverDevices")
    public String discoverDevices(Model model) {
        String subnet = "192.168.5"; // Subnet de exemplo, altere conforme necessário
        for (int i = 1; i <= 254; i++) {
            String ip = subnet + "." + i;
            boolean isOnline = isDeviceOnline(ip);
            Device device = new Device(ip, null, isOnline);
            deviceRepository.save(device);
        }
        return "redirect:/network/";
    }

    // Cadastrar IP para monitoramento
    @PostMapping("/addMonitorIp")
    public String addMonitorIp(@RequestParam String monitorIp, Model model) {
        boolean isOnline = isDeviceOnline(monitorIp);
        Device device = new Device(monitorIp, null, isOnline);
        deviceRepository.save(device);
        return "redirect:/network/";
    }

    // Verificar se o dispositivo está online (Ping)
    private boolean isDeviceOnline(String ipAddress) {
        try {
            InetAddress inet = InetAddress.getByName(ipAddress);
            return inet.isReachable(5000); // 5 segundos de timeout
        } catch (IOException e) {
            return false;
        }
    }
}
