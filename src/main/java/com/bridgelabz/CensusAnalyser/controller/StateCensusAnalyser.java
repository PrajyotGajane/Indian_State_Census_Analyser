package com.bridgelabz.CensusAnalyser.controller;
import com.bridgelabz.CensusAnalyser.exception.CensusAnalyserException;
import com.bridgelabz.CensusAnalyser.models.CSVStateCensus;
import com.bridgelabz.CensusAnalyser.models.CSVStateCode;
import com.bridgelabz.CensusAnalyser.service.CSVBuilderFactory;
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
                  ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
                  Iterator<CSVStateCensus> censusCSVIterator = csvBuilder.
                                                            getCSVFileIterator(reader, CSVStateCensus.class);
                  return this.getCount(censusCSVIterator);
            } catch (NoSuchFileException e) {
                  throw new CensusAnalyserException(e.getMessage(),
                                                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            } catch (IOException e) {
                  throw new CensusAnalyserException(e.getMessage(),
                                                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            } catch (RuntimeException e) {
                  throw new CensusAnalyserException(e.getMessage(),
                                                    CensusAnalyserException.ExceptionType.DELIMITER_MISSING);
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
                  ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
                  Iterator<CSVStateCode> stateCSVIterator = csvBuilder.
                                                            getCSVFileIterator(readerState, CSVStateCode.class);
                  return this.getCount(stateCSVIterator);
            } catch (NoSuchFileException e) {
                  throw new CensusAnalyserException(e.getMessage(),
                                                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            } catch (IOException e) {
                  throw new CensusAnalyserException(e.getMessage(),
                                                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            } catch (RuntimeException e) {
                  throw new CensusAnalyserException(e.getMessage(),
                                                    CensusAnalyserException.ExceptionType.DELIMITER_MISSING);
            }
      }
      /**
       * returns the number of entries in the CSV data file
       * @param iterator
       * @param <E>
       * @return numOfEnteries
       */
      private <E> int getCount(Iterator<E> iterator){
            Iterable<E> csvIterable = () -> iterator;
            int numOfEnteries = (int) StreamSupport.stream(csvIterable.spliterator(), false)
                    .count();
            return numOfEnteries;
      }
}