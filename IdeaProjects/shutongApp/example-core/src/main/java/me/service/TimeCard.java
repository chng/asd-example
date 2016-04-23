package me.service;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by chn on 16/1/23.
 */
@Entity
public class TimeCard {
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @Column
    private long empId;

    public TimeCard(Date startTime, Date endTime, long empId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.empId = empId;
    }

    public int getHours() {
        return (int)(getEndTime().getTime() - getStartTime().getTime())/1000/60/60;
    }


    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }
}
