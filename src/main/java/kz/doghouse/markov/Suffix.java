package kz.doghouse.markov;

class Suffix {

  public static final String NONE = "";

  private String suffix;

  public Suffix(String suffix) {
    this.suffix = suffix;
  }

  public boolean endsSentence() {
    return (suffix.toLowerCase() == suffix && suffix.endsWith(".")) || suffix.endsWith("!")
        || suffix.endsWith("?") || suffix.endsWith("...");
  }

  public String get() {
    return suffix;
  }

  public String toString() {
    return suffix;
  }

  @Override
  public int hashCode() {
    return suffix.hashCode();
  }

}
