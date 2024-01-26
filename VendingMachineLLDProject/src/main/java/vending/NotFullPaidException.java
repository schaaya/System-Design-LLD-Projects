package vending;

public class NotFullPaidException extends RuntimeException {
  private String message;
  private long remaining;


  public NotFullPaidException(String message, long remaining){
    this.message = message;
    this.remaining = remaning;
  }

  public long getRemaining(){
    return remaining;
  }

  public String getMessage(){
    return message + remaining;
  }
}