package com.bridgelabz.CensusAnalyser.service;

import com.bridgelabz.CensusAnalyser.exception.CensusAnalyserException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;

public class OpenCSVBuider {
      /**
       * to load data from CSV data file
       * @param reader
       * @param csvClass
       * @param <E>
       * @return iterator
       * @throws CensusAnalyserException
       */
      public <E> Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CensusAnalyserException{
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