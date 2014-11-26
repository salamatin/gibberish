package kz.doghouse.markov;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Scanner;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

class Table {

  private static final Random rand = new Random();

  private LinkedHashMap<Prefix, Multiset<Suffix>> records;
  private ArrayList<Prefix> initialPrefixes;
  private int prefixLength;

  public Table(int prefixLength) {
    records = new LinkedHashMap<Prefix, Multiset<Suffix>>();
    initialPrefixes = new ArrayList<Prefix>();
    this.prefixLength = prefixLength;
  };

  public void insert(InputStream input) {
    Scanner scanner = new Scanner(input);
    String[] tokens = new String[prefixLength];
    Arrays.fill(tokens, Prefix.NONE);
    Prefix p = new Prefix(tokens);
    while (scanner.hasNext()) {
      if (p.startsSentence()) {
        initialPrefixes.add(p);
      }
      String newToken = scanner.next();
      insertChain(p, new Suffix(newToken));
      p = Prefix.copy(p);
      p.update(newToken);
    }
    insertChain(p, new Suffix(Suffix.NONE));

    scanner.close();
  }

  public void insertChain(Prefix prefix, Suffix suffix) {
    Multiset<Suffix> suffixSet = records.get(prefix);
    if (suffixSet == null) {
      suffixSet = HashMultiset.create();
      suffixSet.add(suffix);
      records.put(prefix, suffixSet);
    } else {
      suffixSet.add(suffix);
    }
  }

  public Prefix getFirstPrefix() {
    if (initialPrefixes.size() > 0) {
      return initialPrefixes.get(0);
    } else {
      return null;
    }
  }

  public Prefix getRandomPrefix() {
    if (records.size() > 0) {
      return initialPrefixes.get(rand.nextInt(initialPrefixes.size()));
    } else {
      return null;
    }
  }

  public Suffix getSuffix(Prefix prefix) {
    Multiset<Suffix> suffixes = records.get(prefix);
    int r = rand.nextInt(suffixes.size());
    Iterator<Suffix> suffixIterator = suffixes.iterator();
    for (int i = 0; i < r; i++) {
      suffixIterator.next();
    }
    return suffixIterator.next();
  }
}
