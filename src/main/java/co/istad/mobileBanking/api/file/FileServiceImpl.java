package co.istad.mobileBanking.api.file;

import co.istad.mobileBanking.util.FileUtil;
import jakarta.validation.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service

public class FileServiceImpl implements FileService{

    private FileUtil fileUtil;

    @Autowired
    public void setFileUtil(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    // upload is here
    @Override
    public FileDto uploadSingle(MultipartFile file) {
        return fileUtil.upload(file);
    }

    @Override
    public List<FileDto> uploadMultiple(List<MultipartFile> files) {
        List<FileDto> filesDto = new ArrayList<>();
        for (MultipartFile file : files){
            filesDto.add(fileUtil.upload(file));
        }
        return filesDto;
    }

    // find is here
    @Override
    public List<FileDto> findAllFile() throws FileNotFoundException {
        return fileUtil.getAllFile();
    }

    @Override
    public List<FileDto> findByName(String fileName) throws FileNotFoundException {
        return fileUtil.getFileByName(fileName);
    }

    // delete is here
    @Override
    public void deleteByFileName(String fileName) throws IOException {
        fileUtil.deleteFileByName(fileName);
    }

    @Override
    public void deleteAllFile() throws IOException {
        fileUtil.deleteAllFile();
    }
}
