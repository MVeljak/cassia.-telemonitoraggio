package it.ts.dotcom.cassia.telemonitoraggio.utils;

import lombok.Data;

import java.util.Map;

@Data
public class ExsportHeaderPassi {

    private Map<Integer, String> importExel;
    private Map<Integer, String> export;
}
