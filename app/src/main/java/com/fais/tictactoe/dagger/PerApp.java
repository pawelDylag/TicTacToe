package com.fais.tictactoe.dagger;

import java.lang.annotation.Retention;
import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by paweldylag on 18/10/15.
 */

@Scope @Retention(RUNTIME) public @interface PerApp {}