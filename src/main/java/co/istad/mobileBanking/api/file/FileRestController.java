package co.istad.mobileBanking.api.file;

import co.istad.mobileBanking.base.BaseRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
public class FileRestController {

    private final FileService fileService;

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
}
