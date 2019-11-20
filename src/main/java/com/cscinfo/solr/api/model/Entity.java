package com.cscinfo.solr.api.model;

import lombok.*;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.Score;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.List;

@Value
@Builder
@SolrDocument(collection = "entity_collection")
public class Entity {
	@Id
	private String id;
	@Indexed
	private String name;
	@Indexed(name = "cat")
	private List<String> category;
	@Indexed(name = "store")
	private Point location;
	@Indexed
	private String description;
	@Indexed
	private boolean inStock;
	@Indexed
	private Integer popularity;
	@Score
	private Float score;
}

