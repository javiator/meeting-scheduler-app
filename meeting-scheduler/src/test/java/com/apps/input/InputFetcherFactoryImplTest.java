package com.apps.input;

import junit.framework.Assert;

import org.junit.Test;

public class InputFetcherFactoryImplTest {

    @Test
    public void test() {

        InputFetcherFactory inputFetcherFactory = new InputFetcherFactoryImpl();

        Assert.assertEquals(true, inputFetcherFactory.getInputFetcher(0).getClass().equals(DummyInputFetcher.class));
        Assert.assertEquals(true, inputFetcherFactory.getInputFetcher(1).getClass().equals(ConsoleInputFetcher.class));
        Assert.assertEquals(true, inputFetcherFactory.getInputFetcher(2).getClass().equals(DummyInputFetcher.class));
    }

}
