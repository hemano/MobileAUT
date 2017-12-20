package com.carousell.test;

import manager.ParallelExecution;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Runner {


    @Test
    public static void testApp() throws Exception {
        List<String> tests = new ArrayList<>();
        tests.add("CarousellTest");

        ParallelExecution parallelExecution = new ParallelExecution();

        parallelExecution.runner("com.carousell.test", tests);
    }
}
