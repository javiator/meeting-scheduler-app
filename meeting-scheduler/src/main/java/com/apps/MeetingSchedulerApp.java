package com.apps;

import com.apps.controller.MeetingSchedulerAppController;
import com.apps.controller.MeetingSchedulerAppControllerImpl;

/**
 * Main class to start the MeetingScheduler App.
 * 
 */
public class MeetingSchedulerApp {

    /**
     * Meeting Scheduler App Controller that takes responsibility of running the
     * app by getting input and then processing it (similar to controller
     * classes in MVC frameworks)
     */
    static MeetingSchedulerAppController controller = new MeetingSchedulerAppControllerImpl();

    /**
     * Main method to start execution of the program.
     * 
     * @param args
     */
    public static void main(String[] args) {

        controller.execute(args);

    }

}
