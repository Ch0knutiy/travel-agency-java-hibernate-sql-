package Entity;

import jakarta.persistence.*;

import java.util.Objects;

@org.hibernate.annotations.NamedQuery(name = "city.all", query = "From CityEntity  order by id")
@org.hibernate.annotations.NamedQuery(name = "city.byId", query = "From CityEntity d where d.id = ?1  order by d.id ")
@org.hibernate.annotations.NamedQuery(name = "city.byName", query = "From CityEntity d where upper(d.city) like upper(?1)")
@org.hibernate.annotations.NamedQuery(name = "city.byCountry", query = "Select c From CityEntity c where upper(c.country.country) like upper(?1)")
@Entity
@Table(name = "city", schema = "public", catalog = "travel_agency")
public class CityEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "city", nullable = false, length = 168)
    private String city;
    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private CountryEntity country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public CountryEntity getCountry() {
        return country;
    }

    public void setCountryId(CountryEntity country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityEntity that = (CityEntity) o;
        return id == that.id && country == that.country && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, country);
    }
}
