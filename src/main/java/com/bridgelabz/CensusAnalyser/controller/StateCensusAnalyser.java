package com.bridgelabz.CensusAnalyser.controller;
import com.bridgelabz.CensusAnalyser.exception.CensusAnalyserException;
import com.bridgelabz.CensusAnalyser.models.CSVStateCensus;
import com.bridgelabz.CensusAnalyser.service.CSVBuilderException;
import com.bridgelabz.CensusAnalyser.service.CSVBuilderFactory;
import com.bridgelabz.CensusAnalyser.service.ICSVBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
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
                  List<CSVStateCensus> censusCSVList = csvBuilder.getCSVFileList(reader, CSVStateCensus.class);
                  return censusCSVList.size();
            } catch (NoSuchFileException e) {
                  throw new CensusAnalyserException(e.getMessage(),
                                                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            } catch (IOException e) {
                  throw new CensusAnalyserException(e.getMessage(),
                                                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            } catch (RuntimeException e) {
                  throw new CensusAnalyserException(e.getMessage(),
                                                    CensusAnalyserException.ExceptionType.DELIMITER_MISSING);
            } catch (CSVBuilderException e) {
                  throw new CensusAnalyserException(e.getMessage(),
                                                    CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
            }
      }
      /**
       * to load csv file data into the program
       * @param csvFilePath
       * @return to total number of records
       * @throws CensusAnalyserException
       */
      public int loadIndianStateCode(String csvFilePath) {
            try (Reader readerState = Files.newBufferedReader(Paths.get(csvFilePath))) {
                  ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
                  List<CSVStateCensus> censusCSVList = csvBuilder.getCSVFileList(readerState, CSVStateCensus.class);
                  return censusCSVList.size();
            } catch (IOException e) {
                  throw new CensusAnalyserException(e.getMessage(),
                                                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            } catch (RuntimeException e) {
                  throw new CensusAnalyserException(e.getMessage(),
                                                    CensusAnalyserException.ExceptionType.DELIMITER_MISSING);
            } catch (CSVBuilderException e) {
                  throw new CensusAnalyserException(e.getMessage(),
                                                    CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
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

      public String getStateWiseSortedCensusData(String csvFilePath) {
            try (Reader readerState = Files.newBufferedReader(Paths.get(csvFilePath))) {
                  ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
                  List<CSVStateCensus> censusCSVList = csvBuilder.getCSVFileList(readerState, CSVStateCensus.class);
                  Comparator<CSVStateCensus> censusComparator = Comparator.comparing(census -> census.state);
                  this.sort(censusCSVList, censusComparator);
                  String sortedStateCensusJson = new Gson().toJson(censusCSVList);
                  return sortedStateCensusJson;
            } catch (IOException e) {
                  throw new CensusAnalyserException(e.getMessage(),
                          CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            } catch (RuntimeException e) {
                  throw new CensusAnalyserException(e.getMessage(),
                          CensusAnalyserException.ExceptionType.DELIMITER_MISSING);
            } catch (CSVBuilderException e) {
                  throw new CensusAnalyserException(e.getMessage(),
                          CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
            }
      }

      private void sort(List<CSVStateCensus> censusCSVList, Comparator<CSVStateCensus> censusComparator) {

            for (int i =0; i<censusCSVList.size(); i++){
                  for (int j= 0; j < censusCSVList.size() -i -j; j++){
                        CSVStateCensus census1= censusCSVList.get(j);
                        CSVStateCensus census2= censusCSVList.get(j+1);
                        if (censusComparator.compare(census1,census2) > 0){
                              censusCSVList.set(j, census2);
                              censusCSVList.set(j+1,census1);
                        }
                  }
            }
      }

}