package com.outspin.app.ui.camera;


import java.io.File;
import java.io.Serializable;

public class ImageFile implements Serializable {
    private File imageFolder;
    private String imageFileName;

    public ImageFile(File imageFolder, String imageFileName){
        this.imageFileName = imageFileName;
        this.imageFolder = imageFolder;
    }

    public File getImageFolder() {
        return imageFolder;
    }

    public void setImageFolder(File imageFolder) {
        this.imageFolder = imageFolder;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }
}