package ro.tuc.ds2020.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class UserDTO extends RepresentationModel<UserDTO> {
    private UUID id;
    private String name;
    private String mail;
    private String password;
    private String address;
    private int age;
    private String role;

    public UserDTO(){

    }
    public UserDTO(UUID id, String name, String mail, String password, String address, int age, String role){
        this.id=id;
        this.name=name;
        this.mail=mail;
        this.password=password;
        this.address=address;
        this.age=age;
        this.role=role;
    }
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o==null || getClass()!=o.getClass()) return false;
        UserDTO userDTO=(UserDTO) o;
        return (Objects.equals(name, userDTO.name) && Objects.equals(address, userDTO.address) && Objects.equals(age, userDTO.age) && Objects.equals(role, userDTO.role));
    }
    @Override
    public int hashCode(){return Objects.hash(id, name);}

}
