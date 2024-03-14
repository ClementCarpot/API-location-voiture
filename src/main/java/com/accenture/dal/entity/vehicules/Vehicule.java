package com.accenture.dal.entity.vehicules;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Voiture.class, name = "voiture"),
        @JsonSubTypes.Type(value = Velo.class, name = "velo"),
        @JsonSubTypes.Type(value = Utilitaire.class, name = "utilitaire"),
        @JsonSubTypes.Type(value = Moto.class, name = "moto"),
        @JsonSubTypes.Type(value = CampingCar.class, name = "campingCar")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "VEHICULES")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @NotNull
    protected String marque;

    @NotNull
    protected String modele;

    @NotNull
    protected String couleur;

    @NotNull
    protected double tarifJournalier;

    @NotNull
    protected int kilometrage;

    protected boolean actif;

    protected boolean retireDuParc;

    protected Vehicule(String marque, String modele, String couleur, double tarifJournalier, int kilometrage, boolean actif, boolean retireDuParc) {
        this.marque = marque;
        this.modele = modele;
        this.couleur = couleur;
        this.tarifJournalier = tarifJournalier;
        this.kilometrage = kilometrage;
        this.actif = actif;
        this.retireDuParc = retireDuParc;
    }
}
