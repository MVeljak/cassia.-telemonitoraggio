package it.ts.dotcom.cassia.telemonitoraggio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EsitoExportDto implements Serializable {
    private UUID id;
    private Instant dataTentativo;
    private String sostituzioni;
    private String noteChiamata;
    private String noteTl;
    private String esitoChiamata;
    private String operatore;
}
