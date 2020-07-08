package com.bridgelabz.CensusAnalyser;
import com.bridgelabz.CensusAnalyser.adaptor.CensusAdaptor;
import com.bridgelabz.CensusAnalyser.adaptor.IndiaCensusAdaptor;
import com.bridgelabz.CensusAnalyser.adaptor.USCensusAdaptor;
import com.bridgelabz.CensusAnalyser.controller.StateCensusAnalyser;
import com.bridgelabz.CensusAnalyser.exception.CensusAnalyserException;
import com.bridgelabz.CensusAnalyser.dao.CensusDAO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
public class CensusAnalyserMockitoTest {
      private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
      private static final String INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
      private static final String US_CENSUS_CSV_FILE_PATH = "./src/test/resources/USCensusData.csv";
      Map<String, CensusDAO> censusDAOMap;
      @Before
      public void setUp() {
            this.censusDAOMap = new HashMap<>();
            this.censusDAOMap.put("Odisha", new CensusDAO("Odisha", 298757, 2987.56, 5678.98, "OD"));
            this.censusDAOMap.put("Maharashtra", new CensusDAO("Maharashtra", 5489364, 89564.9, 768.44, "MH"));
            this.censusDAOMap.put("Karnataka", new CensusDAO("Karnataka", 7689699, 7686.8, 7568.8, "KA"));
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

      @Test
      public void givenUSCensusData_withMockito_ShouldReturnCorrectRecord() {
            StateCensusAnalyser censusAnalyser = mock(StateCensusAnalyser.class);
            when(censusAnalyser.loadCensusData(StateCensusAnalyser.Country.US, US_CENSUS_CSV_FILE_PATH)).thenReturn(51);
            int numOfState = censusAnalyser.loadCensusData(StateCensusAnalyser.Country.US, US_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(51, numOfState);
      }

      @Test
      public void givenIndiaCensusAdaptorMock_ShouldReturnCorrectRecords() {
            try {
                  CensusAdaptor censusAdapter = mock(IndiaCensusAdaptor.class);
                  when(censusAdapter.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH)).thenReturn(this.censusDAOMap);
                  Map<String, CensusDAO> censusDAOMapActual = censusAdapter.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
                  Assert.assertEquals(3, censusDAOMapActual.size());
            } catch (CensusAnalyserException e) {
                  e.printStackTrace();
            }
      }
      @Test
      public void givenUSCensusAdaptorMock_ShouldReturnCorrectRecords() {
            try {
                  CensusAdaptor censusAdapter = mock(USCensusAdaptor.class);
                  when(censusAdapter.loadCensusData(US_CENSUS_CSV_FILE_PATH)).thenReturn(this.censusDAOMap);
                  Map<String, CensusDAO> censusDAOMapActual = censusAdapter.loadCensusData(US_CENSUS_CSV_FILE_PATH);
                  Assert.assertEquals(3, censusDAOMapActual.size());
            } catch (CensusAnalyserException e) {
                  e.printStackTrace();
            }
      }
}