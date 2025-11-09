package com.tokio.demo.repository;

import com.tokio.demo.domain.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    //Para buscar títulos que contienen el texto
    List<Film> findByTitleContaining(String titleFragment);
}
