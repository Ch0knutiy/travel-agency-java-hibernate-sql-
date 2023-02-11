package Entity;

import jakarta.persistence.*;

import java.util.Objects;

@org.hibernate.annotations.NamedQuery(name = "transfer.all", query = "From TransferEntity order by id")
@org.hibernate.annotations.NamedQuery(name = "transfer.byId", query = "From TransferEntity r where r.id = ?1")
@org.hibernate.annotations.NamedQuery(name = "transfer.byNum", query = "From TransferEntity d where d.num = ?1")
@org.hibernate.annotations.NamedQuery(name = "transfer.byPost", query = "From TransferEntity d where upper(d.post) like upper(?1)")
@org.hibernate.annotations.NamedQuery(name = "transfer.byReason", query = "From TransferEntity d where upper(d.reason) like upper(?1)")

@Entity
@Table(name = "transfer", schema = "public", catalog = "travel_agency")
public class TransferEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "num", nullable = false)
    private int num;
    @Basic
    @Column(name = "post", nullable = false, length = 100)
    private String post;
    @Basic
    @Column(name = "reason", nullable = false, length = 100)
    private String reason;
    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false)
    private java.util.Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferEntity that = (TransferEntity) o;
        return id == that.id && num == that.num && Objects.equals(post, that.post) && Objects.equals(reason, that.reason) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, num, post, reason, date);
    }
}
