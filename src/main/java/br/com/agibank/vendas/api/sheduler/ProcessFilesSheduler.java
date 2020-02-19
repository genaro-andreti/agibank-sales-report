package br.com.agibank.vendas.api.sheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.agibank.vendas.api.handler.ProcessSalesFilesHandler;

@Component
public class ProcessFilesSheduler {
	
	@Autowired
	private ProcessSalesFilesHandler processSaleFilesHandler;
	
    @Scheduled(fixedDelay = 1000)
    private void handleFiles() {
    	processSaleFilesHandler.processSaleFiles();
    }

}
