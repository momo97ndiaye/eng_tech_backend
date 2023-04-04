package com.engtechnologie.microcredit.payment;

import feign.Logger;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

@FeignClient(name = "orange-money-client", url = "${app.rest.client.om.url}", configuration = OrangeMoneyClient.FeignConfig.class)
interface OrangeMoneyClient {

    @PostMapping("/api/eWallet/v1/cashins")
    void cashIn(@RequestBody CashInRequest cashInRequest);

    @PostMapping("/api/eWallet/v1/bulkcashins")
    void bulkCashIn(@RequestBody Collection<CashInRequest> bulkCashInRequest, @RequestHeader("X-Callback-Url") String callbackUrl);


    record CashInRequest(Partner partner, Customer customer, Amount amount) {
    }

    record Amount(String unit, BigDecimal value) {
    }

    record Customer(String idType, String id) {
    }

    record Partner(String idType, String id, String encryptedPinCode) {
    }

    @RequiredArgsConstructor
    class FeignConfig {

        private static final String CLIENT_REGISTRATION_ID = "orange-money";

        private final OAuth2AuthorizedClientManager manager;

        @Bean
        RequestInterceptor requestInterceptor() {
            return requestTemplate -> requestTemplate.header("Authorization", "Bearer " + getAccessToken());
        }

        @Bean
        Logger.Level loggerLevel() {
            return Logger.Level.FULL;
        }

        String getAccessToken() {

            var oAuth2AuthorizeRequest = OAuth2AuthorizeRequest
                    .withClientRegistrationId(CLIENT_REGISTRATION_ID)
                    .principal(CLIENT_REGISTRATION_ID)
                    .build();

            return Optional.ofNullable(manager.authorize(oAuth2AuthorizeRequest))
                    .map(OAuth2AuthorizedClient::getAccessToken)
                    .map(OAuth2AccessToken::getTokenValue)
                    .orElseThrow(() -> new IllegalStateException("client credentials flow on " + CLIENT_REGISTRATION_ID + " failed"));
        }
    }
}