package com.bridgelabz.CensusAnalyser.models;
public class USCensusDAO {
      public Double area;
      public String stateId;
      public Integer population;
      public Double housingDensity;
      public Double populationDensity;
      public Double landArea;
      public Double waterArea;
      public Integer housingUnits;
      public String state;
      public USCensusDAO(USCensus usCensus) {
            stateId = usCensus.stateId;
            state = usCensus.state;
            population = usCensus.population;
            housingUnits = usCensus.housingUnits;
            area = usCensus.area;
            waterArea = usCensus.waterArea;
            landArea = usCensus.landArea;
            populationDensity = usCensus.populationDensity;
            housingDensity = usCensus.housingDensity;
      }
}
