package it.ts.dotcom.cassia.telemonitoraggio.entity.export;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "new_export_data_view")
@NoArgsConstructor
public class NewExportDataView {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_export_id")
    private UserExport userExport;

    @OneToMany(mappedBy = "newExportDataView", orphanRemoval = true)
    private Set<EsitoExport> esitoExports = new LinkedHashSet<>();

    @OneToMany(mappedBy = "newExportDataView", orphanRemoval = true)
    private Set<AdditionalFielExport> additionalFielExports = new LinkedHashSet<>();

}