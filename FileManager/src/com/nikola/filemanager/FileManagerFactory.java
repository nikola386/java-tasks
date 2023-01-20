package com.nikola.filemanager;

import com.nikola.filemanager.exceptions.OperationException;
import com.nikola.filemanager.operations.*;

public class FileManagerFactory {
    public enum Operation {
        ADD_FILE,
        ADD_DIRECTORY,
        DELETE
    }

    public AbstractOperation getOperation(Operation op) throws OperationException {
        if(op == null){
            throw new OperationException("invalid operation");
        }

        switch(op) {
            case ADD_FILE -> {
                return new CreateFile();
            }
            case ADD_DIRECTORY -> {
                return new CreateDirectory();
            }
            case DELETE -> {
                return new DeleteFile();
            }
            default -> throw new OperationException("invalid operation");
        }
    }
}
