import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
@Entity
@Table(name = "Subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Course course;

    @Column(name = "subscription_date")
    protected Date subscriptionDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}