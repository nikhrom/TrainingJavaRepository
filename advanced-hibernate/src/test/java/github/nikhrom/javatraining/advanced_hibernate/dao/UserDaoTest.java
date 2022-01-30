package github.nikhrom.javatraining.advanced_hibernate.dao;

import github.nikhrom.javatraining.advanced_hibernate.entity.Birthday;
import github.nikhrom.javatraining.advanced_hibernate.entity.Payment;
import github.nikhrom.javatraining.advanced_hibernate.entity.PersonalData;
import github.nikhrom.javatraining.advanced_hibernate.entity.User;
import github.nikhrom.javatraining.advanced_hibernate.util.HibernateUtil;
import github.nikhrom.javatraining.advanced_hibernate.util.TestDataImporter;
import org.assertj.core.api.Assertions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserDaoTest {

    SessionFactory sessionFactory;

    UserDao userDao = UserDao.getInstance();

    @BeforeAll
    void initDB(){
        sessionFactory = HibernateUtil.buildSessionFactory();
        TestDataImporter.importData(sessionFactory);
    }


    @Test
    void findAll(){
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<User> allUsers = userDao.findAll(session);

            assertThat(allUsers).hasSize(5);

            List<String> fullNames = allUsers.stream()
                    .map(User::getPersonalData)
                    .map(data -> data.getFirstname() + " " + data.getLastname())
                    .collect(toList());


            assertThat(fullNames).containsExactlyInAnyOrder(
                    "Bill Gates", "Steve Jobs", "Sergey Brin",
                    "Tim Cook", "Diane Greene"
            );

            session.getTransaction().commit();
        }
    }

    @Test
    public void findAllByFirstName(){
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<User> allUsers = userDao.findAllByFirstName(session, "Bill");

            assertThat(allUsers).hasSize(1);

            List<String> firstNames = allUsers.stream()
                    .map(User::getPersonalData)
                    .map(PersonalData::getFirstname)
                    .collect(toList());


            assertThat(firstNames).containsExactlyInAnyOrder(
                    "Bill"
            );

            session.getTransaction().commit();
        }
    }

    @Test
    public void findLimitedUsersOrderedByBirthday(){
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<User> allUsers = userDao.findLimitedUsersOrderedByBirthday(session, 2);

            assertThat(allUsers).hasSize(2);

            List<LocalDate> birthdays = allUsers.stream()
                    .map(User::getPersonalData)
                    .map(PersonalData::getBirthday)
                    .map(Birthday::getDate)
                    .collect(toList());


            var orderedBirthdays = List.of(
                    LocalDate.of(1955, Month.JANUARY, 1),
                    LocalDate.of(1955, Month.FEBRUARY, 24)
            );


            assertThat(birthdays).containsExactly(
                    orderedBirthdays.toArray(LocalDate[]::new)
            );

            session.getTransaction().commit();
        }
    }


    @Test
    public void findAveragePaymentAmountByFirstAndLastName(){
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            Double avgPaymentInCompany = userDao
                    .findAveragePaymentAmountByFirstAndLastName(session, "Bill", "Gates");

            assertThat(avgPaymentInCompany).isEqualTo(300);

            session.getTransaction().commit();
        }
    }

    @Test
    public void findAllPaymentsByCompanyName(){
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Payment> allPayments = userDao.findAllPaymentsByCompanyName(session, "Microsoft");

            assertThat(allPayments).hasSize(3);

            var amounts = allPayments.stream()
                    .map(Payment::getAmount)
                    .collect(toList());

            assertThat(amounts).contains(100, 300, 500);

            session.getTransaction().commit();
        }
    }


    @AfterAll
    void finish(){
        sessionFactory.close();
    }


}
