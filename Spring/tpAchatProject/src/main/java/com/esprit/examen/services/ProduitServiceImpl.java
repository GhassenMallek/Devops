package com.esprit.examen.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.entities.dto.ProduitDTO;
import com.esprit.examen.repositories.CategorieProduitRepository;
import com.esprit.examen.repositories.ProduitRepository;
import com.esprit.examen.repositories.StockRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProduitServiceImpl implements IProduitService {

	@Autowired
	ProduitRepository produitRepository;
	@Autowired
	StockRepository stockRepository;
	@Autowired
	CategorieProduitRepository categorieProduitRepository;

	@Override
	public List<Produit> retrieveAllProduits() {
		List<Produit> produits = produitRepository.findAll();
		for (Produit produit : produits) {
			log.info(" Produit : " + produit);
		}
		return produits;
	}

	@Transactional
	public Produit addProduit(ProduitDTO p) {
	return	produitRepository.save(Produit.builder()
				.codeProduit(p.getCodeProduit())
				.libelleProduit(p.getCodeProduit())
				.prix(p.getPrix())
				.dateCreation(p.getDateCreation())
				.dateDerniereModification(p.getDateDerniereModification())
				.stock(p.getStock())
				.detailFacture(p.getDetailFacture())
				.categorieProduit(p.getCategorieProduit())
				.build()
				);
	}

	@Override
	public void deleteProduit(Long produitId) {
		produitRepository.deleteById(produitId);
	}

	@Override
	public Produit updateProduit(ProduitDTO p) {
		return produitRepository.save(Produit.builder()
				.codeProduit(p.getCodeProduit())
				.libelleProduit(p.getCodeProduit())
				.prix(p.getPrix())
				.dateCreation(p.getDateCreation())
				.dateDerniereModification(p.getDateDerniereModification())
				.stock(p.getStock())
				.detailFacture(p.getDetailFacture())
				.categorieProduit(p.getCategorieProduit())
				.build()
				);
	}

	@Override
	public Produit retrieveProduit(Long produitId) {
		Produit produit = produitRepository.findById(produitId).orElse(null);
		log.info("produit :" + produit);
		return produit;
	}

	@Override
	public void assignProduitToStock(Long idProduit, Long idStock) {

			Optional<Produit>  produit = produitRepository.findById(idProduit);
			if (produit.isPresent()) {
				Stock stock = stockRepository.findById(idStock).orElse(null);
				produit.get().setStock(stock);
				produitRepository.save(produit.get());
			}
			

	}

}