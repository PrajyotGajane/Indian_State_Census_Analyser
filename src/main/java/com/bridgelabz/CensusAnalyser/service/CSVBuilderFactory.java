package com.bridgelabz.CensusAnalyser.service;

import com.bridgelabz.CensusAnalyser.controller.ICSVBuilder;

public class CSVBuilderFactory {
      public static ICSVBuilder createCSVBuilder() {
            return new OpenCSVBuilder();
      }
}
