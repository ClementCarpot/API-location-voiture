package com.accenture.dal.entity.vehicules;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "VELOS")
public class Velo extends Vehicule {

    @NotNull
    private String tailleCadre;

    @NotNull
    private int poidsKg;

    private boolean isElectrique;

    private Batterie batterie;

    private boolean freinsDisque;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeVelo typeVelo;

    public Velo(String marque, String modele, String couleur, double tarifJournalier, int kilometrage, boolean actif, boolean retireDuParc, String tailleCadre, int poidsKg, boolean isElectrique, Batterie batterie, boolean freinsDisque, TypeVelo typeVelo) {
        super(marque, modele, couleur, tarifJournalier, kilometrage, actif, retireDuParc);
        this.tailleCadre = tailleCadre;
        this.poidsKg = poidsKg;
        this.isElectrique = isElectrique;
        this.batterie = batterie;
        this.freinsDisque = freinsDisque;
        this.typeVelo = typeVelo;
    }




}
