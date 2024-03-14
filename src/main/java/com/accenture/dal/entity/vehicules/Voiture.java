package com.accenture.dal.entity.vehicules;

import com.accenture.dal.entity.Accessoire;
import com.accenture.dal.entity.Permis;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "VOITURES")
public class Voiture extends Vehicule {

    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeEnergie typeEnergie;

    @NotNull
    private int nombrePortes;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeTransmission transmission;

    private boolean climatisation;

    @NotNull
    private int nombrePlaces;

    @NotNull
    private int nombreBagages;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeVoiture typeVoiture;

    @Enumerated(EnumType.STRING)
    private Permis permisNecessaire;


    public Voiture(String marque, String modele, String couleur, double tarifJournalier, int kilometrage, boolean actif, boolean retireDuParc, int nombrePlaces, TypeEnergie typeEnergie, int nombrePortes, TypeTransmission transmission, boolean climatisation, int nombreBagages, TypeVoiture typeVoiture) {
        super(marque, modele, couleur, tarifJournalier, kilometrage, actif, retireDuParc);
        this.nombrePlaces = nombrePlaces;
        this.typeEnergie = typeEnergie;
        this.nombrePortes = nombrePortes;
        this.transmission = transmission;
        this.climatisation = climatisation;
        this.nombreBagages = nombreBagages;
        this.typeVoiture = typeVoiture;
    }
}
