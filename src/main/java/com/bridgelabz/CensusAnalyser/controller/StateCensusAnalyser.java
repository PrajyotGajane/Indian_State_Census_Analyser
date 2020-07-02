package com.bridgelabz.CensusAnalyser.controller;
import com.bridgelabz.CensusAnalyser.exception.CensusAnalyserException;
import com.bridgelabz.CensusAnalyser.models.CSVStateCensus;
import com.bridgelabz.CensusAnalyser.models.CSVStateCensusDAO;
import com.bridgelabz.CensusAnalyser.models.CSVStateCode;
import com.bridgelabz.CensusAnalyser.models.CSVStateCodeDAO;
import com.bridgelabz.CensusAnalyser.service.CSVBuilderException;
import com.bridgelabz.CensusAnalyser.service.CSVBuilderFactory;
import com.bridgelabz.CensusAnalyser.service.ICSVBuilder;
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
      HashMap<String, CSVStateCensusDAO> censusCSVMap = new LinkedHashMap<>();
      public StateCensusAnalyser(){
            this.censusList = new ArrayList<>();
            this.stateCodeList = new ArrayList<>();
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
                  StreamSupport.stream(csvStateCensusIterable.spliterator(), false)
                          .forEach(csvCensus -> censusCSVMap.put(csvCensus.state, new CSVStateCensusDAO(csvCensus)));
                  return censusCSVMap.size();
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
       * @throws CensusAnalyserException
       */
      public int loadIndianStateCode(String csvFilePath) {
            try (Reader readerState = Files.newBufferedReader(Paths.get(csvFilePath))) {
                  Iterator<CSVStateCode> stateCodeIterator = CSVBuilderFactory.createCSVBuilder()
                          .getCSVFileIterator(readerState, CSVStateCode.class);
                  while (stateCodeIterator.hasNext())
                  {
                        this.stateCodeList.add(new CSVStateCodeDAO(stateCodeIterator.next()));
                  }
                  return stateCodeList.size();
            } catch (IOException e) {
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
      public void jsonFileWriter(String csvFilePath){
            try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){
                  ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
                  censusList = csvBuilder.getCSVFileList(reader, CSVStateCensus.class);
                  FileWriter writer = new FileWriter(SAMPLE_JSON_FILE_PATH);
                  writer.write(sortByArea());
                  writer.close();
            } catch (IOException | CSVBuilderException e) {
                  throw new CensusAnalyserException(e.getMessage(),
                          CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            }
      }
}