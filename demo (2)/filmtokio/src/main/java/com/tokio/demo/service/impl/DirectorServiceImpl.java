package com.tokio.demo.service.impl;

import com.tokio.demo.domain.Director;
import com.tokio.demo.repository.DirectorRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Lazy(true)
@Service
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;

    public DirectorServiceImpl(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @Transactional(readOnly=true)
    @Override
    public List<Director> findAll(){
        return directorRepository.findAll();
    }

    @Override
    public Optional<Director> findById(Long id){
        return directorRepository.findById(id);
    }

    @Override
    public Director save(Director director){
        return directorRepository.save(director);
    }

}
