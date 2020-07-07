package com.bridgelabz.CensusAnalyser.adaptor;

import com.bridgelabz.CensusAnalyser.exception.CensusAnalyserException;
import com.bridgelabz.CensusAnalyser.models.CSVStateCensus;
import com.bridgelabz.CensusAnalyser.models.USCensus;
import com.bridgelabz.CensusAnalyser.models.censusDAO;
import com.bridgelabz.CensusAnalyser.service.CSVBuilderException;
import com.bridgelabz.CensusAnalyser.service.CSVBuilderFactory;
import com.bridgelabz.CensusAnalyser.service.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class CensusAdaptor {
      public abstract Map<String, censusDAO> loadCensusData(String... csvFilePath);

      public <E> Map<String, censusDAO> loadCensusData(Class<E> censusCSVClass, String csvFilePath) {
            Map<String, censusDAO> censusCSVMap = new HashMap<>();
            try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
                  ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
                  Iterator<E> csvFileIterator = csvBuilder.getCSVFileIterator(reader, censusCSVClass);
                  Iterable<E> csvIterable = () -> csvFileIterator;
                  if (censusCSVClass.getName() == "com.bridgelabz.CensusAnalyser.models.CSVStateCensus") {
                        StreamSupport.stream(csvIterable.spliterator(), false)
                                .map(CSVStateCensus.class::cast)
                                .forEach(csvState -> censusCSVMap.put(csvState.state, new censusDAO(csvState)));
                  } else if (censusCSVClass.getName() == "com.bridgelabz.CensusAnalyser.models.USCensus") {
                        StreamSupport.stream(csvIterable.spliterator(), false)
                                .map(USCensus.class::cast)
                                .forEach(csvState -> censusCSVMap.put(csvState.state, new censusDAO(csvState)));
                  }
                  return censusCSVMap;
            } catch (IOException | CSVBuilderException e) {
                  throw new CensusAnalyserException(e.getMessage(),
                          CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            } catch (RuntimeException e){
                  throw new CensusAnalyserException("Wrong Header or Delimiter",
                          CensusAnalyserException.ExceptionType.WRONG_HEADER_FILE_OR_DELIMITER);
            }
      }
}