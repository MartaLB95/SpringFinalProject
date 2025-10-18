package com.tokio.demo.controller;

import com.tokio.demo.domain.Actor;
import com.tokio.demo.service.impl.ActorServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/actors")
@Controller
public class ActorController {

    private final ActorServiceImpl actorServiceImpl;

    public ActorController(ActorServiceImpl actorServiceImpl) {
        this.actorServiceImpl = actorServiceImpl;
    }

    @GetMapping
    public String getAll(){
        return "ok get";
    }

    @GetMapping ("/{id}")
    public String getById(@PathVariable Long id){
        return "ok get by id";
    }

    @PostMapping
    public String post (Actor actor){
        return "ok post";
    }


}
