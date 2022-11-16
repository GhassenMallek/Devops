package com.esprit.examen.entities.dto;

import com.esprit.examen.entities.Fournisseur;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Builder
public class SecteurActiviteDTO implements Serializable{

	private Long idSecteurActivite;
	private String codeSecteurActivite;
	private String libelleSecteurActivite;
	private Set<Fournisseur> fournisseurs;
}
