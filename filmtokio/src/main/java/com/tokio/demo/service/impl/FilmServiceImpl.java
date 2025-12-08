package com.tokio.demo.service.impl;

import com.tokio.demo.domain.Film;
import com.tokio.demo.repository.FilmRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Lazy(true)
public class FilmServiceImpl implements FilmService {

    private static final Logger logger = LoggerFactory.getLogger(FilmServiceImpl.class);

    private final FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Film> findAll() {
        logger.info("Service: fetching all films");
        return filmRepository.findAll();
    }

    @Override
    public Film findById(Long id) {
        logger.info("Service: fetching film with id {} ", id);
        return filmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Film with ID " + id + " not found"));
    }

    @Override
    public Film save(Film film) {
        logger.info("Film created");
        return filmRepository.save(film);
    }

    public List<Film> findByTitleContaining(String titleFragment) {
        logger.info("Search films with title containing {}", titleFragment);
        return filmRepository.findByTitleContaining(titleFragment);
    }

}
