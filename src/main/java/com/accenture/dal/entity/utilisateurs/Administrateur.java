package com.accenture.dal.entity.utilisateurs;

import jakarta.persistence.Entity;
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
public class Administrateur extends Utilisateur {

    @NotNull
    private String fonction;

    public Administrateur(String nom, String prenom, String email, String motDePasse, boolean desactive, Role role, String fonction) {
        super(nom, prenom, email, motDePasse, desactive, role);
        this.fonction = fonction;
    }

    public Administrateur(int id, String nom, String prenom, String email, String motDePasse, boolean desactive, Role role, String fonction) {
        super(id, nom, prenom, email, motDePasse, desactive, role);
        this.fonction = fonction;
    }
}