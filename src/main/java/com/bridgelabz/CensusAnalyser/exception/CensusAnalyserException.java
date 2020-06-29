package com.bridgelabz.CensusAnalyser.exception;

public class CensusAnalyserException extends RuntimeException {
      public enum ExceptionType {
           CENSUS_FILE_PROBLEM,CENSUS_FILE_WRONG_EXTENSION,WRONG_HEADER_FILE,DELIMITER_MISSING
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
