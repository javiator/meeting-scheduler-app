package com.apps.input;

import java.util.Scanner;

/**
 * Console based input collector class for getting meeting request text data.
 * 
 * @author agautam
 * 
 */
public class ConsoleInputFetcher implements InputFetcher {

    private Scanner scanner;

    /**
     * Method to fetch input data from console. It will keep getting the user
     * input until user enter 'done' and then press enter.
     * 
     */
    @Override
    public String getInputData() {

        System.out.println("Please provide scheduling data as input parameter...  ");
        System.out.println("Enter \"done\" when complete...  ");
        System.out.println();

        scanner = new Scanner(System.in);

        StringBuffer inputData = new StringBuffer();

        String enteredText = scanner.nextLine();

        while (!enteredText.equalsIgnoreCase("done")) {
            inputData.append(enteredText).append("\r\n");
            enteredText = scanner.nextLine();
        }

        scanner.close();

        return inputData.toString();

    }

}
