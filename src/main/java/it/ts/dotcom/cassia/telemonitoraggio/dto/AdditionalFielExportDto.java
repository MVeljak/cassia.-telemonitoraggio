package it.ts.dotcom.cassia.telemonitoraggio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalFielExportDto implements Serializable {
    private UUID id;
    private String nameField;
    private String typeField;
    private String valueField;
}
