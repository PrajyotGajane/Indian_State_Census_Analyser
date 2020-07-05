package com.bridgelabz.CensusAnalyser.controller;
import com.bridgelabz.CensusAnalyser.Utilities.CensusLoader;
import com.bridgelabz.CensusAnalyser.exception.CensusAnalyserException;
import com.bridgelabz.CensusAnalyser.models.*;
import com.google.gson.Gson;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
public class StateCensusAnalyser {
      public enum Country {INDIA, INDIA_STATE, US}
      public enum SortField {
            population, populationDensity, state, area,stateId,areaInSqKm;
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
      public List sortList(List listToSort, SortField fieldName) {
            listToSort.sort(Comparator.comparing(report -> {
                  try {
                        return (Comparable) report.getClass().getDeclaredField(String.valueOf(fieldName)).get(report);
                  } catch (Exception e) {
                        throw new RuntimeException("INVALID FIELD", e);
                  }
            }).reversed());
            return listToSort;
      }
      /**
       * to create a json file with passed file path and sorted string
       * @param csvFilePath
       */
}