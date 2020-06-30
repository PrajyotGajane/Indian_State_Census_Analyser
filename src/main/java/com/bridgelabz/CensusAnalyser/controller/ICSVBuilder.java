package com.bridgelabz.CensusAnalyser.controller;

import com.bridgelabz.CensusAnalyser.exception.CensusAnalyserException;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder<E> {
      public <E> Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CensusAnalyserException;
}