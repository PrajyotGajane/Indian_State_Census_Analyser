package com.bridgelabz.CensusAnalyser.Utilities;
import java.io.FileWriter;
import java.io.IOException;
public class JsonFileWriter {
      public JsonFileWriter(String csvFilePath, String json) {
            FileWriter writer;
            try {
                  writer = new FileWriter(csvFilePath);
                  writer.write(json);
                  writer.close();
            } catch (IOException e) {
                  e.printStackTrace();
            }
      }
}
