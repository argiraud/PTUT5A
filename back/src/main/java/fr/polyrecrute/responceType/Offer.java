package fr.polyrecrute.responceType;

import fr.polyrecrute.models.Company__;
import fr.polyrecrute.models.File__;

import java.util.HashSet;
import java.util.Set;

public class Offer {

    private String idOffer;
    private Entity company;
    private Set<File> files = new HashSet<>();
    private String title;
    private String keyWord;
    private String description;

    public Offer() {
    }

    public Offer(String idOffer, Company__ pCompany, Set<File__> pFiles, String title, String keyWord, String description) {
        this.idOffer = idOffer;
        this.company = pCompany.getTransactionalObject();
        for (File__ file: pFiles) {
            files.add(file.getTransactionalObject());
        }
        this.title = title;
        this.keyWord = keyWord;
        this.description = description;
    }

    public String getIdOffer() {
        return idOffer;
    }

    public Entity getCompany() {
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
}
