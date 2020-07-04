package com.bridgelabz.CensusAnalyser.controller;
import com.bridgelabz.CensusAnalyser.Utilities.CensusLoader;
import com.bridgelabz.CensusAnalyser.exception.CensusAnalyserException;
import com.bridgelabz.CensusAnalyser.models.*;
import com.bridgelabz.CensusAnalyser.service.CSVBuilderException;
import com.bridgelabz.CensusAnalyser.service.CSVBuilderFactory;
import com.google.gson.Gson;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;
public class StateCensusAnalyser {
      public enum Country {INDIA, US}
      List<censusDAO> censusList;
      List<censusDAO> stateCodeList;
      List<censusDAO> usCensusList;
      Map<String, censusDAO> censusCSVMap = new HashMap<>();
      public StateCensusAnalyser(){
            this.censusList = new ArrayList<>();
            this.stateCodeList = new ArrayList<>();
            this.usCensusList = new ArrayList<>();
      }
      public int loadCensusData(Country country, String... csvFilePath){
            censusCSVMap = new CensusLoader().loadCensusData(country, csvFilePath);
            return censusCSVMap.size();
      }
      /**
       * to load csv file data into the program
       * @param csvFilePath
       * @return to total number of records
       */
      public int loadIndianStateCode(String csvFilePath) {
            try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
                  Iterator<CSVStateCode> censusIterator = CSVBuilderFactory.createCSVBuilder()
                          .getCSVFileIterator(reader, CSVStateCode.class);
                  Iterable<CSVStateCode> csvStateCensusIterable = () -> censusIterator;
                  StreamSupport.stream(csvStateCensusIterable.spliterator(),false)
                          .forEach(csvCensus -> stateCodeList.add(new censusDAO(csvCensus)));
                  return stateCodeList.size();
            } catch (IOException e) {
                  throw new CensusAnalyserException(e.getMessage(),
                                                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            } catch (RuntimeException e) {
                  throw new CensusAnalyserException(e.getMessage(),
                                                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            } catch (CSVBuilderException e) {
                  throw new CensusAnalyserException(e.getMessage(),
                                                    CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
            }
      }
      /**
       * to sort alphabetically by state name for IndiaStateCode.csv
       * @return sortedState
       */
      public String sortStateCodeByState() {
            stateCodeList.sort(((Comparator<censusDAO>)
                    (census1, census2) -> census2.stateName.compareTo(census1.stateName)).reversed());
            String sortedState = new Gson().toJson(stateCodeList);
            return sortedState;
      }
      /**
       * to sort alphabetically by state name for IndiaStateCensusData.csv
       * @return sortedState
       */
      public String sortByState() {
            censusList.sort(((Comparator<censusDAO>)
                    (census1, census2) -> census2.state.compareTo(census1.state)).reversed());
            String sortedState = new Gson().toJson(censusList);
            return sortedState;
      }
      /**
       * to sort by population
       * @return mostPopulatedState
       */
      public String sortByPopulation() {
            censusList.sort(((census1, census2) -> census2.population.compareTo(census1.population)));
            String mostPopulatedState = new Gson().toJson(censusList);
            return mostPopulatedState;
      }
      public String sortUSByPopulationDensity(){
            usCensusList.sort(((census1, census2) -> census2.populationDensity.compareTo(census1.populationDensity)));
            String mostDensity = new Gson().toJson(usCensusList);
            return mostDensity;
      }
      /**
       * to sort US csv by population
       * @return mostPopulatedState
       */
      public String sortUSByPopulation() {
            usCensusList.sort(((census1, census2) -> census2.populationUS.compareTo(census1.populationUS)));
            String mostPopulatedState = new Gson().toJson(usCensusList);
            return mostPopulatedState;
      }
      /**
       * to sort by state density
       * @return mostDenseState
       */
      public String sortByDensity() {
            censusList.sort(((census1, census2) -> census2.density.compareTo(census1.density)));
            String mostDenseState = new Gson().toJson(censusList);
            return mostDenseState;
      }
      /**
       * to sort state by area
       * @return sortByArea
       */
      public String sortByArea() {
            censusList.sort(((census1, census2) -> census2.areaInSqKm.compareTo(census1.areaInSqKm)));
            String sortByArea = new Gson().toJson(censusList);
            return sortByArea;
      }
      public String sortUSByArea() {
            usCensusList.sort(((census1, census2) -> census2.area.compareTo(census1.area)));
            String mostPopulatedState = new Gson().toJson(usCensusList);
            return mostPopulatedState;
      }
      /**
       * to create a json file with passed file path and sorted string
       * @param csvFilePath
       */
      public void jsonFileWriter(String csvFilePath, String json) {
            FileWriter writer;
            try {
                  writer = new FileWriter(csvFilePath);
                  writer.write(json);
                  writer.close();
            } catch (IOException e) {
                  e.printStackTrace();
            }
      }
}