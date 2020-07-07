package com.bridgelabz.CensusAnalyser.adaptor;
import com.bridgelabz.CensusAnalyser.models.USCensus;
import com.bridgelabz.CensusAnalyser.dao.CensusDAO;
import java.util.Map;
public class USCensusAdaptor extends CensusAdaptor {
      public Map<String, CensusDAO> loadCensusData(String... csvFilePath) {
            Map<String, CensusDAO> censusDTOMap = super.loadCensusData(USCensus.class, csvFilePath[0]);
            return censusDTOMap;
      }
}
