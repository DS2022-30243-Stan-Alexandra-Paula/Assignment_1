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
public class Users implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;
    //id, name, role:admin/client

    @Column(name="name", nullable = false)
    private String name;
    @Column(name="mail", nullable = false)
    private String mail;
    @Column(name="password", nullable = false)
    private String password;
    @Column(name="role", nullable = false)
    private String role;
    @Column(name="address", nullable = false)
    private String address;
    @Column(name="age", nullable = false)
    private int age;

    @OneToMany(mappedBy = "users", orphanRemoval=true)
    private List<Device> devices=new ArrayList<>();

    public Users(){

    }
    public Users(String name, String mail, String password, String role, String address, int age){
        this.name=name;
        this.mail=mail;
        this.password=password;
        this.role=role;
        this.address = address;
        this.age=age;
        this.devices=new ArrayList<>();
    }


    public void addDevice(Device device) {
        devices.add(device);
    }

}
