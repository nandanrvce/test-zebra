package com.test.zebra.validation;


import com.test.zebra.model.Frame;

import java.util.List;
/**
 * This interface exposes validation contract to validate the input
 */
public interface BowlingValidations {
    /**
     * This function validates the input by validating the number of players and pin number form each players
     * @param frames
     * @return empty string incase of valid else return error reason
     */
    String validate(List<Frame> frames);
}
