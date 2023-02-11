package Entity;

import jakarta.persistence.*;

import java.util.Objects;

@org.hibernate.annotations.NamedQuery(name = "employee.all", query = "From EmployeeEntity  order by id")
@org.hibernate.annotations.NamedQuery(name = "employee.byId", query = "From EmployeeEntity d where d.id = ?1  order by d.id ")
@org.hibernate.annotations.NamedQuery(name = "employee.bySurname", query = "From EmployeeEntity d where upper(d.surname) like upper(?1)")
@org.hibernate.annotations.NamedQuery(name = "employee.byName", query = "From EmployeeEntity d where upper(d.name) like upper(?1)")
@org.hibernate.annotations.NamedQuery(name = "employee.byPatronymic", query = "From EmployeeEntity d where upper(d.patronymic) like upper(?1)")
@org.hibernate.annotations.NamedQuery(name = "employee.byAddress", query = "From EmployeeEntity d where upper(d.address) like upper(?1)")
@org.hibernate.annotations.NamedQuery(name = "employee.byPost", query = "From EmployeeEntity d where upper(d.post) like upper(?1)")
@org.hibernate.annotations.NamedQuery(name = "employee.bySalary", query = "From EmployeeEntity d where d.salary = ?1")
@org.hibernate.annotations.NamedQuery(name = "employee.byTransfer", query = "Select c From EmployeeEntity c where c.transfer.num = ?1")
@org.hibernate.annotations.NamedQuery(name = "employee.byPhone", query = "From EmployeeEntity d where upper(d.phone) like upper(?1)")
@Entity
@Table(name = "employee", schema = "public", catalog = "travel_agency")
public class EmployeeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "surname", nullable = false, length = 20)
    private String surname;
    @Basic
    @Column(name = "name", nullable = false, length = 20)
    private String name;
    @Basic
    @Column(name = "patronymic", nullable = false, length = 20)
    private String patronymic;
    @Basic
    @Column(name = "address", nullable = false, length = 20)
    private String address;
    @Temporal(TemporalType.DATE)
    @Column(name = "birth", nullable = false)
    private java.util.Date birth;
    @Basic
    @Column(name = "post", nullable = false, length = 100)
    private String post;
    @Basic
    @Column(name = "salary", nullable = false)
    private int salary;
    @OneToOne
    @JoinColumn(name = "transfer_id", referencedColumnName = "id")
    private TransferEntity transfer;
    @Basic
    @Column(name = "phone", nullable = false, length = 11)
    private String phone;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public java.util.Date getBirth() {
        return birth;
    }

    public void setBirth(java.util.Date birth) {
        this.birth = birth;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public TransferEntity getTransferId() {
        return transfer;
    }

    public void setTransferId(TransferEntity id) {
        this.transfer = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return id == that.id && salary == that.salary && Objects.equals(surname, that.surname) && Objects.equals(name, that.name) && Objects.equals(patronymic, that.patronymic) && Objects.equals(address, that.address) && Objects.equals(birth, that.birth) && Objects.equals(post, that.post) && Objects.equals(id, that.id) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, name, patronymic, address, birth, post, salary, id, phone);
    }
}
