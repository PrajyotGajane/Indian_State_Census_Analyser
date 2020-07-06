package com.bridgelabz.CensusAnalyser.utilities;

import com.bridgelabz.CensusAnalyser.controller.StateCensusAnalyser;
import com.bridgelabz.CensusAnalyser.exception.CensusAnalyserException;
import com.bridgelabz.CensusAnalyser.models.CSVStateCensus;
import com.bridgelabz.CensusAnalyser.models.CSVStateCode;
import com.bridgelabz.CensusAnalyser.models.USCensus;
import com.bridgelabz.CensusAnalyser.models.censusDAO;
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

public class IndiaCensusAdaptor extends CensusAdaptor{
      @Override
      public Map<String, censusDAO> loadCensusData(String... csvFilePath) {
            Map<String, censusDAO> censusStateMap = super.loadCensusData(CSVStateCensus.class, csvFilePath[0]);
            this.loadIndianStateCode(censusStateMap, csvFilePath[1]);
            return censusStateMap;
      }

      private int loadIndianStateCode(Map<String, censusDAO> censusCSVMap, String csvFilePath) {
            try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
                  ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
                  Iterator<CSVStateCode> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, CSVStateCode.class);
                  Iterable<CSVStateCode> csvIterable = () -> censusCSVIterator;
                  StreamSupport.stream(csvIterable.spliterator(), false).filter(csvState -> censusCSVMap.get(csvState.stateName) != null)
                          .forEach(csvState -> censusCSVMap.get(csvState.stateName).stateId = csvState.stateCode);
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
