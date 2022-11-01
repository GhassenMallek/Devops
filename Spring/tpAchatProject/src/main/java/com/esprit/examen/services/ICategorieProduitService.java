package com.esprit.examen.services;

import java.util.List;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.entities.dto.CategorieProduitDTO;


public interface ICategorieProduitService {

	List<CategorieProduit> retrieveAllCategorieProduits();

	CategorieProduit addCategorieProduit(CategorieProduitDTO cp);

	void deleteCategorieProduit(Long id);

	CategorieProduit updateCategorieProduit(CategorieProduitDTO cp);

	CategorieProduit retrieveCategorieProduit(Long id);

}
