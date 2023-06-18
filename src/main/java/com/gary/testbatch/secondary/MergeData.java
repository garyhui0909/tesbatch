package com.gary.testbatch.secondary;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "data")
public class MergeData {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
//    @SequenceGenerator(name = "seqGen", sequenceName = "seq", initialValue = 1)
    private int id;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp datetime;
    private int channel;
    private float value;
    @Column(name="c_district")
    private String cDistrict;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getcDistrict() {
        return cDistrict;
    }

    public void setcDistrict(String cDistrict) {
        this.cDistrict = cDistrict;
    }
}
