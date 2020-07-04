package com.bridgelabz.CensusAnalyser.exception;
public class CensusAnalyserException extends RuntimeException {
      public enum ExceptionType {
           CENSUS_FILE_PROBLEM,WRONG_HEADER_FILE,DELIMITER_MISSING, INVALID_COUNTRY, UNABLE_TO_PARSE
      }
      public ExceptionType type;
      public CensusAnalyserException(String message, ExceptionType type) {
            super(message);
            this.type = type;
      }
}
