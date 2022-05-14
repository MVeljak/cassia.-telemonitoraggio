package it.ts.dotcom.cassia.telemonitoraggio.entity.export;

import it.ts.dotcom.cassia.telemonitoraggio.utils.RuoloEnum;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "export_data_view")
public class ExportlDataView {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

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

//    @Enumerated(EnumType.STRING)
//    @Column(name = "ruolo_enum")
//    private RuoloEnum ruoloEnum;

    @Column(name = "telefono", length = 40)
    private String telefono;

    @Column(name = "ruolo", length = 40)
    private String ruolo;

    @Column(name = "data_tentativo_1")
    private Instant dataTentativo1;
    @Column(name = "data_tentativo_2")
    private Instant dataTentativo2;
    @Column(name = "data_tentativo_3")
    private Instant dataTentativo3;
    @Column(name = "data_tentativo_4")
    private Instant dataTentativo4;
    @Column(name = "data_tentativo_5")
    private Instant dataTentativo5;
    @Column(name = "data_tentativo_6")
    private Instant dataTentativo6;

    @Column(name = "tentativo_del_sabato")
    private Instant tentativoDelSabato;

    @Column(name = "tentativi_aggiuntivi")
    private Instant tentativiAggiuntivi;

    @Column(name = "tentativo_serale")
    private Instant tentativoSerale;

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

}