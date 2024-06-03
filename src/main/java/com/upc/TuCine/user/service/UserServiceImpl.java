package com.upc.TuCine.user.service;

import com.upc.TuCine.security.domain.model.entity.Gender;
import com.upc.TuCine.security.domain.model.entity.TypeUser;
import com.upc.TuCine.security.domain.model.enumeration.Genders;
import com.upc.TuCine.security.domain.model.enumeration.TypeUsers;
import com.upc.TuCine.security.domain.persistence.GenderRepository;
import com.upc.TuCine.security.domain.persistence.TypeUserRepository;
import com.upc.TuCine.security.domain.service.communication.AuthenticateRequest;
import com.upc.TuCine.security.domain.service.communication.AuthenticateResponse;
import com.upc.TuCine.security.domain.service.communication.RegisterRequest;
import com.upc.TuCine.security.domain.service.communication.RegisterResponse;
import com.upc.TuCine.security.resource.AuthenticateDto;
import com.upc.TuCine.security.resource.TypeUserDto;
import com.upc.TuCine.shared.exception.ResourceNotFoundException;
import com.upc.TuCine.shared.mapping.EnhancedModelMapper;
import com.upc.TuCine.user.domain.model.entity.User;
import com.upc.TuCine.user.domain.persistence.UserRepository;
import com.upc.TuCine.user.domain.service.UserService;
import com.upc.TuCine.user.mapping.UserMapper;
import com.upc.TuCine.user.resource.UserDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hibernate.usertype.DynamicParameterizedType.ENTITY;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private TypeUserRepository typeUserRepository;
    @Autowired
    private EnhancedModelMapper enhancedMapper;

    private UserMapper mapper;

    UserServiceImpl(UserMapper userMapper) {
        this.mapper = userMapper;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return mapper.modelListToResource(userRepository.findAll());
    }

    @Override
    public UserDto getById(Integer userId) {
        return mapper.toResource(userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId)));
    }

    @Override
    public TypeUserDto getTypeUserById(Integer id) {
        User person = userRepository.findById(id).orElse(null);
        if (person == null) {
            return null;
        }
        return enhancedMapper.map(person.getTypeUser(), TypeUserDto.class);
    }

    @Override
    public ResponseEntity<?> register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email is already used.");
        }
        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            return ResponseEntity.badRequest().body("Phone number is already used.");
        }

        try {
            Set<String> rolesStringSet = request.getTypeUser();
            Set<TypeUser> roles = new HashSet<>();
            Set<String> gendersStringSet = request.getGender();
            Set<Gender> genders = new HashSet<>();

            if (rolesStringSet == null) {
                typeUserRepository.findByName(TypeUsers.BUSINESS)
                        .map(roles::add)
                        .orElseThrow(() -> new RuntimeException("TypeUser not found."));
            } else {
                rolesStringSet.forEach(roleString ->
                        typeUserRepository.findByName(TypeUsers.valueOf(roleString))
                                .map(roles::add)
                                .orElseThrow(() -> new RuntimeException("TypeUser not found.")));
            }

            if (gendersStringSet == null) {
                genderRepository.findByName(Genders.FEMALE)
                        .map(genders::add)
                        .orElseThrow(() -> new RuntimeException("Gender not found."));
            } else {
                gendersStringSet.forEach(genderString ->
                        genderRepository.findByName(Genders.valueOf(genderString))
                                .map(genders::add)
                                .orElseThrow(() -> new RuntimeException("Gender not found.")));
            }

            logger.info("Roles: {}", roles);
            logger.info("Genders: {}", genders);

            LocalDate currentDate = LocalDate.now();
            User user = new User()
                    .withName(request.getName())
                    .withLastname(request.getLastname())
                    .withBirthdate(request.getBirthdate())
                    .withEmail(request.getEmail())
                    .withEmailVerified(currentDate)
                    .withPassword(request.getPassword()) // No encoding since no security is used
                    .withPhoneNumber(request.getPhoneNumber())
                    .withGender(genders.iterator().next())
                    .withTypeUser(roles.iterator().next());

            userRepository.save(user);
            UserDto resource = enhancedMapper.map(user, UserDto.class);
            return ResponseEntity.ok(resource);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> authenticate(AuthenticateRequest request) {
        try{
            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("Email not found."));

            if (!user.getPassword().equals(request.getPassword())) {
                return ResponseEntity.badRequest().body("Invalid password.");
            }

            AuthenticateDto resource = enhancedMapper.map(user, AuthenticateDto.class);
            return ResponseEntity.ok(resource);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
