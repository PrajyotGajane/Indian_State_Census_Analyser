package com.bridgelabz.CensusAnalyser;
import com.bridgelabz.CensusAnalyser.controller.StateCensusAnalyser;
import com.bridgelabz.CensusAnalyser.exception.CensusAnalyserException;
import com.bridgelabz.CensusAnalyser.models.*;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
public class CensusAnalyserTest {
      private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
      private static final String US_CENSUS_CSV_FILE_PATH = "./src/test/resources/USCensusData.csv";
      private static final String INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
      private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
      private static final String WRONG_CSV_FILE_TYPE = "./src/main/resources/IndiaStateCensusData.jpg";
      private static final String WRONG_STATE_CODE_CSV_FILE_PATH = "./src/main/resources/IndiaStateCode.csv";
      private static final String WRONG_STATE_CODE_CSV_FILE_TYPE = "./src/test/resources/IndiaStateCode.jpg";
      private static final String JSON_FOR_US_SORTED_AREA = "./JSON_US_AREA.json";
      private static final String JSON_FOR_US_SORTED_POPULATION = "./JSON_US_POPULATION.json";

      @Test
      public void StatusCensusCsvFile_ReturnsCorrectRecords() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
      }
      @Test
      public void givenStatusCensusCsvFile_WithWrongFile_ShouldThrowException() {
            try {
                  StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
                  censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
            } catch (CensusAnalyserException e) {
                  Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
            }
      }
      @Test
      public void givenStatusCensusCsvFile_WithWrongFileType_ShouldThrowException() {
            try {
                  StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
                  censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_TYPE);
            } catch (CensusAnalyserException e) {
                  Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
            }
      }
      @Test
      public void givenStatusCensusCsvFile_WithWrongHeader_ShouldThrowException() {
            try {
                  StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
                  censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            } catch (CensusAnalyserException e) {
                  Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_HEADER_FILE, e.type);
            }
      }

      @Test
      public void givenStatesCensusCsvFile_WithIncorrectDelimiter_ShouldThrowException() {
            try {
                  StateCensusAnalyser censusAnalyser =  new StateCensusAnalyser();
                  censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            } catch (CensusAnalyserException e){
                  Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_MISSING, e.type);
            }
      }
      @Test
      public void StatusStateCodeFile_ReturnsCorrectRecords() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndianStateCode(INDIA_STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(37, numOfRecords);
      }
      @Test
      public void givenStatesCodeCsvFile_WithWrongFile_ShouldThrowException() {
            try {
                  StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
                  censusAnalyser.loadIndianStateCode(WRONG_STATE_CODE_CSV_FILE_PATH);
            } catch (CensusAnalyserException e) {
                  Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
            }
      }
      @Test
      public void givenStatesCodeCsvFile_WithWrongFileType_ShouldThrowException() {
            try {
                  StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
                  censusAnalyser.loadIndianStateCode(WRONG_STATE_CODE_CSV_FILE_TYPE);
            } catch (CensusAnalyserException e) {
                  Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
            }
      }
      @Test
      public void givenStatesCodesCsvFile_WithIncorrectDelimiter_ShouldThrowException() {
            try {
                  StateCensusAnalyser censusAnalyser =  new StateCensusAnalyser();
                  censusAnalyser.loadIndianStateCode(INDIA_STATE_CODE_CSV_FILE_PATH);
            } catch (CensusAnalyserException e){
                  Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_MISSING, e.type);
            }
      }
      @Test
      public void getIndianCensusData_WhenSortedOnState_ShouldReturnSortedStateResult() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.sortByState();
            CSVStateCensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensusDAO[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
      }
      @Test
      public void getIndianStateCodeData_WhenSortedOnState_ShouldReturnSortedResult() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadIndianStateCode(INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortStateCodeData = censusAnalyser.sortStateCodeByState();
            CSVStateCodeDAO[] censusCSV = new Gson().fromJson(sortStateCodeData, CSVStateCodeDAO[].class);
            Assert.assertEquals("Andaman and Nicobar Islands", censusCSV[0].state);
      }
      @Test
      public void getIndianCensusData_WhenSortedOnStateByPopulation_ShouldReturnSortedResult() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.sortByPopulation();
            CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
            Assert.assertEquals(199812341, (int) censusCSV[0].population);
      }
      @Test
      public void getIndianCensusData_WhenSortedOnStateByDensity_ShouldReturnSortedResult() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.sortByDensity();
            CSVStateCensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensusDAO[].class);
            Assert.assertEquals(1102, (int) censusCSV[0].density);
      }
      @Test
      public void getIndianCensusData_WhenSortedOnStateByArea_ShouldReturnSortedResult() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.sortByArea();
            CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
            Assert.assertEquals(342239, (int) censusCSV[0].areaInSqKm);
      }
      @Test
      public void getUSCensusData_WhenSortedOnStateByPopulationDensity_ShouldReturnSortedResult() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadUSCensusData(US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.sortUSByPopulationDensity();
            USCensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, USCensusDAO[].class);
            Assert.assertEquals((Double) 3805.61, censusCSV[0].populationDensity);
      }
      @Test
      public void USCensusFile_ReturnsCorrectRecords() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            int numOfRecords = censusAnalyser.loadUSCensusData(US_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(51, numOfRecords);
      }
      @Test
      public void getUSCensusData_WhenSortedOnStateByPopulation_ShouldReturnSortedResult() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadUSCensusData(US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.sortUSByPopulation();
            censusAnalyser.jsonFileWriter(JSON_FOR_US_SORTED_POPULATION,sortedCensusData);
            USCensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, USCensusDAO[].class);
            Assert.assertEquals((Integer) 37253956, censusCSV[0].population);
      }
      @Test
      public void getUSCensusData_WhenSortedOnStateByArea_ShouldReturnSortedResult() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadUSCensusData(US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.sortUSByArea();
            censusAnalyser.jsonFileWriter(JSON_FOR_US_SORTED_AREA, sortedCensusData);
            USCensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, USCensusDAO[].class);
            Assert.assertEquals((Double) 1723338.01, censusCSV[0].area);
      }

}