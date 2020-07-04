package com.bridgelabz.CensusAnalyser.models;

public class censusDAO {
      public Integer areaInSqKm;
      public Integer density;
      public Integer population;
      public String state;
      public censusDAO(CSVStateCensus indiaCensusCSV){
            state = indiaCensusCSV.state;
            areaInSqKm = indiaCensusCSV.areaInSqKm;
            density = indiaCensusCSV.densityPerSqKm;
            population = indiaCensusCSV.population;
      }

      public Integer tin;
      public Integer srNo;
      public String stateCode;
      public String stateName;
      public censusDAO(CSVStateCode stateCodeCSV){
            srNo = stateCodeCSV.srNo;
            stateName = stateCodeCSV.stateName;
            tin = stateCodeCSV.tin;
            stateCode = stateCodeCSV.stateCode;
      }
      public Double area;
      public String stateId;
      public Integer populationUS;
      public Double housingDensity;
      public Double populationDensity;
      public Double landArea;
      public Double waterArea;
      public Integer housingUnits;
      public String stateNameUS;
      public censusDAO(USCensus usCensus) {
            stateId = usCensus.stateId;
            stateNameUS = usCensus.state;
            populationUS = usCensus.population;
            housingUnits = usCensus.housingUnits;
            area = usCensus.area;
            waterArea = usCensus.waterArea;
            landArea = usCensus.landArea;
            populationDensity = usCensus.populationDensity;
            housingDensity = usCensus.housingDensity;
      }
}
