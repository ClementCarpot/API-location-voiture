package com.accenture.dal.entity.utilisateurs;

import com.accenture.dal.entity.Permis;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@Entity
public class Client extends Utilisateur {

    @Embedded
    private Adresse adresse;

    @Past
    @NotNull
    @Temporal(TemporalType.DATE)
    private LocalDate dateNaissance;

    @Temporal(TemporalType.DATE)
    private LocalDate dateInscription;

    @Enumerated(EnumType.STRING)
    private List<Permis> permis;

    public Client() {
        adresse = new Adresse();
    }

    public Client(String nom, String prenom, String email, String motDePasse, boolean desactive, Role role, Adresse adresse, LocalDate dateNaissance, LocalDate dateInscription, List<Permis> permis) {
        super(nom, prenom, email, motDePasse, desactive, role);
        this.adresse = adresse;
        this.dateNaissance = dateNaissance;
        this.dateInscription = dateInscription;
        this.permis = permis;
    }

    public Client(int id, String nom, String prenom, String email, String motDePasse, boolean desactive, Role role, Adresse adresse, LocalDate dateNaissance, LocalDate dateInscription, List<Permis> permis) {
        super(id, nom, prenom, email, motDePasse, desactive, role);
        this.adresse = adresse;
        this.dateNaissance = dateNaissance;
        this.dateInscription = dateInscription;
        this.permis = permis;
    }
}