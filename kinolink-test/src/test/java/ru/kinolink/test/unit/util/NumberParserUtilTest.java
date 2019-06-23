package ru.kinolink.test.unit.util;

import org.junit.Test;
import ru.kinolink.service.util.NumberParserUtil;

import static org.junit.Assert.assertTrue;

public class NumberParserUtilTest {

    @Test
    public void parseWithDefault(){
        int one = NumberParserUtil.parseWithDefault("5",2);
        int two = NumberParserUtil.parseWithDefault(null, -1);
        int three = NumberParserUtil.parseWithDefault("test", -1);
        int four = NumberParserUtil.parseWithDefault("34", -1);

        assertTrue(one == 5);
        assertTrue(two == -1);
        assertTrue( three == -1);
        assertTrue( four == 34);
    }

}
