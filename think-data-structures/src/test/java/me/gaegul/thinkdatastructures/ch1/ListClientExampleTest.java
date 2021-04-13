package me.gaegul.thinkdatastructures.ch1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ListClientExampleTest {

    @Test
    void testListClientExample() {
        ListClientExample lce = new ListClientExample();
        List list = lce.getList();
        assertTrue(list instanceof ArrayList);
    }

}