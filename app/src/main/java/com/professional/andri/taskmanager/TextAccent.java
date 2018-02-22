package com.professional.andri.taskmanager;

public class TextAccent {

  private final String text;
  private final int color;

  public TextAccent(String text, int color) {
    this.text = text;
    this.color = color;
  }

  public String getText() {
    return text;
  }

  public int getColor() {
    return color;
  }

}
