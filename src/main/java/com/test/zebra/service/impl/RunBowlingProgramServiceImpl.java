package com.test.zebra.service.impl;

import com.test.zebra.model.Frame;
import com.test.zebra.service.BowlingService;
import com.test.zebra.service.FileReaderService;
import com.test.zebra.service.PrinterService;
import com.test.zebra.service.RunProgramService;
import com.test.zebra.validation.BowlingValidations;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
/**
 * Executes bowling program
 */
public class RunBowlingProgramServiceImpl implements RunProgramService {
    BowlingService bowlingService;
    BowlingValidations bowlingValidations ;
    PrinterService printerService ;
    FileReaderService fileReaderService ;

    public RunBowlingProgramServiceImpl(BowlingService bowlingService, BowlingValidations bowlingValidations,
                                        PrinterService printerService, FileReaderService fileReaderService){
        this.bowlingService = bowlingService;
        this.bowlingValidations = bowlingValidations;
        this.printerService = printerService;
        this.fileReaderService = fileReaderService;
    }
    @Override
    public void runProgram(String[] args) {
        Stream<String> lines = fileReaderService.readFile(args[0]);

        Map<String, List<Frame>> playsMap =
                bowlingService.calcPlayScoresFromMap(
                        bowlingService.buildPlayListFromStream(lines));

        playsMap.forEach((name,plays)->{
            String error = bowlingValidations.validate(plays);
            if(!error.isEmpty()){
                throw new IllegalArgumentException(name+": "+error);
            }
        });

        printerService.printBoard(playsMap);
    }
}
