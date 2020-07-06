package com.bridgelabz.CensusAnalyser.utilities;

import com.bridgelabz.CensusAnalyser.exception.CensusAnalyserException;
import com.bridgelabz.CensusAnalyser.models.CSVStateCensus;
import com.bridgelabz.CensusAnalyser.models.CSVStateCode;
import com.bridgelabz.CensusAnalyser.models.USCensus;
import com.bridgelabz.CensusAnalyser.models.censusDAO;
import com.bridgelabz.CensusAnalyser.service.CSVBuilderException;
import com.bridgelabz.CensusAnalyser.service.CSVBuilderFactory;
import com.bridgelabz.CensusAnalyser.service.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class USCensusAdaptor extends CensusAdaptor {
      public Map<String, censusDAO> loadCensusData(String... csvFilePath) {
            Map<String, censusDAO> censusDTOMap = super.loadCensusData(USCensus.class, csvFilePath[0]);
            return censusDTOMap;
      }
}
