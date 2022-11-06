package com.esprit.examen.entities.dto;

import com.esprit.examen.entities.Produit;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class StockDTO {
	private Long idStock;
	private String libelleStock;
	private Integer qte;
	private Integer qteMin;
	private Set<Produit> produits;


}
