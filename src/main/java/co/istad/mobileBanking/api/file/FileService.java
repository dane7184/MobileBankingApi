package co.istad.mobileBanking.api.file;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    /**
     *uses to upload a single file
     * @param file request form data client
     * @return fileDto
     */
    FileDto uploadSingle(MultipartFile file);

    /**
     *uses to upload a multiple file
     * @param files request form data client
     * @return fileDto
     */
    List<FileDto> uploadMultiple(List<MultipartFile> files);
}
