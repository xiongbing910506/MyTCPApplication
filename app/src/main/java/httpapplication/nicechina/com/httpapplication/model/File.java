package httpapplication.nicechina.com.httpapplication.model;
import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.Date;
public class File extends SugarRecord implements Serializable {

    private Long fileId;

    private Long fileGroupId;

    private Long workNodeDetailId;

    private String name;

    private String filePath;

    private String fileSuffix;

    private Date dateday;

    private Long creatorId;

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getFileGroupId() {
        return fileGroupId;
    }

    public void setFileGroupId(Long fileGroupId) {
        this.fileGroupId = fileGroupId;
    }

    public Long getWorkNodeDetailId() {
        return workNodeDetailId;
    }

    public void setWorkNodeDetailId(Long workNodeDetailId) {
        this.workNodeDetailId = workNodeDetailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix == null ? null : fileSuffix.trim();
    }

    public Date getDateday() {
        return dateday;
    }

    public void setDateday(Date dateday) {
        this.dateday = dateday;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
}