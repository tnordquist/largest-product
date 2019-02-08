package edu.cnm.deepdive;

import java.io.IOException;

public class ProjectEuler11 {

  public static void main(String[] args) throws IOException {
    DataLoader loader = new DataLoader("largest-product-data.txt");
    int[][] data = loader.load();
    System.out.println(maxProduct(data,4));
  }

  public static int maxProduct(int[][] data, int productLength) {
    int maxProduct = Integer.MIN_VALUE;
    for (int row = 0; row < data.length; row++) {
      for (int col = 0; col < data[row].length; col++) {
        maxProduct = Math.max(maxProduct, maxProduct(data, productLength, row, col));
//        int testProduct = maxProduct(data, productLength, row, col);
//        if (testProduct > maxProduct) {
//          maxProduct = testProduct;
//        }
      }
    }
    return maxProduct;
  }

  private static int maxProduct(int[][] data, int productLength, int row, int col) {
    int best = Integer.MIN_VALUE;
    for (Direction direction : Direction.values()) {
      try {
        int product = 1;
        for (int step = 0; step < productLength; step++) {
          product *= data[row + step * direction.rowOffset][col + step * direction.colOffset];
        }
        best = Math.max(best, product);
      } catch (ArrayIndexOutOfBoundsException e) {
        // Do nothing! Go on with your life!
      }
    }
    return best;
  }

  private enum Direction {
    NORTH(-1, 0),
    NORTHEAST(-1, 1),
    EAST(0, 1),
    SOUTHEAST(1, 1);

    public final int rowOffset;
    public final int colOffset;

    Direction(int rowOffset, int colOffset) {
      this.rowOffset = rowOffset;
      this.colOffset = colOffset;
    }

  }

}
