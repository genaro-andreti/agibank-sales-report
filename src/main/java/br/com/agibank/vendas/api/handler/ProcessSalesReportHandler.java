package br.com.agibank.vendas.api.handler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.agibank.vendas.api.model.SalesReport;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProcessSalesReportHandler {
    @Value(value = "${file.path.out}")
    private String filePathOut;

    public Boolean createSaleReport(String inputFileName, SalesReport salesReport) {
        File dir = new File(filePathOut);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(dir, inputFileName.concat("_report.dat"));
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            log.info("Generating report file of: {}", inputFileName);
            bw.write(salesReport.toString());
        } catch (IOException error) {
            log.error("Was not possible to generate the report file.");
            return false;
        }
        return true;
    }

}
