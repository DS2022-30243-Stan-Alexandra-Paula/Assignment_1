package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.dtos.LoginDto;
import ro.tuc.ds2020.dtos.UserDTO;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.services.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping()
    public ResponseEntity<List<UserDTO>> getUsers(){
        List<UserDTO> users= userService.findUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable UUID id){
        UserDTO dto=userService.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<UUID> insertUser(@Valid @RequestBody UserDTO userDTO) {
        UUID personID = userService.insert(userDTO);
        return new ResponseEntity<>(personID, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable UUID id,@RequestBody UserDTO userDTO){
        UUID idR=userService.update(userDTO,id);
        return new ResponseEntity<>(idR, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id){
        userService.delete(id);
        return new ResponseEntity<>(id,HttpStatus.OK);
    }
    @PostMapping("/login/{mail}/{password}")
    public ResponseEntity<?> login(@PathVariable String mail, @PathVariable String password){
        LoginDto role =userService.login(mail,password);
        return new ResponseEntity<>(role,HttpStatus.OK);
    }
    @PostMapping("/addDevice/{idUser}/{idDevice}")
    public ResponseEntity<?> addDeviceToUser(@PathVariable UUID idUser, @PathVariable UUID idDevice){
        List<DeviceDTO> devices=userService.addDevice(idUser,idDevice);
        return new ResponseEntity<>(devices,HttpStatus.OK);
    }
    @GetMapping("/DeviceByMail/{mail}")
    public ResponseEntity<?> getDeviceByMail(@PathVariable String mail){
        List<DeviceDTO> dto=userService.getDevicesForAUserByMail(mail);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
