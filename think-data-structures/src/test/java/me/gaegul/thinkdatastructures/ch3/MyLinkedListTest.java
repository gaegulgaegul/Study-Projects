package me.gaegul.thinkdatastructures.ch3;

import me.gaegul.thinkdatastructures.ch2.MyArrayListTest;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

class MyLinkedListTest extends MyArrayListTest {

    @BeforeEach
    public void setUp() throws Exception {
        list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        mylist = new MyLinkedList<>();
        mylist.addAll(list);
    }

}