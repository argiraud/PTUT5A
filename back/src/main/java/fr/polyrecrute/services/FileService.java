package fr.polyrecrute.services;

import fr.polyrecrute.models.Entity__;
import fr.polyrecrute.models.File__;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    File__ storeFile(MultipartFile file, Entity__ entity);
    File__ findById(String fileId);
}
