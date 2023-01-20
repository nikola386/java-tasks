package com.nikola.filemanager.operations;

import com.nikola.filemanager.exceptions.OperationException;
import java.io.File;
public class DeleteFile extends AbstractOperation {
    @Override
    public void execute(File file) throws OperationException {
        if (file == null) {
            throw new OperationException("file not provided");
        }

        try {
            if (!file.delete()){
                throw new OperationException("file cannot be deleted");
            }
        } catch (SecurityException se) {
            throw new OperationException(se.getLocalizedMessage(), se);
        }
    }
}
