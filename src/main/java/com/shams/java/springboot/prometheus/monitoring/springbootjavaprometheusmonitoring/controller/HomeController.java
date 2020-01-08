package com.shams.java.springboot.prometheus.monitoring.springbootjavaprometheusmonitoring.controller;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import io.prometheus.client.exporter.common.TextFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.Writer;

@Controller
public class HomeController {

    private Counter requestCounter = Counter.build()
            .name("request_to_index")
            .help("Total number of requests to /index URL")
            .register();

    @RequestMapping("/index")
    public @ResponseBody
    String index() {
        requestCounter.inc();
        return "Hello world";
    }

    @RequestMapping("/metrics")
    public void metrics(Writer responseWriter) throws IOException {
        TextFormat.write004(responseWriter, CollectorRegistry.defaultRegistry.metricFamilySamples());
        responseWriter.close();
    }
}
