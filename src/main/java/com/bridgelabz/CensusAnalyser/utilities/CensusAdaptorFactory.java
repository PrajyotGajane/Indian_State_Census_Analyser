package com.bridgelabz.CensusAnalyser.utilities;

import com.bridgelabz.CensusAnalyser.controller.StateCensusAnalyser;
import com.bridgelabz.CensusAnalyser.exception.CensusAnalyserException;
import com.bridgelabz.CensusAnalyser.models.censusDAO;

import java.util.Map;

public class CensusAdaptorFactory {
      public static Map<String, censusDAO> getCensusAdaptor(StateCensusAnalyser.Country country, String... csvFilePath) {
            if (country.equals(StateCensusAnalyser.Country.INDIA))
                  return new IndiaCensusAdaptor().loadCensusData(csvFilePath);
            if (country.equals(StateCensusAnalyser.Country.US))
                  return new USCensusAdaptor().loadCensusData(csvFilePath);
            throw new CensusAnalyserException("Invalid Country Name", CensusAnalyserException.ExceptionType.INVALID_COUNTRY);
      }
}
