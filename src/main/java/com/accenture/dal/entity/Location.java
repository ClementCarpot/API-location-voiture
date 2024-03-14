package com.accenture.dal.entity;

import com.accenture.dal.entity.utilisateurs.Client;
import com.accenture.dal.entity.vehicules.Vehicule;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LOCATIONS")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    private Client client;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    private Vehicule vehicule;

    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Accessoire> accessoires;

    @NotNull
    private LocalDate dateDebut;

    @NotNull
    private LocalDate dateFin;

    @NotNull
    private int nombreKmParcourus;

    private double montantLocation;

    private LocalDate dateValidation;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EtatLocation etatLocation;

    public Location(Client client, Vehicule vehicule, List<Accessoire> accessoires, LocalDate dateDebut, LocalDate dateFin, int nombreKmParcourus, int montantLocation, LocalDate dateValidation, EtatLocation etatLocation) {
        this.client = client;
        this.vehicule = vehicule;
        this.accessoires = accessoires;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nombreKmParcourus = nombreKmParcourus;
        this.montantLocation = montantLocation;
        this.dateValidation = dateValidation;
        this.etatLocation = etatLocation;
    }

    public Location(Client client, Vehicule vehicule, List<Accessoire> accessoires, LocalDate dateDebut, LocalDate dateFin, int nombreKmParcourus, int montantLocation, EtatLocation etatLocation) {
        this.client = client;
        this.vehicule = vehicule;
        this.accessoires = accessoires;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nombreKmParcourus = nombreKmParcourus;
        this.montantLocation = montantLocation;
        this.etatLocation = etatLocation;
    }
}
