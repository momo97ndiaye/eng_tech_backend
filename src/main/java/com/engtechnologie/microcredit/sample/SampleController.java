package com.engtechnologie.microcredit.sample;

import org.springframework.web.bind.annotation.RestController;

@RestController
class SampleController implements SampleApi {

    @Override
    public String ping() {
        return "Response from a public endpoint";
    }
}
