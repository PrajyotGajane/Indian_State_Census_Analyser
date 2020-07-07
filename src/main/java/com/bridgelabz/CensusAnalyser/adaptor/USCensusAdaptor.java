package com.bridgelabz.CensusAnalyser.adaptor;

import com.bridgelabz.CensusAnalyser.adaptor.CensusAdaptor;
import com.bridgelabz.CensusAnalyser.models.USCensus;
import com.bridgelabz.CensusAnalyser.models.censusDAO;

import java.util.Map;

public class USCensusAdaptor extends CensusAdaptor {
      public Map<String, censusDAO> loadCensusData(String... csvFilePath) {
            Map<String, censusDAO> censusDTOMap = super.loadCensusData(USCensus.class, csvFilePath[0]);
            return censusDTOMap;
      }
}
