package com.outspin.app.models;

public class Moment {
    public static long MOMENT_ID = 0;
    private String momentTitle, momentDisco, videoURI;
    private String thumbnailURI;

    private final long id;

    public Moment(String mMomentTitle, String momentDisco, String videoURI) {
        this.momentTitle = mMomentTitle;
        this.momentDisco = momentDisco;
        this.videoURI = videoURI;
        this.thumbnailURI = "https://turingnotes.com/wp-content/uploads/2022/02/photo12.jpeg";
        this.id = MOMENT_ID++;
    }

    /*  getters & setters   */
    public String getMomentTitle() {
        return momentTitle;
    }
    public void setMomentTitle(String mMomentTitle) {
        this.momentTitle = mMomentTitle;
    }

    public String getMomentDisco() {
        return momentDisco + " id: " + this.id;
    }
    public void setMomentDisco(String mMomentDescription) {
        this.momentDisco = mMomentDescription;
    }

    public String getUri() {
        return videoURI;
    }
    public void setUri(String mUri) {
        this.videoURI = mUri;
    }

    public String getThumbnailURI() { return thumbnailURI; }
    public void setThumbnailURI(String thumbnailURI) { this.thumbnailURI = thumbnailURI; }
}
