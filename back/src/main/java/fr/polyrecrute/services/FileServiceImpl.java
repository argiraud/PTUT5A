package fr.polyrecrute.services;

import fr.polyrecrute.models.Entity__;
import fr.polyrecrute.models.File__;
import fr.polyrecrute.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public File__ storeFile(MultipartFile pFile, Entity__ entity) {
        String fileName = StringUtils.cleanPath(pFile.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Filename contains invalid path sequence " + fileName);
            }

            File__ file = new File__(entity, fileName, pFile.getContentType(), pFile.getBytes());

            return fileRepository.save(file);
        } catch (IOException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not store file " + fileName);
        }
    }

    @Override
    @Transactional
    public File__ findById(String fileId) {
        return fileRepository.findByIdFile(fileId)
                .orElseThrow(() -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found");});
    }
}
