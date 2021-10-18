package com.test.zebra.service.impl;


import com.test.zebra.model.Frame;
import com.test.zebra.service.PrinterService;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static com.test.zebra.utils.Constants.*;


/**
 * This class implements PrinterService and prints the result in console
 */
public class PrinterServiceImpl implements PrinterService {
    /**
     * This function builds the frame string for printing
     * @return String for frame printing in console
     */
    private String buildFrameString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(FRAME);
        stringBuilder.append(TAB_SPACE).append(TAB_SPACE);
        IntStream.range(ONE, ELEVEN).forEach(e -> {
            stringBuilder.append(e);
            stringBuilder.append("\t\t");
        });
        return stringBuilder.toString();
    }
    /**
     * This function builds pin fall string for printing
     * @param frames list of frames
     * @return String for pin fall printing in console
     */
    private String buildPinFallsString(List<Frame> frames) {
        StringBuilder stringBuilder = new StringBuilder();

        frames.forEach(play -> {
            String firstScore = play.getFirstBallScore();
            String secondScore = play.getSecondBallScore();
            String finalScore = play.getFinalBallScore();

            stringBuilder.append(TAB_SPACE);
            if (play.isStrike()) {
                stringBuilder.append(TAB_SPACE);
                stringBuilder.append(X);
            } else if (play.isSpare()) {
                stringBuilder.append(firstScore);
                stringBuilder.append(TAB_SPACE).append(BACKSLASH);
            } else {
                stringBuilder.append(firstScore.equals(TEN.toString()) ? X : firstScore);
                stringBuilder.append(TAB_SPACE);
                stringBuilder.append(secondScore);
                if (play.getBall() == TEN) {
                    stringBuilder.append(TAB_SPACE);
                    stringBuilder.append(finalScore);
                }
            }
        });
        return stringBuilder.toString();

    }
    /**
     * This function builds score string
     * @param frames list of frames
     * @return String for score printing in console
     */
    private String buildScoreString(List<Frame> frames) {
        StringBuilder stringBuilder = new StringBuilder();
        frames.forEach(play -> {
            stringBuilder.append(TAB_SPACE).append(TAB_SPACE);
            stringBuilder.append(play.getTotalScore());
        });
        return stringBuilder.toString();
    }
    /**
     * This function builds board for printing in console
     * @param playsMap with key as player name and value as list of frames
     * @return String for board printing in console
     */
    @Override
    public String buildBoard(Map<String, List<Frame>> playsMap) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(buildFrameString());
        stringBuilder.append(NEW_LINE);
        playsMap.forEach((name, plays) -> {
            stringBuilder.append(name);
            stringBuilder.append(NEW_LINE);
            stringBuilder.append(PIN_FALLS);
            stringBuilder.append(buildPinFallsString(plays));
            stringBuilder.append(NEW_LINE);
            stringBuilder.append(SCORE);
            stringBuilder.append(buildScoreString(plays));
            stringBuilder.append(NEW_LINE);

        });
        return stringBuilder.toString();
    }
    /**
     * This function prints board
     * @param playsMap with key as player name and value as list of frames
     * @return String for board printing in console
     */
    @Override
    public void printBoard(Map<String, List<Frame>> playsMap) {

        System.out.println(buildBoard(playsMap));
    }

}
