package com.nikola.filemanager.operations;

import com.nikola.filemanager.exceptions.OperationException;
import java.io.File;
import java.io.IOException;

public class CreateFile extends AbstractOperation {
    @Override
    public void execute(File file) throws OperationException {
        if (file == null) {
            throw new OperationException("file not provided");
        }

        try {
            if(!file.createNewFile()) {
                throw new OperationException("file already exists");
            }
        } catch (IOException e) {
            throw new OperationException(e.getLocalizedMessage(), e);
        }
    }
}
