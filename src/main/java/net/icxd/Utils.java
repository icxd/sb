package net.icxd;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Utils {
  public static String color(String text) {return text.replace("&", "§");}
}
