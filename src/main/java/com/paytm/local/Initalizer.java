package com.paytm.local;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Slf4jReporter;
import com.paytm.local.datasource.model.User;
import com.paytm.local.datasource.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.PostLoad;
import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

@Component
public class Initalizer {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MetricRegistry metricRegistry;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init(){
        //System.out.println(dataSource.toString());

//        final Slf4jReporter reporter = Slf4jReporter.forRegistry(metricRegistry)
//                .outputTo(LoggerFactory.getLogger(this.getClass()))
//                .convertRatesTo(TimeUnit.SECONDS)
//                .convertDurationsTo(TimeUnit.MILLISECONDS)
//                .build();

        //reporter.start(1, TimeUnit.MINUTES);

    }
}
