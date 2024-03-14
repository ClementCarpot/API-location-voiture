package com.accenture.dal.entity.utilisateurs;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Adresse {

    @NotNull
    private String rue;

    @NotNull
    private String ville;

    @NotNull
    @Pattern(regexp = "^\\d{5}$")
    private String codePostal;
}
