package br.com.agibank.vendas.api.handler;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.agibank.vendas.api.enumeration.DataTypeEnum;
import br.com.agibank.vendas.api.mapper.CustomerMapper;
import br.com.agibank.vendas.api.model.Customer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProcessSaleFilesHandler {
	
    private static final String PROPERTY_IDENTIFIER = "ç";

    @Value(value = "${file.path.in}")
    private String fileInputPath;

    @Value(value = "${file.path.success}")
    private String fileSuccessPath;

    @Value(value = "${file.format}")
    private String fileFormat;
    
    public void processSaleFiles() {
        Stream<File> fileStream = Stream.of(new File(fileInputPath).listFiles());
        fileStream.filter(isDatFile()).forEach(this::processFile);

    }

    private void processFile(File file) {
        List<String> lines = getFileLines(file);
        
        List<Customer> fileCustomers = new ArrayList<>();

        for (String line : lines) {
            try (Scanner scan = new Scanner(line).useDelimiter(PROPERTY_IDENTIFIER)) {
            	while (scan.hasNext()) {
                    String type = scan.next();
                    DataTypeEnum dataType = DataTypeEnum.fromValue(type);
                    switch (dataType) {
                        case CUSTOMER:
                            fileCustomers.add(CustomerMapper.mapperFromScanner(scan));
                            break;
                        default:
                            log.warn("DataType not found.");
                            break;
                    }
                }
                
            } catch (NoSuchElementException | IllegalStateException error) {
                log.error("An error occurred while reading the file: {}", error.getMessage());
            }
        }
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
    
}
