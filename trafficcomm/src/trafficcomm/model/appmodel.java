package trafficcomm.model;

public class appmodel {
	private String id;
	private String appname;
	private String url;
	private String pic;
	private String dex;
	public appmodel(){
		
	}
	public appmodel(String id, String appname, String url,String pic, String dex){
		this.id=id;
		this.appname=appname;
	    this.url=url;
	    this.dex=dex; 
	}
	
	public void setId(String id){
		this.id=id;
	}
	public String getId(){
		return id;
		
	}
	
    public void setAppname(String appname){
    	this.appname=appname;
    }
    public String getAppname(){
    	return appname;
    }
    public void seturl(String url){
    	this.url=url;
    }
    public String geturl(){
    return url;}
   
    public void setpic(String pic){
    	this.pic=pic;
    }
    public String getpic(){
    	return pic;
    }
    public void setdex(String dex){
    	this.dex=dex;
    }
    public String getdex(){
    	return dex;
    }
    
   
}

