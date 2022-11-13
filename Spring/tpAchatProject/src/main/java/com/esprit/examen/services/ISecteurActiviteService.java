package com.esprit.examen.services;

import java.util.List;

import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.entities.dto.SecteurActiviteDTO;

public interface ISecteurActiviteService {

	List<SecteurActivite> retrieveAllSecteurActivite();

	SecteurActivite addSecteurActivite(SecteurActiviteDTO sa);

	void deleteSecteurActivite(Long id);

	SecteurActivite updateSecteurActivite(SecteurActiviteDTO sa);

	SecteurActivite retrieveSecteurActivite(Long id);

}
