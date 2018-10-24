package ir.maktab.contactphone.model.contact.service;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.Expressions;
import ir.maktab.contactphone.model.contact.Contact;
import ir.maktab.contactphone.model.contact.QContact;
import ir.maktab.contactphone.model.contact.dao.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void save(Contact contact) {
        this.contactRepository.save(contact);
    }

    public List<Contact> readAll() {
        return this.contactRepository.findAll();
    }

    public Optional<Contact> findById(Long id) {
        return this.contactRepository.findById(id);
    }

    public void update(Contact contact) {
        this.contactRepository.save(contact);
    }

    public void deleteById(Long id) {
        this.contactRepository.deleteById(id);
    }

    public Iterable<Contact> search(String firstName, String lastName, String homePhone,
                                    String cellPhone, String email) {
        QContact contact = QContact.contact;
        BooleanBuilder where = new BooleanBuilder();

        if (firstName != null) {
            where.and(contact.name.like(Expressions.asString("%").concat(firstName).concat("%")));
        }
        if (lastName != null) {
            where.and(contact.family.like(Expressions.asString("%").concat(lastName).concat("%")));
        }
        if (homePhone != null) {
            where.and(contact.tel.like(Expressions.asString("%").concat(homePhone).concat("%")));
        }
        if (cellPhone != null) {
            where.and(contact.mobile.like(Expressions.asString("%").concat(cellPhone).concat("%")));
        }
        if (email != null) {
            where.and(contact.email.like(Expressions.asString("%").concat(email).concat("%")));
        }
        Iterable<Contact> contacts = contactRepository.findAll(where);
        return contacts;
    }

}
