package com.example.firebase3;
import com.google.firebase.database.Exclude;

public class Upload {
    private String mName;
    private String mPdfUrl;
    private String mKey;

    public Upload() {
        // Empty constructor needed
    }

    public Upload(String name, String pdfUrl) {
        if (name.trim().equals("")) {
            name = "No Name";
        }

        mName = name;
        mPdfUrl = pdfUrl == null ? "" : pdfUrl;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPdfUrl() {
        return mPdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        mPdfUrl = pdfUrl;
    }

    @Exclude
    public String getKey() {
        return mKey;
    }

    @Exclude
    public void setKey(String key) {
        mKey = key;
    }
}
