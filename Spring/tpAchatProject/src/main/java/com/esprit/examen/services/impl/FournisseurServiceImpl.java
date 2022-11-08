package com.esprit.examen.services.impl;

import java.util.Date;
import java.util.List;
import com.esprit.examen.entities.dto.FournisseurDTO;
import com.esprit.examen.services.IFournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esprit.examen.entities.DetailFournisseur;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.repositories.DetailFournisseurRepository;
import com.esprit.examen.repositories.FournisseurRepository;
import com.esprit.examen.repositories.SecteurActiviteRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FournisseurServiceImpl implements IFournisseurService {

	@Autowired
	FournisseurRepository fournisseurRepository;
	@Autowired
	DetailFournisseurRepository detailFournisseurRepository;
	@Autowired
	SecteurActiviteRepository secteurActiviteRepository;

	@Override
	public List<Fournisseur> retrieveAllFournisseurs() {
		List<Fournisseur> fournisseurs = fournisseurRepository.findAll();
		for (Fournisseur fournisseur : fournisseurs) {
			log.info(" fournisseur : " + fournisseur);
		}
		return fournisseurs;
	}


	public Fournisseur addFournisseur(FournisseurDTO f) {
		DetailFournisseur df= new DetailFournisseur();//Slave
		df.setDateDebutCollaboration(new Date()); //util
		f.setDetailFournisseur(df);
		return fournisseurRepository.save(Fournisseur.builder()
				.idFournisseur(f.getIdFournisseur())
				.code(f.getCode())
				.libelle(f.getLibelle())
				.categorieFournisseur(f.getCategorieFournisseur())
				.factures(f.getFactures())
				.secteurActivites(f.getSecteurActivites())
				.detailFournisseur(f.getDetailFournisseur())
				.build());
	}
	
	private DetailFournisseur  saveDetailFournisseur(Fournisseur f){
		DetailFournisseur df = f.getDetailFournisseur();
		detailFournisseurRepository.save(df);
		return df;
	}

	public Fournisseur updateFournisseur(FournisseurDTO f) {
		Fournisseur ff = Fournisseur.builder()
				.idFournisseur(f.getIdFournisseur())
				.code(f.getCode())
				.libelle(f.getLibelle())
				.categorieFournisseur(f.getCategorieFournisseur())
				.factures(f.getFactures())
				.secteurActivites(f.getSecteurActivites())
				.detailFournisseur(f.getDetailFournisseur())
				.build();
		DetailFournisseur df = saveDetailFournisseur(ff);
		ff.setDetailFournisseur(df);
		return fournisseurRepository.save(ff);
	}

	@Override
	public void deleteFournisseur(Long fournisseurId) {
		fournisseurRepository.deleteById(fournisseurId);

	}

	@Override
	public Fournisseur retrieveFournisseur(Long fournisseurId) {
		return fournisseurRepository.findById(fournisseurId).orElse(null);
	}

	@Override
	public void assignSecteurActiviteToFournisseur(Long idSecteurActivite, Long idFournisseur) {
		Fournisseur fournisseur = fournisseurRepository.findById(idFournisseur).orElse(null);
		if( fournisseur != null ){
			SecteurActivite secteurActivite = secteurActiviteRepository.findById(idSecteurActivite).orElse(null);
			fournisseur.getSecteurActivites().add(secteurActivite);
			fournisseurRepository.save(fournisseur);
		}
	}

	

}