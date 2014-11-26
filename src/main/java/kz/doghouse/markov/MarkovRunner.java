package kz.doghouse.markov;

import java.io.IOException;

public class MarkovRunner {

  public static void main(String[] args) throws IOException {

    MarkovChainGenerator m = new MarkovChainGenerator(3, args);
    String separator = "";
    for (String s : m.getSentence()) {
      System.out.print(separator + s);
      separator = " ";
    }
    System.out.println();
  }

}
