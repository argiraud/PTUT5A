package fr.polyrecrute.models;

import fr.polyrecrute.responceType.Offer;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@javax.persistence.Entity(name = "Offer")
@Table(name = "offer")
public class Offer__ {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "offer_id")
    private String idOffer;

    @ManyToOne(targetEntity = Company__.class, fetch = FetchType.LAZY)
    private Company__ company;

    @ManyToMany(targetEntity = File__.class, cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "offer_file",
            joinColumns = @JoinColumn(name = "offer_id"),
            inverseJoinColumns = @JoinColumn(name = "file_id"))
    private Set<File__> files = new HashSet<>();

    @Column(length = 100)
    private String title;

    @Column(length = 50)
    private String keyWord;

    @Column(length = 500)
    private String description;

    @Column(length = 10)
    private String state;

    private boolean validate;

    public Offer__() {
    }

    public Offer__(Company__ company, String title, String keyWord, String description, String state) {
        this.company = company;
        this.title = title;
        this.keyWord = keyWord;
        this.description = description;
        this.validate = false;
        this.state = state;
    }

    public String getIdOffer() {
        return idOffer;
    }

    public Company__ getCompany() {
        return company;
    }

    public Set<File__> getFiles() {
        return files;
    }

    public String getTitle() {
        return title;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public String getDescription() {
        return description;
    }

    public boolean isValidate() {
        return validate;
    }

    public void addFile(File__ file){
        files.add(file);
    }

    public Offer getTransactionalObject() {
        return new Offer(idOffer, company, files, title, keyWord, description, state, validate);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }
}
