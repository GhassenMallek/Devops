package com.esprit.examen.services;

import java.util.List;

import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.dto.ProduitDTO;

public interface IProduitService {

	List<Produit> retrieveAllProduits();

	Produit addProduit(ProduitDTO p);

	void deleteProduit(Long id);

	Produit updateProduit(ProduitDTO p);

	Produit retrieveProduit(Long id);

	void assignProduitToStock(Long idProduit, Long idStock);

}
