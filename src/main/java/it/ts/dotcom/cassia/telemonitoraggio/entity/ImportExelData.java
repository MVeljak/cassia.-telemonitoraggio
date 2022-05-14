package it.ts.dotcom.cassia.telemonitoraggio.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

public class ImportExelData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID uuid;


    @Column(name = "ID")
    private Long id;

    @Column(name = "Anno")
    private String anno;

    @Column(name = "Mese")
    private String mese;

    @Column(name = "Ruolo")
    private String ruolo;

    @Column(name = "nome_intervistatore")
    private String nomeIntervistatore;
    @Column(name = "nome_intervistato")
    private String nomeIntervistato;



    @Column(name = "cognome_intervistatore")
    private String cognomeIntervistato;

    @Column(name = "telefono_intervistatore")
    private String telefonoIntervistato;

    @Column(name = "data_di_nascita_intervistato")
    private String dataDiNascitaIntervistato;
    @Column(name = "sesso_intevistato")
    private String sessoIntervistato;
    @Column(name = "via_intervistato")
    private String viaIntervistato;
    @Column(name = "civico_intevistato")
    private String civicoIntervistato;
    @Column(name = "cap_intervistato")
    private String capIntervistato;

    @Column(name = "comune_intervistato")
    private String comuneIntervistato;
    @Column(name = "cod_comune_istat")
    private String codComuneISTAT;

    @Column(name = "distretto_intevistato")
    private String distrettoIntervistato;

    @Column(name = "nome_medico")
    private String nomeMedico;



    @Column(name = "cognome_medico")
    private String cognomeMedico;

    @Column(name = "telefono_medico")
    private String telefonoMedico;


    @Column(name = "via_ambulatorio")
    private String viamedico;
    @Column(name = "civico_ambulatorio")
    private String civicomedico;
    @Column(name = "cap_medico")
    private String capMedico;

    @Column(name = "comune_ambulatorio")
    private String comuneAmbulatorio;

    @Column(name = "nota")
    private String nota;

    @Column(name = "nota_x_intervistatore")
    private String notaXIntervistatore;

    @Column(name = "istat_nascita")
    private String istatNascita;

    @Column(name = "istat_cittadinanza")
    private String istatCittadinanza;



}
