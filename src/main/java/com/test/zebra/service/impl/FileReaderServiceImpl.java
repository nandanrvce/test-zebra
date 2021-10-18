package com.test.zebra.service.impl;


import com.test.zebra.service.FileReaderService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
/**
 * This class implements the interface of FileReaderService for reading file as Streams
 */
public class FileReaderServiceImpl implements FileReaderService {
    /**
     * This function implements the interface of FileReaderService for reading file as Streams
     * @param file name
     * @return  Stream of string
     */
    @Override
    public Stream<String> readFile(String file) {
        Stream<String> lines = Stream.empty();
        try {
            lines = Files.lines(Paths.get(file));
        }catch (IOException e){
            System.out.println("FILE NOT FOUND");
        }
        return lines;
    }
}
