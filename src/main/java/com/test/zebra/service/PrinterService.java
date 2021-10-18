package com.test.zebra.service;


import com.test.zebra.model.Frame;

import java.util.List;
import java.util.Map;
/**
 * This interface creates contract for printing results(board)
 */
public interface PrinterService {
    /**
     * This function builds board for printing in console
     * @param playsMap with key as player name and value as list of frames
     * @return String for board printing in console
     */
    String buildBoard(Map<String, List<Frame>> playsMap);
    /**
     * This function prints board
     * @param playsMap with key as player name and value as list of frames
     * @return String for board printing in console
     */
    void printBoard(Map<String, List<Frame>> playsMap);
}
