package com.test.zebra.validation.impl;


import com.test.zebra.model.Frame;
import com.test.zebra.utils.Helper;
import com.test.zebra.validation.BowlingValidations;

import java.util.ArrayList;
import java.util.List;
import static com.test.zebra.utils.Constants.*;
/**
 * This class implements the validation logic used to validate the input
 */
public class BowlingValidationsImpl implements BowlingValidations {

    private boolean validatePinsNumber(Frame frame) {
        List<String> pins = new ArrayList<>();
        pins.add(frame.getFirstBallScore());
        pins.add(frame.getSecondBallScore());
        pins.add(frame.getFinalBallScore());

        return pins.stream().allMatch(el -> {
            int numberOfPins = Helper.getIntegerValue(el);
            return numberOfPins <= TEN && numberOfPins >= ZERO;
        });
    }

    private boolean validatePinsNumberFromPlays(List<Frame> frames) {
        return frames.stream().allMatch(this::validatePinsNumber);
    }

    private boolean validateTenPlays(List<Frame> frames) {
        return frames.size() <= TEN;
    }
    /**
     * This function validates the input by validating the number of players and pin number form each players
     * @param frames
     * @return empty string incase of valid else return error reason
     */
    @Override
    public String validate(List<Frame> frames) {
        return
                !validateTenPlays(frames) ? "Bad input, more than 10 plays!" :
                        !validatePinsNumberFromPlays(frames) ? "Bad input, invalid amount of pins!" :
                                "";

    }
}
