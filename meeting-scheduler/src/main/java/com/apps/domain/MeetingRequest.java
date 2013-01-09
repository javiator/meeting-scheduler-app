package com.apps.domain;

import java.util.Date;

/**
 * This class will store meeting request data parsed from the input string. This
 * implements Comparable interface to allow sorting in a collection when
 * required in order of meeting start time per day
 * 
 * @author agautam
 * 
 */
public class MeetingRequest implements Comparable<MeetingRequest> {

    /**
     * Requester Employee Id
     */
    private String requesterEmpId;

    /**
     * Meeting Date
     */
    private Date meetingDate;

    /**
     * Meeting start time
     */
    private Date meetingStartTime;

    /**
     * Meeting end time
     */
    private Date meetingEndTime;

    /**
     * Meeting duration
     */
    private int meetingDuration;

    /**
     * Meeting submission time
     */
    private Date submissionTime;

    public String getRequesterEmpId() {

        return requesterEmpId;
    }

    public void setRequesterEmpId(String requesterEmpId) {

        this.requesterEmpId = requesterEmpId;
    }

    public Date getMeetingStartTime() {

        return meetingStartTime;
    }

    public Date getMeetingDate() {

        return meetingDate;
    }

    public void setMeetingDate(Date meetingDate) {

        this.meetingDate = meetingDate;
    }

    public void setMeetingStartTime(Date meetingStartTime) {

        this.meetingStartTime = meetingStartTime;
    }

    public Date getMeetingEndTime() {

        return meetingEndTime;
    }

    public void setMeetingEndTime(Date meetingEndTime) {

        this.meetingEndTime = meetingEndTime;
    }

    public int getMeetingDuration() {

        return meetingDuration;
    }

    public void setMeetingDuration(int meetingDuration) {

        this.meetingDuration = meetingDuration;
    }

    public Date getSubmissionTime() {

        return submissionTime;
    }

    public void setSubmissionTime(Date submissionTime) {

        this.submissionTime = submissionTime;
    }

    /**
     * Defining compareTo method to allow sorting based on meeting start time.
     * This will help in storing meeting requests per day in order of their
     * starting time.
     * 
     */
    @Override
    public int compareTo(MeetingRequest o) {
        return (int) (this.getMeetingStartTime().getTime() - o.getMeetingStartTime().getTime());
    }

    @Override
    public String toString() {
        return "MeetingRequest [requesterEmpId=" + requesterEmpId + ", meetingDate=" + meetingDate
                + ", meetingStartTime=" + meetingStartTime + ", meetingEndTime=" + meetingEndTime
                + ", meetingDuration=" + meetingDuration + ", submissionTime=" + submissionTime + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((meetingDate == null) ? 0 : meetingDate.hashCode());
        result = prime * result + meetingDuration;
        result = prime * result + ((meetingEndTime == null) ? 0 : meetingEndTime.hashCode());
        result = prime * result + ((meetingStartTime == null) ? 0 : meetingStartTime.hashCode());
        result = prime * result + ((requesterEmpId == null) ? 0 : requesterEmpId.hashCode());
        result = prime * result + ((submissionTime == null) ? 0 : submissionTime.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MeetingRequest other = (MeetingRequest) obj;
        if (meetingDate == null) {
            if (other.meetingDate != null)
                return false;
        } else if (!meetingDate.equals(other.meetingDate))
            return false;
        if (meetingDuration != other.meetingDuration)
            return false;
        if (meetingEndTime == null) {
            if (other.meetingEndTime != null)
                return false;
        } else if (!meetingEndTime.equals(other.meetingEndTime))
            return false;
        if (meetingStartTime == null) {
            if (other.meetingStartTime != null)
                return false;
        } else if (!meetingStartTime.equals(other.meetingStartTime))
            return false;
        if (requesterEmpId == null) {
            if (other.requesterEmpId != null)
                return false;
        } else if (!requesterEmpId.equals(other.requesterEmpId))
            return false;
        if (submissionTime == null) {
            if (other.submissionTime != null)
                return false;
        } else if (!submissionTime.equals(other.submissionTime))
            return false;
        return true;
    }
}
