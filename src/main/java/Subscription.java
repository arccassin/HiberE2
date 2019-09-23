import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created by User on 21 Сент., 2019
 */
@Entity
@Table(name = "Subscriptions")
@org.hibernate.annotations.Immutable
public class Subscription {

    @Embeddable
    public static class Id implements Serializable {

        @Column(name = "student_id")
        protected int studentId;

        @Column(name = "course_id")
        protected int courseId;

        public Id() {
        }

        public Id(int studentId, int courseId) {
            this.courseId = courseId;
            this.studentId = studentId;
        }

        @Override
        public boolean equals(Object o) {
            if (o != null && (o instanceof Id)){
                Id that = (Id) o;
                return this.studentId == that.studentId
                        && this.courseId == that.courseId;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(studentId, courseId);
        }
    }

    @EmbeddedId
    protected Id id = new Id();

    @ManyToOne
    @JoinColumn(name = "student_id",
            insertable = false, updatable = false)
    protected Student student;

    @ManyToOne
    @JoinColumn(name = "course_id",
        insertable = false, updatable = false)
    protected Course course;

    @Column(name = "subscription_date",
            updatable = false)
    @NotNull
    protected Date subscriptionDate;

    public Subscription(){

    }

    public Subscription(
            Date subscriptionDate,
            Student student,
            Course course) {
        this.subscriptionDate = subscriptionDate;
        this.id.courseId = course.getId();
        this.id.studentId = student.getId();

        course.getSubscriptions().add(this);
        student.getSubscriptions().add(this);

    }

}
