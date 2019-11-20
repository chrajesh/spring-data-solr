package com.cscinfo.solr.api;

import com.cscinfo.solr.api.controller.EntitySearchController;
import com.cscinfo.solr.api.model.Entity;
import com.cscinfo.solr.api.repository.EntityRepository;
import com.sun.org.apache.xpath.internal.operations.Bool;
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
import sun.util.resources.LocaleData;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
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


    String[] juris = {"AK", "AL", "AR", "AS", "AZ", "CA", "CO", "CT", "DC", "DE", "FL", "GA", "GU", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MI", "MN", "MO", "MP", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UM", "UT", "VA", "VI", "VT", "WA", "WI", "WV", "WY"};

    String[] status = {"ACTIVE", "INACTIVE"};
    String[] entityTypes = {"CSC", "NONCSC"};
    String[] cscStatus = {"CSC", "NONCSC"};
    Boolean[] international = {true, false};

    @Bean
    public CommandLineRunner commandLineRunner(@Autowired EntityRepository repository,
                                               @Autowired SolrTemplate solrTemplate) {
        return args -> {
            repository.deleteAll();

            Random random = new Random();

            long starttime = System.currentTimeMillis();
            IntStream.range(0, 10_000).forEach(index ->
                    repository.save(
                            Entity.builder()
                                    .id("" + index)
                                    .name("Entity name "+index)
                                    .cscStatus(cscStatus[random.nextInt(cscStatus.length)])
                                    .annualMeetingDate(between(Instant.now().minus(Duration.ofDays(2 * 365)), Instant.now()).toString())
                                    .fiscalYearEnd(between(Instant.now().minus(Duration.ofDays(2 * 365)), Instant.now()).toString())
                                    .entityType(entityTypes[random.nextInt(entityTypes.length)])
                                    .international(international[random.nextInt(international.length)])
                                    .jurisdiction(juris[random.nextInt(juris.length)])
                                    .entityStatus(status[random.nextInt(status.length)])
                                    .entityPartyId(random.nextInt(1000))
                                    .orgPartyId(random.nextInt(5))
                                    .build()
                    )
            );
            System.err.println("stored data");
            long endtime = System.currentTimeMillis();
            System.err.println("store all = "+((endtime - starttime)/1000));



            starttime = System.currentTimeMillis();
            repository.findAll().forEach(System.out::println);
            endtime = System.currentTimeMillis();
            System.err.println("fetch all =  "+((endtime - starttime)/1000));

            /*System.err.println("---------------------------------------");

            solrTemplate.queryForCursor("entity_collection", new SimpleQuery("*:*").addSort(Sort.by("id")),
                    Entity.class).forEachRemaining(System.out::println);*/
        };
    }

    public Instant between(Instant startInclusive, Instant endExclusive) {
        //between(Instant.now().minus(Duration.ofDays(2 * 365)), Instant.now())
        long startSeconds = startInclusive.getEpochSecond();
        long endSeconds = endExclusive.getEpochSecond();
        long random = ThreadLocalRandom
                .current()
                .nextLong(startSeconds, endSeconds);

        return Instant.ofEpochSecond(random);
    }
}
