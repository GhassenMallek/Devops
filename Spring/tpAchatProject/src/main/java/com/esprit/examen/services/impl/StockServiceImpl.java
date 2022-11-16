package com.esprit.examen.services.impl;

import com.esprit.examen.entities.Stock;
import com.esprit.examen.entities.dto.StockDTO;
import com.esprit.examen.repositories.StockRepository;
import com.esprit.examen.services.IStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class StockServiceImpl implements IStockService {

	@Autowired
	StockRepository stockRepository;


	@Override
	public List<Stock> retrieveAllStocks() {
		// récuperer la date à l'instant t1
		log.info("In method retrieveAllStocks");
		List<Stock> stocks = stockRepository.findAll();
		for (Stock stock : stocks) {
			log.info(" Stock : " + stock);
		}
		log.info("out of method retrieveAllStocks");
		// récuperer la date à l'instant t2
		// temps execution = t2 - t1
		return stocks;
	}

	@Override
	public Stock addStock(StockDTO s) {
		// récuperer la date à l'instant t1
		log.info("In method addStock");
		return stockRepository.save(Stock.builder()
						.idStock(s.getIdStock())
				.libelleStock(s.getLibelleStock())
				.qte(s.getQte())
				.qteMin(s.getQteMin())
				.produits(s.getProduits())
				.build());
		
	}

	@Override
	public void deleteStock(Long stockId) {
		log.info("In method deleteStock");
		stockRepository.deleteById(stockId);

	}

	@Override
	public Stock updateStock(StockDTO s) {
		log.info("In method updateStock");
		return stockRepository.save(Stock.builder()
				.libelleStock(s.getLibelleStock())
				.qte(s.getQte())
				.qteMin(s.getQteMin())
				.produits(s.getProduits())
				.build());
	}

	@Override
	public Stock retrieveStock(Long stockId) {
		long start = System.currentTimeMillis();
		log.info("In method retrieveStock");
		Stock stock = stockRepository.findById(stockId).orElse(null);
		log.info("out of method retrieveStock");
		 long elapsedTime = System.currentTimeMillis() - start;
		log.info("Method execution time: " + elapsedTime + " milliseconds.");

		return stock;
	}

	@Override
	public String retrieveStatusStock() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		String msgDate = sdf.format(now);
		String newLine = System.getProperty("line.separator");
		List<Stock> stocksEnRouge =  stockRepository.retrieveStatusStock();
		StringBuilder finalMessage = new StringBuilder();
		for (int i = 0; i < stocksEnRouge.size(); i++) {
			finalMessage.append(newLine).append(finalMessage).append(msgDate).append(newLine).append(": le stock ").
			append(stocksEnRouge.get(i).getLibelleStock()).append(" a une quantité de ").append(stocksEnRouge.get(i).getQte())
					.append(" inférieur à la quantité minimale a ne pas dépasser de ").append(stocksEnRouge.get(i).getQteMin())
					.append(newLine);
		}
		return finalMessage.toString();
	}

}