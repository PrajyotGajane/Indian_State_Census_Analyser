package com.bridgelabz.CensusAnalyser.adaptor;

import com.bridgelabz.CensusAnalyser.exception.CensusAnalyserException;
import com.bridgelabz.CensusAnalyser.models.CSVStateCensus;
import com.bridgelabz.CensusAnalyser.models.CSVStateCode;
import com.bridgelabz.CensusAnalyser.dao.CensusDAO;
import com.bridgelabz.CensusAnalyser.service.CSVBuilderException;
import com.bridgelabz.CensusAnalyser.service.CSVBuilderFactory;
import com.bridgelabz.CensusAnalyser.service.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IndiaCensusAdaptor extends CensusAdaptor {
      @Override
      public Map<String, CensusDAO> loadCensusData(String... csvFilePath) {
            Map<String, CensusDAO> censusStateMap = super.loadCensusData(CSVStateCensus.class, csvFilePath[0]);
            this.loadIndianStateCode(censusStateMap, csvFilePath[1]);
            return censusStateMap;
      }
      private int loadIndianStateCode(Map<String, CensusDAO> censusCSVMap, String csvFilePath) {
            try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
                  ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
                  Iterator<CSVStateCode> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, CSVStateCode.class);
                  Iterable<CSVStateCode> csvIterable = () -> censusCSVIterator;
                  StreamSupport.stream(csvIterable.spliterator(), false).filter(csvState -> censusCSVMap.get(csvState.stateName) != null)
                          .forEach(csvState -> censusCSVMap.get(csvState.stateName).stateCode = csvState.stateCode);
                  return censusCSVMap.size();
            } catch (IOException | CSVBuilderException e) {
                  throw new CensusAnalyserException(e.getMessage(),
                          CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            } catch (RuntimeException e){
                  throw new CensusAnalyserException("Wrong Header or Delimiter",
                          CensusAnalyserException.ExceptionType.WRONG_HEADER_FILE_OR_DELIMITER);
            }
      }
}
