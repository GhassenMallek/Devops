package com.esprit.examen.services.impl;

import java.util.List;

import com.esprit.examen.entities.dto.OperateurDTO;
import com.esprit.examen.services.IOperateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esprit.examen.entities.Operateur;
import com.esprit.examen.repositories.OperateurRepository;

@Service
public class OperateurServiceImpl implements IOperateurService {

	@Autowired
	OperateurRepository  operateurRepository;
	@Override
	public List<Operateur> retrieveAllOperateurs() {
		return (List<Operateur>) operateurRepository.findAll();
	}

	@Override
	public Operateur addOperateur(OperateurDTO o) {
		Operateur op =Operateur.builder()
				//.factures(o.getFactures())
				.nom(o.getNom())
				.password(o.getPassword())
				.prenom(o.getPrenom())
				.build();
		return operateurRepository.save(op);
    }

	@Override
	public void deleteOperateur(Long id) {operateurRepository.deleteById(id);}

	@Override
	public Operateur updateOperateur(OperateurDTO o) {return operateurRepository.save(Operateur.builder()
			.idOperateur(o.getIdOperateur())
			.nom(o.getNom())
			.prenom(o.getPrenom())
			.password(o.getPassword())
			.factures(o.getFactures())
			.build());}

	@Override
	public Operateur retrieveOperateur(Long id) {return operateurRepository.findById(id).orElse(null);}

}
