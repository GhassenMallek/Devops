package com.esprit.examen.entities.dto;

import java.util.Date;

import com.esprit.examen.entities.Facture;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReglementDTO {
	private Long idReglement;
	private float montantPaye;
	private float montantRestant;
	private Boolean payee;
	private Date dateReglement;
	private Facture facture;
	
}
