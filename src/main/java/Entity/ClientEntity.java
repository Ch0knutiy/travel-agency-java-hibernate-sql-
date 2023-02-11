package Entity;

import jakarta.persistence.*;

import java.util.Objects;

@org.hibernate.annotations.NamedQuery(name = "client.all", query = "From ClientEntity  order by id")
@org.hibernate.annotations.NamedQuery(name = "client.byId", query = "From ClientEntity d where d.id = ?1  order by d.id ")
@org.hibernate.annotations.NamedQuery(name = "client.bySurname", query = "From ClientEntity d where upper(d.surname) like upper(?1)")
@org.hibernate.annotations.NamedQuery(name = "client.byName", query = "From ClientEntity d where upper(d.name) like upper(?1)")
@org.hibernate.annotations.NamedQuery(name = "client.byPatronymic", query = "From ClientEntity d where upper(d.patronymic) like upper(?1)")
@org.hibernate.annotations.NamedQuery(name = "client.byPhone", query = "From ClientEntity d where upper(d.phone) like upper(?1)")
@org.hibernate.annotations.NamedQuery(name = "client.byRoute", query = "Select d From ClientEntity d where upper(d.route.name) like upper(?1)")

@Entity
@Table(name = "client", schema = "public", catalog = "travel_agency")
public class ClientEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "surname", nullable = false, length = 30)
    private String surname;
    @Basic
    @Column(name = "name", nullable = false, length = 30)
    private String name;
    @Basic
    @Column(name = "patronymic", nullable = false, length = 30)
    private String patronymic;
    @Basic
    @Column(name = "phone", nullable = false, length = 11)
    private String phone;
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_buy", nullable = false)
    private java.util.Date dateOfBuy;
    @Temporal(TemporalType.TIME)
    @Column(name = "time_of_buy", nullable = false)
    private java.util.Date timeOfBuy;
    @ManyToOne
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    private RouteEntity route;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public java.util.Date getDateOfBuy() {
        return dateOfBuy;
    }

    public void setDateOfBuy(java.util.Date dateOfBuy) {
        this.dateOfBuy = dateOfBuy;
    }

    public java.util.Date getTimeOfBuy() {
        return timeOfBuy;
    }

    public void setTimeOfBuy(java.util.Date timeOfBuy) {
        this.timeOfBuy = timeOfBuy;
    }

    public RouteEntity getRoute() {
        return route;
    }

    public void setRoute(RouteEntity route) {
        this.route = route;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientEntity that = (ClientEntity) o;
        return id == that.id && route == that.route && Objects.equals(surname, that.surname) && Objects.equals(name, that.name) && Objects.equals(patronymic, that.patronymic) && Objects.equals(phone, that.phone) && Objects.equals(dateOfBuy, that.dateOfBuy) && Objects.equals(timeOfBuy, that.timeOfBuy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, name, patronymic, phone, dateOfBuy, timeOfBuy, route);
    }
}
