package github.nikhrom.javatraining.spring.mvc_hibernate.dao;

import github.nikhrom.javatraining.spring.mvc_hibernate.entity.Department;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DepartmentDao implements Dao<Integer, Department>{

    private final SessionFactory sessionFactory;

    @Override
    public Optional<Department> get(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Department> getAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.createQuery("from Department", Department.class)
                .getResultList();
    }

    @Override
    public void save(Department value) {
        var currentSession = sessionFactory.getCurrentSession();
        currentSession.save(value);
    }

    @Override
    public void update(Department value) {

    }

    @Override
    public void delete(Department value) {

    }
}
