package com.bridgelabz.CensusAnalyser.service;

public class CSVBuilderFactory {
      public static ICSVBuilder createCSVBuilder() {
            return new OpenCSVBuilder();
      }
}
