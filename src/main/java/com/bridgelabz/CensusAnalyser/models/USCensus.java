package com.bridgelabz.CensusAnalyser.models;
import com.opencsv.bean.CsvBindByName;
public class USCensus {
      @CsvBindByName(column = "State Id", required = true)
      public String stateId;
      @CsvBindByName(column = "State", required = false)
      public String state;
      @CsvBindByName(column = "Population", required = true)
      public Integer population;
      @CsvBindByName(column = "Housing units", required = true)
      public Integer housingUnits;
      @CsvBindByName(column = "Total area", required = true)
      public Double area;
      @CsvBindByName(column = "Water area", required = true)
      public Double waterArea;
      @CsvBindByName(column = "Land area", required = true)
      public Double landArea;
      @CsvBindByName(column = "Population Density", required = true)
      public Double populationDensity;
      @CsvBindByName(column = "Housing Density", required = true)
      public Double housingDensity;
      @Override
      public String toString() {
            return "USCensus{" +
                    "stateId='" + stateId + '\'' +
                    ", state='" + state + '\'' +
                    ", population='" + population + '\'' +
                    ", housingUnits='" + housingUnits + '\'' +
                    ", area='" + area + '\'' +
                    ", waterArea='" + waterArea + '\'' +
                    ", landArea='" + landArea + '\'' +
                    ", populationDensity='" + populationDensity + '\'' +
                    ", housingDensity='" + housingDensity + '\'' +
                    '}';
      }
      public USCensus(){}
      public USCensus(String state, String stateId, Double area, Integer population, Double populationDensity, Double totalArea) {
            this.state = state;
            this.area = area;
            this.stateId = stateId;
            this.population = population;
            this.populationDensity = populationDensity;
            this.area = totalArea;
      }
}
