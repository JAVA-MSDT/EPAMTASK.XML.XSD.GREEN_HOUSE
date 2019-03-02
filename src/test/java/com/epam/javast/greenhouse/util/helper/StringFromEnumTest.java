package com.epam.javast.greenhouse.util.helper;

import org.junit.Assert;
import org.junit.Test;

enum Number {
    ONE,
    TWO,
    THREE
}

public class StringFromEnumTest {

    @Test
    public void getStingPass() {
        String seek = "one";
        String actual = StringFromEnum.getString(Number.values(), seek);
        String expected = "ONE";

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getStringFailIfReturnNull() {
        String seek = "fd";
        String actual = StringFromEnum.getString(Number.values(), seek);
        String expected = null;

        Assert.assertEquals(actual, expected);
    }


}

