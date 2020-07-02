package com.bridgelabz.CensusAnalyser.models;

public class CSVStateCodeDAO {
      public Integer tin;
      public Integer srNo;
      public String stateCode;
      public String state;

      public CSVStateCodeDAO(CSVStateCode stateCodeCSV){
            state = stateCodeCSV.stateName;
            tin = stateCodeCSV.tin;
            srNo = stateCodeCSV.srNo;
            stateCode = stateCodeCSV.stateCode;

      }
}
