package it.ts.dotcom.cassia.telemonitoraggio.entity.utenza;

import it.ts.dotcom.cassia.telemonitoraggio.entity.utenza.Indirizzo;
import it.ts.dotcom.cassia.telemonitoraggio.utils.RuoloEnum;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "intervistato")
public class Intervistato {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uuid", nullable = false)
    private UUID uuid;

    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ruolo")
    private RuoloEnum ruoloEnum;

    @Column(name = "onme")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "data_di_nascita")
    private String dataDiNascita;
    @Column(name = "sesso")
    private String sesso;

    @Column(name = "distretto_intevistato")
    private String distrettoIntervistato;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "indirizzo_id")
    private Indirizzo indirizzo;

}

