package com.esprit.examen.services.impl;

import java.util.Date;
import java.util.List;

import com.esprit.examen.services.IReglementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.examen.entities.Reglement;
import com.esprit.examen.entities.dto.ReglementDTO;
import com.esprit.examen.repositories.FactureRepository;
import com.esprit.examen.repositories.ReglementRepository;

@Service
public class ReglementServiceImpl implements IReglementService {

	@Autowired
	ReglementRepository reglementRepository;

	@Override
	public List<Reglement> retrieveAllReglements() {
		return (List<Reglement>) reglementRepository.findAll();
	}

	@Override
	public Reglement addReglement(ReglementDTO r) {
		return reglementRepository.save(Reglement.builder()
				.montantPaye(r.getMontantPaye())
				.montantRestant(r.getMontantRestant())
				.payee(r.getPayee())
				.dateReglement(r.getDateReglement())
				.facture(r.getFacture())
				.build()
				);

	}

	@Override
	public Reglement retrieveReglement(Long id) {
		return reglementRepository.findById(id).orElse(null);

	}

	@Override
	public List<Reglement> retrieveReglementByFacture(Long idFacture) {
		return reglementRepository.retrieveReglementByFacture(idFacture);

	}

	@Override
	public float getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate) {
		return reglementRepository.getChiffreAffaireEntreDeuxDate(startDate, endDate);
	}

}
