package com.apps.domain;

/**
 * Value object to store office timings value for all meeting rooms It stores
 * office start and end time value in term of milliseconds calculated from
 * midnight ie. 00:00 Default values are set as below: OfficeTiming
 * [officeStartTime=32400000, officeEndTime=63000000]
 * 
 * @author agautam
 * 
 */
public class OfficeTiming {

    /**
     * start time in millis from 12 am
     */
    private long officeStartTime = 32400000;

    /**
     * end time in millis from 12 am
     */
    private long officeEndTime = 63000000;

    public long getOfficeStartTime() {

        return officeStartTime;
    }

    public void setOfficeStartTime(long officeStartTime) {

        this.officeStartTime = officeStartTime;
    }

    public long getOfficeEndTime() {

        return officeEndTime;
    }

    public void setOfficeEndTime(long officeEndTime) {

        this.officeEndTime = officeEndTime;
    }

    @Override
    public String toString() {

        return "OfficeTiming [officeStartTime=" + officeStartTime + ", officeEndTime=" + officeEndTime + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (officeEndTime ^ (officeEndTime >>> 32));
        result = prime * result + (int) (officeStartTime ^ (officeStartTime >>> 32));
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
        OfficeTiming other = (OfficeTiming) obj;
        if (officeEndTime != other.officeEndTime)
            return false;
        if (officeStartTime != other.officeStartTime)
            return false;
        return true;
    }

}
