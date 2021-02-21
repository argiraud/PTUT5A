package fr.polyrecrute.models;

import fr.polyrecrute.responceType.File;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.transaction.Transactional;

@javax.persistence.Entity(name = "File")
@Table(name = "file")
public class File__ {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "file_id")
    private String idFile;

    @ManyToOne(targetEntity = Entity__.class, fetch = FetchType.LAZY)
    private Entity__ entity;

    @Column(length = 50)
    private String name;

    private String fileType;

    @Lob
    private byte[] data;

    public File__() {
    }

    public File__(Entity__ entity, String name, String fileType, byte[] data) {
        this.entity = entity;
        this.name = name;
        this.fileType = fileType;
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }

    public String getIdFile() {
        return idFile;
    }

    public Entity__ getEntity() {
        return entity;
    }

    public String getName() {
        return name;
    }

    public String getFileType() {
        return fileType;
    }

    public File getTransactionalObject() {
        return new File(idFile, name, fileType);
    }
}
