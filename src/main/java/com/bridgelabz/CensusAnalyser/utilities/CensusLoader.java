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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;
public class CensusLoader {
      /**
       * to load census data
       * @param country
       * @param csvFilePath
       * @return
       */
      public Map<String, censusDAO> loadCensusData(StateCensusAnalyser.Country country, String... csvFilePath) {
            if (country.equals(StateCensusAnalyser.Country.INDIA))
                  return loadCensusData(CSVStateCensus.class, csvFilePath);
            if (country.equals(StateCensusAnalyser.Country.US))
                  return loadCensusData(USCensus.class, csvFilePath);
            if (country.equals(StateCensusAnalyser.Country.INDIA_STATE))
                  return loadCensusData(CSVStateCode.class, csvFilePath);
            throw new CensusAnalyserException("Invalid Country Name", CensusAnalyserException.ExceptionType.INVALID_COUNTRY);
      }
      /**
       * load census data according to class and file path passed
       * @param censusCSVClass
       * @param csvFilePath
       * @param <E>
       * @return loaded map
       */
      public <E> Map<String, censusDAO> loadCensusData(Class<E> censusCSVClass, String... csvFilePath) {
            Map<String, censusDAO> censusStateMap = new HashMap<>();
            try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath[0]));) {
                  ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
                  Iterator<E> csvFileIterator = csvBuilder.getCSVFileIterator(reader, censusCSVClass);
                  Iterable<E> csvIterable = () -> csvFileIterator;
                  if (censusCSVClass.getName().equals("com.bridgelabz.CensusAnalyser.models.CSVStateCensus")) {
                        StreamSupport.stream(csvIterable.spliterator(), false)
                                .map(CSVStateCensus.class::cast)
                                .forEach(csvState -> censusStateMap.put(csvState.state, new censusDAO(csvState)));
                  } else if (censusCSVClass.getName() == "com.bridgelabz.CensusAnalyser.models.USCensus") {
                        StreamSupport.stream(csvIterable.spliterator(), false)
                                .map(USCensus.class::cast)
                                .forEach(csvState -> censusStateMap.put(csvState.state, new censusDAO(csvState)));
                  } else if (censusCSVClass.getName() == "com.bridgelabz.CensusAnalyser.models.CSVStateCode") {
                        StreamSupport.stream(csvIterable.spliterator(), false)
                                .map(CSVStateCode.class::cast)
                                .forEach(csvState -> censusStateMap.put(csvState.stateName, new censusDAO(csvState)));
                  }
                  return censusStateMap;
            } catch (IOException e) {
                  throw new CensusAnalyserException("Wrong File",
                          CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            } catch (RuntimeException | CSVBuilderException e){
                throw new CensusAnalyserException("Wrong Header or Delimiter",
                        CensusAnalyserException.ExceptionType.WRONG_HEADER_FILE_OR_DELIMITER);
            }
      }
}