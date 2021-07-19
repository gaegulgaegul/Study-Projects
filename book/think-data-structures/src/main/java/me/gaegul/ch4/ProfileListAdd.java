package me.gaegul.ch4;

import org.jfree.data.xy.XYSeries;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import me.gaegul.ch4.Profiler.*;

public class ProfileListAdd {

    public static void main(String[] args) {
        profileArrayListAddEnd();
        profileArrayListAddBeginning();
        profileLinkedListAddEnd();
        profileLinkedListAddBeginning();
    }

    /**
     * Characterize the run time of adding to the end of an ArrayList
     */
    public static void profileArrayListAddEnd() {
        Timeable timeable = new Timeable() {
            List<String> list;

            @Override
            public void setup(int n) {
                list = new ArrayList<>();
            }

            @Override
            public void timeMe(int n) {
                for (int i = 0; i < n; i++) {
                    list.add("a string");
                }
            }
        };
        runProfiler("ArrayList add end", timeable, 4000, 1000);
    }

    /**
     * Characterize the run time of adding to the beginning of an ArrayList
     */
    public static void profileArrayListAddBeginning() {
        Timeable timeable = new Timeable() {
            List<String> list;
            @Override
            public void setup(int n) {
                 list = new ArrayList<>();
            }

            @Override
            public void timeMe(int n) {
                for (int i = 0; i < n; i++) {
                    list.add(0, "a string");
                }
            }
        };
        runProfiler("ArrayList add begin", timeable, 4000, 1000);
    }

    /**
     * Characterize the run time of adding to the beginning of a LinkedList
     */
    public static void profileLinkedListAddBeginning() {
        Timeable timeable = new Timeable() {
            List<String> list;

            @Override
            public void setup(int n) {
                list = new LinkedList<>();
            }

            @Override
            public void timeMe(int n) {
                for (int i = 0; i < n; i++) {
                    list.add(0, "a string");
                }
            }
        };
        runProfiler("LinkedList add begin", timeable, 4000, 1000);
    }

    /**
     * Characterize the run time of adding to the end of a LinkedList
     */
    public static void profileLinkedListAddEnd() {
        Timeable timeable = new Timeable() {
            List<String> list;

            @Override
            public void setup(int n) {
                list = new LinkedList<>();
            }

            @Override
            public void timeMe(int n) {
                for (int i = 0; i < n; i++) {
                    list.add("a string");
                }
            }
        };
        runProfiler("LinkedList add end", timeable, 4000, 1000);
    }

    /**
     * Runs the profiles and displays results.
     *
     * @param timeable
     * @param startN
     * @param endMillis
     */
    private static void runProfiler(String title, Timeable timeable, int startN, int endMillis) {
        Profiler profiler = new Profiler(title, timeable);
        XYSeries series = profiler.timingLoop(startN, endMillis);
        profiler.plotResults(series);
    }
}
