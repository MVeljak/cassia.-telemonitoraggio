package it.ts.dotcom.cassia.telemonitoraggio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewExportDataViewDto implements Serializable {
    private UUID id;
    private UserExportDto userExport;
    private Set<EsitoExportDto> esitoExports = new LinkedHashSet<>();
    private Set<AdditionalFielExportDto> additionalFielExports = new LinkedHashSet<>();
}
