package com.example.demo.controllers;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/hello")
public class HelloController {
  private static final Logger log = LoggerFactory.getLogger(HelloController.class);

  @GetMapping("/world")
  public String hello() {
    return "Hello World";
  }

  @PostMapping("/name")
  public String getMethodName(@RequestParam String param) {
    return "Hello " + param;
  }

  @PostMapping("/mono")
  public Mono<String> getMonoName(@RequestParam String param) {
    return Mono.just("Hello Mono " + param);
  }

  @PostMapping("/flux")
  public Flux<String> getFlux(@RequestParam String param) {
    long startTime = System.currentTimeMillis();
    return Flux.just("Hello Flux", param, param).delayElements(Duration.ofSeconds(1))
        .doFinally(signal -> {
          long endTime = System.currentTimeMillis();
          log.info("Time taken to process the flux: {} ms", (endTime - startTime));
        });
  }
}
