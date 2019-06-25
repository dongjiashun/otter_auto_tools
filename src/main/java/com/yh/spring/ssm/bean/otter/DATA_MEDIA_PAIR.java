package com.yh.spring.ssm.bean.otter;

public class DATA_MEDIA_PAIR
{
    private boolean blank;

    private String clazzPath;

    private String extensionDataType;

    private boolean notBlank;

    private long timestamp;

    public void setBlank(boolean blank){
        this.blank = blank;
    }
    public boolean getBlank(){
        return this.blank;
    }
    public void setClazzPath(String clazzPath){
        this.clazzPath = clazzPath;
    }
    public String getClazzPath(){
        return this.clazzPath;
    }
    public void setExtensionDataType(String extensionDataType){
        this.extensionDataType = extensionDataType;
    }
    public String getExtensionDataType(){
        return this.extensionDataType;
    }
    public void setNotBlank(boolean notBlank){
        this.notBlank = notBlank;
    }
    public boolean getNotBlank(){
        return this.notBlank;
    }
    public void setTimestamp(long timestamp){
        this.timestamp = timestamp;
    }
    public long getTimestamp(){
        return this.timestamp;
    }
    
}
