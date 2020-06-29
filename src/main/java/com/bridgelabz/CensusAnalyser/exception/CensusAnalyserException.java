package com.bridgelabz.CensusAnalyser.exception;

public class CensusAnalyserException extends RuntimeException {
      public enum ExceptionType {
            UNABLE_TO_PARSE, NO_CENSUS_DATA, INVALID_COUNTRY, CENSUS_FILE_PROBLEM
      }
      public ExceptionType type;
      public CensusAnalyserException(String message, ExceptionType type) {
            super(message);
            this.type = type;
      }
      public CensusAnalyserException(String message, ExceptionType type, Throwable cause) {
            super(message, cause);
            this.type = type;
      }
}
