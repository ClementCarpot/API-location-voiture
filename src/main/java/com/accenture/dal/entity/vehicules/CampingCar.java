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
@Table(name = "CampingsCars")
public class CampingCar extends Vehicule {

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
    private int poidsPATC;

    @NotNull
    private int hauteur;

    @NotNull
    private int nombreCouchages;

    private boolean equipementCuisine;

    private boolean lingeLit;

    private boolean equipementRefregirateur;

    private boolean equipementDouche;

    @NotNull
    @Enumerated(EnumType.STRING)
    TypeCampingCar typeCampingCar;

    @Enumerated(EnumType.STRING)
    Permis permisNecessaire;


    public CampingCar(String marque, String modele, String couleur, double tarifJournalier, int kilometrage, boolean actif, boolean retireDuParc, int nombrePlaces, TypeEnergie typeEnergie, TypeTransmission transmission, boolean climatisation, int poidsPATC, int hauteur, int nombreCouchages, boolean equipementCuisine, boolean lingeLit, boolean equipementRefregirateur, boolean equipementDouche, TypeCampingCar typeCampingCar) {
        super(marque, modele, couleur, tarifJournalier, kilometrage, actif, retireDuParc);
        this.nombrePlaces = nombrePlaces;
        this.typeEnergie = typeEnergie;
        this.transmission = transmission;
        this.climatisation = climatisation;
        this.poidsPATC = poidsPATC;
        this.hauteur = hauteur;
        this.nombreCouchages = nombreCouchages;
        this.equipementCuisine = equipementCuisine;
        this.lingeLit = lingeLit;
        this.equipementRefregirateur = equipementRefregirateur;
        this.equipementDouche = equipementDouche;
        this.typeCampingCar = typeCampingCar;
    }
}
