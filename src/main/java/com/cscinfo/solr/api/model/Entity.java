package com.cscinfo.solr.api.model;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SolrDocument(collection = "entity")
public class Entity {
	@Id
	@Field
	private int id;
	@Field
	private String name;
	@Field
	private String[] address;

}
