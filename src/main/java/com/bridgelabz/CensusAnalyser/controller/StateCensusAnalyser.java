package com.bridgelabz.CensusAnalyser.controller;
import com.bridgelabz.CensusAnalyser.adaptor.CensusAdaptorFactory;
import com.bridgelabz.CensusAnalyser.exception.CensusAnalyserException;
import com.bridgelabz.CensusAnalyser.models.*;
import com.google.gson.Gson;
import java.util.*;
import java.util.stream.Collectors;
public class StateCensusAnalyser {
      public enum Country {INDIA, US}
      List<censusDAO> collect = null;
      Map<String, censusDAO> censusCSVMap = null;
      Map<SortField, Comparator<censusDAO>> sortMap;
      public StateCensusAnalyser() {
            this.collect = new ArrayList<censusDAO>() ;
            this.censusCSVMap = new HashMap<String, censusDAO>();
            this.sortMap = new HashMap<>();
      }

      /**
       * to load India and US census data
       * @param country
       * @param csvFilePath
       * @return size of the map
       */
      public int loadCensusData(Country country, String... csvFilePath) throws CensusAnalyserException {
            censusCSVMap = new CensusAdaptorFactory().getCensusAdaptor(country, csvFilePath);
            collect.addAll(censusCSVMap.values());
            return censusCSVMap.values().size();
      }
      /**
       * to get sorted data
       * @return sorted data for test cases
       */
      public String getStateWiseSortedCensusData(String sortingParam,Country country) {
            String sortedStateCensusJson = "";
            ArrayList<Object> censusDTOs = new ArrayList<>();
            switch(sortingParam){
                  case ParamConstants.STATE :
                        censusDTOs = this.censusCSVMap.values()
                                .stream().sorted((censusDAO c1, censusDAO c2) -> c1.state.compareTo(c2.state))
                                .map(censusDAO -> censusDAO.getSpecificCensusData(country))
                                .collect(Collectors.toCollection(ArrayList::new));
                        break;
                  case ParamConstants.POPULAS_STATE :
                        censusDTOs = this.censusCSVMap.values()
                                .stream().sorted((censusDAO c1, censusDAO c2) -> c2.population.compareTo(c1.population))
                                .map(censusDAO -> censusDAO.getSpecificCensusData(country))
                                .collect(Collectors.toCollection(ArrayList::new));
                        break;
                  case ParamConstants.POPULATION_DENSITY :
                        censusDTOs = this.censusCSVMap.values()
                                .stream().sorted((censusDAO c1, censusDAO c2) -> c2.densityPerSqKm.compareTo(c1.densityPerSqKm))
                                .map(censusDAO -> censusDAO.getSpecificCensusData(country))
                                .collect(Collectors.toCollection(ArrayList::new));
                        break;
                  case ParamConstants.AREA :
                        censusDTOs = this.censusCSVMap.values()
                                .stream().sorted((censusDAO c1, censusDAO c2) -> c2.area.compareTo(c1.area))
                                .map(censusDAO -> censusDAO.getSpecificCensusData(country))
                                .collect(Collectors.toCollection(ArrayList::new));
                        break;
                  case ParamConstants.AREA_IN_SQR_KM :
                        censusDTOs = this.censusCSVMap.values()
                                .stream().sorted((censusDAO c1, censusDAO c2) -> c2.areaInSqKm.compareTo(c1.areaInSqKm))
                                .map(censusDAO -> censusDAO.getSpecificCensusData(country))
                                .collect(Collectors.toCollection(ArrayList::new));
                        break;
                  case ParamConstants.US_POPULATION_DENSITY :
                        censusDTOs = this.censusCSVMap.values()
                                .stream().sorted((censusDAO c1, censusDAO c2) -> c2.populationDensity.compareTo(c1.populationDensity))
                                .map(censusDAO -> censusDAO.getSpecificCensusData(country))
                                .collect(Collectors.toCollection(ArrayList::new));
                        break;
                  case ParamConstants.US_POPULATION_STATE_ID :
                        censusDTOs = this.censusCSVMap.values()
                                .stream().sorted((censusDAO c1, censusDAO c2) -> c2.stateId.compareTo(c1.stateId))
                                .map(censusDAO -> censusDAO.getSpecificCensusData(country))
                                .collect(Collectors.toCollection(ArrayList::new));
                        break;
                  default:
                        System.out.println("Wrong parameter");
            }
            sortedStateCensusJson = new Gson().toJson(censusDTOs);
            return sortedStateCensusJson;
      }
}