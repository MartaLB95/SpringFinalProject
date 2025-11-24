package com.tokio.demo.service.impl;

import com.tokio.demo.controller.RatingRestController;
import com.tokio.demo.domain.Actor;
import com.tokio.demo.repository.ActorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Lazy(true) //para que solo se ejecute cuando sea necesario
public class ActorServiceImpl implements ActorService {

    private static final Logger logger = LoggerFactory.getLogger(ActorServiceImpl.class);

    private final ActorRepository actorRepository;


    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Transactional(readOnly=true)
    @Override
    public List<Actor> findAll(){
        return actorRepository.findAll();
    }

    @Override
    public Actor save(Actor actor){
        return actorRepository.save(actor);
    }

    @Override
    public Optional <Actor> findById(Long id){
        return actorRepository.findById(id);
    }


}
