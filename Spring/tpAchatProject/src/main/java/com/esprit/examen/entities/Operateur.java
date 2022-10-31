package com.esprit.examen.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.esprit.examen.entities.dto.OperateurDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Operateur implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOperateur;
	private String nom;
	private String prenom;
	
	private String password;
	@OneToMany
	@JsonIgnore
	private Set<Facture> factures;

	public OperateurDTO toOperateurDTO(){
		return OperateurDTO.builder()
				.idOperateur(this.idOperateur)
				.nom(this.nom)
				.prenom(this.prenom)
				.password(this.password)
				.factures(this.factures)
				.build();
	}
}
