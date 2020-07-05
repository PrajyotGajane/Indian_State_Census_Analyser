package com.bridgelabz.CensusAnalyser.controller;
import com.bridgelabz.CensusAnalyser.utilities.CensusLoader;
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
      public enum Country {INDIA, INDIA_STATE, US}
      public enum SortField {
            population, populationDensity, state, area,stateId,areaInSqKm,stateCode;
      }
      List<censusDAO> collect;
      public StateCensusAnalyser() {
            this.collect = new ArrayList<>();
      }
      Map<String, censusDAO> censusCSVMap = new HashMap<>();
      /**
       * to load India and US census data
       * @param country
       * @param csvFilePath
       * @return size of the map
       */
      public int loadCensusData(Country country, String... csvFilePath){
            censusCSVMap = new CensusLoader().loadCensusData(country, csvFilePath);
            return censusCSVMap.size();
      }
      /**
       * to get sorted data
       * @param sortField
       * @return sorted data for test cases
       */
      public String getStateWiseSortedCensusData(SortField sortField) {
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
       * @param listToSort
       * @param fieldName
       * @return sorted list
       */
      public List sortList(List listToSort, SortField fieldName) {
            listToSort.sort(Comparator.comparing(censusData -> {
                  try {
                        return (Comparable) censusData.getClass().getDeclaredField(String.valueOf(fieldName)).get(censusData);
                  } catch (Exception e) {
                        throw new RuntimeException("INVALID FIELD PASSED", e);
                  }
            }).reversed());
            return listToSort;
      }
}