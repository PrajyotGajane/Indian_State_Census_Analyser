package com.bridgelabz.CensusAnalyser.models;
public class USCensusDAO {
      public Integer area;
      public String stateId;
      public Integer population;
      public Integer housingDensity;
      public Integer populationDensity;
      public Integer landArea;
      public Integer waterArea;
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
