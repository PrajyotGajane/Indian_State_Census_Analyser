package com.bridgelabz.CensusAnalyser.models;

public class CSVStateCodeDAO {
      public Integer tin;
      public Integer srNo;
      public String stateCode;
      public String state;

      public CSVStateCodeDAO(CSVStateCode stateCodeCSV){
            srNo = stateCodeCSV.srNo;
            state = stateCodeCSV.stateName;
            tin = stateCodeCSV.tin;
            stateCode = stateCodeCSV.stateCode;
      }

}
