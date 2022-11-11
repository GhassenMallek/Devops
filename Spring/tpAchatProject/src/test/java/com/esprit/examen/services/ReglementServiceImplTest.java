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

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Reglement;
import com.esprit.examen.entities.dto.ProduitDTO;
import com.esprit.examen.entities.dto.ReglementDTO;
import com.esprit.examen.repositories.ProduitRepository;
import com.esprit.examen.repositories.ReglementRepository;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class ReglementServiceImplTest {
	@Mock
	private ReglementRepository  reglementRepository;

	@InjectMocks
	private ReglementServiceImpl reglementServiceImpl;

	private Reglement r1;
	private Reglement r2;
	private Reglement r3;
	private Facture f1;
	ModelMapper modelMapper;

	@BeforeEach
	public void init() {
		this.r1 = new Reglement();
		this.r1.setIdReglement(0L);
		this.r1.setMontantPaye(100);
		this.r1.setMontantRestant(10);
		this.r1.setDateReglement(null);
		this.r1.setFacture(null);
		this.r2 = new Reglement();
		this.r2.setIdReglement(1L);
		this.r2.setMontantPaye(90);
		this.r2.setMontantRestant(0);
		this.r2.setDateReglement(null);
		this.r2.setFacture(null);
		this.modelMapper = new ModelMapper();
	}
	

	@Test
	public void addReglementTest() {
		init();
		when(reglementRepository.save(any(Reglement.class))).thenReturn(r1);
		ReglementDTO prm=modelMapper.map(r1, ReglementDTO.class);
		Reglement newReglement = reglementServiceImpl.addReglement(prm);
		assertNotNull(newReglement);
		assertThat(newReglement.getMontantRestant()).isEqualTo(10);
	}
	
	@Test
	public void getReglement() {
		init();
		List<Reglement> list = new ArrayList<>();
		list.add(r1);
		list.add(r2);
		when(reglementRepository.findAll()).thenReturn(list);
		List<Reglement> Reglements = reglementServiceImpl.retrieveAllReglements();
		assertEquals(2, Reglements.size());
		assertNotNull(Reglements);
	}
	
	@Test
	public void retrieveReglementByFacture() {
		this.f1 = new Facture();
		this.f1.setIdFacture(1L);
		
		this.r2 = new Reglement();
		this.r2.setIdReglement(2L);
		this.r2.setFacture(f1);
		this.r3 = new Reglement();
		this.r3.setIdReglement(3L);
		this.r3.setFacture(f1);
		this.modelMapper = new ModelMapper();
		
		System.out.println("hello ghassef " + r2.getFacture().toString()+ " " + r3.getFacture().toString());
		List<Reglement> Reglements = reglementServiceImpl.retrieveReglementByFacture(f1.getIdFacture());
		System.out.println(reglementServiceImpl.retrieveReglementByFacture(f1.getIdFacture()));
		
	}
//	
//	@Test
//	public void getChiffreAffaireEntreDeuxDate() {
//		init();
//		when(reglementRepository.findById(anyLong())).thenReturn(Optional.of(r1));
//		
//		when(produitRepository.save(any(Produit.class))).thenReturn(p1);
//		p1.setLibelleProduit("Fantacy");
//		ProduitDTO prm=modelMapper.map(p1, ProduitDTO.class);
//		Produit exisitingProduit = produitService.updateProduit(prm);
//		
//		assertNotNull(exisitingProduit);
//		assertEquals("Fantacy", exisitingProduit.getLibelleProduit());
//	}

}
