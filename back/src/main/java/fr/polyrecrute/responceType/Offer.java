package fr.polyrecrute.responceType;

import fr.polyrecrute.models.Company__;
import fr.polyrecrute.models.File__;

import java.util.HashSet;
import java.util.Set;

public class Offer {

    private String idOffer;
    private Company company;
    private Set<File> files = new HashSet<>();
    private String title;
    private String keyWord;
    private String description;
    private String state;
    private boolean validate;

    public Offer() {
    }

    public Offer(String idOffer, Company__ pCompany, Set<File__> pFiles, String title, String keyWord, String description, String state, boolean validate) {
        this.idOffer = idOffer;
        this.company = pCompany.getTransactionalObject();
        for (File__ file: pFiles) {
            files.add(file.getTransactionalObject());
        }
        this.title = title;
        this.keyWord = keyWord;
        this.description = description;
        this.state = state;
        this.validate = validate;
    }

    public String getIdOffer() {
        return idOffer;
    }

    public Company getCompany() {
        return company;
    }

    public Set<File> getFiles() {
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

    public String getState() {
        return state;
    }
}
