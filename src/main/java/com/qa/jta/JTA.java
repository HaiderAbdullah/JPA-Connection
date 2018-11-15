package com.qa.jta;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import static javax.transaction.Transactional.TxType.REQUIRED;

import com.qa.task.Account;

public class JTA{


	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
    public List<Account> findAllMovies() {
        TypedQuery<Account> query = manager.createQuery("SELECT m FROM Main m ORDER BY m.id DESC", Account.class);
        return query.getResultList();
    }
    
    public Account findMovie(Long id) {
        return manager.find(Account.class, id);
    }
    
    @Transactional(REQUIRED)
    public Account create(Account account) {
        manager.persist(account);
        return account;
    }
    
    @Transactional(REQUIRED)
    public Account update(Account account) {
        manager.merge(account);
        return account;
    }

    @Transactional(REQUIRED)
    public Account delete(Account account) {
        manager.remove(account);
        return account;
    }




	public String createAccount(String account) {
		Account anAccount = JSONUtil.getObjectForJSON(account, Account.class);
		manager.persist(account);
		return "{\"message\": \"account sucessfully added\"}";
	}
}

	