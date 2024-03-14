package com.accenture.dal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ACCESSOIRES")
public class Accessoire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String nom;

    @NotNull
    private double montantUnitaire;

    @NotNull
    private int limite;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AccessoirePourTypeVehicule accessoirePourTypeVehicule;

    public Accessoire(String nom, double montantUnitaire, int limite, AccessoirePourTypeVehicule accessoirePourTypeVehicule) {
        this.nom = nom;
        this.montantUnitaire = montantUnitaire;
        this.limite = limite;
        this.accessoirePourTypeVehicule = accessoirePourTypeVehicule;

    }
}
