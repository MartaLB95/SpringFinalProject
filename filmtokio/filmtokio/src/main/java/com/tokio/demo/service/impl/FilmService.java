package com.tokio.demo.service.impl;

import com.tokio.demo.domain.Film;

import java.util.List;
import java.util.Optional;

public interface FilmService {
    List<Film> findAll();

    Optional<Film> findById(Long id);

    Film save(Film film);

    List <Film>  findByTitleContaining(String titleFragment);
}
