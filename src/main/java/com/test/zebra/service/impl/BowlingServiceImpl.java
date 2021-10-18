package com.test.zebra.service.impl;
import com.test.zebra.model.Frame;
import com.test.zebra.service.BowlingService;
import com.test.zebra.utils.Helper;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.test.zebra.utils.Constants.*;

public class BowlingServiceImpl implements BowlingService {

    Frame currentFrame;
    final String STRIKE = TEN.toString();

    /**
     * This function builds frames for the player
     * @param playsMap which has key as player name and list of name value strings
     * @return list of frame for each player
     */
    private Map<String, List<Frame>> buildFramesByPlayer(Map<String, List<String>> playsMap) {
        Map<String, List<Frame>> plays = new HashMap<>();
        playsMap.forEach((name, values) -> {
            List<Frame> ballList = new ArrayList<>();
            final int[] frame = {ONE};

            HashMap<Integer, String> ballsWithIndex = values
                    .stream()
                    .collect(HashMap::new,
                            (map, streamValue) -> map.put(map.size(), streamValue),
                            (map, map2) -> {
                            });

            ballsWithIndex.forEach((k, v) -> {
                String score = v.split(SPACE)[1];
                if (k == 0) this.currentFrame = new Frame();
                if ((!currentFrame.isOpen()) || k == 0) {
                    currentFrame.setFirstBallScore(score);
                    if (score.equals(STRIKE) && k < values.size() - 3) {
                        currentFrame.setBall(frame[0]);
                        frame[0]++;
                        ballList.add(currentFrame);
                        currentFrame = new Frame();
                    } else {
                        currentFrame.setOpen(true);
                    }
                } else if (k == values.size() - 1) {
                    currentFrame.setFinalBallScore(score);
                    currentFrame.setBall(frame[0]);
                    frame[0]++;
                    ballList.add(currentFrame);
                } else {
                    currentFrame.setSecondBallScore(score);
                    if (k < values.size() - 2) {
                        currentFrame.setBall(frame[0]);
                        frame[0]++;
                        ballList.add(currentFrame);
                        currentFrame = new Frame();
                    }
                }
            });
            plays.put(name, ballList);
        });
        return plays;
    }

    /**
     * This function calculates strike score
     * @param round and the list of frames
     * @return score for the round
     */
    private int calcStrikeScore(int round, List<Frame> frames) {
        int total = 10;
        int index = round - 1;
        int previousPlayScore = index != 0 ? Helper.getIntegerValue(frames.get(index - 1).getTotalScore()) : 0;
        if (round < frames.size()) {
            Frame nextFrame = frames.get(index + 1);

            if (nextFrame.isStrike()) {
                Frame nextNextFrame = frames.get(index + 2);
                total += previousPlayScore + Helper.getIntegerValue(nextFrame.getFirstBallScore()) + Helper.getIntegerValue(nextNextFrame.getFirstBallScore());
            } else {
                total += previousPlayScore + Helper.getIntegerValue(nextFrame.getFirstBallScore()) + Helper.getIntegerValue(nextFrame.getSecondBallScore());
            }
        }
        return total;
    }

    /**
     * This function calculates spare score
     * @param round and the list of frames
     * @return score for the round
     */
    private int calcSpareScore(int round, List<Frame> frames) {
        int total = 10;
        if (round < frames.size()) {
            int index = round - 1;
            int previousPlayScore = index != 0 ? Helper.getIntegerValue(frames.get(index - 1).getTotalScore()) : 0;
            Frame nextFrame = frames.get(index + 1);
            total += previousPlayScore + Helper.getIntegerValue(nextFrame.getFirstBallScore());
        }
        return total;
    }

    /**
     * This function calculates normal score
     * @param round and the list of frames
     * @return score for the round
     */
    private int calcNormalScore(int round, List<Frame> frames) {
        int index = round - 1;
        int previousPlayScore = index != 0 ? Helper.getIntegerValue(frames.get(index - 1).getTotalScore()) : 0;
        Frame frame = frames.get(index);

        return Helper.getIntegerValue(frame.getFirstBallScore()) + Helper.getIntegerValue(frame.getSecondBallScore()) + Helper.getIntegerValue(frame.getFinalBallScore()) + previousPlayScore;
    }

    /**
     * This function identifies whether the play is strike or spare or normal based on which score calculation is done
     * @param plays which is list of frames
     * @return frame updated with scores
     */
    private List<Frame> calcPlayScoresFromList(List<Frame> plays) {
        plays.forEach((play) -> {
            int total;
            if (play.isStrike()) {
                total = calcStrikeScore(play.getBall(), plays);
            } else if (play.isSpare()) {
                total = calcSpareScore(play.getBall(), plays);
            } else {
                total = calcNormalScore(play.getBall(), plays);
            }
            play.setTotalScore(String.valueOf(total));
        });


        return plays;
    }

    /**
     * This function invokes calcPlayScoresFromList which does play level calculation
     * @param playsMap which is map of string and list of frame for each player
     * @return map of string (player name) and list of frame with updated score
     */
    @Override
    public Map<String, List<Frame>> calcPlayScoresFromMap(Map<String, List<Frame>> playsMap) {

        playsMap.forEach((name, values) -> {
            List<Frame> frames = calcPlayScoresFromList(values);
            playsMap.put(name, frames);
        });

        return playsMap;
    }

    /**
     * This function takes in stream of lines read from file and generates list of frame for each player
     * @param  lines stream from file
     * @return map of string (player name) and list of frame
     */
    @Override
    public Map<String, List<Frame>> buildPlayListFromStream(Stream<String> lines) {

        LinkedHashMap<String, List<String>> playsMap = lines.collect(
                Collectors.groupingBy(s -> s.substring(0, s.indexOf(" ")), LinkedHashMap::new, Collectors.mapping(p -> p, //map name
                        Collectors.toList())));

        return this.buildFramesByPlayer(playsMap);

    }

}
