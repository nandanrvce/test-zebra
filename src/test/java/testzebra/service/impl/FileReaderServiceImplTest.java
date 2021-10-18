package testzebra.service.impl;


import com.test.zebra.service.FileReaderService;
import com.test.zebra.service.impl.FileReaderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class FileReaderServiceImplTest {

    @Test
    public void testReadFile() {
        FileReaderService fileReaderService = new FileReaderServiceImpl();
        Stream<String> plays = fileReaderService.readFile("real.txt");
        List<String> playList = plays.collect(Collectors.toList());

        Assertions.assertNotNull(plays);
        Assertions.assertTrue(playList.contains("Jeff 10"), "Jeff 10 is not present in file test case failed");
    }
}