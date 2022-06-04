package it.ts.dotcom.cassia.telemonitoraggio.entity.export;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "esito_export")
public class EsitoExport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "data_tentativo")
    private Instant dataTentativo;

    @Column(name = "sostituzioni")
    private String sostituzioni;

    @Column(name = "note_chiamata")
    private String noteChiamata;

    @Column(name = "note_tl")
    private String noteTl;

    @Column(name = "esito_chiamata")
    private String esitoChiamata;

    @Column(name = "operatore")
    private String  operatore;

    @ManyToOne
    @JoinColumn(name = "data_view_id")
    private NewExportDataView newExportDataView;


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