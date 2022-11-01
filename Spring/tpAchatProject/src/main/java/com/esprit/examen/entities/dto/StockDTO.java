package com.esprit.examen.entities.dto;

import com.esprit.examen.entities.Produit;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Builder
public class StockDTO implements Serializable {
	private String libelleStock;
	private Integer qte;
	private Integer qteMin;
	private Set<Produit> produits;
	public StockDTO(String libelleStock, Integer qte, Integer qteMin) {
		super();
		this.libelleStock = libelleStock;
		this.qte = qte;
		this.qteMin = qteMin;
	}

}
