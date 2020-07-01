package com.bridgelabz.CensusAnalyser.controller;
import com.bridgelabz.CensusAnalyser.exception.CensusAnalyserException;
import com.bridgelabz.CensusAnalyser.models.CSVStateCensus;
import com.bridgelabz.CensusAnalyser.models.CSVStateCode;
import com.bridgelabz.CensusAnalyser.service.CSVBuilderException;
import com.bridgelabz.CensusAnalyser.service.CSVBuilderFactory;
import com.bridgelabz.CensusAnalyser.service.ICSVBuilder;
import com.google.gson.Gson;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
public class StateCensusAnalyser {
      private static final String SAMPLE_JSON_FILE_PATH = "./json-user.json";
      List<CSVStateCensus> censusCSVList;
      List<CSVStateCode> stateCodeList;
      /**
       * to load csv file data into the program
       * @param csvFilePath
       * @return total number of records
       * @throws CensusAnalyserException
       */
      public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
            try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
                  ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
                  censusCSVList = csvBuilder.getCSVFileList(reader, CSVStateCensus.class);
                  jsonFileWriter(csvFilePath);
                  return censusCSVList.size();
            } catch (NoSuchFileException e) {
                  throw new CensusAnalyserException(e.getMessage(),
                                                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
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
       * to load csv file data into the program
       * @param csvFilePath
       * @return to total number of records
       * @throws CensusAnalyserException
       */
      public int loadIndianStateCode(String csvFilePath) {
            try (Reader readerState = Files.newBufferedReader(Paths.get(csvFilePath))) {
                  ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
                  stateCodeList = csvBuilder.getCSVFileList(readerState, CSVStateCode.class);
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
      public String sortStateCodeByState(){
            stateCodeList.sort(((Comparator<CSVStateCode>)
                    (census1, census2) -> census2.stateName.compareTo(census1.stateName)).reversed());
            String sortedState = new Gson().toJson(stateCodeList);
            return sortedState;
      }
      public String sortByState() {
            censusCSVList.sort(((Comparator<CSVStateCensus>)
                    (census1, census2) -> census2.state.compareTo(census1.state)).reversed());
            String sortedState = new Gson().toJson(censusCSVList);
            return sortedState;
      }
      public String sortByPopulation() {
            censusCSVList.sort(((Comparator<CSVStateCensus>)
                    (census1, census2) -> census2.population.compareTo(census1.population)));
            String mostPopulatedState = new Gson().toJson(censusCSVList);
            return mostPopulatedState;
      }
      public String sortByDensity(){
            censusCSVList.sort(((Comparator<CSVStateCensus>)
                    (census1, census2) -> census2.densityPerSqKm.compareTo(census1.densityPerSqKm)));
            String mostDenseState = new Gson().toJson(censusCSVList);
            return mostDenseState;
      }
      public String sortByArea(){
            censusCSVList.sort(((Comparator<CSVStateCensus>)
                    (census1, census2) -> census2.areaInSqKm.compareTo(census1.areaInSqKm)));
            String sortByArea = new Gson().toJson(censusCSVList);
            return sortByArea;
      }
      public void jsonFileWriter(String csvFilePath){
            try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){
                  ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
                  censusCSVList = csvBuilder.getCSVFileList(reader, CSVStateCensus.class);
                  FileWriter writer = new FileWriter(SAMPLE_JSON_FILE_PATH);
                  writer.write(sortByArea());
                  writer.close();
            } catch (IOException | CSVBuilderException e) {
                  throw new CensusAnalyserException(e.getMessage(),
                          CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            }
      }
}