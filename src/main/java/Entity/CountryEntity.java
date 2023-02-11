package Entity;

import jakarta.persistence.*;

import java.util.Objects;

@org.hibernate.annotations.NamedQuery(name = "country.all", query = "From CountryEntity  order by id")
@org.hibernate.annotations.NamedQuery(name = "country.byId", query = "From CountryEntity d where d.id = ?1  order by d.id ")
@org.hibernate.annotations.NamedQuery(name = "country.byName", query = "From CountryEntity d where upper(d.country) like upper(?1)")

@Entity
@Table(name = "country", schema = "public", catalog = "travel_agency")
public class CountryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "country", nullable = false, length = 168)
    private String country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryEntity that = (CountryEntity) o;
        return id == that.id && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country);
    }
}
