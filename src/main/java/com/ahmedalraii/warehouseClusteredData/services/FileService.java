package com.ahmedalraii.warehouseClusteredData.services;


import com.ahmedalraii.warehouseClusteredData.models.Deal;
import com.ahmedalraii.warehouseClusteredData.repositories.DealRepositoryInterface;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService implements FileServiceInterface {

    private static final Logger log = LoggerFactory.getLogger(FileService.class);
    @Autowired
    private DealRepositoryInterface dealRepository;

    @Override
    public boolean hasCsvFormat(MultipartFile file) {

        String type = "text/csv";
        return type.equals(file.getContentType());
    }

    @Override
    public void processAndSaveData(MultipartFile file) {

        try {

            List<Deal> deals = csvToDeals(file.getInputStream());
            dealRepository.saveAll(deals);
            log.info("deals added to db");

        } catch (IOException e) {
            log.error("error in add deals : {}", e.getMessage());
            throw new RuntimeException(e);
        }

    }

    private List<Deal> csvToDeals(InputStream inputStream) {


        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader()
                             .withIgnoreHeaderCase()
                             .withIgnoreEmptyLines()
                             .withTrim())) {

            List<Deal> deals = new ArrayList<>();
            List<CSVRecord> records = csvParser.getRecords();

            int i = 0;
            for (CSVRecord csvRecord : records) {
                Deal deal = new Deal(
                        csvRecord.get("fromCurrency"),
                        csvRecord.get("toCurrency"),
                        csvRecord.get("dealTimestamp"),
                        Double.parseDouble(csvRecord.get("amount")));

                Deal existDeal = dealRepository.findByAmountAndDealTimestampAndFromCurrencyAndToCurrency(
                        deal.getAmount(),
                        deal.getDealTimestamp(),
                        deal.getFromCurrency(),
                        deal.getToCurrency());

                if (existDeal != null) {

                    boolean isEquals = isDealsEquals(existDeal, deal);

                    if (!isEquals) {
                        deals.add(deal);
                    }
                } else {
                    deals.add(deal);
                }


            }


            return deals;

        } catch (IOException e) {
            System.out.println("error : " + e.getMessage());
            return new ArrayList<>();
        }
    }


    public boolean isDealsEquals(Deal dealOne, Deal dealTwo) {
        return dealOne.getAmount() == dealTwo.getAmount() &&
                dealOne.getFromCurrency().equals(dealTwo.getFromCurrency()) &&
                dealOne.getToCurrency().equals(dealTwo.getToCurrency()) &&
                dealOne.getDealTimestamp().equals(dealTwo.getDealTimestamp());
    }
}
