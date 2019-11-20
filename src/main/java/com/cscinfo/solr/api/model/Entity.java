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
    @Indexed
    private String description;
    @Indexed
    private String entityType;
    @Indexed
    private String jurisdiction;
    @Indexed
    private String fiscalYearEnd;
    @Indexed
    private boolean international;
    @Indexed
    private String cscStatus;
    @Indexed
    private String businessPurpose;
    @Indexed
    private String legalStructure;
    @Indexed
    private String annualMeetingDate;
    @Indexed
    private String entityStatus;
    @Indexed
    private String cscIndicator;
    @Indexed
    private Integer entityPartyId;
    @Indexed
    private Integer orgPartyId;


}

