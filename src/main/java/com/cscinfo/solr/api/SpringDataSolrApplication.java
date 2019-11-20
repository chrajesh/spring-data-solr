package com.cscinfo.solr.api;

import com.cscinfo.solr.api.controller.EntitySearchController;
import com.cscinfo.solr.api.model.Entity;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.SimpleQuery;

import java.util.stream.IntStream;

@SpringBootApplication
public class SpringDataSolrApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataSolrApplication.class, args);
    }


    @Value("${spring.data.solr.host}")
    private String solrUrl;

    @Bean
    public SolrTemplate solrTemplate() {
        return new SolrTemplate(new HttpSolrClient.Builder().withBaseSolrUrl(solrUrl).build());
    }


    @Bean
    public CommandLineRunner commandLineRunner(@Autowired CrudRepository<Entity, String> repository,
                                               @Autowired SolrTemplate solrTemplate) {
        return args -> {
            repository.deleteAll();

            IntStream.range(0, 10).forEach(index ->
                    repository.save(
                            Entity.builder().id("p-" + index).name("ANWAR ALAM").build()
                    )
            );


            repository.findAll().forEach(System.out::println);

            System.err.println("---------------------------------------");

            solrTemplate.queryForCursor("entity_collection", new SimpleQuery("*:*").addSort(Sort.by("id")),
                    Entity.class).forEachRemaining(System.out::println);
        };
    }
}
