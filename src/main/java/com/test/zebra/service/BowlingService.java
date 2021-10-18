package com.test.zebra.service;


import com.test.zebra.model.Frame;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
/**
 * This interface describes contract for bowling service
 */
public interface BowlingService {
    /**
     * This function does play level calculation
     * @param playsMap which is map of string and list of frame for each player
     * @return map of string (player name) and list of frame with updated score
     */
    Map<String, List<Frame>> calcPlayScoresFromMap(Map<String, List<Frame>> playsMap);

    /**
     * This function takes in stream of lines read from file and generates list of frame for each player
     * @param  lines stream from file
     * @return map of string (player name) and list of frame
     */
    Map<String, List<Frame>> buildPlayListFromStream(Stream<String> lines);
}
