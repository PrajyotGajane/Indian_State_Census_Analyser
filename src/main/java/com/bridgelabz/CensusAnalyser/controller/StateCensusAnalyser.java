package com.bridgelabz.CensusAnalyser.controller;
import com.bridgelabz.CensusAnalyser.adaptor.CensusAdaptorFactory;
import com.bridgelabz.CensusAnalyser.dao.CensusDAO;
import com.bridgelabz.CensusAnalyser.exception.CensusAnalyserException;
import com.bridgelabz.CensusAnalyser.models.*;
import com.google.gson.Gson;
import java.util.*;
import java.util.stream.Collectors;
public class StateCensusAnalyser {
      public enum Country {INDIA, US}
      List<CensusDAO> collect = null;
      Map<String, CensusDAO> censusCSVMap = null;
      public StateCensusAnalyser() {
            this.collect = new ArrayList<CensusDAO>() ;
            this.censusCSVMap = new HashMap<String, CensusDAO>();
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
                  case ParameterConstants.STATE :
                        censusDTOs = this.censusCSVMap.values()
                                .stream().sorted((CensusDAO c1, CensusDAO c2) -> c1.state.compareTo(c2.state))
                                .map(censusDAO -> censusDAO.getSpecificCensusData(country))
                                .collect(Collectors.toCollection(ArrayList::new));
                        break;
                  case ParameterConstants.POPULATION_STATE:
                        censusDTOs = this.censusCSVMap.values()
                                .stream().sorted((CensusDAO c1, CensusDAO c2) -> c2.population.compareTo(c1.population))
                                .map(censusDAO -> censusDAO.getSpecificCensusData(country))
                                .collect(Collectors.toCollection(ArrayList::new));
                        break;
                  case ParameterConstants.POPULATION_DENSITY :
                        censusDTOs = this.censusCSVMap.values()
                                .stream().sorted((CensusDAO c1, CensusDAO c2) -> c2.densityPerSqKm.compareTo(c1.densityPerSqKm))
                                .map(censusDAO -> censusDAO.getSpecificCensusData(country))
                                .collect(Collectors.toCollection(ArrayList::new));
                        break;
                  case ParameterConstants.AREA :
                        censusDTOs = this.censusCSVMap.values()
                                .stream().sorted((CensusDAO c1, CensusDAO c2) -> c2.area.compareTo(c1.area))
                                .map(censusDAO -> censusDAO.getSpecificCensusData(country))
                                .collect(Collectors.toCollection(ArrayList::new));
                        break;
                  case ParameterConstants.AREA_IN_SQR_KM :
                        censusDTOs = this.censusCSVMap.values()
                                .stream().sorted((CensusDAO c1, CensusDAO c2) -> c2.areaInSqKm.compareTo(c1.areaInSqKm))
                                .map(censusDAO -> censusDAO.getSpecificCensusData(country))
                                .collect(Collectors.toCollection(ArrayList::new));
                        break;
                  case ParameterConstants.US_POPULATION_DENSITY :
                        censusDTOs = this.censusCSVMap.values()
                                .stream().sorted((CensusDAO c1, CensusDAO c2) -> c2.populationDensity.compareTo(c1.populationDensity))
                                .map(censusDAO -> censusDAO.getSpecificCensusData(country))
                                .collect(Collectors.toCollection(ArrayList::new));
                        break;
                  case ParameterConstants.US_POPULATION_STATE_ID :
                        censusDTOs = this.censusCSVMap.values()
                                .stream().sorted((CensusDAO c1, CensusDAO c2) -> c2.stateId.compareTo(c1.stateId))
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