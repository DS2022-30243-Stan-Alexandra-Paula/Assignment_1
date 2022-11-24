package ro.tuc.ds2020.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import java.sql.Time;
import java.util.Objects;
import java.util.UUID;
@Getter
@Setter
public class MeasurementsDTO extends RepresentationModel<MeasurementsDTO> {
    private UUID id;
    private Time timestamp;
    private int energyConsumption;

    public MeasurementsDTO(){

    }
    public MeasurementsDTO(UUID id, Time timestamp, int energyConsumption){
        this.id=id;
        this.timestamp=timestamp;
        this.energyConsumption=energyConsumption;
    }
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o==null || getClass()!=o.getClass()) return false;
        MeasurementsDTO measurements=(MeasurementsDTO) o;
        return (Objects.equals(timestamp,measurements.timestamp) && Objects.equals(energyConsumption, measurements.energyConsumption) );
    }
    @Override
    public int hashCode(){return Objects.hash(id, energyConsumption);}
}
