package com.engtechnologie.microcredit.sample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/sample")
interface SampleApi {

    @GetMapping("/public/ping")
    String ping();
}
