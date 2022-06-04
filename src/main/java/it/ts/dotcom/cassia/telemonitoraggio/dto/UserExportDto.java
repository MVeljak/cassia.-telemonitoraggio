package it.ts.dotcom.cassia.telemonitoraggio.dto;

import it.ts.dotcom.cassia.telemonitoraggio.entity.export.UserExport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserExportDto {
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
    private String telefono;
    private String ruolo;


    public static UserExportDto newIstance() {
        return new UserExportDto();
    }


    public static UserExportDto fromEntity(UserExport userExportDto) {
        return new UserExportDto();
    }
}
