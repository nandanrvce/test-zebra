package com.test.zebra.utils;
import static com.test.zebra.utils.Constants.F;
import static com.test.zebra.utils.Constants.ZERO;
/**
 * This class has helper functions
 */
public class Helper {
    /**
     * This functions returns integer score from string score
     * @param score string
     * @return int score
     */
    static public int getIntegerValue(String score) {
        return score != null ? score.equals(F) ? ZERO : Integer.parseInt(score) : ZERO;
    }
}
