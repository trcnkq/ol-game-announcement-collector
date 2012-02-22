package bean;

import java.util.Date;

public class Announcement {
	
	private String gameName;	//游戏名称
	private String title;		//公告标题
	private String href;		//公告链接
	private Date date;			//公告发布时间

	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
