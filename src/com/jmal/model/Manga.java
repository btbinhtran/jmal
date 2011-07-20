package com.jmal.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import android.text.Html;
import android.text.Spanned;

public class Manga {
	private int aHashCode;
	private int id;
	private String title;
	private String englishTitle;
	private String[] synonyms;
	private String synopsis;
	private int type;
	private int chapters;
	private int volumes;
	private double score;
	private int status;
	private Date start;
	private Date end;
	private String imageUrl;
	private int userListId;
	private int userChaptersRead;
	private int userVolumesRead;
	private Date userStart;
	private Date userFinish;
	private int userScore;
	private int previousUserStatus;
	private int userStatus;
	private Timestamp userLastUpdated;
	private String userTags;
	private int userPriority;
	private int userStorageType;
	private int userStorageValue;
	private int userChaptersDownloaded;
	private boolean userEnableRereading;
	private int userTimesReread;
	private int userRereadValue;
	private String userComments;
	private boolean userEnableDiscussion;
	
	// User Status constants
	public static final int USER_STATUS_READING = 1;
	public static final int USER_STATUS_COMPLETED = 2;
	public static final int USER_STATUS_ONHOLD = 3;
	public static final int USER_STATUS_DROPPED = 4;
	public static final int USER_STATUS_PLAN_TO_READ = 6;
	
	// User Priority constants
	public static final int USER_PRIORITY_LOW = 0;
	public static final int USER_PRIORITY_MEDIUM = 1;
	public static final int USER_PRIORITY_HIGH = 2;
	
	// User Storage Type constants
	public static final int USER_STORAGE_TYPE_NONE = 0;
	public static final int USER_STORAGE_TYPE_HARD_DRIVE = 1;
	public static final int USER_STORAGE_TYPE_EXTERNAL_HD = 2;
	public static final int USER_STORAGE_TYPE_NAS = 6;
	public static final int USER_STORAGE_TYPE_DVD_CD = 3;
	public static final int USER_STORAGE_TYPE_RETAIL_MANGA = 4;
	public static final int USER_STORAGE_TYPE_MAGAZINE = 5;
	
	// User Reread Value constants
	public static final int USER_REREAD_VALUE_UNKNOWN = 0;
	public static final int USER_REREAD_VALUE_VERY_LOW = 1;
	public static final int USER_REREAD_VALUE_LOW = 2;
	public static final int USER_REREAD_VALUE_MEDIUM = 3;
	public static final int USER_REREAD_VALUE_HIGH = 4;
	public static final int USER_REREAD_VALUE_VERY_HIGH = 5;
	
	public Manga() {
		 aHashCode = 0;
		 id = 0;
		 title = "";
		 englishTitle = "";
		 synonyms = null;
		 synopsis = "";
		 type = 0;
		 chapters = 0;
		 volumes = 0;
		 score = 0.0;
		 status = 0;
		 start = null;
		 end = null;
		 imageUrl = "";
		 userListId = 0;
		 userChaptersRead = 0;
		 userVolumesRead = 0;
		 userStart = null;
		 userFinish = null;
		 userScore = 0;
		 previousUserStatus = 0;
		 userStatus = 0;
		 userLastUpdated = null;
		 userTags = "";
		 userPriority = 0;
		 userStorageType = 0;
		 userStorageValue = 0;
		 userChaptersDownloaded = 0;
		 userEnableRereading = false;
		 userTimesReread = 0;
		 userRereadValue = 0;
		 userComments = "";
		 userEnableDiscussion = false;
	}
	
