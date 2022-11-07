package com.github.aushacker.otel.spring.controller;

import com.github.aushacker.otel.spring.model.DummyData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    public String hello() {
        return "Hello world";
    }

    @GetMapping("/hello/data")
    public DummyData data() throws Exception {
        RestTemplate template = new RestTemplateBuilder().build();
        DummyData data = template.getForObject("http://openliberty.aaa-otel-apps.svc.cluster.local:9080/dbtest/data", DummyData.class);
        log.info(data.toString());
        return data;
    }
}
