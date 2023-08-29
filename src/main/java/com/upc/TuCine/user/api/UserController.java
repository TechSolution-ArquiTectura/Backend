package com.upc.TuCine.user.api;

import com.upc.TuCine.security.domain.service.communication.AuthenticateRequest;
import com.upc.TuCine.security.domain.service.communication.RegisterRequest;
import com.upc.TuCine.security.resource.TypeUserDto;
import com.upc.TuCine.user.domain.service.UserService;
import com.upc.TuCine.user.resource.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/TuCine/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //URL: http://localhost:8080/api/TuCine/v1/users
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/users/{userId}
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer userId){
        return new ResponseEntity<>(userService.getById(userId), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/users/auth/sign-in
    //Method: POST
    @PostMapping("/auth/sign-in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthenticateRequest request) {
        return userService.authenticate(request);
    }

    //URL: http://localhost:8080/api/TuCine/v1/users/auth/sign-up
    //Method: POST
    @PostMapping("/auth/sign-up")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    //URL: http://localhost:8080/api/TuCine/v1/users/{id}/typeUser
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("{id}/typeUser")
    public ResponseEntity<TypeUserDto> getTypeUserByUserId(@PathVariable("id") Integer id) {
        TypeUserDto typeUserDto = userService.getTypeUserById(id);
        if (typeUserDto == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(typeUserDto, HttpStatus.OK);
    }
}
