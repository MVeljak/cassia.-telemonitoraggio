package it.ts.dotcom.cassia.telemonitoraggio.entity.utenza;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "indirizzo")
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(name = "via")
    private String via;
    @Column(name = "civico")
    private String civico;
    @Column(name = "cap")
    private String cap;
    @Column(name = "comune")
    private String comune;
    @Column(name = "cod_comune_istat")
    private String codComuneISTAT;

}