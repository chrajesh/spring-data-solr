package com.cscinfo.solr.api.controller;

import com.cscinfo.solr.api.model.Entity;
import com.cscinfo.solr.api.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EntitySearchController {

    @Autowired
    private EntityRepository repository;

    @GetMapping("/entities")
    public Iterable<Entity> getEntities() {
        return repository.findAll();
    }

    @GetMapping("/entities/{id}")
    public Entity getEntityByName(@PathVariable String id) {
        return repository.findById(id).get();
    }
}
