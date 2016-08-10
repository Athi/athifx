package com.github.athi.athifx.gui.security;

import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Athi
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface Secured {

    String[] permissions() default "";

    boolean any() default false; //TODO after implementation test if You can add 2x annotation ( @Secured({ONE, TWO, THREE}, false) @Secured({FOUR, FIVE}, true) )
}
