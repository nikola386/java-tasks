package com.nikola.filemanager.operations;

import com.nikola.filemanager.exceptions.OperationException;
import java.io.File;

public class CreateDirectory extends AbstractOperation {
    @Override
    public void execute(File file) throws OperationException {
        if (file == null) {
            throw new OperationException("file not provided");
        }

        try {
            if (!file.mkdirs()){
                throw new OperationException("directory already exists");
            }
        } catch (SecurityException se) {
            throw new OperationException(se.getLocalizedMessage(), se);
        }
    }
}
