package co.istad.mobileBanking.api.file;

import co.istad.mobileBanking.base.BaseRest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
public class FileRestController {

    private final FileService fileService;

    // post or upload
    @PostMapping
    public BaseRest<?> uploadSingleFile(@RequestPart MultipartFile file){
        FileDto fileDto = fileService.uploadSingle(file);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .massage("User have been upload successfully")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();
    }

    @PostMapping("/multiple")
    public BaseRest<?> uploadMultipleFile(@RequestPart List<MultipartFile> files){
        List<FileDto> fileDto = fileService.uploadMultiple(files);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .massage("User have been upload successfully")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();
    }

    // Get or find something
    @GetMapping("/list")
    public BaseRest<?> findAllFile(){
        List<FileDto> fileDto = null;
        try {
            fileDto = fileService.findAllFile();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Fail to find");
        }
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .massage("User have been Find All Url")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();
    }

    @GetMapping("/{fileName}")
    public BaseRest<?> findFileByName(@PathVariable String fileName){
        List<FileDto> fileDto = null;
        try {
            fileDto = fileService.findByName(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Fail to find");
        }
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .massage("User have been Find Url")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();
    }

    //download file
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Object> download(@PathVariable String fileName) throws IOException {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "force-download"));
        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + ".jpg");

        List<FileDto> fileDtoList = fileService.findByName(fileName);
        if (!fileDtoList.isEmpty()){
            File file = new File(fileDtoList.get(0).url());
            Path path = Paths.get(file.getAbsolutePath());
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
            return ResponseEntity.ok().headers(header).contentLength(file.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // delete something
    @DeleteMapping("/{fileName}")
    public BaseRest<?> deleteFileByUrl(@PathVariable String fileName){
        try {
            fileService.deleteByFileName(fileName);
        } catch (IOException e) {
            return BaseRest.builder()
                    .status(false)
                    .code(HttpStatus.BAD_REQUEST.value())
                    .massage("Fail to delete")
                    .timestamp(LocalDateTime.now())
                    .data("Fail to delete")
                    .build();
        }
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .massage("User have been Delete")
                .timestamp(LocalDateTime.now())
                .data("file have deleted")
                .build();
    }

    @DeleteMapping
    public BaseRest<?> deleteAllFile(){
        try {
            fileService.deleteAllFile();
        } catch (IOException e) {
            return BaseRest.builder()
                    .status(false)
                    .code(HttpStatus.BAD_REQUEST.value())
                    .massage("Fail to delete")
                    .timestamp(LocalDateTime.now())
                    .data("Fail to delete")
                    .build();
        }
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .massage("User have been Delete")
                .timestamp(LocalDateTime.now())
                .data("file have deleted")
                .build();
    }
}
