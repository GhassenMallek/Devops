package com.esprit.examen.entities.DTO;

import com.esprit.examen.entities.Facture;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class OperateurDTO {
    private String nom;
    private String prenom;
    private String password;
    private Set<Facture> factures;
}
