package com.bridgelabz.CensusAnalyser.dao;

import com.bridgelabz.CensusAnalyser.controller.StateCensusAnalyser;
import com.bridgelabz.CensusAnalyser.models.CSVStateCensus;
import com.bridgelabz.CensusAnalyser.models.CSVStateCode;
import com.bridgelabz.CensusAnalyser.models.USCensus;

public class CensusDAO {
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

      public CensusDAO(CSVStateCensus indiaCensusCSV){
            state = indiaCensusCSV.state;
            areaInSqKm = indiaCensusCSV.areaInSqKm;
            densityPerSqKm = indiaCensusCSV.densityPerSqKm;
            population = indiaCensusCSV.population;
      }
      public CensusDAO(CSVStateCode stateCodeCSV){
            srNo = stateCodeCSV.srNo;
            state = stateCodeCSV.stateName;
            tin = stateCodeCSV.tin;
            stateCode = stateCodeCSV.stateCode;
      }
      public CensusDAO(USCensus usCensus) {
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
      public CensusDAO(String state, int areaInSqKm, double densityPerSqKm, double waterArea, String stateCode) {
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