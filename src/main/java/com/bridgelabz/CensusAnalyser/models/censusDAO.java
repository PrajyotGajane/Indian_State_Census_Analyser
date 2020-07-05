package com.bridgelabz.CensusAnalyser.models;
public class censusDAO {
      public Integer areaInSqKm;
      public Integer population;
      public String state;
      public Integer tin;
      public Integer srNo;
      public String stateCode;
      public Double area;
      public String stateId;
      public Double housingDensity;
      public Double populationDensity;
      public Double landArea;
      public Double waterArea;
      public Integer housingUnits;
      public String stateNameUS;
      public censusDAO(CSVStateCensus indiaCensusCSV){
            state = indiaCensusCSV.state;
            areaInSqKm = indiaCensusCSV.areaInSqKm;
            populationDensity = indiaCensusCSV.densityPerSqKm;
            population = indiaCensusCSV.population;
      }
      public censusDAO(CSVStateCode stateCodeCSV){
            srNo = stateCodeCSV.srNo;
            state = stateCodeCSV.stateName;
            tin = stateCodeCSV.tin;
            stateCode = stateCodeCSV.stateCode;
      }
      public censusDAO(USCensus usCensus) {
            stateId = usCensus.stateId;
            stateNameUS = usCensus.state;
            population = usCensus.population;
            housingUnits = usCensus.housingUnits;
            area = usCensus.area;
            waterArea = usCensus.waterArea;
            landArea = usCensus.landArea;
            populationDensity = usCensus.populationDensity;
            housingDensity = usCensus.housingDensity;
      }
}