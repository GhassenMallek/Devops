package com.esprit.examen.entities.dto;

import com.esprit.examen.entities.Facture;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@Builder
public class OperateurDTO {
    private Long idOperateur;
    private String nom;
    private String prenom;
    private String password;
    private Set<Facture> factures;

}
