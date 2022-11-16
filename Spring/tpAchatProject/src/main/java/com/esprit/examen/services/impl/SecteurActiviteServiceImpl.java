package com.esprit.examen.services.impl;

import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.entities.dto.SecteurActiviteDTO;
import com.esprit.examen.repositories.SecteurActiviteRepository;
import com.esprit.examen.services.ISecteurActiviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SecteurActiviteServiceImpl implements ISecteurActiviteService {

	@Autowired
	SecteurActiviteRepository secteurActiviteRepository;
	@Override
	public List<SecteurActivite> retrieveAllSecteurActivite() {
		return (List<SecteurActivite>) secteurActiviteRepository.findAll();
	}

	@Override

	public SecteurActivite addSecteurActivite(SecteurActiviteDTO sa) {
		return secteurActiviteRepository.save(SecteurActivite.builder()
				.idSecteurActivite(sa.getIdSecteurActivite())
				.codeSecteurActivite(sa.getCodeSecteurActivite())
				.libelleSecteurActivite(sa.getLibelleSecteurActivite())
				.fournisseurs(sa.getFournisseurs())
		.build());
	}

	@Override
	public void deleteSecteurActivite(Long id) {
		secteurActiviteRepository.deleteById(id);
		
	}

	@Override
	public SecteurActivite updateSecteurActivite(SecteurActiviteDTO sa) {
		return secteurActiviteRepository.save(SecteurActivite.builder()
				.idSecteurActivite(sa.getIdSecteurActivite())
				.codeSecteurActivite(sa.getCodeSecteurActivite())
				.libelleSecteurActivite(sa.getLibelleSecteurActivite())
				.fournisseurs(sa.getFournisseurs())
				.build());	}

	@Override
	public SecteurActivite retrieveSecteurActivite(Long id) {

		return secteurActiviteRepository.findById(id).orElse(null);
	}

}
