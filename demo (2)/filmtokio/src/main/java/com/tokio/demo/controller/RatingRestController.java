package com.tokio.demo.controller;


import com.tokio.demo.domain.Film;
import com.tokio.demo.domain.Rating;
import com.tokio.demo.service.impl.RatingServiceImpl;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/ratings")
public class RatingRestController {

    private final RatingServiceImpl ratingServiceImpl;

    public RatingRestController(RatingServiceImpl ratingServiceImpl) {
        this.ratingServiceImpl = ratingServiceImpl;
    }


    @GetMapping
    public List<Rating> getAll(){
        return ratingServiceImpl.findAll();
    }

    @GetMapping ("/{id}")
    public Optional <Rating> getById(@PathVariable Long id){
        return ratingServiceImpl.findById(id);
    }

    @PostMapping
    public Rating post (@RequestBody Rating rating){
        return ratingServiceImpl.save(rating);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        ratingServiceImpl.delete(id);
    }

}