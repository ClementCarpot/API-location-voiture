package com.accenture.dal.entity.utilisateurs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "utilisateurs")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @NotNull
    protected String nom;

    @NotNull
    protected String prenom;

    @NotNull
    @Email
    @Column(unique = true, name = "email")
    protected String email;

    @NotNull
    @Column(name = "mot_de_passe")
    protected String motDePasse;

    protected boolean desactive;

    @Enumerated(EnumType.STRING)
    @NotNull
    protected Role role;

    protected Utilisateur(String nom, String prenom, String email, String motDePasse, boolean desactive, Role role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.desactive = desactive;
        this.role = role;
    }

    protected Utilisateur(String email, String motDePasse, Role role) {
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role;
    }
}
