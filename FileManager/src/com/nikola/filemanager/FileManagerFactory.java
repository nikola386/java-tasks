package com.nikola.filemanager;

import com.nikola.filemanager.exceptions.OperationException;
import com.nikola.filemanager.operations.*;

public class FileManagerFactory {
    public AbstractOperation getOperation(String op) throws OperationException {
        if(op == null){
            throw new OperationException("invalid operation");
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
            default -> throw new OperationException("invalid operation");
        }
    }
}
