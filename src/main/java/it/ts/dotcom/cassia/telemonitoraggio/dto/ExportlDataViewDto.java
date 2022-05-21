package it.ts.dotcom.cassia.telemonitoraggio.dto;

import it.ts.dotcom.cassia.telemonitoraggio.utils.RuoloEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class  ExportlDataViewDto implements Serializable {
    private UUID id;
    private BigDecimal idNominativo;
    private String nome;
    private String cognome;
    private Date dataDiNascita;
    private String indirizzo;
    private String azienda;
    private String mese;
    private String nr;
    private Long estrazione;
    private String ruolo;
    private String telefono;
    private Instant dataITentativo;
    private Instant dataIiTentativo;
    private Instant dataIiiTentativo;
    private Instant dataIvTentativo;
    private Instant dataVTentativo;
    private Instant dataViTentativo;
    private Instant tentativoDelSabato;
    private Instant tentativiAggiuntivi;
    private Instant tentativoSerale;
    private String sostituzioni;
    private String noteChiamata;
    private String noteTl;
    private String esitoChiamata;
    private String operatore;
}
