package com.bridgelabz.CensusAnalyser.service;

import com.bridgelabz.CensusAnalyser.controller.ICSVBuilder;
import com.bridgelabz.CensusAnalyser.exception.CensusAnalyserException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;

public class OpenCSVBuilder <E> implements ICSVBuilder {
      /**
       * to load data from CSV data file
       * @param reader
       * @param csvClass
       * @param <E>
       * @return iterator
       * @throws CensusAnalyserException
       */
      public Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CensusAnalyserException{
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
