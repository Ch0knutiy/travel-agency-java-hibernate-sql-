package Entity;

import jakarta.persistence.*;
import java.util.Objects;

@org.hibernate.annotations.NamedQuery(name = "ticket.all", query = "From TicketEntity order by id")
@org.hibernate.annotations.NamedQuery(name = "ticket.byId", query = "From TicketEntity r where r.id = ?1")
@org.hibernate.annotations.NamedQuery(name = "ticket.byName", query = "Select d From TicketEntity d where upper(d.flight_num.num) like upper(?1)")
@org.hibernate.annotations.NamedQuery(name = "ticket.bySeat", query = "From TicketEntity d where d.seat=?1")
@org.hibernate.annotations.NamedQuery(name = "ticket.byClient", query = "Select d From TicketEntity d where upper(d.idClient.surname) like upper(?1)")
@Entity
@Table(name = "ticket", schema = "public", catalog = "travel_agency")
public class TicketEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @OneToOne
    @JoinColumn(name = "flight_num", referencedColumnName = "num")
    private FlightsEntity flight_num;
    @Basic
    @Column(name = "seat", nullable = false)
    private int seat;
    @OneToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id")
    private ClientEntity idClient;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FlightsEntity getFlightNum() {
        return flight_num;
    }

    public void setFlightNum(FlightsEntity flightNum) {
        this.flight_num = flightNum;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public ClientEntity getIdClient() {
        return idClient;
    }

    public void setIdClient(ClientEntity idClient) {
        this.idClient = idClient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketEntity that = (TicketEntity) o;
        return id == that.id && seat == that.seat && idClient == that.idClient && Objects.equals(flight_num, that.flight_num);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flight_num, seat, idClient);
    }
}
