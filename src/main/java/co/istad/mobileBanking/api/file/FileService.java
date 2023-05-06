package co.istad.mobileBanking.api.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
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

    /**
     * find all file
     * @return fieDto
     * @throws FileNotFoundException
     */
    List<FileDto> findAllFile() throws FileNotFoundException;

    /**
     * find file by Url name
     * @param fileName request form data client
     * @return fileDto
     * @throws FileNotFoundException
     */
    List<FileDto> findByName(String fileName) throws FileNotFoundException;

    /**
     * uses to delete file
     * @param fileName request data client
     * @return fileDto
     */
    void deleteByFileName(String fileName) throws IOException;

    /**
     * delete All file
     * @throws IOException
     */
    void deleteAllFile() throws IOException;
}
