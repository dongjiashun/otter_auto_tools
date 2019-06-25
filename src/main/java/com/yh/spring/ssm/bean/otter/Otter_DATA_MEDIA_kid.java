package com.yh.spring.ssm.bean.otter;

//otter数据库json字段中的json对象
/**
 * 
 * @author TF02221601
 *id是数据源表的id
 *数据源是name
 */
public class Otter_DATA_MEDIA_kid
{
    private String driver;

    private String encode;

    private long gmtCreate;

    private long gmtModified;

    private long id;

    private String name;

    private String password;

    private String type;

    private String url;

    private String username;

    public void setDriver(String driver){
        this.driver = driver;
    }
    public String getDriver(){
        return this.driver;
    }
    public void setEncode(String encode){
        this.encode = encode;
    }
    public String getEncode(){
        return this.encode;
    }
    public void setGmtCreate(long gmtCreate){
        this.gmtCreate = gmtCreate;
    }
    public long getGmtCreate(){
        return this.gmtCreate;
    }
    public void setGmtModified(long gmtModified){
        this.gmtModified = gmtModified;
    }
    public long getGmtModified(){
        return this.gmtModified;
    }
    public void setId(long id){
        this.id = id;
    }
    public long getId(){
        return this.id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }
	@Override
	public String toString() {
		return "Otter_DATA_MEDIA_kid [driver=" + driver + ", encode=" + encode
				+ ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified
				+ ", id=" + id + ", name=" + name + ", password=" + password
				+ ", type=" + type + ", url=" + url + ", username=" + username
				+ "]";
	}
    
}


