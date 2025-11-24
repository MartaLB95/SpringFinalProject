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

    @Transactional(readOnly=true)
    @Override
    public List<Rating> findAll(){
       return ratingRepository.findAll();
    }

    @Override
    public Optional<Rating> findById(Long id){
        return Optional.ofNullable(ratingRepository.findById(id).orElseThrow(() -> new RatingNotFoundException("Rating with ID " + id + " not found")));
    }

    @Override
    public Rating save(Rating rating){
        return ratingRepository.save(rating);
    }

    @Override
    public void delete(Long id){
        if(ratingRepository.existsById(id)){
            ratingRepository.deleteById(id);
        }
    }

    public double findAverageScoreByFilmId(Long filmId){
        return ratingRepository.findAverageScoreByFilmId(filmId);
    }
}
