package Entity;

import jakarta.persistence.*;

import java.util.Objects;

@org.hibernate.annotations.NamedQuery(name = "route.all", query = "From RouteEntity order by id")
@org.hibernate.annotations.NamedQuery(name = "route.byId", query = "From RouteEntity d where d.id = ?1")
@org.hibernate.annotations.NamedQuery(name = "route.byName", query = "From RouteEntity d where upper(d.name) like upper(?1)")
@org.hibernate.annotations.NamedQuery(name = "route.byCity", query = "Select d From RouteEntity d where upper(d.cityId.city) like upper(?1)")
@org.hibernate.annotations.NamedQuery(name = "route.byHotel", query = "Select d From RouteEntity d where upper(d.hotelId.name) like upper(?1)")
@org.hibernate.annotations.NamedQuery(name = "route.byCompany", query = "Select d From RouteEntity d where upper(d.companyId.name) like upper(?1)")
@org.hibernate.annotations.NamedQuery(name = "route.byEmployee", query = "Select d From RouteEntity d where upper(d.employeeId.name) like upper(?1)")

@Entity
@Table(name = "route", schema = "public", catalog = "travel_agency")
public class RouteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private CityEntity cityId;
    @Temporal(TemporalType.TIME)
    @Column(name = "duration", nullable = false, length = 100)
    private java.util.Date duration;
    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    private HotelEntity hotelId;
    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private CompanyEntity companyId;
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private EmployeeEntity employeeId;

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

    public CityEntity getCityId() {
        return cityId;
    }

    public void setCityId(CityEntity cityId) {
        this.cityId = cityId;
    }

    public java.util.Date getDuration() {
        return duration;
    }

    public void setDuration(java.util.Date duration) {
        this.duration = duration;
    }

    public HotelEntity getHotelId() {
        return hotelId;
    }

    public void setHotelId(HotelEntity hotelId) {
        this.hotelId = hotelId;
    }

    public CompanyEntity getCompanyId() {
        return companyId;
    }

    public void setCompanyId(CompanyEntity companyId) {
        this.companyId = companyId;
    }

    public EmployeeEntity getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(EmployeeEntity employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouteEntity that = (RouteEntity) o;
        return id == that.id && cityId == that.cityId && hotelId == that.hotelId && companyId == that.companyId && employeeId == that.employeeId && Objects.equals(name, that.name) && Objects.equals(duration, that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cityId, duration, hotelId, companyId, employeeId);
    }
}
