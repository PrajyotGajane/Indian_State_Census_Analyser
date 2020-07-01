package com.bridgelabz.CensusAnalyser.service;

import com.bridgelabz.CensusAnalyser.exception.CensusAnalyserException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

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
            return getCSVBean(reader, csvClass).iterator();
      }
      public List getCSVFileList(Reader reader, Class csvClass) {
            return getCSVBean(reader, csvClass).parse();
      }
      private CsvToBean<E> getCSVBean(Reader reader, Class csvClass) throws CensusAnalyserException{
            try{
                  CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
                  csvToBeanBuilder.withType(csvClass);
                  csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
                  return csvToBeanBuilder.build();
            } catch (IllegalStateException e) {
                  throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
            }
      }
}
