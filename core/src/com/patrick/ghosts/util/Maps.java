package com.patrick.ghosts.util;

public class Maps {

    private static String[] MAPS = {"forest", "cave_"};
    public static int CURRENT_MAP = -1;

    public static String GET_MAP() {
        CURRENT_MAP ++;
        return CURRENT_MAP == 0 ? MAPS[0]+".tmx" : MAPS[1]+CURRENT_MAP+".tmx";
    }
}
