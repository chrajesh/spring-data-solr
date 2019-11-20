package com.cscinfo.solr.api.repository;

import com.cscinfo.solr.api.model.Entity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.repository.Boost;
import org.springframework.data.solr.repository.Highlight;
import org.springframework.data.solr.repository.Query;

import java.util.List;

public interface EntityRepository extends EntityRepositoryCustom, CrudRepository<Entity, String> {

    /**
     * Find documents with matching description, highlighting context within a 20 char range around the hit.
     */
    @Highlight(fragsize = 20, snipplets = 3)
    HighlightPage<Entity> findByDescriptionStartingWith(String description, Pageable page);

    /**
     * Find the first 10 documents with a match in name or description. Boosting score for search hits in name by 2 sorts
     * documents by relevance.
     */
    @Query
    List<Entity> findTop10ByNameOrDescription(@Boost(2) String name, String description);
}
