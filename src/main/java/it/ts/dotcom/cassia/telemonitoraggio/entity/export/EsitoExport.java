package it.ts.dotcom.cassia.telemonitoraggio.entity.export;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "esito_export")
public class EsitoExport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @OneToMany(mappedBy = "esitoExport", orphanRemoval = true)
    private Set<NewExportDataView> newExportDataViews = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "data_view_id")
    private NewExportDataView newExportDataView;

    public NewExportDataView getNewExportDataView() {
        return newExportDataView;
    }

    public void setNewExportDataView(NewExportDataView newExportDataView) {
        this.newExportDataView = newExportDataView;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EsitoExport that = (EsitoExport) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}