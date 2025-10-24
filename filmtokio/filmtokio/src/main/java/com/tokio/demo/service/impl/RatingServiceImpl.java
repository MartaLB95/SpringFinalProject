package com.tokio.demo.service.impl;

import com.tokio.demo.domain.Rating;
import com.tokio.demo.repository.RatingRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Lazy(true)
@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

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
        return ratingRepository.findById(id);
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
