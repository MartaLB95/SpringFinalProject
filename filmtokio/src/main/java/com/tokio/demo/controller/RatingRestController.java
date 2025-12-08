package com.tokio.demo.controller;


import com.tokio.demo.domain.Film;
import com.tokio.demo.domain.Rating;
import com.tokio.demo.domain.User;
import com.tokio.demo.service.impl.FilmServiceImpl;
import com.tokio.demo.service.impl.RatingServiceImpl;
import com.tokio.demo.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/ratings")
public class RatingRestController {

    private static final Logger logger = LoggerFactory.getLogger(RatingRestController.class);

    private final RatingServiceImpl ratingServiceImpl;
    private final UserServiceImpl userServiceImpl;
    private final FilmServiceImpl filmServiceImpl;

    public RatingRestController(RatingServiceImpl ratingServiceImpl, UserServiceImpl userServiceImpl, FilmServiceImpl filmServiceImpl) {
        this.ratingServiceImpl = ratingServiceImpl;
        this.userServiceImpl = userServiceImpl;
        this.filmServiceImpl = filmServiceImpl;
    }

    @GetMapping
    public List<Rating> getAll() {
        logger.info("GET /api/ratings/ called");
        return ratingServiceImpl.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Rating> getById(@PathVariable Long id) {
        logger.info("GET /api/ratings/{} called", id);
        return ratingServiceImpl.findById(id);
    }

    @PostMapping
    public Rating post(@RequestBody Rating rating, Principal principal) {
        logger.info("POST /api/ratings/ called");
        User user = userServiceImpl.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Film film = filmServiceImpl.findById(rating.getFilm().getId());
        rating.setFilm(film);
        rating.setUser(user);
        rating.setCreatedAt(LocalDateTime.now());
        return ratingServiceImpl.save(rating);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        logger.info("DELETE /api/ratings/{} called", id);
        ratingServiceImpl.delete(id);
        logger.info("Rating deleted with id {}", id);
    }

}