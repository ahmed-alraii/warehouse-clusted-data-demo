package com.ahmedalraii.warehouseClusteredData.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileServiceInterface {

    boolean hasCsvFormat(MultipartFile file);

    void processAndSaveData(MultipartFile file);
}
