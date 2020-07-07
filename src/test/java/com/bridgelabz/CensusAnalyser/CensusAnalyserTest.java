package com.bridgelabz.CensusAnalyser;

import com.bridgelabz.CensusAnalyser.utilities.JsonFileWriter;
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
      //wrong files and paths
      private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
      private static final String WRONG_CSV_FILE_TYPE = "./src/main/resources/WrongFileTypeIndiaStateCensusData.mkv";
      private static final String WRONG_DELIMITER_INDIA_CENSUS_CSV_FILE = "src/test/resources/WrongDelimiterIndiaStateCensusData.csv";
      private static final String WRONG_US_CENSUS_CSV_TYPE = "src/test/resources/WrongFileTypeUSCensusData.mkv";
      private static final String WRONG_HEADER_US_CENSUS_CSV_FILE = "src/test/resources/WrongHeaderUSCensusData.csv";
      private static final String WRONG_DELIMITER_US_CENSUS_CSV_FILE = "src/test/resources/WrongDelimiterUSCensusData.csv";
      //Json files to be created
      private static final String JSON_FOR_US_SORTED_AREA = "./JSON_US_AREA.json";
      private static final String JSON_FOR_INDIA_SORTED_DENSITY = "./JSON_INDIA_CENSUS_DENSITY.json";
      private static final String JSON_FOR_INDIA_SORTED_STATE = "./JSON_INDIA_CENSUS_STATE.json";
      private static final String JSON_FOR_INDIA_SORTED_POPULATION = "./JSON_INDIA_CENSUS_POPULATION.json";
      private static final String JSON_FOR_US_SORTED_STATE_ID = "./JSON_US_SORTED_STATE_ID.json";
      private static final String WRONG_HEADER_INDIA_CENSUS_CSV_FILE = "src/test/resources/WrongHeaderIndiaStateCensusData.csv";

      @Test
      public void StatusCensusCsvFile_ReturnsCorrectRecords() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            int numOfRecords = censusAnalyser.loadCensusData(StateCensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
      }

      @Test
      public void givenIndiaCensusCsvFile_WithWrongFilePath_ShouldThrowException() {
            try {
                  StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
                  censusAnalyser.loadCensusData(StateCensusAnalyser.Country.INDIA, WRONG_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
            } catch (CensusAnalyserException e) {
                  System.out.println("Exception occurred:" + e.getMessage());
                  Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
            }
      }

      @Test
      public void givenIndiaCensusCsvFile_WithWrongFileType_ShouldThrowException() {
            try {
                  StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
                  censusAnalyser.loadCensusData(StateCensusAnalyser.Country.INDIA, WRONG_CSV_FILE_TYPE, INDIA_STATE_CODE_CSV_FILE_PATH);
            } catch (CensusAnalyserException e) {
                  System.out.println("Exception occurred:" + e.getMessage());
                  Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
            }
      }

      @Test
      public void givenIndiaCensusCsvFile_WithWrongHeader_ShouldThrowException() {
            try {
                  StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
                  censusAnalyser.loadCensusData(StateCensusAnalyser.Country.INDIA, WRONG_HEADER_INDIA_CENSUS_CSV_FILE, INDIA_STATE_CODE_CSV_FILE_PATH);
            } catch (CensusAnalyserException e) {
                  System.out.println("Exception occurred:" + e.getMessage());
                  Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_HEADER_FILE_OR_DELIMITER, e.type);
            }
      }

      @Test
      public void givenIndiaCensusCsvFile_WithIncorrectDelimiter_ShouldThrowException() {
            try {
                  StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
                  censusAnalyser.loadCensusData(StateCensusAnalyser.Country.INDIA, WRONG_DELIMITER_INDIA_CENSUS_CSV_FILE);
            } catch (CensusAnalyserException e) {
                  System.out.println("Exception occurred:" + e.getMessage());
                  Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_HEADER_FILE_OR_DELIMITER, e.type);
            }
      }

      @Test
      public void getIndianCensusData_WhenSortedOnState_ShouldReturnSortedStateResult() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadCensusData(StateCensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData("STATE", StateCensusAnalyser.Country.INDIA);
            new JsonFileWriter(JSON_FOR_INDIA_SORTED_STATE, sortedCensusData);
            CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
      }

      @Test
      public void getIndianCensusData_WhenSortedOnStateByPopulation_ShouldReturnSortedResult() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadCensusData(StateCensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData("POPULAS_STATE", StateCensusAnalyser.Country.INDIA);
            new JsonFileWriter(JSON_FOR_INDIA_SORTED_POPULATION, sortedCensusData);
            CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
            Assert.assertEquals(199812341, (int) censusCSV[0].population);
      }

      @Test
      public void getIndianCensusData_WhenSortedOnStateByDensity_ShouldReturnSortedResult() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadCensusData(StateCensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData("POPULATION_DENSITY", StateCensusAnalyser.Country.INDIA);
            new JsonFileWriter(JSON_FOR_INDIA_SORTED_DENSITY, sortedCensusData);
            CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
            Assert.assertEquals((Double) 1102.00, censusCSV[0].densityPerSqKm);
      }

      @Test
      public void getIndianCensusData_WhenSortedOnStateByArea_ShouldReturnSortedResult() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadCensusData(StateCensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData("AREA_IN_SQR_KM", StateCensusAnalyser.Country.INDIA);
            CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
            Assert.assertEquals(342239, (int) censusCSV[0].areaInSqKm);
      }

      @Test
      public void USCensusFile_ReturnsCorrectRecords() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            int numOfRecords = censusAnalyser.loadCensusData(StateCensusAnalyser.Country.US, US_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(51, numOfRecords);
      }

      @Test
      public void givenUSCensusCsvFile_WithWrongHeader_ShouldThrowException() {
            try {
                  StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
                  censusAnalyser.loadCensusData(StateCensusAnalyser.Country.US, WRONG_HEADER_US_CENSUS_CSV_FILE);
            } catch (CensusAnalyserException e) {
                  System.out.println("Exception occurred:" + e.getMessage());
                  Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_HEADER_FILE_OR_DELIMITER, e.type);
            }
      }

      @Test
      public void givenUSCensusCsvFile_WithWrongFileType_ShouldThrowException() {
            try {
                  StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
                  censusAnalyser.loadCensusData(StateCensusAnalyser.Country.US, WRONG_US_CENSUS_CSV_TYPE);
            } catch (CensusAnalyserException e) {
                  System.out.println("Exception occurred:" + e.getMessage());
                  Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
            }
      }

      @Test
      public void givenUSCensusCsvFile_WithIncorrectDelimiter_ShouldThrowException() {
            try {
                  StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
                  censusAnalyser.loadCensusData(StateCensusAnalyser.Country.US, WRONG_DELIMITER_US_CENSUS_CSV_FILE);
            } catch (CensusAnalyserException e) {
                  System.out.println("Exception occurred:" + e.getMessage());
                  Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_HEADER_FILE_OR_DELIMITER, e.type);
            }
      }

      @Test
      public void getUSCensusData_WhenSortedOnStateByArea_ShouldReturnSortedResult() {
            String sortedCensusData;
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadCensusData(StateCensusAnalyser.Country.US, US_CENSUS_CSV_FILE_PATH);
            sortedCensusData = censusAnalyser.getStateWiseSortedCensusData("AREA", StateCensusAnalyser.Country.US);
            USCensus[] censusCSV = new Gson().fromJson(sortedCensusData, USCensus[].class);
            new JsonFileWriter(JSON_FOR_US_SORTED_AREA, sortedCensusData);
            Assert.assertEquals((Double) 1723338.01, censusCSV[0].area);
      }

      @Test
      public void givenUSCensusData_WhenSortedOnStateID_ShouldReturnSortedResult() {
            String sortedCensusData;
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadCensusData(StateCensusAnalyser.Country.US, US_CENSUS_CSV_FILE_PATH);
            sortedCensusData = censusAnalyser.getStateWiseSortedCensusData("STATE_ID", StateCensusAnalyser.Country.US);
            USCensus[] censusCSV = new Gson().fromJson(sortedCensusData, USCensus[].class);
            new JsonFileWriter(JSON_FOR_US_SORTED_STATE_ID, sortedCensusData);
            Assert.assertEquals("AK", censusCSV[censusCSV.length - 1].stateId);
      }

      @Test
      public void givenUSCensusData_WhenSortedOnState_ShouldReturnSortedResult() {
            String sortedCensusData;
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadCensusData(StateCensusAnalyser.Country.US, US_CENSUS_CSV_FILE_PATH);
            sortedCensusData = censusAnalyser.getStateWiseSortedCensusData("STATE", StateCensusAnalyser.Country.US);
            USCensus[] censusCSV = new Gson().fromJson(sortedCensusData, USCensus[].class);
            Assert.assertEquals("Alabama", censusCSV[0].state);

      }

      @Test
      public void getUSCensusData_WhenSortedOnPopulatedState_ShouldReturnSortedResult() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadCensusData(StateCensusAnalyser.Country.US, US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData("POPULAS_STATE", StateCensusAnalyser.Country.US);
            CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
            Assert.assertEquals(37253956, (int) censusCSV[0].population);
      }

      @Test
      public void getUSCensusData_WhenSortedOnDensity_ShouldReturnSortedResult() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadCensusData(StateCensusAnalyser.Country.US, US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData("US_POPULATION_DENSITY", StateCensusAnalyser.Country.US);
            USCensus[] censusCSV = new Gson().fromJson(sortedCensusData, USCensus[].class);
            Assert.assertEquals((Double) 3805.61, censusCSV[0].populationDensity);
      }

      @Test
      public void getUSCensusData_WhenSortedOnArea_ShouldReturnSortedResult() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadCensusData(StateCensusAnalyser.Country.US, US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData("US_POPULATION_DENSITY", StateCensusAnalyser.Country.US);
            USCensus[] censusCSV = new Gson().fromJson(sortedCensusData, USCensus[].class);
            Assert.assertEquals((Double) 177.0, censusCSV[0].area);
      }
}