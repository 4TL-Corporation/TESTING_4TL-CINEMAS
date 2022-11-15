package domain;

import java.util.Date;

public class FavoriteReport {
	private String videoTitle;
	private Long count;
	private Date newsDate;
	private Date oldDate;
	
	
	public FavoriteReport() {
		
	}
	public FavoriteReport(String videoTitle, Long count, Date newsDate, Date oldDate) {
		
		this.videoTitle = videoTitle;
		this.count = count;
		this.newsDate = newsDate;
		this.oldDate = oldDate;
	}
	public String getVideoTitle() {
		return videoTitle;
	}
	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Date getNewsDate() {
		return newsDate;
	}
	public void setNewsDate(Date newsDate) {
		this.newsDate = newsDate;
	}
	public Date getOldDate() {
		return oldDate;
	}
	public void setOldDate(Date oldDate) {
		this.oldDate = oldDate;
	}
	
	
}
