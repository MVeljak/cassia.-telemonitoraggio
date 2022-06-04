package it.ts.dotcom.cassia.telemonitoraggio.entity.export;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "additional_fiel_export")
public class AdditionalFielExport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;


    @ManyToOne
    @JoinColumn(name = "data_view_id")
    private NewExportDataView newExportDataView;

    @Column(name = "name_field")
    private String nameField;

    @Column(name = "type_field")
    private String typeField;

    @Column(name = "value_field")
    private String valueField;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AdditionalFielExport that = (AdditionalFielExport) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}