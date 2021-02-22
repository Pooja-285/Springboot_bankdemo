package com.demo.bank.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.bank.model.BankAccount;

@Repository
public class BankDaoImpl implements BankDao {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	@Transactional
	public Optional<BankAccount> findById(Long id) {

//		Get the current hibernate session	
		Session currentSession = entityManager.unwrap(Session.class);
		Optional<BankAccount> account = Optional.of(currentSession.get(BankAccount.class, id));
		
		return account;
	}

	@Override
	public void saveBankAccount(BankAccount account) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public List<BankAccount> findAll() {
		
		Session currentSession = entityManager.unwrap(Session.class);
			Query<BankAccount> query = 	currentSession.createQuery("from BankAccount", BankAccount.class);
			List<BankAccount> account = query.getResultList();
	
		return account;
	}

}
