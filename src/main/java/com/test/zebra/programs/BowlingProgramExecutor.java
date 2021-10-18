package com.test.zebra.programs;

import com.test.zebra.service.RunProgramService;

/**
 * Validates and Executes bowling program
 */
class BowlingProgramExecutor extends ProgramExecutor {
  public static String PROGRAM_NAME = "bowling_program";
  public BowlingProgramExecutor(final RunProgramService runProgramService) {
    super(runProgramService);
  }
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean validateArguments(String[] args) {
    if(args.length==0){
      System.err.println("ERROR!! Please run the command with filename argument");
      return false;
    }
    return true;
  }
  /**
   * {@inheritDoc}
   */
  @Override
  public void execute(String[] args) {
    runProgramService.runProgram(args);
  }

}
