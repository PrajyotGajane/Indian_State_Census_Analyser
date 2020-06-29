package com.bridgelabz.CensusAnalyser;

import com.bridgelabz.CensusAnalyser.controller.StateCensusAnalyser;
import com.bridgelabz.CensusAnalyser.exception.CensusAnalyserException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {
      private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
      private static final String INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
      private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
      private static final String WRONG_CSV_FILE_TYPE = "./src/main/resources/IndiaStateCensusData.jpg";
      @Test
      public void StatusCensusCsvFile_ReturnsCorrectRecords() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29,numOfRecords);
      }

      @Test
      public void givenStatusCensusCsvFile_WithWrongFile_ShouldThrowException() {
            try {
                  StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
                  ExpectedException exceptionRule = ExpectedException.none();
                  exceptionRule.expect(CensusAnalyserException.class);
                  censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
            } catch (CensusAnalyserException e) {
                  Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
            }
      }
      @Test
      public void givenStatusCensusCsvFile_WithWrongFileType_ShouldThrowException() {
            try {
                  StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
                  ExpectedException exceptionRule = ExpectedException.none();
                  exceptionRule.expect(CensusAnalyserException.class);
                  censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_TYPE);
            } catch (CensusAnalyserException e) {
                  Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
            }
      }
      @Test
      public void givenStatusCensusCsvFile_WithWrongHeader_ShouldThrowException() {
            try {
                  StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
                  ExpectedException exceptionRule = ExpectedException.none();
                  exceptionRule.expect(CensusAnalyserException.class);
                  censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            } catch (CensusAnalyserException e) {
                  Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_HEADER_FILE,e.type);
            }
      }

      @Test
      public void givenStatesCensusCsvFile_WithIncorrectDelimiter_ShouldThrowException() {
            try {
                  StateCensusAnalyser censusAnalyser =  new StateCensusAnalyser();
                  ExpectedException exceptionRule = ExpectedException.none();
                  exceptionRule.expect(CensusAnalyserException.class);
                  censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            } catch (CensusAnalyserException e){
                  Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_MISSING,e.type);
            }
      }
      @Test
      public void StatusStateCodeFile_ReturnsCorrectRecords() {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndianStateCode(INDIA_STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(37,numOfRecords);
      }
      @Test
      public void givenStatesCodeCsvFile_WithWrongFile_ShouldThrowException() {
            try {
                  StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
                  ExpectedException exceptionRule = ExpectedException.none();
                  exceptionRule.expect(CensusAnalyserException.class);
                  censusAnalyser.loadIndianStateCode(WRONG_CSV_FILE_PATH);
            } catch (CensusAnalyserException e) {
                  Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
            }
      }
      @Test
      public void givenStatesCodeCsvFile_WithWrongFileType_ShouldThrowException() {
            try {
                  StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
                  ExpectedException exceptionRule = ExpectedException.none();
                  exceptionRule.expect(CensusAnalyserException.class);
                  censusAnalyser.loadIndianStateCode(WRONG_CSV_FILE_TYPE);
            } catch (CensusAnalyserException e) {
                  Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
            }
      }
      @Test
      public void givenStatesCodesCsvFile_WithIncorrectDelimiter_ShouldThrowException() {
            try {
                  StateCensusAnalyser censusAnalyser =  new StateCensusAnalyser();
                  ExpectedException exceptionRule = ExpectedException.none();
                  exceptionRule.expect(CensusAnalyserException.class);
                  censusAnalyser.loadIndianStateCode(INDIA_STATE_CODE_CSV_FILE_PATH);
            } catch (CensusAnalyserException e){
                  Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_MISSING,e.type);
            }
      }
}
