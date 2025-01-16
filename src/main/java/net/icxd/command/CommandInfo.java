package net.icxd.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ICommand {
  String name();
  String usage();
}
