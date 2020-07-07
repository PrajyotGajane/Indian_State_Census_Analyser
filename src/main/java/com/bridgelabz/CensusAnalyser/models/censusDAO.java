package com.bridgelabz.CensusAnalyser.models;

import com.bridgelabz.CensusAnalyser.controller.StateCensusAnalyser;

public class censusDAO {
      public Double densityPerSqKm;
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

      public censusDAO(CSVStateCensus indiaCensusCSV){
            state = indiaCensusCSV.state;
            areaInSqKm = indiaCensusCSV.areaInSqKm;
            //populationDensity = indiaCensusCSV.densityPerSqKm;
            densityPerSqKm = indiaCensusCSV.densityPerSqKm;
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
            state = usCensus.state;
            population = usCensus.population;
            housingUnits = usCensus.housingUnits;
            area = usCensus.area;
            waterArea = usCensus.waterArea;
            landArea = usCensus.landArea;
            populationDensity = usCensus.populationDensity;
            housingDensity = usCensus.housingDensity;
      }
      public censusDAO(String state, int areaInSqKm, double densityPerSqKm, double waterArea, String stateCode) {
            this.state = state;
            this.areaInSqKm = areaInSqKm;
            this.densityPerSqKm = densityPerSqKm;
            this. waterArea = waterArea;
            this.stateCode = stateCode;
      }
      public Object getSpecificCensusData(StateCensusAnalyser.Country country){
            if(country.equals(StateCensusAnalyser.Country.INDIA))
                  return new CSVStateCensus(this.state,this.population,this.areaInSqKm,this.densityPerSqKm);
            return new USCensus(this.state, this.stateId,this.area, this.population, this.populationDensity, this.area);
      }
}