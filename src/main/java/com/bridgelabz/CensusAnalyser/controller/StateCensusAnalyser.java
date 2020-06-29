package com.bridgelabz.CensusAnalyser.controller;
import com.bridgelabz.CensusAnalyser.exception.CensusAnalyserException;
import com.bridgelabz.CensusAnalyser.models.CSVStateCensus;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
public class StateCensusAnalyser {
      public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
            try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
                  CsvToBeanBuilder<CSVStateCensus> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
                  csvToBeanBuilder.withType(CSVStateCensus.class);
                  csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
                  CsvToBean<CSVStateCensus> csvToBean = csvToBeanBuilder.build();
                  Iterator<CSVStateCensus> censusCSVIterator = csvToBean.iterator();;
                  int numberOfEntries = 0;
                  while (censusCSVIterator.hasNext()) {
                        numberOfEntries++;
                        CSVStateCensus censusData = censusCSVIterator.next();
                  }
                  return numberOfEntries;
            } catch (IOException e) {
                  throw new CensusAnalyserException(e.getMessage(),CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            }
      }
}