package it.ts.dotcom.cassia.telemonitoraggio.entity.export;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "additional_fiel_export")
public class AdditionalFielExport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @OneToMany(mappedBy = "additionalFielExport", orphanRemoval = true)
    private Set<NewExportDataView> newExportDataViews = new LinkedHashSet<>();

    public Set<NewExportDataView> getNewExportDataViews() {
        return newExportDataViews;
    }

    public void setNewExportDataViews(Set<NewExportDataView> newExportDataViews) {
        this.newExportDataViews = newExportDataViews;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}