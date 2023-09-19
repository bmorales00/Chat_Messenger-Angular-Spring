package com.brianmorales.chatapplication.clutter;

import com.brianmorales.chatapplication.clutter.FileEntity;
import com.brianmorales.chatapplication.clutter.FileModel;

public class FileMapper {
    public static FileModel to(FileEntity fileEntity) {
        return new FileModel(fileEntity.getUrl(), fileEntity.getType());
    }
    public static FileEntity from(FileModel fileModel){
        return  new FileEntity(fileModel.getUrl(), fileModel.getType());
    }
}
