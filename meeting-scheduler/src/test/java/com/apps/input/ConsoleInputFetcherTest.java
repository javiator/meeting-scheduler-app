package com.apps.input;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Scanner;

import junit.framework.Assert;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Scanner.class)
public class ConsoleInputFetcherTest {

    @Mock
    private Scanner scanner;

    @InjectMocks
    InputFetcher consoleInputFetcher = new ConsoleInputFetcher();;

    public void verify1() {

        when(scanner.nextLine()).thenReturn("0900 1730", "2011-03-17 10:17:06 EMP001", "2011-03-21 09:00 2", "done");

        doNothing().when(scanner).close();

        String inputText = consoleInputFetcher.getInputData();

        Assert.assertEquals("", inputText);

    }

}
