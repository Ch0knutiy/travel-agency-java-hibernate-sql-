package Entity;

import jakarta.persistence.*;

import java.util.Objects;

@org.hibernate.annotations.NamedQuery(name = "flights.all", query = "From FlightsEntity")
@org.hibernate.annotations.NamedQuery(name = "flights.byNum", query = "From FlightsEntity d where upper(d.num) like upper(?1)")
@org.hibernate.annotations.NamedQuery(name = "flights.byAircraft", query = "From FlightsEntity d where upper(d.aircraft) like upper(?1)")
@org.hibernate.annotations.NamedQuery(name = "flights.byClass", query = "From FlightsEntity d where upper(d.clazz) like upper(?1)")
@org.hibernate.annotations.NamedQuery(name = "flights.byFree", query = "From FlightsEntity d where d.free = ?1")
@Entity
@Table(name = "flights", schema = "public", catalog = "travel_agency")
public class FlightsEntity {
    @Id
    @Column(name = "num", nullable = false, length = 13)
    private String num;
    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false)
    private java.util.Date date;
    @Temporal(TemporalType.TIME)
    @Column(name = "time", nullable = false)
    private java.util.Date time;
    @Basic
    @Column(name = "aircraft", nullable = false, length = 12)
    private String aircraft;
    @Basic
    @Column(name = "class", nullable = false, length = 1)
    private String clazz;
    @Basic
    @Column(name = "free", nullable = false)
    private int free;


    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public java.util.Date getTime() {
        return time;
    }

    public void setTime(java.util.Date time) {
        this.time = time;
    }

    public String getAircraft() {
        return aircraft;
    }

    public void setAircraft(String aircraft) {
        this.aircraft = aircraft;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightsEntity that = (FlightsEntity) o;
        return free == that.free && Objects.equals(num, that.num) && Objects.equals(date, that.date) && Objects.equals(time, that.time) && Objects.equals(aircraft, that.aircraft) && Objects.equals(clazz, that.clazz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, date, time, aircraft, clazz, free);
    }
}
