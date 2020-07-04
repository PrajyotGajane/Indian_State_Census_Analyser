package com.bridgelabz.CensusAnalyser.controller;
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
      private static final String SAMPLE_JSON_FILE_PATH = "./json-user.json";
      List<CSVStateCensusDAO> censusList;
      List<CSVStateCodeDAO> stateCodeList;
      List<USCensusDAO> usCensusList;
      HashMap<String, CSVStateCensusDAO> censusCSVMap = new LinkedHashMap<>();
      public StateCensusAnalyser(){
            this.censusList = new ArrayList<>();
            this.stateCodeList = new ArrayList<>();
            this.usCensusList = new ArrayList<>();
      }

      /**
       * to load csv file data into the program
       * @param csvFilePath
       * @return total number of records
       * @throws CensusAnalyserException
       */
      public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
            try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
                  Iterator<CSVStateCensus> censusIterator = CSVBuilderFactory.createCSVBuilder()
                          .getCSVFileIterator(reader, CSVStateCensus.class);
                  Iterable<CSVStateCensus> csvStateCensusIterable = () -> censusIterator;
                  StreamSupport.stream(csvStateCensusIterable.spliterator(),false)
                          .forEach(csvCensus -> censusList.add(new CSVStateCensusDAO(csvCensus)));
                  StreamSupport.stream(csvStateCensusIterable.spliterator(), false)
                          .forEach(csvCensus -> censusCSVMap.put(csvCensus.state, new CSVStateCensusDAO(csvCensus)));
                  return censusList.size();
            } catch (IOException e) {
                  throw new CensusAnalyserException(e.getMessage(),
                                                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            } catch (RuntimeException e){
                  throw new CensusAnalyserException(e.getMessage(),
                          CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            }
            catch (CSVBuilderException e) {
                  throw new CensusAnalyserException(e.getMessage(),
                                                    CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
            }
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
                          .forEach(csvCensus -> stateCodeList.add(new CSVStateCodeDAO(csvCensus)));
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
       * to load US census data csv file into the program
       * @param csvFilePath
       * @return
       * @throws CensusAnalyserException
       */
      public int loadUSCensusData(String csvFilePath) {
            try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
                  Iterator<USCensus> censusIterator = CSVBuilderFactory.createCSVBuilder()
                          .getCSVFileIterator(reader, USCensus.class);
                  Iterable<USCensus> usCensusIterable = () -> censusIterator;
                  StreamSupport.stream(usCensusIterable.spliterator(),false)
                          .forEach(csvCensus -> usCensusList.add(new USCensusDAO(csvCensus)));
                 return usCensusList.size();
            }catch (IOException e) {
                  throw new CensusAnalyserException(e.getMessage(),
                          CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            } catch (RuntimeException e) {
                  throw new CensusAnalyserException(e.getMessage(),
                          CensusAnalyserException.ExceptionType.DELIMITER_MISSING);
            } catch (CSVBuilderException e) {
                  throw new CensusAnalyserException(e.getMessage(),
                          CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
            }
      }
      /**
       * to sort alphabetically by state name for IndiaStateCode.csv
       * @return sortedState
       */
      public String sortStateCodeByState(){
            stateCodeList.sort(((Comparator<CSVStateCodeDAO>)
                    (census1, census2) -> census2.state.compareTo(census1.state)).reversed());
            String sortedState = new Gson().toJson(stateCodeList);
            return sortedState;
      }
      /**
       * to sort alphabetically by state name for IndiaStateCensusData.csv
       * @return sortedState
       */
      public String sortByState() {
            SortedSet<String> sortMap = new TreeSet<>(censusCSVMap.keySet());
            return sortMap.first();
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
            usCensusList.sort(((census1, census2) -> census2.population.compareTo(census1.population)));
            String mostPopulatedState = new Gson().toJson(usCensusList);
            return mostPopulatedState;
      }
      /**
       * to sort by state density
       * @return mostDenseState
       */
      public String sortByDensity(){
            censusList.sort(((census1, census2) -> census2.density.compareTo(census1.density)));
            String mostDenseState = new Gson().toJson(censusList);
            return mostDenseState;
      }
      /**
       * to sort state by area
       * @return sortByArea
       */
      public String sortByArea(){
            censusList.sort(((census1, census2) -> census2.areaInSqKm.compareTo(census1.areaInSqKm)));
            String sortByArea = new Gson().toJson(censusList);
            return sortByArea;
      }
      /**
       * to create a json file with sorted State with Area
       * @param csvFilePath
       */
//      public void jsonFileWriter(String csvFilePath){
//            try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){
//                  ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
//                  censusList = csvBuilder.getCSVFileList(reader, CSVStateCensus.class);
//                  FileWriter writer = new FileWriter(SAMPLE_JSON_FILE_PATH);
//                  Gson json = new Gson().toJson(sortByArea());
//                  writer.write(json);
//                  writer.close();
//            } catch (IOException | CSVBuilderException e) {
//                  throw new CensusAnalyserException(e.getMessage(),
//                          CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
//            }
//      }
}