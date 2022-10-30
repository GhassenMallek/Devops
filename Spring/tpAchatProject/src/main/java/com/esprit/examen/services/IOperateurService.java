package com.esprit.examen.services;

import java.util.List;

import com.esprit.examen.entities.dto.OperateurDTO;
import com.esprit.examen.entities.Operateur;


public interface IOperateurService {

	List<Operateur> retrieveAllOperateurs();

	Operateur addOperateur(OperateurDTO o);

	void deleteOperateur(Long id);

	Operateur updateOperateur(Operateur o);

	Operateur retrieveOperateur(Long id);

}
