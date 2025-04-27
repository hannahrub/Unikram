package softwarequali.seminarprojekt;

import org.springframework.data.annotation.Id;

/*

The other two properties, firstName and lastName, are left unannotated. It is assumed that they are mapped to
fields that share the same name as the properties themselves.

The convenient toString() method prints out the details about a customer.
MongoDB stores data in collections. Spring Data MongoDB maps the Customer class into a collection called customer.
 If you want to change the name of the collection, you can use Spring Data MongoDB’s @Document annotation on the class.*/

public class Customer {

    @Id
    public String id;

    public String firstName;
    public String lastName;

    public Customer() {}

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

}

// todo: remove, this is an example class for database operations