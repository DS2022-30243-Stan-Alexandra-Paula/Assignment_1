package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.dtos.MeasurementsDTO;
import ro.tuc.ds2020.services.MeasurementsService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/measurements")
public class MeasurementsController {
    private final MeasurementsService measurementsService;
    @Autowired
    public MeasurementsController(MeasurementsService measurementsService) {
        this.measurementsService = measurementsService;
    }
    @GetMapping()
    public ResponseEntity<List<MeasurementsDTO>> getMeasurementss(){
        List<MeasurementsDTO> measurementss= measurementsService.findMeasurements();
        return new ResponseEntity<>(measurementss, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getMeasurements(@PathVariable UUID id){
        MeasurementsDTO dto=measurementsService.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<UUID> insertMeasurements(@Valid @RequestBody MeasurementsDTO measurementsDTO) {
        UUID personID = measurementsService.insert(measurementsDTO);
        return new ResponseEntity<>(personID, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMeasurements(@RequestBody MeasurementsDTO measurementsDTO,@PathVariable UUID id){
        UUID idR=measurementsService.update(measurementsDTO,id);
        return new ResponseEntity<>(idR, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMeasurements(@PathVariable UUID id){
        measurementsService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/addMeasurements/{idDevice}/{idMeasurement}")
    public ResponseEntity<?> addMeasurementToDevice(@PathVariable UUID idDevice, @PathVariable UUID idMeasurement){
        List<MeasurementsDTO> measurements=measurementsService.addMeasurementToDevice(idMeasurement, idDevice);
        return new ResponseEntity<>(measurements,HttpStatus.OK);
    }
}
