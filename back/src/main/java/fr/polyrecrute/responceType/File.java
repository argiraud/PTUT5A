package fr.polyrecrute.responceType;

public class File {

    private String idFile;
    private String name;
    private String fileType;

    public File(String idFile, String name, String fileType) {
        this.idFile = idFile;
        this.name = name;
        this.fileType = fileType;
    }

    public String getIdFile() {
        return idFile;
    }

    public String getName() {
        return name;
    }

    public String getFileType() {
        return fileType;
    }
}
