package com.esprit.examen.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.esprit.examen.entities.dto.SecteurActiviteDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SecteurActivite implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSecteurActivite;
	private String codeSecteurActivite;
	private String libelleSecteurActivite;
	@ManyToMany(mappedBy="secteurActivites")
	@JsonIgnore
	private Set<Fournisseur> fournisseurs;

	public SecteurActiviteDTO toSecteurActiviteDTO(){
		return SecteurActiviteDTO.builder()
				.idSecteurActivite(this.idSecteurActivite)
				.codeSecteurActivite(this.codeSecteurActivite)
				.libelleSecteurActivite(this.libelleSecteurActivite)
				.fournisseurs(this.fournisseurs)
				.build();
	}
}
