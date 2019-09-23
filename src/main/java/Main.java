import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

/**
 * Created by User on 15 Сент., 2019
 */
public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

        try {
            Course course = session.get(Course.class, 1);

//        course.getStudents().stream().forEach(student -> student.getName());
            System.out.println("Start my code");
            List<Subscription> courseSubscriptions = course.getSubscriptions(); //.stream().forEach(s -> s.student.getName());
            for (int i = 0; i < courseSubscriptions.size(); i++) {
                Subscription sub = courseSubscriptions.get(i);
                Student student = sub.student;
                System.out.println("Студент " + student.getName() + " начал курс " + sub.subscriptionDate.toString());
            }

            System.out.println("Finish my code");

        }finally {
            session.close();
            sessionFactory.close();
        }
    }
}
