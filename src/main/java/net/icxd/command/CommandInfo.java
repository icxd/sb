package net.icxd.command;

import net.icxd.user.Rank;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CommandInfo {
  String name();
  String description() default "";
  String aliases() default "";
  int minArgs() default 0;
  Rank rank() default Rank.DEFAULT;
  String usage() default "/<command>";
  boolean supportsConsole() default false;
}