	@Override
	public int hashCode() {
		if(aHashCode == 0) {
			aHashCode = 17 * 37 + id;
			aHashCode = aHashCode * 17 + type;
			aHashCode = aHashCode * 17 + status;
			aHashCode = aHashCode * 17 + imageUrl.hashCode();
		}
		return aHashCode;
	}

	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Manga))
			return false;
		Manga manga = (Manga) o;

		if(this.hashCode() == manga.hashCode())
			return true;
		return false;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitleRaw() {
		return this.title;
	}
	
	public Spanned getTitle() {
		return Html.fromHtml(title);
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getEnglishTitle() {
		return englishTitle;
	}
	
	public void setEnglishTitle(String englishTitle) {
		this.englishTitle = englishTitle;
	}
	
	public String[] getSynonyms() {
		return this.synonyms;
	}
	
	public void setSynonyms(String[] synonyms) {
		this.synonyms = synonyms;
	}
	
	public String getSynopsis() {
		return this.synopsis;
	}
	
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
	public int getType() {
		return this.type;
	}
	
	public String getTypeString() {
		switch(this.type) {
			case 1: return "Manga";
			case 2: return "Novel";
			case 3: return "One Shot";
			case 4: return "Doujin";
			case 5: return "Manhwa";
			case 6: return "Manhua";
			case 7: return "OEL";
			default: return "Manga";
		}
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public void setType(String typeStr) {
		if(typeStr.equals("Manga")) {
			this.type = 1;
		} else if(typeStr.equals("Novel")) {
			this.type = 2;
		} else if(typeStr.equals("One Shot")) {
			this.type = 3;
		} else if(typeStr.equals("Doujin")) {
			this.type = 4;
		} else if(typeStr.equals("Manhwa")) {
			this.type = 5;
		} else if(typeStr.equals("Manhua")) {
			this.type = 6;
		} else if(typeStr.equals("OEL")) {
			this.type = 7;
		} else {
			this.type = 1;
		}
	}

	public int getChapters() {
		return this.chapters;
	}
	
	public String getChaptersString() {
		return chapters == 0 ? "Unknown" : "" + chapters;
	}
	
	public void setChapters(int chapters) {
		this.chapters = chapters;
	}
	
	public int getVolumes() {
		return this.volumes;
	}
	
	public String getVolumesString() {
		return volumes == 0 ? "Unknown" : "" + volumes;
	}
	
	public void setVolumes(int volumes) {
		this.volumes = volumes;
	}
	
	public double getScore() {
		return this.score;
	}
	
	public String getScoreString() {
		return score == 0.0 ? "Unknown" : "" + score;
	}
	
	public void setScore(double score) {
		this.score = score;
	}
	
	public int getStatus() {
		return this.status;
	}
	
	public String getStatusString() {
		switch(this.status) {
			case 1: return "Publishing";
			case 2: return "Finished";
			case 3: return "Not yet published";
			default: return "Finished";
		}
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public void setStatus(String statusStr) {
		if(statusStr.equals("Publishing")) {
			this.status = 1;
		} else if(statusStr.equals("Finished")) {
			this.status = 2;
		} else if(statusStr.equals("Not yet published")) {
			this.status = 3;
		} else {
			this.status = 2;
		}
	}
	
	public Date getStart() {
		return start;
	}
	
	public void setStart(Date start) {
		this.start = start;
	}
	
	public Date getEnd() {
		return end;
	}
	
	public void setEnd(Date end) {
		this.end = end;
	}
	
	public String getImageUrl() {
		return this.imageUrl;
	}
	
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setUserListId(int userListId) {
		this.userListId = userListId;
	}

	public int getUserListId() {
		return userListId;
	}

	public void setUserChaptersRead(int userChaptersRead) {
		this.userChaptersRead = userChaptersRead;
	}

	public int getUserChaptersRead() {
		return userChaptersRead;
	}
	
	public String getUserChaptersReadString() {
		return userChaptersRead == 0 ? "-" : "" + userChaptersRead;
	}
	
	public void incUserChaptersRead() {
		++userChaptersRead;
	}
	
	public void setUserVolumesRead(int userVolumesRead) {
		this.userVolumesRead = userVolumesRead;
	}

	public int getUserVolumesRead() {
		return userVolumesRead;
	}
	
	public String getUserVolumesReadString() {
		return userVolumesRead == 0 ? "-" : "" + userVolumesRead;
	}
	
	public void incUserVolumesRead() {
		++userVolumesRead;
	}
	
	public void setUserChaptersAndVolumesReadToCompleted() {
		if(chapters > 0)
			this.userChaptersRead = this.chapters;
		if(volumes > 0)
			this.userVolumesRead = this.volumes;
	}

	public void setUserStart(Date userStart) {
		this.userStart = userStart;
	}

	public Date getUserStart() {
		return userStart;
	}

	public void setUserFinish(Date userFinish) {
		this.userFinish = userFinish;
	}

	public Date getUserFinish() {
		return userFinish;
	}

	public void setUserScore(int userScore) {
		this.userScore = userScore;
	}

	public int getUserScore() {
		return userScore;
	}
	
	public String getUserScoreString() {
		return userScore == 0.0 ? "Unknown" : "" + userScore;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}
	
	public void setPreviousUserStatus(int previousUserStatus) {
		this.previousUserStatus = previousUserStatus;
	}

	public int getPreviousUserStatus() {
		return previousUserStatus;
	}

	public int getUserStatus() {
		return userStatus;
	}
	
	public String getUserStatusString() {
		switch(this.userStatus) {
			case Manga.USER_STATUS_READING: return "Reading";
			case Manga.USER_STATUS_COMPLETED: return (userEnableRereading ? "Rereading" : "Completed");
			case Manga.USER_STATUS_ONHOLD: return "On-Hold";
			case Manga.USER_STATUS_DROPPED: return "Dropped";
			case Manga.USER_STATUS_PLAN_TO_READ: return "Plan to Read";
			default: return "Reading";
		}
	}

	public void setUserLastUpdated(Timestamp userLastUpdated) {
		this.userLastUpdated = userLastUpdated;
	}
	
	public void setUserLastUpdated(String secondsStr) {
		this.userLastUpdated = new Timestamp(TimeUnit.SECONDS.toMillis(Long.parseLong(secondsStr)));
	}

	public Timestamp getUserLastUpdated() {
		return userLastUpdated;
	}

	public void setUserTags(String userTags) {
		this.userTags = userTags;
	}

	public String getUserTags() {
		return userTags;
	}
	
	public String getUserChaptersProgress() {
		String progress;
		String chaptersReadStr = userChaptersRead == 0 ? "-" : "" + userChaptersRead;
		String totalChaptersStr = chapters == 0 ? "-" : "" + chapters;
		
		progress = chaptersReadStr + "/" + totalChaptersStr + " chapters";
		
		return progress;
	}
	
	public String getUserVolumesProgress() {
		String progress;
		String volumesReadStr = userVolumesRead == 0 ? "-" : "" + userVolumesRead;
		String totalVolumesStr = volumes == 0 ? "-" : "" + volumes;
		
		progress = volumesReadStr + "/" + totalVolumesStr + " volumes";
		
		return progress;
	}
	
	public void setUserPriority(int userPriority) {
		this.userPriority = userPriority;
	}

	public int getUserPriority() {
		return userPriority;
	}

	public void setUserStorageType(int userStorageType) {
		this.userStorageType = userStorageType;
	}

	public int getUserStorageType() {
		return userStorageType;
	}

	public void setUserStorageValue(int userStorageValue) {
		this.userStorageValue = userStorageValue;
	}

	public int getUserStorageValue() {
		return userStorageValue;
	}

	public void setUserChaptersDownloaded(int userChaptersDownloaded) {
		this.userChaptersDownloaded = userChaptersDownloaded;
	}

	public int getUserChaptersDownloaded() {
		return userChaptersDownloaded;
	}

	public void setUserEnableRereading(boolean userEnableRereading) {
		this.userEnableRereading = userEnableRereading;
	}

	public boolean getUserEnableRereading() {
		return userEnableRereading;
	}

	public void setUserTimesReread(int userTimesReread) {
		this.userTimesReread = userTimesReread;
	}
	
	public void incUserTimesReread() {
		++userTimesReread;
	}

	public int getUserTimesReread() {
		return userTimesReread;
	}

	public void setUserRereadValue(int userRereadValue) {
		this.userRereadValue = userRereadValue;
	}

	public int getUserRereadValue() {
		return userRereadValue;
	}

	public void setUserComments(String userComments) {
		this.userComments = userComments;
	}

	public String getUserComments() {
		return userComments;
	}

	public void setUserEnableDiscussion(boolean userEnableDiscussion) {
		this.userEnableDiscussion = userEnableDiscussion;
	}

	public boolean getUserEnableDiscussion() {
		return userEnableDiscussion;
	}
	
	public boolean isChaptersUnknown() {
		return chapters <= 0;
	}
	
	public boolean isChaptersCompleted() {
		boolean completed = false;
		
		if(chapters > 0) {
			completed = userChaptersRead >= chapters;
		}
		
		return completed;
	}
	
	public boolean isVolumesUnknown() {
		return volumes <= 0;
	}
	
	public boolean isVolumesCompleted() {
		boolean completed = false;
		
		if(volumes > 0) {
			completed = userVolumesRead >= volumes;
		}
		
		return completed;
	}
	
	public boolean isCompleted() {
		boolean completed = false;
		
		if(chapters > 0 && volumes > 0) {
			completed = (userChaptersRead >= chapters && userVolumesRead >= volumes);
		}
		
		return completed;
	}
	
	public String getInfoUrlString() {
		return "http://myanimelist.net/manga/" + id;
	}

	public Manga copy() {
		Manga copy = new Manga();
		
		copy.aHashCode = aHashCode;
		copy.id = id;
		copy.title = title;
		copy.englishTitle = englishTitle;
		copy.synonyms = synonyms;
		copy.synopsis = synopsis;
		copy.type = type;
		copy.chapters = chapters;
		copy.volumes = volumes;
		copy.score = score;
		copy.status = status;
		copy.start = start;
		copy.end = end;
		copy.imageUrl = imageUrl;
		copy.userListId = userListId;
		copy.userChaptersRead = userChaptersRead;
		copy.userVolumesRead = userVolumesRead;
		copy.userStart = userStart;
		copy.userFinish = userFinish;
		copy.userScore = userScore;
		copy.previousUserStatus = previousUserStatus;
		copy.userStatus = userStatus;
		copy.userLastUpdated = userLastUpdated;
		copy.userTags = userTags;
		copy.userPriority = userPriority;
		copy.userStorageType = userStorageType;
		copy.userStorageValue = userStorageValue;
		copy.userChaptersDownloaded = userChaptersDownloaded;
		copy.userEnableRereading = userEnableRereading;
		copy.userTimesReread = userTimesReread;
		copy.userRereadValue = userRereadValue;
		copy.userComments = userComments;
		copy.userEnableDiscussion = userEnableDiscussion;
		
		return copy;
	}
	
	public String toXML() {
		SimpleDateFormat sdFormatter = new SimpleDateFormat("MMddyyyy");
		String chapter = "" + this.userChaptersRead;
		String volume = "" + this.userVolumesRead;
		String status = "" + this.userStatus;
		String score = "" + this.userScore;
		String downloadedChapters = "" + this.userChaptersDownloaded;
		String timesReread = "" + this.userTimesReread;
		String rereadValue = "" + this.userRereadValue;
		String dateStart = this.userStart == null ? "null" : sdFormatter.format(this.userStart);
		String dateFinish = this.userFinish == null ? "null" : sdFormatter.format(this.userFinish);
		String priority = "" + this.userPriority;
		String enableDiscussion = this.userEnableDiscussion ? "1" : "0";
		String enableRereading = this.userEnableRereading ? "1" : "0";
		String comments = this.userComments;
		String scanGroup = "";
		String tags = this.userTags;
		String retailVolumes = "" + this.userStorageValue;
		
		String xmlStr = "<entry>" +
							"<chapter>" + chapter + "</chapter>" +
							"<volume>" + volume + "</volume>" +
							"<status>" + status + "</status>" +
							"<score>" + score + "</score>" +
							"<downloaded_chapters>" + downloadedChapters + "</downloaded_chapters>" +
							"<times_reread>" + timesReread + "</times_reread>" +
							"<reread_value>" + rereadValue + "</reread_value>" +
							"<date_start>" + dateStart + "</date_start>" +
							"<date_finish>" + dateFinish + "</date_finish>" +
							"<priority>" + priority + "</priority>" +
							"<enable_discussion>" + enableDiscussion + "</enable_discussion>" +
							"<enable_rereading>" + enableRereading + "</enable_rereading>" +
							"<comments>" + comments + "</comments>" +
							"<scan_group>" + scanGroup + "</scan_group>" +
							"<tags>" + tags + "</tags>" +
							"<retail_volumes>" + retailVolumes + "</retail_volumes>" +
						"</entry>";
		
		return xmlStr;
	}
}
