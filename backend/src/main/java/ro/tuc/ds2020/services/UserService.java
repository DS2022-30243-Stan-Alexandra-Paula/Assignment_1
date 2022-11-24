package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.dtos.LoginDto;
import ro.tuc.ds2020.dtos.UserDTO;
import ro.tuc.ds2020.dtos.builders.DeviceBuilder;
import ro.tuc.ds2020.dtos.builders.UserBuilder;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.Users;
import ro.tuc.ds2020.repositories.DeviceRepository;
import ro.tuc.ds2020.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final Logger LOGGER= LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;
    @Autowired
    public UserService(UserRepository userRepository, DeviceRepository deviceRepository) {
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
    }
    public List<UserDTO> findUsers(){
        List<Users> users =userRepository.findAll();
        return users.stream().map(UserBuilder::toUserDTO).collect(Collectors.toList());
    }
    public UserDTO findById(UUID id){
        Optional<Users> userOptional=userRepository.findById(id);
        if(!userOptional.isPresent()){
            LOGGER.error("User with id {} was not found in db", id);
            throw new ResourceNotFoundException(Users.class.getSimpleName() + " with id: " + id);
        }
        return UserBuilder.toUserDTO(userOptional.get());
    }
    @Transactional
    public UUID insert(UserDTO userDTO){
        Users users =UserBuilder.toEntity(userDTO);
        users =userRepository.save(users);
        LOGGER.debug("User with id {} was inserted in db", users.getId());
        return users.getId();
    }
    @Transactional
    public UUID update(UserDTO userDTO,UUID id){
        Users users =UserBuilder.toEntity(userDTO);
        users.setId(id);
        users =userRepository.save(users);
        LOGGER.debug("User with id {} was updated in db", users.getId());
        return users.getId();
    }
    @Transactional
    public void delete(UUID id) {
        Optional<Users> user = userRepository.findById(id);
        if (user.isPresent()) {
            LOGGER.debug("User with id {} is deleted from db", user.get().getId());
            userRepository.delete(user.get());
        }
    }
    @Transactional
    public List<DeviceDTO> addDevice(UUID idUser, UUID idDevice){
        Optional<Device> device=deviceRepository.findById(idDevice);
        Optional<Users> user=userRepository.findById(idUser);
        if(user.isPresent() && device.isPresent()){
            device.get().setUsers(user.get());
            user.get().addDevice(device.get());
        }
        return user.get().getDevices().stream().map(DeviceBuilder::toDeviceDTO).collect(Collectors.toList());
    }

    @Transactional
    public LoginDto login(String mail, String password){
        LoginDto role=new LoginDto();
        if(userRepository.findUsersByMailAndPassword(mail,password).isPresent()){
           role.setRole(userRepository.findUsersByMailAndPassword(mail,password).get().getRole());
        }
        return role;
    }
    @Transactional
    public List<DeviceDTO> getDevicesForAUserByMail(String mail){
        Optional<Users> user=userRepository.findUsersByMail(mail);
        List<DeviceDTO> devices=new ArrayList<>();
        if(user.isPresent()){
             devices=user.get().getDevices().stream().map(DeviceBuilder::toDeviceDTO).collect(Collectors.toList());
        }
        return devices;
    }
}
