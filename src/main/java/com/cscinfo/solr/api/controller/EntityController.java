package com.cscinfo.solr.api.controller;

import com.cscinfo.solr.api.model.Entity;
import com.cscinfo.solr.api.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EntityController {

	@Autowired
	private EntityRepository repository;

	@PostConstruct
	public void addEntitys() {
		List<Entity> entitys = new ArrayList<>();

		entitys.add(new Entity(373, "Basant", new String[] { "Bangalore", "BTM" }));
		entitys.add(new Entity(908, "Santosh", new String[] { "Hyderbad", "XYZ" }));
		entitys.add(new Entity(321, "Sagar", new String[] { "Pune", "PQR" }));
		repository.saveAll(entitys);
	}

	@GetMapping("/getALL")
	public Iterable<Entity> getEntitys() {
		return repository.findAll();
	}

	@GetMapping("/getEntity/{name}")
	public Entity getEntityByName(@PathVariable String name) {
		return repository.findByName(name);
	}

}
