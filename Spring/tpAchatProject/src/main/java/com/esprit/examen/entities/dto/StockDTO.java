package com.esprit.examen.entities.dto;

import com.esprit.examen.entities.Produit;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@Builder
public class StockDTO {
	private String libelleStock;
	private Integer qte;
	private Integer qteMin;
	private Set<Produit> produits;

}
