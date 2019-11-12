package com.cscinfo.solr.api.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;
import com.cscinfo.solr.api.model.Entity;
public interface EntityRepository extends SolrCrudRepository<Entity, Integer>{

	Entity findByName(String name);

}
