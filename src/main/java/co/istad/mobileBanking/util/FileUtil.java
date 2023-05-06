package co.istad.mobileBanking.util;

import co.istad.mobileBanking.api.file.FileDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
public class FileUtil {

    @Value("${file.server-path}")
    private String fileServerPath;

    @Value("${file.client-path}")
    private String fileClientPath;

    // upload file
    public FileDto upload(MultipartFile file){
        int lastDotIndex = file.getOriginalFilename().lastIndexOf(".");
        String extension = file.getOriginalFilename().substring(lastDotIndex);
        long size = file.getSize();
        String name = String.format("%s%s", UUID.randomUUID(),extension);
        String url = String.format("%s%s",fileClientPath,name);
        Path path = Paths.get(fileServerPath + name);

        try {
            Files.copy(file.getInputStream(), path);
            return FileDto.builder()
                    .name(name)
                    .url(url)
                    .extension(extension)
                    .size(size)
                    .build();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    //Get file
    public List<FileDto> getAllFile() throws FileNotFoundException{
        List<FileDto> fileDtoList = new ArrayList<>();
        File file = new File(fileServerPath);
        File[] files = file.listFiles();
        if (Objects.nonNull(files)){
            for (File obj : files) {
                int lastDotIndex = obj.getName().lastIndexOf(".");
                String extension = obj.getName().substring(lastDotIndex);
                fileDtoList.add(FileDto.builder()
                        .name(obj.getName())
                        .url(obj.getAbsolutePath())
                        .extension(extension)
                        .size(obj.length())
                        .build());
            }
        }
        return fileDtoList;
    }

    public List<FileDto> getFileByName(String fileName) throws FileNotFoundException {
        List<FileDto> fileDtoList = new ArrayList<>();
        File file = new File(fileServerPath);
        File[] files= file.listFiles((dir, name) -> name.startsWith(fileName));
        if (Objects.nonNull(files)){
            for (File obj : files) {
                int lastDotIndex = obj.getName().lastIndexOf(".");
                String extension = obj.getName().substring(lastDotIndex);
                fileDtoList.add(FileDto.builder()
                        .name(obj.getName())
                        .url(obj.getAbsolutePath())
                        .extension(extension)
                        .size(obj.length())
                        .build());
            }
        }
        return fileDtoList;
    }

    public void deleteFileByName(String fileName) throws IOException {
        List<FileDto> fileDtoList = getFileByName(fileName);
        if (!fileDtoList.isEmpty()){
            for (FileDto obj : fileDtoList){
                Path path = Paths.get(obj.url());
                Files.delete(path);
            }
        } else {
            throw new FileNotFoundException("File doesn't exist");
        }
    }

    public void deleteAllFile() throws IOException {
        List<FileDto> fileDtoList = getAllFile();
        if (!fileDtoList.isEmpty()){
            for (FileDto obj : fileDtoList){
                Path path = Paths.get(obj.url());
                Files.delete(path);
            }
        } else {
            throw new FileNotFoundException("File doesn't exist");
        }
    }
}


