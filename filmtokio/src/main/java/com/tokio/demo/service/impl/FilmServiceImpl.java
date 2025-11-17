package com.tokio.demo.service.impl;

import com.tokio.demo.domain.Film;
import com.tokio.demo.repository.FilmRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Lazy(true)
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Transactional(readOnly=true)
    @Override
    public List<Film> findAll(){
      return filmRepository.findAll();
    }

    @Override
    public Film findById(Long id) {
        return filmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Film with ID " + id + " not found"));
    }

    @Override
    public Film save(Film film){
        return filmRepository.save(film);
    }

    public List <Film> findByTitleContaining(String titleFragment){
        return filmRepository.findByTitleContaining(titleFragment);
    }

}
