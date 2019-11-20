package com.cscinfo.solr.api.controller;

import com.cscinfo.solr.api.model.Entity;
import com.cscinfo.solr.api.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Entity> getEntityById(@PathVariable Integer id) {
        long starttime = System.currentTimeMillis();
        List<Entity> byOrgPartyId = repository.findByOrgPartyId(id);

        long endtime = System.currentTimeMillis();
        System.err.println("fetch orgpartyid all = "+((endtime - starttime)/1000));

        return byOrgPartyId;
    }
}
