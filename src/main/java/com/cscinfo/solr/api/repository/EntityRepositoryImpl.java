package com.cscinfo.solr.api.repository;

import com.cscinfo.solr.api.model.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.Cursor;

public class EntityRepositoryImpl implements EntityRepositoryCustom {

    @Value("${solr.core.collection.entity}")
    private String solrCollection;

    @Autowired
    private SolrTemplate solrTemplate;

    @Override
    public Cursor<Entity> findAllUsingCursor() {

        // NOTE: Using Cursor requires to sort by an unique field
        return solrTemplate.queryForCursor(solrCollection, new SimpleQuery("*:*").addSort(Sort.by("id")), Entity.class);
    }
}
