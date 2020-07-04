package com.bridgelabz.CensusAnalyser.models;

public class CSVStateCensusDAO {
      public Integer areaInSqKm;
      public Integer density;
      public Integer population;
      public String state;
      public CSVStateCensusDAO(CSVStateCensus indiaCensusCSV){
            state = indiaCensusCSV.state;
            areaInSqKm = indiaCensusCSV.areaInSqKm;
            density = indiaCensusCSV.densityPerSqKm;
            population = indiaCensusCSV.population;
      }
}
