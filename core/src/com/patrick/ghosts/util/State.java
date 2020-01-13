package com.patrick.ghosts.util;

public class State {

    public static boolean BANNER = false;

    public static boolean PAUSE = false;

    public static boolean PAUSED() {
        return BANNER || PAUSE;
    }
}
