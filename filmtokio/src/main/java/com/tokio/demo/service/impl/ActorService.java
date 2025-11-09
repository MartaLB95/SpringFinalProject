package com.tokio.demo.service.impl;

import com.tokio.demo.domain.Actor;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface ActorService {
    @Transactional
    List<Actor> findAll();

    Actor save(Actor actor);

    Optional<Actor> findById(Long id);

}
