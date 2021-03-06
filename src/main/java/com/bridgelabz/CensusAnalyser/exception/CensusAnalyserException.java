package com.bridgelabz.CensusAnalyser.exception;
public class CensusAnalyserException extends RuntimeException {
      public enum ExceptionType {
           CENSUS_FILE_PROBLEM,WRONG_HEADER_FILE_OR_DELIMITER, INVALID_COUNTRY
      }
      public ExceptionType type;
      public CensusAnalyserException(String message, ExceptionType type) {
            super(message);
            this.type = type;
      }
}
