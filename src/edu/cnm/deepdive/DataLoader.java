package edu.cnm.deepdive;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class DataLoader {

  private String filename;

  public DataLoader(String filename) {
    this.filename = filename;
  }

  public int[][] load() throws IOException {

    try (
        InputStream input = new FileInputStream(filename);
        InputStreamReader reader = new InputStreamReader(input);
        BufferedReader buffer = new BufferedReader(reader)
    ) {
      List<int[]> workingRows = new LinkedList<>();
      String line = buffer.readLine();
      while (line != null) {
        String[] parts = line.trim().split("\\s+");
        int[] values = new int[parts.length];
        for (int i = 0; i < parts.length; ++i) {
          values[i] = Integer.parseInt(parts[i]);
        }
        workingRows.add(values);
        line = buffer.readLine();
      }
      return workingRows.toArray(new int[0][]);
    }
  }

}
