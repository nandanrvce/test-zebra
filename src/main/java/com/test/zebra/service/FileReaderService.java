package com.test.zebra.service;

import java.util.stream.Stream;
/**
 * This interface describes contract for reading file as Streams
 */
public interface FileReaderService {
     /**
      * This function is to be implemented for reading file as Streams
      * @param file name
      * @return  Stream of string
      */
     Stream<String> readFile(String file);
}
