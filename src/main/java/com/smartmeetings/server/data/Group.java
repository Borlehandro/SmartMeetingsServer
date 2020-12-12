package com.smartmeetings.server.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "groups")
public class Group {
    @Id
    @Column(name = "number")
    int groupNumber;

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }
}
