package org.sdgas.VO;

import java.io.File;

/**
 * Created by wilson.he on 2016/7/31.
 */
public class GovDocVO extends BaseVO {

    private String subject;

    private String fileName;
    private File uploadFile;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public File getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(File uploadFile) {
        this.uploadFile = uploadFile;
    }
}
