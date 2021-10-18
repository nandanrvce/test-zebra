package testzebra;


import com.test.zebra.model.Frame;
import com.test.zebra.service.BowlingService;
import com.test.zebra.service.FileReaderService;
import com.test.zebra.service.PrinterService;
import com.test.zebra.service.impl.BowlingServiceImpl;
import com.test.zebra.service.impl.FileReaderServiceImpl;
import com.test.zebra.service.impl.PrinterServiceImpl;
import com.test.zebra.validation.BowlingValidations;
import com.test.zebra.validation.impl.BowlingValidationsImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class AppTest {
    private BowlingService bowlingService = new BowlingServiceImpl();
    private BowlingValidations bowlingValidations = new BowlingValidationsImpl();
    private PrinterService printerService = new PrinterServiceImpl();
    private FileReaderService fileReaderService = new FileReaderServiceImpl();

    @Test
    public void testRealCase() {
        String result = "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\t\t\n" +
                "Jeff\n" +
                "Pinfalls\t\tX\t7\t/\t9\t0\t\tX\t0\t8\t8\t/\tF\t6\t\tX\t\tX\tX\t8\t1\n" +
                "Score\t\t20\t\t39\t\t48\t\t66\t\t74\t\t84\t\t90\t\t120\t\t148\t\t167\n" +
                "John\n" +
                "Pinfalls\t3\t/\t6\t3\t\tX\t8\t1\t\tX\t\tX\t9\t0\t7\t/\t4\t4\tX\t9\t0\n" +
                "Score\t\t16\t\t25\t\t44\t\t53\t\t82\t\t101\t\t110\t\t124\t\t132\t\t151\n";

        Stream<String> lines = fileReaderService.readFile("real.txt");

        Map<String, List<Frame>> playsMap =
                bowlingService.calcPlayScoresFromMap(
                        bowlingService.buildPlayListFromStream(lines));

        playsMap.forEach((name, plays) -> {
            String error = bowlingValidations.validate(plays);
            if (!error.isEmpty()) {
                throw new IllegalArgumentException(name + ": " + error);
            }
        });

        Assertions.assertEquals(printerService.buildBoard(playsMap), result);
    }

    @Test
    public void testZeroCase() {
        String result = "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\t\t\n" +
                "Carl\n" +
                "Pinfalls\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
                "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n";
        Stream<String> lines = fileReaderService.readFile("zero.txt");
        Map<String, List<Frame>> playsMap =
                bowlingService.calcPlayScoresFromMap(
                        bowlingService.buildPlayListFromStream(lines));

        playsMap.forEach((name, plays) -> {
            String error = bowlingValidations.validate(plays);
            if (!error.isEmpty()) {
                throw new IllegalArgumentException(name + ": " + error);
            }
        });
        Assertions.assertEquals(printerService.buildBoard(playsMap), result);

    }
    @Test
    public void testPerfectCase() {
        String result = "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\t\t\n" +
                "Carl\n" +
                "Pinfalls\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\tX\t10\t10\n" +
                "Score\t\t30\t\t60\t\t90\t\t120\t\t150\t\t180\t\t210\t\t240\t\t270\t\t300\n";
        Stream<String> lines = fileReaderService.readFile("perfect.txt");
        Map<String, List<Frame>> playsMap =
                bowlingService.calcPlayScoresFromMap(
                        bowlingService.buildPlayListFromStream(lines));

        playsMap.forEach((name, plays) -> {
            String error = bowlingValidations.validate(plays);
            if (!error.isEmpty()) {
                throw new IllegalArgumentException(name + ": " + error);
            }
        });
        Assertions.assertEquals(printerService.buildBoard(playsMap), result);
    }
}