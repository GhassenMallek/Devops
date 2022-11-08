package com.esprit.examen.services;

import java.util.List;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.entities.dto.FournisseurDTO;

public interface IFournisseurService {

	List<Fournisseur> retrieveAllFournisseurs();

	Fournisseur addFournisseur(FournisseurDTO f);

	void deleteFournisseur(Long id);

	Fournisseur updateFournisseur(FournisseurDTO f);

	Fournisseur retrieveFournisseur(Long id);
	
	void assignSecteurActiviteToFournisseur(Long idSecteurActivite, Long idFournisseur);

}
