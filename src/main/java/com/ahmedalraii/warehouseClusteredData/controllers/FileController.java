package com.ahmedalraii.warehouseClusteredData.controllers;



import com.ahmedalraii.warehouseClusteredData.responses.ResponseMessage;
import com.ahmedalraii.warehouseClusteredData.services.FileServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
//@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileServiceInterface fileService;

    @GetMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file")MultipartFile file) {

        if(fileService.hasCsvFormat(file)){

            fileService.processAndSaveData(file);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("File uploaded successfully : " + file.getOriginalFilename()));

        }

        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseMessage("Please upload csv file"));
    }




}
