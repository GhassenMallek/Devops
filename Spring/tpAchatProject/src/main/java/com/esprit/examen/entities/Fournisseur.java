package com.esprit.examen.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.esprit.examen.entities.dto.FournisseurDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fournisseur implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFournisseur;
	private String code;
	private String libelle;
	@Enumerated(EnumType.STRING)
	private CategorieFournisseur  categorieFournisseur;
	@OneToMany(mappedBy="fournisseur")
	@JsonIgnore
	private Set<Facture> factures;
    @ManyToMany
    @JsonIgnore
    private Set<SecteurActivite> secteurActivites;
    @OneToOne(cascade= CascadeType.ALL,fetch=FetchType.EAGER)
    private DetailFournisseur detailFournisseur;

	public FournisseurDTO toFournisseurDTO(){
		return FournisseurDTO.builder()
				.idFournisseur(this.idFournisseur)
				.code(this.code)
            	.libelle(this.libelle)
				.categorieFournisseur(this.categorieFournisseur)
				.factures(this.factures)
				.secteurActivites(this.secteurActivites)
				.detailFournisseur(this.detailFournisseur)
				.build();
	}

	
}
