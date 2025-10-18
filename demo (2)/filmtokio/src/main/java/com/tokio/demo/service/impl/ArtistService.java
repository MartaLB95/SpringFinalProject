package com.tokio.demo.service.impl;

import com.tokio.demo.domain.Artist;

import java.util.List;
import java.util.Optional;

public interface ArtistService {
    List<Artist> findAll();

    Optional<Artist> findById(Long id);

    Artist save(Artist artist);

}
