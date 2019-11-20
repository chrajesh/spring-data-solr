package com.cscinfo.solr.api.repository;

import com.cscinfo.solr.api.model.Entity;
import org.springframework.data.solr.core.query.result.Cursor;

interface EntityRepositoryCustom {

    /**
     * Use a {@link Cursor} to scroll through documents in index. <br />
     * <strong>NOTE:</strong> Requires at least Solr 4.7.
     *
     * @return
     */
    Cursor<Entity> findAllUsingCursor();
}
