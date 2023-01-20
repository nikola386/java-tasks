package com.nikola.filemanager;

import com.nikola.filemanager.operations.*;

public class FileManagerFactory {
    public AbstractOperation getOperation(String op) {
        if(op == null){
            throw new IllegalArgumentException("invalid operation");
        }

        switch(op.toLowerCase()) {
            case "addfile" -> {
                return new CreateFile();
            }
            case "adddir" -> {
                return new CreateDirectory();
            }
            case "delete" -> {
                return new DeleteFile();
            }
            default -> throw new IllegalArgumentException("invalid operation");
        }
    }
}
