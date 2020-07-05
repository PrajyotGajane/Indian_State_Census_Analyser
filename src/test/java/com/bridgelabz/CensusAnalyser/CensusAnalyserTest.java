package com.bridgelabz.CensusAnalyser;
import com.bridgelabz.CensusAnalyser.Utilities.JsonFileWriter;
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
      private static final String DELIMITER_MISSING = "src/test/resources/WrongIndiaStateCensusData.csv";
      private static final String WRONG_STATE_CODE_CSV_FILE = "src/test/resources/WrongIndiaStateCode.csv";
      private static final String JSON_FOR_US_SORTED_AREA = "./JSON_US_AREA212.json";
      private static final String JSON_FOR_US_SORTED_POPULATION = "./JSON_US_POPULATION.json";

      @Test
      public void StatusCensusCsvFile_ReturnsCorrectRecords() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            int numOfRecords = censusAnalyser.loadCensusData(StateCensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
      }
      @Test
      public void givenStatusCensusCsvFile_WithWrongFile_ShouldThrowException() {
            try {
                  StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
                  censusAnalyser.loadCensusData(StateCensusAnalyser.Country.INDIA, WRONG_CSV_FILE_PATH);
            } catch (CensusAnalyserException e) {
                  System.out.println("Exception occurred:"+e.getMessage());
                  Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
            }
      }
      @Test
      public void givenStatusCensusCsvFile_WithWrongFileType_ShouldThrowException() {
            try {
                  StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
                  censusAnalyser.loadCensusData(StateCensusAnalyser.Country.INDIA, WRONG_CSV_FILE_TYPE);
            } catch (CensusAnalyserException e) {
                  System.out.println("Exception occurred:"+e.getMessage());
                  Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
            }
      }
      @Test
      public void givenStatusCensusCsvFile_WithWrongHeader_ShouldThrowException() {
            try {
                  StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
                  censusAnalyser.loadCensusData(StateCensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH);
            } catch (CensusAnalyserException e) {
                  System.out.println("Exception occurred:"+e.getMessage());
                  Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_HEADER_FILE_OR_DELIMITER, e.type);
            }
      }

      @Test
      public void givenStatesCensusCsvFile_WithIncorrectDelimiter_ShouldThrowException() {
            try {
                  StateCensusAnalyser censusAnalyser =  new StateCensusAnalyser();
                  censusAnalyser.loadCensusData(StateCensusAnalyser.Country.INDIA, DELIMITER_MISSING);
            } catch (CensusAnalyserException e){
                  System.out.println("Exception occurred:"+e.getMessage());
                  Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_HEADER_FILE_OR_DELIMITER, e.type);
            }
      }


      @Test
      public void StatusStateCodeFile_ReturnsCorrectRecords() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            int numOfRecords = censusAnalyser.loadCensusData(StateCensusAnalyser.Country.INDIA_STATE,INDIA_STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(37, numOfRecords);
      }

      @Test
      public void givenStatesCodeCsvFile_WithWrongFile_ShouldThrowException() {
            try {
                  StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
                  censusAnalyser.loadCensusData(StateCensusAnalyser.Country.INDIA_STATE,WRONG_STATE_CODE_CSV_FILE);
            } catch (CensusAnalyserException e) {
                  System.out.println("Exception occurred:"+e.getMessage());
                  Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
            }
      }


      @Test
      public void givenStatesCodeCsvFile_WithWrongFileType_ShouldThrowException() {
            try {
                  StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
                  censusAnalyser.loadCensusData(StateCensusAnalyser.Country.INDIA_STATE,WRONG_STATE_CODE_CSV_FILE);
            } catch (CensusAnalyserException e) {
                  System.out.println("Exception occurred:"+e.getMessage());
                  Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
            }
      }

      @Test
      public void givenStatesCodesCsvFile_WithIncorrectDelimiter_ShouldThrowException() {
            try {
                  StateCensusAnalyser censusAnalyser =  new StateCensusAnalyser();
                  censusAnalyser.loadCensusData(StateCensusAnalyser.Country.INDIA,WRONG_STATE_CODE_CSV_FILE);
            } catch (CensusAnalyserException e){
                  System.out.println("Exception occurred:"+e.getMessage());
                  Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_HEADER_FILE_OR_DELIMITER, e.type);
            }
      }
      @Test
      public void getIndianCensusData_WhenSortedOnState_ShouldReturnSortedStateResult() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadCensusData(StateCensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData(StateCensusAnalyser.SortField.state);
            censusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, censusDAO[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[censusCSV.length - 1].state);
      }

      @Test
      public void getIndianStateCodeData_WhenSortedOnState_ShouldReturnSortedResult() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
                  censusAnalyser.loadCensusData(StateCensusAnalyser.Country.INDIA_STATE, INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortStateCodeData = censusAnalyser.getStateWiseSortedCensusData(StateCensusAnalyser.SortField.state);
            censusDAO[] censusCSV = new Gson().fromJson(sortStateCodeData, censusDAO[].class);
            Assert.assertEquals("Andaman and Nicobar Islands", censusCSV[censusCSV.length - 1].state);
      }
      @Test
      public void getIndianCensusData_WhenSortedOnStateByPopulation_ShouldReturnSortedResult() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadCensusData(StateCensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData(StateCensusAnalyser.SortField.population);
            censusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, censusDAO[].class);
            Assert.assertEquals(199812341, (int) censusCSV[0].population);
      }
      @Test
      public void getIndianCensusData_WhenSortedOnStateByDensity_ShouldReturnSortedResult() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadCensusData(StateCensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData(StateCensusAnalyser.SortField.populationDensity);
            censusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, censusDAO[].class);
            Assert.assertEquals((Double) 1102.00, censusCSV[0].populationDensity);
      }
      @Test
      public void getIndianCensusData_WhenSortedOnStateByArea_ShouldReturnSortedResult()  {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadCensusData(StateCensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData(StateCensusAnalyser.SortField.areaInSqKm);
            censusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, censusDAO[].class);
            Assert.assertEquals(342239, (int) censusCSV[0].areaInSqKm);
      }
      @Test
      public void getUSCensusData_WhenSortedOnStateByPopulationDensity_ShouldReturnSortedResult() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadCensusData(StateCensusAnalyser.Country.US, US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData(StateCensusAnalyser.SortField.populationDensity);
            censusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, censusDAO[].class);
            Assert.assertEquals((Double) 3805.61, censusCSV[0].populationDensity);
      }
      @Test
      public void USCensusFile_ReturnsCorrectRecords() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            int numOfRecords = censusAnalyser.loadCensusData(StateCensusAnalyser.Country.US, US_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(51, numOfRecords);
      }
      @Test
      public void getUSCensusData_WhenSortedOnStateByPopulation_ShouldReturnSortedResult() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadCensusData(StateCensusAnalyser.Country.US, US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData(StateCensusAnalyser.SortField.population);
            new JsonFileWriter(JSON_FOR_US_SORTED_POPULATION,sortedCensusData);
            censusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, censusDAO[].class);
            Assert.assertEquals((Integer) 37253956, censusCSV[0].population);
      }
      @Test
      public void getUSCensusData_WhenSortedOnStateByArea_ShouldReturnSortedResult() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadCensusData(StateCensusAnalyser.Country.US, US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData(StateCensusAnalyser.SortField.area);
            new JsonFileWriter(JSON_FOR_US_SORTED_AREA, sortedCensusData);
            censusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, censusDAO[].class);
            Assert.assertEquals((Double) 1723338.01, censusCSV[0].area);
      }
      @Test
      public void givenUSCensusData_WhenSortedOnStateID_ShouldReturnSortedResult() {
            try {
                  StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
                  censusAnalyser.loadCensusData(StateCensusAnalyser.Country.US, US_CENSUS_CSV_FILE_PATH);
                  String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData(StateCensusAnalyser.SortField.stateId);
                  censusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, censusDAO[].class);
                  Assert.assertEquals("AK", censusCSV[censusCSV.length - 1].stateId);
            } catch (CensusAnalyserException e) {
                  e.printStackTrace();
            }
      }
}