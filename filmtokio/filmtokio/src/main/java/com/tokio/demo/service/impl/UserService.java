package com.tokio.demo.service.impl;

import com.tokio.demo.domain.User;
import com.tokio.demo.dto.web.UserRegisterDTO;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    Optional<User> findById(Long id);

    User save(@Valid UserRegisterDTO user);

    void delete(Long id);

    boolean existsByUsername(String username);
}
