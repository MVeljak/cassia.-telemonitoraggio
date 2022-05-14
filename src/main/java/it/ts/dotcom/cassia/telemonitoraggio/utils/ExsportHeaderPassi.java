package it.ts.dotcom.cassia.telemonitoraggio.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Data
public class ExsportHeaderPassi {

    private Map<Integer, String> importExel;
    private Map<Integer, String> export;
}
