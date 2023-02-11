package Entity;

import jakarta.persistence.*;

import java.util.Objects;

@org.hibernate.annotations.NamedQuery(name = "hotel.all", query = "From HotelEntity  order by id")
@org.hibernate.annotations.NamedQuery(name = "hotel.byId", query = "From HotelEntity d where d.id = ?1  order by d.id ")
@org.hibernate.annotations.NamedQuery(name = "hotel.byName", query = "From HotelEntity d where upper(d.name) like upper(?1)")
@org.hibernate.annotations.NamedQuery(name = "hotel.byClass", query = "From HotelEntity d where d.clazz = ?1")
@org.hibernate.annotations.NamedQuery(name = "hotel.byCategories", query = "From HotelEntity d where upper(d.categories) like upper(?1)")

@Entity
@Table(name = "hotel", schema = "public", catalog = "travel_agency")
public class HotelEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Basic
    @Column(name = "class", nullable = false)
    private int clazz;
    @Basic
    @Column(name = "categories", nullable = false, length = 100)
    private String categories;


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

    public int getClazz() {
        return clazz;
    }

    public void setClazz(int clazz) {
        this.clazz = clazz;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelEntity that = (HotelEntity) o;
        return id == that.id && clazz == that.clazz && Objects.equals(name, that.name) && Objects.equals(categories, that.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, clazz, categories);
    }
}
