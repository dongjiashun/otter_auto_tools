package com.yh.spring.ssm.bean.otter;
public class Otter_DATA_MEDIA
{
    private String mode;

    private String name;

    private String namespace;

    private Otter_DATA_MEDIA_kid source;

    public void setMode(String mode){
        this.mode = mode;
    }
    public String getMode(){
        return this.mode;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setNamespace(String namespace){
        this.namespace = namespace;
    }
    public String getNamespace(){
        return this.namespace;
    }
    public void setSource(Otter_DATA_MEDIA_kid source){
        this.source = source;
    }
    public Otter_DATA_MEDIA_kid getSource(){
        return this.source;
    }
	@Override
	public String toString() {
		return "Otter_DATA_MEDIA [mode=" + mode + ", name=" + name
				+ ", namespace=" + namespace + ", source=" + source + "]";
	}
    
}
