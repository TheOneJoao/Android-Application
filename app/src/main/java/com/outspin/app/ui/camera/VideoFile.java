package com.outspin.app.ui.camera;

import java.io.File;
import java.io.Serializable;

public class VideoFile implements Serializable {
    private File videoFolder;
    private String videoFileName;

    public VideoFile(File videoFolder, String videoFileName) {
        this.videoFolder = videoFolder;
        this.videoFileName = videoFileName;
    }


    public File getVideoFolder() {
        return videoFolder;
    }

    public void setVideoFolder(File videoFolder) {
        this.videoFolder = videoFolder;
    }

    public String getVideoFileName() {
        return videoFileName;
    }

    public void setVideoFileName(String videoFileName) {
        this.videoFileName = videoFileName;
    }
}
