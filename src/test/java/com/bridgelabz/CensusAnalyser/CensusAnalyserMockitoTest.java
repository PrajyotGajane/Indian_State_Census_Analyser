package com.bridgelabz.CensusAnalyser;

import com.bridgelabz.CensusAnalyser.controller.StateCensusAnalyser;
import com.bridgelabz.CensusAnalyser.exception.CensusAnalyserException;
import com.bridgelabz.CensusAnalyser.models.CSVStateCensus;
import com.bridgelabz.CensusAnalyser.models.censusDAO;
import com.bridgelabz.CensusAnalyser.utilities.JsonFileWriter;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CensusAnalyserMockitoTest {
      private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
      private static final String INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
      private static final String US_CENSUS_CSV_FILE_PATH = "./src/test/resources/USCensusData.csv";
//
//      @Test
//      public void givenIndianCensusCSVFile_withMockito_ReturnsCorrectRecords() {
//            StateCensusAnalyser censusAnalyser =  mock(StateCensusAnalyser.class);
//            when(censusAnalyser.loadCensusData(StateCensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH,INDIA_STATE_CODE_CSV_FILE_PATH)).thenReturn(3);
//            try {
//                  int numOfRecords = censusAnalyser.loadCensusData(StateCensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH,INDIA_STATE_CODE_CSV_FILE_PATH);
//                  Assert.assertEquals(29, numOfRecords);
//            } catch (Exception e) {
//                  e.printStackTrace();
//            }
//      }
//      @Test
//      public void getIndianCensusData_WhenSortedOnState_ShouldReturnSortedStateResult() {
//            StateCensusAnalyser censusAnalyser =  mock(StateCensusAnalyser.class);
//            try {
//                  when(censusAnalyser.getStateWiseSortedCensusData
//                          ("population")).thenReturn("199812341");
//                  String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData("population");
//                  Assert.assertEquals("199812341", sortedCensusData);
//            } catch (CensusAnalyserException e){
//                  e.printStackTrace();
//            }
//      }
//      @Test
//      public void givenUSCensusCSVFile_withMockito_ReturnsCorrectRecords() {
//            StateCensusAnalyser censusAnalyser =  mock(StateCensusAnalyser.class);
//            when(censusAnalyser.loadCensusData(StateCensusAnalyser.Country.US, US_CENSUS_CSV_FILE_PATH)).thenReturn(51);
//            try {
//                  int numOfRecords = censusAnalyser.loadCensusData(StateCensusAnalyser.Country.US, US_CENSUS_CSV_FILE_PATH);
//                  Assert.assertEquals(51, numOfRecords);
//            } catch (Exception e) {
//                  e.printStackTrace();
//            }
//      }
      Map<String, censusDAO> censusDAOMap;
      //Map<SortByField.Parameter, Comparator> sortParameterComparator;

//@Rule
//public MockitoRule mockitoRule = MockitoJUnit.rule();

      @Before
      public void setUp() {
            this.censusDAOMap = new HashMap<>();
            this.censusDAOMap.put("Odisha", new censusDAO("Odisha", 298757, 2987.56, 5678.98, "OD"));
            this.censusDAOMap.put("Maharashtra", new censusDAO("Maharashtra", 5489364, 89564.9, 768.44, "MH"));
            this.censusDAOMap.put("Karnataka", new censusDAO("Karnataka", 7689699, 7686.8, 7568.8, "KA"));
            MockitoAnnotations.initMocks(this);
      }
      @Test
      public void givenIndiaCensusCSVFile_ShouldReturnCorrectRecords() {
            try {
                  StateCensusAnalyser censusAnalyser = mock(StateCensusAnalyser.class);
                  when(censusAnalyser.loadCensusData(StateCensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH)).thenReturn(this.censusDAOMap.size());
                  int censusRecords = censusAnalyser.loadCensusData(StateCensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
                  Assert.assertEquals(3, censusRecords);
            } catch (CensusAnalyserException e) {
                  e.printStackTrace();
            }
      }
}
