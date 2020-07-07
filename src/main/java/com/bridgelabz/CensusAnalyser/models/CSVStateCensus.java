package com.bridgelabz.CensusAnalyser.models;
import com.opencsv.bean.CsvBindByName;
public class CSVStateCensus {
      @CsvBindByName(column = "State", required = true)
      public String state;
      @CsvBindByName(column = "Population", required = false)
      public Integer population;
      @CsvBindByName(column = "AreaInSqKm", required = true)
      public Integer areaInSqKm;
      @CsvBindByName(column = "DensityPerSqKm", required = true)
      public Double densityPerSqKm;
      @Override
      public String toString() {
            return "IndiaCensusCSV{" +
                    "State='" + state + '\'' +
                    ", Population='" + population + '\'' +
                    ", AreaInSqKm='" + areaInSqKm + '\'' +
                    ", DensityPerSqKm='" + densityPerSqKm + '\'' +
                    '}';
      }
      public CSVStateCensus() {}
      public CSVStateCensus(String state, Integer population, Integer areaInSqKm, Double densityPerSqKm) {
            this.state = state;
            this.population = population;
            this.areaInSqKm = areaInSqKm;
            this.densityPerSqKm = densityPerSqKm;

      }
}