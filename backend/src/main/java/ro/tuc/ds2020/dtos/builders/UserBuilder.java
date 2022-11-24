package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.UserDTO;
import ro.tuc.ds2020.entities.Users;

public class UserBuilder {
    public UserBuilder(){

    }
    public static UserDTO toUserDTO(Users users){
        return new UserDTO(users.getId(), users.getName(), users.getMail(), users.getPassword(), users.getAddress(), users.getAge(), users.getRole());
    }
    public static Users toEntity(UserDTO userDTO){
        return new Users(userDTO.getName(), userDTO.getMail(), userDTO.getPassword(), userDTO.getRole(), userDTO.getAddress(), userDTO.getAge());
    }
}
