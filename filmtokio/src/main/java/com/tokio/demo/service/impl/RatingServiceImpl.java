package com.tokio.demo.service.impl;

import com.tokio.demo.domain.Rating;
import com.tokio.demo.exception.RatingNotFoundException;
import com.tokio.demo.repository.RatingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Lazy(true)
@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    private static final Logger logger = LoggerFactory.getLogger(RatingServiceImpl.class);

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Transactional(readOnly = true)
    @Override

    public List<Rating> findAll() {
        logger.info("Service: fetching all ratings");
        return ratingRepository.findAll();
    }

    @Override
    public Optional<Rating> findById(Long id) {
        logger.info("Service: fetching film with id", id);
        return Optional.ofNullable(ratingRepository.findById(id).orElseThrow(() -> new RatingNotFoundException("Rating with ID " + id + " not found")));
    }

    @Override
    public Rating save(Rating rating) {
        logger.info("New rating created");
        return ratingRepository.save(rating);
    }

    @Override
    public void delete(Long id) {
        if (ratingRepository.existsById(id)) {
            logger.info("Rating with id {} exists", id);
            ratingRepository.deleteById(id);
            logger.info("Rating with id {} deleted", id);
        }
    }

    public double findAverageScoreByFilmId(Long filmId) {
        logger.info("Average score calculated");
        return ratingRepository.findAverageScoreByFilmId(filmId);
    }
}
