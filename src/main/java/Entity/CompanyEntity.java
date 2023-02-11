package Entity;

import jakarta.persistence.*;
import java.util.Objects;

@org.hibernate.annotations.NamedQuery(name = "company.all", query = "From CompanyEntity  order by id")
@org.hibernate.annotations.NamedQuery(name = "company.byId", query = "From CompanyEntity d where d.id = ?1  order by d.id ")
@org.hibernate.annotations.NamedQuery(name = "company.byName", query = "From CompanyEntity d where upper(d.name) like upper(?1)")
@org.hibernate.annotations.NamedQuery(name = "company.byFlights", query = "From CompanyEntity d where upper(d.num) like upper(?1)")

@Entity
@Table(name = "company", schema = "public", catalog = "travel_agency")
public class CompanyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @ManyToOne
    @JoinColumn(name = "flights_num", referencedColumnName = "num")
    private FlightsEntity num;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FlightsEntity getNum() {
        return num;
    }

    public void setNum(FlightsEntity num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyEntity that = (CompanyEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(num, that.num);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, num);
    }
}
