package ro.tuc.ds2020.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@Entity
public class Device implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;
    // id, description, address, maximum hourly energy consumption

    @Column(name="description", nullable = false)
    private String description;

    @Column(name="address", nullable = false)
    private String address;

    @Column(name="hourlyconsumption", nullable = false)
    private int consumption;

    @ManyToOne
    private Users users;

    @OneToMany(mappedBy = "device", orphanRemoval=true)
    private List<Measurements> measurements=new ArrayList<>();

    public Device(){

    }
    public Device(String description, String address, int consumption){
        this.description=description;
        this.address=address;
        this.consumption=consumption;
        this.measurements=new ArrayList<>();
    }
    public void addMeasurement(Measurements measurements){
        this.measurements.add(measurements);
    }
}
