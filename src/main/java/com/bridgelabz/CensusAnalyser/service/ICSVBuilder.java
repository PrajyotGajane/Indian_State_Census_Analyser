package com.bridgelabz.CensusAnalyser.service;

import com.bridgelabz.CensusAnalyser.exception.CensusAnalyserException;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder<E> {
      Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CSVBuilderException;

}