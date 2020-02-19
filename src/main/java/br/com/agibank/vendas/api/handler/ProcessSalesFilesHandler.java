package br.com.agibank.vendas.api.handler;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.agibank.vendas.api.enumeration.DataTypeEnum;
import br.com.agibank.vendas.api.mapper.CustomerMapper;
import br.com.agibank.vendas.api.mapper.SaleMapper;
import br.com.agibank.vendas.api.mapper.SalesReportMapper;
import br.com.agibank.vendas.api.mapper.SalesmanMapper;
import br.com.agibank.vendas.api.model.Customer;
import br.com.agibank.vendas.api.model.Sale;
import br.com.agibank.vendas.api.model.SalesReport;
import br.com.agibank.vendas.api.model.Salesman;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProcessSalesFilesHandler {
	
    //private static final String PROPERTY_IDENTIFIER = new String("ç");

    @Value(value = "${file.path.in}")
    private String filePathIn;

    @Value(value = "${file.path.success}")
    private String filePathSuccess;

    @Value(value = "${file.format}")
    private String fileFormat;
    
    @Value(value = "${file.delimiter}")
    private String fileDelimiter;
    
    @Autowired
    private ProcessSalesReportHandler processSalesReportHandler;
    
    public void processSaleFiles() {
        Stream<File> fileStream = Stream.of(new File(filePathIn).listFiles());
        fileStream.filter(isDatFile()).forEach(this::processFile);
    }

    private void processFile(File file) {
        List<String> lines = getFileLines(file);
        
        List<Customer> fileCustomers = new ArrayList<Customer>();
        List<Salesman> fileSalesmans = new ArrayList<Salesman>();
        List<Sale> fileSales = new ArrayList<Sale>();
        
        for (String line : lines) {
            try (Scanner scan = new Scanner(line).useDelimiter(fileDelimiter)) {
            	 while (scan.hasNext()) {
            		 String type = scan.next();
                     DataTypeEnum dataType = DataTypeEnum.fromValue(type);

                     if(DataTypeEnum.CUSTOMER.equals(dataType)) {
                    	 Customer customer = CustomerMapper.scanToCustomer(scan);
                    	 if(Objects.nonNull(customer)) {
                    		 fileCustomers.add(customer);
                    	 }
                     } else if (DataTypeEnum.SALESMAN.equals(dataType)) {
                    	 Salesman salesman = SalesmanMapper.scanToSalesman(scan);
                    	 if(Objects.nonNull(salesman)) {
                    		 fileSalesmans.add(salesman);
                    	 }
                     }  else if (DataTypeEnum.SALE.equals(dataType)) {
                    	 Sale sale = SaleMapper.scanToSale(scan);
                    	 if(Objects.nonNull(sale)) {
                    		 fileSales.add(sale);
                    	 }
                     } else {
                         log.warn("Unknown type: {}", type);
                     }
                 }
            } catch (NoSuchElementException | IllegalStateException error) {
                log.error("An error occurred while reading the file: {}", error.getMessage());
            }
        }
        
        setDataToSalesReport(file, fileCustomers, fileSalesmans, fileSales);
        
    }

    private List<String> getFileLines(File file) {
        try (Stream<String> lines = Files.lines(file.toPath(), Charset.defaultCharset())) {
            return lines.collect(Collectors.toList());
        } catch (IOException error) {
            log.error("Error while getting file lines: {}", error.getMessage());
            return Collections.emptyList();
        }
    }

    private Predicate<File> isDatFile() {
        return (File file) -> file.getName().endsWith(fileFormat);
    }
    
    private void moveFile(File file) {
        File dir = new File(filePathSuccess);
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            file.renameTo(new File(filePathSuccess.concat("/").concat(file.getName())));
        } catch (SecurityException | NullPointerException error) {
            log.error("Unable to move file to Success folder, the file will be excluded.");
        } finally {
        	file.delete();
		}
    }
    
	private void setDataToSalesReport(File file, List<Customer> fileCustomers, List<Salesman> fileSalesmans,
			List<Sale> fileSales) {
		String fileName = file.getName();
		Boolean createSaleReport = Boolean.FALSE;
		
		SalesReport salesReport = setReportData(fileCustomers, fileSalesmans, fileSales);
		if(Objects.nonNull(salesReport)) {
			String fileNameToGenerate = fileName.substring(0, fileName.length() -4);
			createSaleReport = processSalesReportHandler.createSaleReport(fileNameToGenerate, salesReport);
		}
		
        if (createSaleReport) {
            moveFile(file);
        } else {
            log.error("Unable to create report, fileName: {}", fileName);
        }
	}

	private SalesReport setReportData(List<Customer> fileCustomers, List<Salesman> fileSalesmans,
			List<Sale> fileSales) {
		return SalesReportMapper.scanToSalesReport(
				fileSalesmans.stream()
		                .filter(salesman -> !ObjectUtils.isEmpty(salesman))
		                .collect(Collectors.toList()),
		        fileCustomers.stream()
		                .filter(customer -> !ObjectUtils.isEmpty(customer))
		                .collect(Collectors.toList()),
		        fileSales.stream()
		                .filter(sale -> !ObjectUtils.isEmpty(sale))
		                .collect(Collectors.toList()));
	}
    
}
