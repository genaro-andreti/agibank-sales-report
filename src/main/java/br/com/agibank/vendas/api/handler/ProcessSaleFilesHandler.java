package br.com.agibank.vendas.api.handler;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProcessSaleFilesHandler {
	
    private static final String PROPERTY_IDENTIFIER = "Ã§";

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
        for (String line : lines) {
            try (Scanner scan = new Scanner(line).useDelimiter(PROPERTY_IDENTIFIER)) {
                while (scan.hasNext()) {
                	if (!ObjectUtils.isEmpty(scan) && scan.hasNext()) {
                        while (scan.hasNext()) {
                            log.info(">>>> " + scan.next());
						}
                    }
                }
                
                moveFile(file);
            } catch (NoSuchElementException | IllegalStateException error) {
                log.error("Error while trying to read file: {}", error.getMessage());
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
    
    private void moveFile(File file) {
        File dir = new File(fileSuccessPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            file.renameTo(new File(fileSuccessPath.concat("/").concat(file.getName())));
        } catch (SecurityException | NullPointerException error) {
            log.error("Unable to move file to Success folder, the file will be excluded.");
            file.delete();
        }
    }

}
