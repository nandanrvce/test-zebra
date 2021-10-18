package testzebra.service.impl;


import com.test.zebra.model.Frame;
import com.test.zebra.service.BowlingService;
import com.test.zebra.service.impl.BowlingServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BowlingServiceImplTest {

    @Test
    public void testBuildPlayTest() {
        BowlingService bowlingService = new BowlingServiceImpl();
        List<String> lines = new ArrayList<>();
        lines.add("Jeff 10");
        lines.add("John 3");
        lines.add("John 7");
        lines.add("Jeff 7");
        lines.add("Jeff 3");
        lines.add("John 6");
        lines.add("John 3");
        lines.add("Jeff 9");
        lines.add("Jeff 0");
        lines.add("John 10");
        lines.add("Jeff 10");
        lines.add("John 8");
        lines.add("John 1");
        lines.add("Jeff 0");
        lines.add("Jeff 8");
        lines.add("John 10");
        lines.add("Jeff 8");
        lines.add("Jeff 2");
        lines.add("John 10");
        lines.add("Jeff F");
        lines.add("Jeff 6");
        lines.add("John 9");
        lines.add("John 0");
        lines.add("Jeff 10");
        lines.add("John 7");
        lines.add("John 3");
        lines.add("Jeff 10");
        lines.add("John 4");
        lines.add("John 4");
        lines.add("Jeff 10");
        lines.add("Jeff 8");
        lines.add("Jeff 1");
        lines.add("John 10");
        lines.add("John 9");
        lines.add("John 0");

        Map<String, List<Frame>> plays = bowlingService.buildPlayListFromStream(lines.stream());
        Assertions.assertEquals(plays.size(),2);
        plays.forEach((name,values)->{
            Assertions.assertTrue(values.size()==10);
        });

    }


}