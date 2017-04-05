package component.file;


public class FileModel {

    /**
     * 上传文件
     */
    private java.io.File upFile;
    /**
     * 文件ID
     */
    private String fileId;
    /**
     * 文件名称
     */
    private String trueName;
    /**
     * 模块
     */
    private String module;
    /**
     * 路径
     */
    private String path;

    public FileModel(java.io.File upFile, String trueName, String module) {
        super();
        this.upFile = upFile;
        this.trueName = trueName;
        this.module = module;
    }

    public FileModel() {
        super();
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public java.io.File getUpFile() {
        return upFile;
    }

    public void setUpFile(java.io.File upFile) {
        this.upFile = upFile;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
