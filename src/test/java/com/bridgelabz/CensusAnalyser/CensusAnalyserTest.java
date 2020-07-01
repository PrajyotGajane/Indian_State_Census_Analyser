package com.bridgelabz.CensusAnalyser;
import com.bridgelabz.CensusAnalyser.controller.StateCensusAnalyser;
import com.bridgelabz.CensusAnalyser.exception.CensusAnalyserException;
import com.bridgelabz.CensusAnalyser.models.CSVStateCensus;
import com.bridgelabz.CensusAnalyser.models.CSVStateCode;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
public class CensusAnalyserTest {
      private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
      private static final String INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
      private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
      private static final String WRONG_CSV_FILE_TYPE = "./src/main/resources/IndiaStateCensusData.jpg";
      private static final String WRONG_STATE_CODE_CSV_FILE_PATH = "./src/main/resources/IndiaStateCode.csv";
      private static final String WRONG_STATE_CODE_CSV_FILE_TYPE = "./src/test/resources/IndiaStateCode.jpg";
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
      public void getIndianCensusData_WhenSortedOnState_ShouldReturnSortedResult() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
      }
      @Test
      public void getIndianStateCodeData_WhenSortedOnState_ShouldReturnSortedResult() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            String sortedCensusData = censusAnalyser.getStateWiseSortedStateCodeData(INDIA_STATE_CODE_CSV_FILE_PATH);
            CSVStateCode[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCode[].class);
            Assert.assertEquals("Andaman and Nicobar Islands", censusCSV[0].stateName);
      }
}