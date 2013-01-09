package com.apps.process;

import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import com.apps.domain.MeetingRequest;

/**
 * Interface for process meeting scheduling request
 * 
 * @author agautam
 * 
 */
public interface MeetingScheduleProcessor {

    SortedMap<Date, List<MeetingRequest>> process(String rawInputData);

}
