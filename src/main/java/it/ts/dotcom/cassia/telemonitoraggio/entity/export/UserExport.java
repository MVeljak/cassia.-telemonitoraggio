package it.ts.dotcom.cassia.telemonitoraggio.entity.export;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
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
@Table(name = "user_export")
public class UserExport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "data_view_id")
    private NewExportDataView newExportDataView;

    @Column(name = "id_nominativo", unique = true, precision = 19, scale = 2)
    private BigDecimal idNominativo;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "data_di_nascita")
    private Date dataDiNascita;

    @Column(name = "indirizzo")
    private String indirizzo;

    @Column(name = "azienda", length = 20)
    private String azienda;

    @Column(name = "mese", length = 20)
    private String mese;

    @Column(name = "nr", length = 20)
    private String nr;

    @Column(name = "estrazione")
    private Long estrazione;

    @Column(name = "telefono", length = 40)
    private String telefono;

    @Column(name = "ruolo", length = 40)
    private String ruolo;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserExport that = (UserExport) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}