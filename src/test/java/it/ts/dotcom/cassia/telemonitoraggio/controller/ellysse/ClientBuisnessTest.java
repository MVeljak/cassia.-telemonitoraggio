package it.ts.dotcom.cassia.telemonitoraggio.controller.ellysse;

import it.ts.dotcom.cassia.telemonitoraggio.controller.ellysse.request.PraticeRequest;
import it.ts.dotcom.cassia.telemonitoraggio.controller.ellysse.response.PratceResponse;
import it.ts.dotcom.cassia.telemonitoraggio.controller.ellysse.response.TockenResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
@SpringBootTest
class ClientBuisnessTest {

    @Autowired
    ClientService clientService;



    @BeforeEach
    void setUp() {
    }

    @Test
    void getTocken() {

        Mono<TockenResponse> tocken = clientService.getTocken();

//        System.out.println(tocken.block());



       // System.out.println(response);


    }

    @Test
    void  create_request_for_insert_ptatice(){

        PraticeRequest request = new PraticeRequest();

        request.setComment("commento");
        request.setIdCampaign(UUID.randomUUID().toString());
        request.setSegmento("segmento");

        Mono<PratceResponse> response = clientService.createPratice(request);
        response.doOnSuccess(System.out::println);
    }

    @Test
    void  create_request_for_insert_ptatice2(){

        PraticeRequest request = new PraticeRequest();

        request.setComment("commento");
        request.setIdCampaign(UUID.randomUUID().toString());
        request.setSegmento("segmento");

        ResponseEntity<String> pratice2 = clientService.createPraticeResponseEntity(request);

        System.out.println(pratice2.getStatusCode());
        System.out.println(pratice2.getBody());
    }
}