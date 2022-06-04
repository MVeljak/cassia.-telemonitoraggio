package it.ts.dotcom.cassia.telemonitoraggio.controller.ellysse;

import it.ts.dotcom.cassia.telemonitoraggio.controller.ellysse.request.PraticeRequest;
import it.ts.dotcom.cassia.telemonitoraggio.controller.ellysse.response.PratceResponse;
import it.ts.dotcom.cassia.telemonitoraggio.controller.ellysse.response.TockenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ClientService {

    @Autowired
    private WebClient webclient;

    @Value("${ellysse.request.tocken}")
    private String TOCKEN_REQUEST;

    @Value("${ellysse.request.insert.pratice}")
    private String INSERT_PRATICE_REQUEST;


    public Mono<TockenResponse> getTocken() {
        return webclient.post()
                .uri(TOCKEN_REQUEST)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("x-username","admin")
                .header("x-society","dotcom")
                .body(Mono.empty(), String.class)
                .retrieve()
                .bodyToMono(TockenResponse.class);
    }

    public Mono<PratceResponse> createPratice(PraticeRequest pratica){
        return webclient.post()
                .uri(INSERT_PRATICE_REQUEST)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("x-auth", "tocken1234")
                .body(Mono.just(pratica), PraticeRequest.class)
                .retrieve()
                .bodyToMono(PratceResponse.class);
    }

    public ResponseEntity<String> createPraticeResponseEntity(PraticeRequest pratice){
        return webclient.post()
                .uri(INSERT_PRATICE_REQUEST)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("x-auth", "tocken1234")
                .body(Mono.just(pratice), PraticeRequest.class)
                .retrieve()
                .onStatus(
                        status -> status.value() == 404,
                        clientResponse -> Mono.empty()
                )
                .onStatus(
                         httpStatus -> httpStatus.value() == 500,
                        clientResponse -> Mono.empty())
                .toEntity(String.class).block();
    }


}
