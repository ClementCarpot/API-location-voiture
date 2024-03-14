package com.accenture.dal.entity.vehicules;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Batterie {

    @NotNull
    private int autonomieKm;

    @NotNull
    private int capaciteKwh;


}
