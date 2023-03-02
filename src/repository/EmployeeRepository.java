package repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import entity.Employee;

public class EmployeeRepository {
	private SessionFactory factory;
	
	public EmployeeRepository() {
		factory = new Configuration()
				.configure()
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();
	}
	
	public void createEmployee(Employee employee) {		
		Session session = factory.getCurrentSession();

		session.beginTransaction();
		
		session.save(employee);
		
		session.getTransaction().commit();
	
		session.close();
	}
	
	public Employee readEmployee(int id) {
		Session session = factory.getCurrentSession();

		session.beginTransaction();
		
		Query query = session.createQuery("select e from Employee e where e.id=:paramId");
		query.setParameter("paramId", id);
		
		List<Employee> employees = query.getResultList();
		
		session.getTransaction().commit();
	
		session.close();
		
		return employees.get(0);
	}
	
	public List<Employee> readEmployee(String company) {
		Session session = factory.getCurrentSession();

		session.beginTransaction();
		
		Query query = session.createQuery("select e from Employee e where e.company=:paramCompany");
		query.setParameter("paramCompany", company);
		
		List<Employee> employees = query.getResultList();
		
		session.getTransaction().commit();
	
		session.close();
		
		return employees;
	}
	
	public void updateEmployee() {
		Session session = factory.getCurrentSession();
	
		session.getTransaction().commit();
		
		session.close();
	}
	
	public void deleteEmployee(int id) {
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		Query query = session.createQuery("delete from Employee e where e.id=:paramId");
		query.setParameter("paramId", id);
		
		query.executeUpdate();
		
		session.getTransaction().commit();
		
		session.close();
	}
}
