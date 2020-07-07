package com.bridgelabz.CensusAnalyser.models;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

//      public enum SortField {
//            POPULATION, POPULATION_DENSITY, TOTAL_AREA, STATE, STATE_ID;
//      }
public class SortField {
      static Map<Parameter, Comparator> censusSortMap = new HashMap<>();

      public enum Parameter {
            STATE, POPULATION, AREA, DENSITY, STATE_CODE;
      }

      /**
       * @param parameter
       * @return
       */
      public static Comparator getParameter(Parameter parameter) {

            Comparator<censusDAO> stateComparator = Comparator.comparing(census -> census.state);
            Comparator<censusDAO> populationComparator = Comparator.comparing(census -> census.population);
            Comparator<censusDAO> areaComparator = Comparator.comparing(census -> census.area);
            Comparator<censusDAO> densityComparator = Comparator.comparing(census -> census.populationDensity);
            Comparator<censusDAO> stateCodeComparator = Comparator.comparing(census -> census.stateCode);

            censusSortMap.put(Parameter.STATE, stateComparator);
            censusSortMap.put(Parameter.POPULATION, populationComparator);
            censusSortMap.put(Parameter.AREA, areaComparator);
            censusSortMap.put(Parameter.DENSITY, densityComparator);
            censusSortMap.put(Parameter.STATE_CODE, stateCodeComparator);

            Comparator<censusDAO> comparator = censusSortMap.get(parameter);

            return comparator;
      }
}
