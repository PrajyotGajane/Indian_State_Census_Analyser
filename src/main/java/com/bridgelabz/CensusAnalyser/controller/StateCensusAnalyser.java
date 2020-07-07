package com.bridgelabz.CensusAnalyser.controller;
import com.bridgelabz.CensusAnalyser.utilities.CensusAdaptorFactory;
import com.bridgelabz.CensusAnalyser.exception.CensusAnalyserException;
import com.bridgelabz.CensusAnalyser.models.*;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class StateCensusAnalyser {
      public enum Country {INDIA, US}
      List<censusDAO> collect;
      Map<String, censusDAO> censusCSVMap = new HashMap<>();
      public StateCensusAnalyser() {
            this.collect = new ArrayList<>();
      }
      /**
       * to load India and US census data
       * @param country
       * @param csvFilePath
       * @return size of the map
       */
      public int loadCensusData(Country country, String... csvFilePath) throws CensusAnalyserException {
            censusCSVMap = new CensusAdaptorFactory().getCensusAdaptor(country, csvFilePath);

            return censusCSVMap.size();
      }
      /**
       * to get sorted data
       * @return sorted data for test cases
       */
      public String getStateWiseSortedCensusData(){
            if (censusCSVMap == null || censusCSVMap.size() == 0) {
                  throw new CensusAnalyserException("No Census Data",
                          CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            }
            collect = censusCSVMap.values().stream().collect(Collectors.toList());
            ArrayList<Object> censusDTOs = this.censusCSVMap.values()
                    .stream().sorted((censusDAO c1, censusDAO c2) -> c1.state.compareTo(c2.state))
                    .map(censusDAO -> censusDAO.getSpecificCensusData(Country.INDIA))
                    .collect(Collectors.toCollection(ArrayList::new));
           String sortedStateCensusJson = new Gson().toJson(censusDTOs) ;
            return sortedStateCensusJson;
}
 public String getStateWiseSortedCensusData(String sortField) {
            if (censusCSVMap == null || censusCSVMap.size() == 0) {
                  throw new CensusAnalyserException("No Census Data",
                          CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            }
            collect = censusCSVMap.values().stream().collect(Collectors.toList());
            this.sortList(collect, sortField);
            String sortedStateCensusJson = new Gson().toJson(collect);
            return sortedStateCensusJson;
      }
      /**
       * to sort list according to field name
       * @param listSort
       * @param fieldName
       * @return sorted list
       */
      public List sortList(List listSort, String fieldName) {
            listSort.sort(Comparator.comparing(censusData -> {
                  try {
                        return (Comparable) censusData.getClass().getDeclaredField(String.valueOf(fieldName)).get(censusData);
                  } catch (Exception e) {
                        throw new RuntimeException("INVALID FIELD PASSED", e);
                  }
            }).reversed());
            return listSort;
      }

}