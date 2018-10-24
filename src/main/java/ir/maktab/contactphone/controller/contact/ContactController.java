package ir.maktab.contactphone.controller.contact;

import ir.maktab.contactphone.model.contact.Contact;
import ir.maktab.contactphone.model.contact.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/phonebook")
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity addContact(@RequestBody @Validated Contact contact, BindingResult be) {
        try {
            if (be.hasErrors()) {
                System.out.println(be.getAllErrors());
                return ResponseEntity.status(401).build();
            }
            this.contactService.save(contact);
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Email or mobile phone not unique!!!");
        }
    }

    @GetMapping("/searchbyid")
    public ResponseEntity getById(@RequestParam("id") Long id){

        Optional<Contact> contactOptional = contactService.findById(id);

        if(contactOptional.isPresent()) {
            System.out.println("this id is ");
            return ResponseEntity.ok(contactOptional.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("no contact added!!");
        }
   }

    @GetMapping("/list")
    public ResponseEntity<List<Contact>> list() {
        return ResponseEntity.ok(contactService.readAll());
//        return ResponseEntity.status(205).body(phoneBookService.readAll());
    }

    @GetMapping("/show-contact")
    public ModelAndView show() {
        //return ResponseEntity.ok(contactService.readAll());
//        return ResponseEntity.status(205).body(phoneBookService.readAll());
        ModelAndView mv = new ModelAndView("/WEB-INF/views/show-contact.html");
        return mv;
        //return new ModelAndView("show-contact");
    }

//    @GetMapping("/show-contacts")
//    public ModelAndView showlist() {
//        //return ResponseEntity.ok(contactService.readAll());
////        return ResponseEntity.status(205).body(phoneBookService.readAll());
//        ModelAndView mv = new ModelAndView("/show-contacts");
//        return mv;
//        //return new ModelAndView("show-contact");
//    }


    @PostMapping(value = "/delete/{id}")
    public void addDelete(@PathVariable("id")  Long id) {
        System.out.println(id);
        this.contactService.deleteById(id);
    }

    @GetMapping(value = "/search" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Iterable<Contact>> search(@RequestParam(required = false, value = "firstName") String firstName,
                                                    @RequestParam(required = false, value = "lastName") String lastName,
                                                    @RequestParam(required = false, value = "homePhone") String homePhone,
                                                    @RequestParam(required = false, value = "cellPhone") String cellPhone,
                                                    @RequestParam(required = false, value = "email") String email) {
        return ResponseEntity.ok(contactService.search(firstName, lastName, homePhone, cellPhone, email));
    }

}
