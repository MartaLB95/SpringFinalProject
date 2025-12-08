package com.tokio.demo.service.impl;

import com.tokio.demo.domain.Role;
import com.tokio.demo.domain.User;
import com.tokio.demo.dto.web.UserRegisterDTO;
import com.tokio.demo.repository.RoleRepository;
import com.tokio.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Lazy(true)
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        logger.info("Service: fetching all users");
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        logger.info("Service: fetching user with id {}", id);
        return Optional.ofNullable(userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User with ID " + id + " not found")));
    }

    @Override
    public User save(@Valid UserRegisterDTO userDTO) {
        //Entidad user
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setDateOfBirth(userDTO.getDateOfBirth());

        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setPasswordBis(passwordEncoder.encode(userDTO.getPasswordBis()));
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(Set.of(userRole));

        logger.info("New user created");

        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        if (userRepository.existsById(id)) {
            logger.info("User with id {} exists", id);
            userRepository.deleteById(id);
            logger.info("User with id {} deleted", id);
        }
    }

    public boolean existsByUsername(@NotBlank(message = "Username is required") String username) {
        logger.warn("Checking if username {} exists", username);
        return userRepository.existsByUsername(username);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
