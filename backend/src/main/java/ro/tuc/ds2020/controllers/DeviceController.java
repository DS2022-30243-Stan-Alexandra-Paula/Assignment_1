package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.dtos.UserDTO;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.services.DeviceService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/device")
public class DeviceController {
    private final DeviceService deviceService;
    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }
    @GetMapping()
    public ResponseEntity<List<DeviceDTO>> getDevices(){
        List<DeviceDTO> devices= deviceService.findDevices();
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getDevice(@PathVariable UUID id){
        DeviceDTO dto=deviceService.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<UUID> insertDevice(@Valid @RequestBody DeviceDTO deviceDTO) {
        UUID personID = deviceService.insert(deviceDTO);
        return new ResponseEntity<>(personID, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable UUID id,@RequestBody DeviceDTO deviceDTO){
        UUID idR=deviceService.update(deviceDTO,id);
        return new ResponseEntity<>(idR, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDevice(@PathVariable UUID id){
        deviceService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
