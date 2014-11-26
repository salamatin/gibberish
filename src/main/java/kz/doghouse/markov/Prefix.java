package kz.doghouse.markov;

import java.util.LinkedList;


/**
 * Markov chain prefix
 * 
 * @author Andrey
 *
 */
class Prefix {

  public static final String NONE = "";

  private LinkedList<String> tokens = new LinkedList<String>();

  public static Prefix copy(Prefix p) {
    Prefix copy = new Prefix();
    LinkedList<String> copyTokens = new LinkedList<String>();
    for (String s : p.getTokens()) {
      copyTokens.add(s);
    }
    copy.tokens = copyTokens;
    return copy;
  }


  public Prefix(String... tokens) {
    for (String t : tokens) {
      this.tokens.add(t);
    }
  }

  /**
   * Updates the prefix with a new token. The new token is inserted at the end of the prefix. The
   * token at the beginning is removed.
   * 
   * @param token a new token
   */
  public void update(String token) {
    tokens.removeFirst();
    tokens.addLast(token);
  }

  /**
   * 
   * @return the list of tokens in this prefix
   */
  public LinkedList<String> getTokens() {
    return tokens;
  }

  public boolean startsSentence() {
    if (tokens.get(0) == NONE || Character.isUpperCase(tokens.get(0).charAt(0))) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    int hash = 13;
    for (String s : tokens) {
      hash = hash * 17 + s.hashCode();
    }
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Prefix)) {
      return false;
    }
    return this.hashCode() == obj.hashCode();
  }

}
