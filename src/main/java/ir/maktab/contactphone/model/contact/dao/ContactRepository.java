package ir.maktab.contactphone.model.contact.dao;

import ir.maktab.contactphone.model.contact.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ContactRepository extends JpaRepository<Contact, Long>, QuerydslPredicateExecutor<Contact> {

}
