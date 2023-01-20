package com.nikola.filemanager.operations;
import com.nikola.filemanager.exceptions.OperationException;

import java.io.File;
abstract public class AbstractOperation {
    abstract public void execute(File file) throws OperationException;
}