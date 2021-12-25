package com.zachesov.spring.mvc_hibernate_aop.dao;

import com.zachesov.spring.mvc_hibernate_aop.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private SessionFactory factory;

    @Override
    public List<Employee> getAllEmployees() {

        Session session = factory.getCurrentSession();
//        List<Employee> allEmployee = session.createQuery("from Employee", Employee.class)
//                .getResultList();
        Query<Employee> query = session.createQuery("from Employee", Employee.class);
        List<Employee> allEmployee = query.getResultList();

        System.out.println(allEmployee);
        return allEmployee;
    }

    @Override
    public void saveEmployee(Employee employee) {

        Session session = factory.getCurrentSession();

        session.saveOrUpdate(employee);

    }

    @Override
    public Employee getEmployee(int id) {

        Session session = factory.getCurrentSession();

        Employee employee = session.get(Employee.class, id);

        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        Session session = factory.getCurrentSession();
        Query<Employee> query = session.createQuery("delete from Employee where id=:empId");
        query.setParameter("empId", id);
        query.executeUpdate();

    }
}
