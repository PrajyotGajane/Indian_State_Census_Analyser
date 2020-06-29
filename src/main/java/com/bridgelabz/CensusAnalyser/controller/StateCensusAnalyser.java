package com.bridgelabz.CensusAnalyser.controller;
import com.bridgelabz.CensusAnalyser.exception.CensusAnalyserException;
import com.bridgelabz.CensusAnalyser.models.CSVStateCensus;
import com.bridgelabz.CensusAnalyser.models.CSVStateCode;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;
public class StateCensusAnalyser {
      public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException{
            try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
                  CsvToBeanBuilder<CSVStateCensus> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
                  csvToBeanBuilder.withType(CSVStateCensus.class);
                  csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
                  CsvToBean<CSVStateCensus> csvToBean = csvToBeanBuilder.build();
                  Iterator<CSVStateCensus> censusCSVIterator = csvToBean.iterator();
                  Iterable<CSVStateCensus> csvIterable = () -> censusCSVIterator;
                  int numberOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
                  return numberOfEntries;
            } catch (NoSuchFileException e){
                  throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_WRONG_EXTENSION);
            } catch (RuntimeException e) {
                  throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.WRONG_HEADER_FILE);
            } catch (IOException e) {
                  throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            }
      }

}