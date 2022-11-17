package com.esprit.examen.services;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.esprit.examen.services.impl.ProduitServiceImpl;
import com.esprit.examen.services.impl.StockServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.entities.dto.ProduitDTO;
import com.esprit.examen.repositories.ProduitRepository;
import com.esprit.examen.repositories.StockRepository;



@RunWith(SpringRunner.class)
@Slf4j
@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
	@Mock
	private ProduitRepository produitRepository;
	@Mock
	private StockRepository stockrepository;
	@Mock
	private ProduitServiceImpl produitServiceMock;

	@InjectMocks
	private ProduitServiceImpl produitService;
	@InjectMocks
	private StockServiceImpl stockServiceImpl;


	private Produit p1;
	private Produit p2;
	private Produit p3;
	private Stock s1;
	ModelMapper modelMapper;

	@BeforeEach
	public void init() {
		this.p1 = new Produit();
		this.p1.setIdProduit(0L);
		this.p1.setPrix(100);
		this.p1.setLibelleProduit("test");
		this.p2 = new Produit();
		this.p2.setIdProduit(2L);
		this.p2.setPrix(100);
		this.p2.setStock(s1);
		this.p2.setLibelleProduit("test2");
		this.s1 = new Stock();
		this.s1.setIdStock(0L);
		this.s1.setQte(100);
		this.s1.setLibelleStock("Stock 1");
		this.modelMapper = new ModelMapper();
	}

	@Test
	public void testAddProduit() {
		log.info("entry function : testAddProduit");
		init();
		when(produitRepository.save(any(Produit.class))).thenReturn(p1);
		ProduitDTO prm=modelMapper.map(p1, ProduitDTO.class);
		Produit pnew=produitService.addProduit(prm);
		assertNotNull(pnew);
		assertThat(pnew.getIdProduit()).isZero();
		log.info("exit function : testAddProduit");
	}

	@Test
	public void getProduits() {
		log.info("entry function : getProduits");
		init();
		List<Produit> list = new ArrayList<>();
		list.add(p1);
		list.add(p2);
		when(produitRepository.findAll()).thenReturn(list);
		List<Produit> Produits = produitService.retrieveAllProduits();
		assertEquals(2, Produits.size());
		assertNotNull(Produits);
		log.info("exit function : getProduits");
	}

	@Test
	public void getProduitById() {
		log.info("entry function : getProduitById");
		init();
		when(produitRepository.save(any(Produit.class))).thenReturn(p1);
		ProduitDTO prm=modelMapper.map(p1, ProduitDTO.class);
		Produit pnew=produitService.addProduit(prm);
		when(produitRepository.findById(anyLong())).thenReturn(Optional.of(p1));
		Produit existingProduit = produitService.retrieveProduit(pnew.getIdProduit());
		assertNotNull(existingProduit);
		assertThat(existingProduit.getIdProduit()).isNotNull();
		log.info("exit function : getProduitById");
	}

	@Test
	public void updateProduit() {
		log.info("entry function : updateProduit");
		init();
		when(produitRepository.findById(anyLong())).thenReturn(Optional.of(p1));
		when(produitRepository.save(any(Produit.class))).thenReturn(p1);
		p1.setLibelleProduit("Fantacy");
		ProduitDTO prm=modelMapper.map(p1, ProduitDTO.class);
		Produit exisitingProduit = produitService.updateProduit(prm);
		assertNotNull(exisitingProduit);
		assertEquals("Fantacy", exisitingProduit.getLibelleProduit());
		log.info("exit function : updateProduit");
	}
	@Test

	public void assignProduitToStockTruecondion() {
		log.info("entry function : assignProduitToStockTruecondion");
		init();
		assertThat(p3).isNull();
		when(produitRepository.findById(anyLong())).thenReturn(null);
		when(stockrepository.findById(anyLong())).thenReturn(Optional.of(s1));
		when(produitRepository.findById(anyLong())).thenReturn(Optional.of(p1));
		assertNotNull(p1);
		produitService.assignProduitToStock(p1.getIdProduit(), s1.getIdStock());
		assertThat(p1.getStock().getIdStock()).isEqualTo(s1.getIdStock());
		log.info("exit function : assignProduitToStockTruecondion");
	}
	@Test
	public void assignProduitToStockFalsecondion() {
		log.info("entry function : assignProduitToStockFalsecondion");
		init();
		assertThat(p3).isNull();
		log.info("exit function : assignProduitToStockFalsecondion");
	}

	@Test
	public void deleteProduit() {
		log.info("entry function : deleteProduit");
		init();
		Long ProduitId = 1L;
		when(produitRepository.findById(anyLong())).thenReturn(Optional.of(p1));
		doNothing().when(produitRepository).deleteById(anyLong());
		produitService.deleteProduit(ProduitId);
		verify(produitRepository, times(1)).deleteById(anyLong());
		log.info("exit function : deleteProduit");
	}


}
