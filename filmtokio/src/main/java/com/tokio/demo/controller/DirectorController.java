package com.tokio.demo.controller;

import com.tokio.demo.domain.Director;
import com.tokio.demo.service.impl.DirectorServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/directors")

public class DirectorController {
    private final DirectorServiceImpl directorServiceImpl;

    public DirectorController(DirectorServiceImpl directorServiceImpl) {
        this.directorServiceImpl = directorServiceImpl;
    }

    @GetMapping
    public String getAll(){
        return "ok get";
    }

    @GetMapping ("/{id}")
    public String getById(@PathVariable Long id){
        return "ok get id";
    }

    @PostMapping
    public String post(Director director){
        return "ok post";
    }

}
