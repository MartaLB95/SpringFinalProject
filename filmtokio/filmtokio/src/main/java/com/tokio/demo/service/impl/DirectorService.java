package com.tokio.demo.service.impl;

import com.tokio.demo.domain.Director;

import java.util.List;
import java.util.Optional;

public interface DirectorService {
    List<Director> findAll();

    Optional<Director> findById(Long id);

    Director save(Director director);
}
