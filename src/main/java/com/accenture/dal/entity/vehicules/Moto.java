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
@Table(name = "MOTOS")
public class Moto extends Vehicule {

    @NotNull
    private int nombreDeCylindres;

    @NotNull
    private int cylindree;

    @NotNull
    private int poids;

    @NotNull
    private int puissanceKW;

    @NotNull
    private int hauteurSelle;

    @Enumerated(EnumType.STRING)
    private TypeTransmission transmission;

    @Enumerated(EnumType.STRING)
    private TypeMoto typeMoto;

    @Enumerated(EnumType.STRING)
    private Permis permisNecessaire;

    public Moto(String marque, String modele, String couleur, double tarifJournalier, int kilometrage, boolean actif, boolean retireDuParc, int nombreDeCylindres, int cylindree, int poids, int puissanceKW, int hauteurSelle, TypeTransmission transmission, TypeMoto typeMoto, Permis permisNecessaire) {
        super(marque, modele, couleur, tarifJournalier, kilometrage, actif, retireDuParc);
        this.nombreDeCylindres = nombreDeCylindres;
        this.cylindree = cylindree;
        this.poids = poids;
        this.puissanceKW = puissanceKW;
        this.hauteurSelle = hauteurSelle;
        this.transmission = transmission;
        this.typeMoto = typeMoto;
        this.permisNecessaire = permisNecessaire;
    }
}
