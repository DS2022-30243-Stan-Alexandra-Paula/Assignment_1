package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.MeasurementsDTO;
import ro.tuc.ds2020.entities.Measurements;

public class MeasurementsBuilder {
    private MeasurementsBuilder(){

    }
    public static MeasurementsDTO toMeasurementsDTO(Measurements measurements){
        return new MeasurementsDTO(measurements.getId(),measurements.getTimestamp(),measurements.getEnergyConsumption());
    }
    public static Measurements toEntity(MeasurementsDTO measurements){
        return new Measurements(measurements.getTimestamp(),measurements.getEnergyConsumption());
    }

}
