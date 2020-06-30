package com.bridgelabz.CensusAnalyser.controller;
import com.bridgelabz.CensusAnalyser.exception.CensusAnalyserException;
import com.bridgelabz.CensusAnalyser.models.CSVStateCensus;
import com.bridgelabz.CensusAnalyser.models.CSVStateCode;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;
public class StateCensusAnalyser {
      /**
       * to load csv file data into the program
       * @param csvFilePath
       * @return total number of records
       * @throws CensusAnalyserException
       */
      public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
            try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
                  Iterator<CSVStateCensus> censusCSVIterator = this.getCSVFileIterator(reader, CSVStateCensus.class);
                  Iterable<CSVStateCensus> csvIterable = () -> censusCSVIterator;
                  int numberOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
                  return numberOfEntries;
            } catch (NoSuchFileException e) {
                  throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            } catch (IOException e) {
                  throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            } catch (RuntimeException e) {
                  throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.DELIMITER_MISSING);
            }
      }
      /**
       * to load csv file data into the program
       * @param csvFilePath
       * @return to total number of records
       * @throws CensusAnalyserException
       */
      public int loadIndianStateCode(String csvFilePath) throws CensusAnalyserException {
            try (Reader readerState = Files.newBufferedReader(Paths.get(csvFilePath))) {
                  Iterator<CSVStateCode> stateCSVIterator = this.getCSVFileIterator(readerState, CSVStateCode.class);
                  Iterable<CSVStateCode> csvIterable = () -> stateCSVIterator;
                  int numberOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
                  return numberOfEntries;
            } catch (NoSuchFileException e) {
                  throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            } catch (IOException e) {
                  throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            } catch (RuntimeException e) {
                  throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.DELIMITER_MISSING);
            }
      }
      private <E> Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CensusAnalyserException{
            try{
                  CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
                  csvToBeanBuilder.withType(csvClass);
                  csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
                  CsvToBean<E> csvToBean = csvToBeanBuilder.build();
                  return csvToBean.iterator();
            } catch (IllegalStateException e) {
                  throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
            }
      }
}