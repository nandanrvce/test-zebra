package com.test.zebra;


import com.test.zebra.programs.ProgramExecutor;
import com.test.zebra.programs.ProgramExecutorFactory;
import com.test.zebra.service.BowlingService;
import com.test.zebra.service.FileReaderService;
import com.test.zebra.service.PrinterService;
import com.test.zebra.service.RunProgramService;
import com.test.zebra.service.impl.BowlingServiceImpl;
import com.test.zebra.service.impl.FileReaderServiceImpl;
import com.test.zebra.service.impl.PrinterServiceImpl;
import com.test.zebra.service.impl.RunBowlingProgramServiceImpl;
import com.test.zebra.utils.Constants;
import com.test.zebra.validation.BowlingValidations;
import com.test.zebra.validation.impl.BowlingValidationsImpl;

/**
 * Main program to run the program
 */
public class App {

    public static void main(String args[]) {
        BowlingService bowlingService = new BowlingServiceImpl();
        BowlingValidations bowlingValidations = new BowlingValidationsImpl();
        PrinterService printerService = new PrinterServiceImpl();
        FileReaderService fileReaderService = new FileReaderServiceImpl();
        RunProgramService runProgramService = new RunBowlingProgramServiceImpl(
                bowlingService,
                bowlingValidations,
                printerService,
                fileReaderService);
        ProgramExecutorFactory factory = new ProgramExecutorFactory(runProgramService);
        ProgramExecutor programExecutor = factory.getProgramExecutor(Constants.BOWLING_PROGRAM);
        if(programExecutor.validateArguments(args)){
            programExecutor.execute(args);
        }
    }
}
