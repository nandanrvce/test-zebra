package com.test.zebra.model;


import com.test.zebra.utils.Helper;

import static com.test.zebra.utils.Constants.TEN;
/**
 * This class maintains the frame state for player
 */
public class Frame {

    private String firstBallScore;
    private String secondBallScore;
    private String finalBallScore;
    private String totalScore;
    private int ball;

    private boolean isOpen;

    public String getFirstBallScore() {
        return firstBallScore;
    }

    public void setFirstBallScore(String firstBallScore) {
        this.firstBallScore = firstBallScore;
    }

    public String getSecondBallScore() {
        return secondBallScore;
    }

    public void setSecondBallScore(String secondBallScore) {
        this.secondBallScore = secondBallScore;
    }

    public String getFinalBallScore() {
        return finalBallScore;
    }

    public void setFinalBallScore(String finalBallScore) {
        this.finalBallScore = finalBallScore;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public int getBall() {
        return ball;
    }

    public void setBall(int ball) {
        this.ball = ball;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        this.isOpen = open;
    }

    /**
     * This function calculates whether frame is strike or not
     * @return boolean result
     */
    public boolean isStrike() {
        return (this.firstBallScore.equals(TEN.toString()) || this.secondBallScore.equals(TEN.toString())) && this.ball < TEN;
    }

    /**
     * This function calculates whether frame is spare or not
     * @return boolean result
     */
    public boolean isSpare() {
        return !this.isStrike() && Helper.getIntegerValue(this.firstBallScore) + Helper.getIntegerValue(this.secondBallScore) == TEN && this.ball < TEN;
    }

}
