package com.test.zebra.programs;

import com.test.zebra.service.RunProgramService;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory to get correct {@link ProgramExecutor}.
 */
public class ProgramExecutorFactory {
  private Map<String, ProgramExecutor> programs = new HashMap<>();

  public ProgramExecutorFactory(final RunProgramService runProgramService) {
    programs.put(
        BowlingProgramExecutor.PROGRAM_NAME,
        new BowlingProgramExecutor(runProgramService));
  }

  /**
   * Gets {@link ProgramExecutor} for a particular program. It basically uses name of program to
   * fetch its corresponding executor.
   *
   * @param programName  for which executor has to be fetched.
   * @return program executor.
   */
  public ProgramExecutor getProgramExecutor(final String programName) {
    final ProgramExecutor programExecutor = programs.get(programName);
    if (programExecutor == null) {
      throw new RuntimeException("Invalid Program name");
    }
    return programExecutor;
  }
}
