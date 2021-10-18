package com.test.zebra.programs;

import com.test.zebra.service.RunProgramService;

/**
 * Program executor interface.
 */
public abstract class ProgramExecutor {
  protected RunProgramService runProgramService;

  public ProgramExecutor(final RunProgramService runProgramService) {
    this.runProgramService = runProgramService;
  }

  /**
   * Validates that whether args to program is valid to be executed or not.
   * @param args
   * @return Boolean indicating whether command is valid or not.
   */
  public abstract boolean validateArguments(String[] args);

  /**
   * Executes the program with args.
   * @param args
   */
  public abstract void execute(String[] args);
}
