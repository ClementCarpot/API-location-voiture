package com.accenture.dal.entity.vehicules;

import com.accenture.dal.entity.Permis;
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
@Table(name = "UTILITAIRES")
public class Utilitaire extends Vehicule {

    @NotNull
    private int nombrePlaces;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeEnergie typeEnergie;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeTransmission transmission;

    private boolean climatisation;

    @NotNull
    private int chargeMax;

    @NotNull
    private int poidsPATC;

    @NotNull
    private int capaciteMetreCube;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeUtilitaire typeUtilitaire;

    @Enumerated(EnumType.STRING)
    private Permis permisNecessaire;

    public Utilitaire(String marque, String modele, String couleur, double tarifJournalier, int kilometrage, boolean actif, boolean retireDuParc, int nombrePlaces, TypeEnergie typeEnergie, TypeTransmission transmission, boolean climatisation, int chargeMax, int poidsPATC, int capaciteMetreCube, TypeUtilitaire typeUtilitaire) {
        super(marque, modele, couleur, tarifJournalier, kilometrage, actif, retireDuParc);
        this.nombrePlaces = nombrePlaces;
        this.typeEnergie = typeEnergie;
        this.transmission = transmission;
        this.climatisation = climatisation;
        this.chargeMax = chargeMax;
        this.poidsPATC = poidsPATC;
        this.capaciteMetreCube = capaciteMetreCube;
        this.typeUtilitaire = typeUtilitaire;
    }
}
