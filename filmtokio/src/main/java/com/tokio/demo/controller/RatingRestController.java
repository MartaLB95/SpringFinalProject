package com.tokio.demo.controller;


import com.tokio.demo.domain.Film;
import com.tokio.demo.domain.Rating;
import com.tokio.demo.service.impl.RatingServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/ratings")
public class RatingRestController {

    private static final Logger logger = LoggerFactory.getLogger(RatingRestController.class);

    private final RatingServiceImpl ratingServiceImpl;

    public RatingRestController(RatingServiceImpl ratingServiceImpl) {
        this.ratingServiceImpl = ratingServiceImpl;
    }

    @GetMapping
    public List<Rating> getAll(){
        logger.info("GET /api/ratings/ called");
        return ratingServiceImpl.findAll();
    }

    @GetMapping ("/{id}")
    public Optional <Rating> getById(@PathVariable Long id){
        logger.info("GET /api/ratings/{} called", id);
        return ratingServiceImpl.findById(id);
    }

    @PostMapping
    public Rating post (@RequestBody Rating rating){
        logger.info("POST /api/ratings/ called");
        return ratingServiceImpl.save(rating);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        logger.info("DELETE /api/ratings/{} called", id);
        ratingServiceImpl.delete(id);
        logger.info("Rating deleted with id {}", id);
    }

}