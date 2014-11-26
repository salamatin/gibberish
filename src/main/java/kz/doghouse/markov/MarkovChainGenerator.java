package kz.doghouse.markov;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MarkovChainGenerator {

  private Table t;

  public MarkovChainGenerator(int length, String... files) throws IOException {
    t = new Table(length);
    for (String filename : files) {
      t.insert(new FileInputStream(filename));
    }
  }

  public void addText(String filename) throws IOException {
    t.insert(new FileInputStream(filename));
  }

  public Iterable<String> getSentence() {
    ArrayList<String> sentence = new ArrayList<String>();
    Prefix p = t.getRandomPrefix();
    sentence.addAll(p.getTokens());
    Suffix s = t.getSuffix(p);
    while (true) {
      sentence.add(s.get());
      if (s.endsSentence()) {
        break;
      }
      p.update(s.get());
      s = t.getSuffix(p);
    }
    return sentence;
  }
}
