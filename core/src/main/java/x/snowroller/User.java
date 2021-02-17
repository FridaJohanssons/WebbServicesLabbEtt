package x.snowroller;


import javax.persistence.*;


//SKAPA EN NY TABELL MED FÄRRE VARIABLER. INTE HA USERS FÖR DEN HAR FÖR MÅNGA
@Entity
@Table(name="UserWeb")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "Lastname")
    private String lastName;

    public User(String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.firstName = lastName;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String userName) {
        this.firstName = userName;
    }

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    @Override
    public String toString() {
        return "[" + id + "/" + firstName + "/" + lastName +"]";
    }
}