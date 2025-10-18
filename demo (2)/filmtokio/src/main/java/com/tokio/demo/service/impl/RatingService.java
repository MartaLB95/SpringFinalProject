package com.tokio.demo.service.impl;

import com.tokio.demo.domain.Rating;

import java.util.List;
import java.util.Optional;

public interface RatingService {
    List<Rating> findAll();

    Optional<Rating> findById(Long id);

    Rating save(Rating rating);

    void delete(Long id);

    double findAverageScoreByFilmId(Long filmId);
}
