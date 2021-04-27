package io.github.eikefab.libs.commandcreator;

import io.github.eikefab.libs.commandcreator.type.CommandTarget;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Command {

    String name();
    String permission() default "";
    String permissionMessage() default "";
    String usage() default "";
    String[] aliases() default {};
    CommandTarget target() default CommandTarget.BOTH;
    boolean async() default false;

}
