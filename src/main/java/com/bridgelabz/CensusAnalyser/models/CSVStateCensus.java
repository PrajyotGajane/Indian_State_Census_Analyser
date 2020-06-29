package com.bridgelabz.CensusAnalyser.models;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus {
      @CsvBindByName(column = "State", required = true)
      public String state;

      @CsvBindByName(column = "Population", required = false)
      public int population;

      @CsvBindByName(column = "AreaInSqKm", required = true)
      public int areaInSqKm;

      @CsvBindByName(column = "DensityPerSqKm", required = true)
      public int densityPerSqKm;

      @Override
      public String toString() {
            return "IndiaCensusCSV{" +
                    "State='" + state + '\'' +
                    ", Population='" + population + '\'' +
                    ", AreaInSqKm='" + areaInSqKm + '\'' +
                    ", DensityPerSqKm='" + densityPerSqKm + '\'' +
                    '}';
      }
}
