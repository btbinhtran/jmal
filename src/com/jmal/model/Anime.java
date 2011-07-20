package com.jmal.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.text.Html;
import android.text.Spanned;

public class Anime {
	private int aHashCode;
	private int id;
	private String title;
	private String englishTitle;
	private String[] synonyms;
	private String synopsis;
	private int type;
	private int episodes;
	private double score;
	private int status;
	private Date start;
	private Date end;
	private String imageUrl;
	private int userListId;
	private int userEpisodesWatched;
	private Date userStart;
	private Date userFinish;
	private int userScore;
	private int previousUserStatus;
	private int userStatus;
	private Timestamp userLastUpdated;
	private String userTags;
	private String userFansubGroup;
	private String userComments;
	private int userPriority;
	private int userStorageType;
	private double userStorageValue;
	private int userDownloadedEpisodes;
	private int userTimesRewatched;
	private int userRewatchValue;
	private boolean userEnableDiscussion;
	private boolean userEnableRewatching;
	
	// Type constants
	public static final int TYPE_TV = 1;
	public static final int TYPE_OVA = 2;
	public static final int TYPE_MOVIE = 3;
	public static final int TYPE_SPECIAL = 4;
	public static final int TYPE_ONA = 5;
	public static final int TYPE_MUSIC = 6;
	
	// Status constants
	public static final int STATUS_CURRENTLY_AIRING = 1;
	public static final int STATUS_FINISHED_AIRING = 2;
	public static final int STATUS_NOT_YET_AIRED = 3;
	
	// User Status constants
	public static final int USER_STATUS_WATCHING = 1;
	public static final int USER_STATUS_COMPLETED = 2;
	public static final int USER_STATUS_ONHOLD = 3;
	public static final int USER_STATUS_DROPPED = 4;
	public static final int USER_STATUS_PLAN_TO_WATCH = 6;
	
	// User Priority constants
	public static final int USER_PRIORITY_LOW = 0;
	public static final int USER_PRIORITY_MEDIUM = 1;
	public static final int USER_PRIORITY_HIGH = 2;
	
	// User Storage Type constants
	public static final int USER_STORAGE_TYPE_NONE = 3;
	public static final int USER_STORAGE_TYPE_HARD_DRIVE = 1;
	public static final int USER_STORAGE_TYPE_EXTERNAL_HD = 6;
	public static final int USER_STORAGE_TYPE_NAS = 7;
	public static final int USER_STORAGE_TYPE_DVD_CD = 2;
	public static final int USER_STORAGE_TYPE_RETAIL_DVD = 4;
	public static final int USER_STORAGE_TYPE_VHS = 5;
	
	// User Rewatch Value constants
	public static final int USER_REWATCH_VALUE_UNKNOWN = 0;
	public static final int USER_REWATCH_VALUE_VERY_LOW = 1;
	public static final int USER_REWATCH_VALUE_LOW = 2;
	public static final int USER_REWATCH_VALUE_MEDIUM = 3;
	public static final int USER_REWATCH_VALUE_HIGH = 4;
	public static final int USER_REWATCH_VALUE_VERY_HIGH = 5;
	
	public Anime() {
		aHashCode = 0;
		id = 0;
		title = "";
		englishTitle = "";
		synonyms = null;
		synopsis = "";
		type = 0;
		episodes = 0;
		score = 0.0;
		status = 0;
		start = null;
		end = null;
		imageUrl = "";
		userListId = 0;
		userEpisodesWatched = 0;
		userStart = null;
		userFinish = null;
		userScore = 0;
		previousUserStatus = 0;
		userStatus = 0;
		userLastUpdated = null;
		userTags = "";
		userFansubGroup = "";
		userComments = "";
		userPriority = 0;
		userStorageType = 0;
		userStorageValue = 0.0;
		userDownloadedEpisodes = 0;
		userTimesRewatched = 0;
		userRewatchValue = 0;
		userEnableDiscussion = false;
		userEnableRewatching = false;
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
		if(!(o instanceof Anime))
			return false;
		Anime anime = (Anime) o;

		if(this.hashCode() == anime.hashCode())
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
			case TYPE_TV: return "TV";
			case TYPE_OVA: return "OVA";
			case TYPE_MOVIE: return "Movie";
			case TYPE_SPECIAL: return "Special";
			case TYPE_ONA: return "ONA";
			case TYPE_MUSIC: return "Music";
			default: return "TV";
		}
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public void setType(String typeStr) {
		if(typeStr.equals("TV")) {
			this.type = TYPE_TV;
		} else if(typeStr.equals("OVA")) {
			this.type = TYPE_OVA;
		} else if(typeStr.equals("Movie")) {
			this.type = TYPE_MOVIE;
		} else if(typeStr.equals("Special")) {
			this.type = TYPE_SPECIAL;
		} else if(typeStr.equals("ONA")) {
			this.type = TYPE_ONA;
		} else if(typeStr.equals("Music")) {
			this.type = TYPE_MUSIC;
		} else {
			this.type = TYPE_TV;
		}
	}

	public int getEpisodes() {
		return this.episodes;
	}
	
	public String getEpisodesString() {
		return episodes == 0 ? "Unknown" : "" + episodes;
	}
	
	public void setEpisodes(int episodes) {
		this.episodes = episodes;
	}
	
	public boolean isEpisodesUnknown() {
		return (episodes < 1);
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
			case STATUS_CURRENTLY_AIRING: return "Currently Airing";
			case STATUS_FINISHED_AIRING: return "Finished Airing";
			case STATUS_NOT_YET_AIRED: return "Not yet aired";
			default: return "Finished Airing";
		}
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public void setStatus(String statusStr) {
		if(statusStr.equals("Currently Airing")) {
			this.status = STATUS_CURRENTLY_AIRING;
		} else if(statusStr.equals("Finished Airing")) {
			this.status = STATUS_FINISHED_AIRING;
		} else if(statusStr.equals("Not yet aired")) {
			this.status = STATUS_NOT_YET_AIRED;
		} else {
			this.status = STATUS_FINISHED_AIRING;
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

	public void setUserEpisodesWatched(int userEpisodesWatched) {
		this.userEpisodesWatched = userEpisodesWatched;
	}
	
	public void setUserEpisodesWatchedToCompleted() {
		if(!this.isEpisodesUnknown()) {
			this.userEpisodesWatched = this.episodes;
		}
	}
	
	public void incUserEpisodesWatched() {
		++userEpisodesWatched;
	}

	public int getUserEpisodesWatched() {
		return userEpisodesWatched;
	}
	
	public String getUserEpisodesWatchedString() {
		return userEpisodesWatched == 0 ? "-" : "" + userEpisodesWatched;
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

	public int getUserStatus() {
		return userStatus;
	}
	
	public void setPreviousUserStatus(int previousUserStatus) {
		this.previousUserStatus = previousUserStatus;
	}
	
	public int getPreviousUserStatus() {
		return this.previousUserStatus;
	}
	
	public String getUserStatusString() {
		switch(this.userStatus) {
			case USER_STATUS_WATCHING: return "Watching";
			case USER_STATUS_COMPLETED: return (userEnableRewatching ? "Rewatching" : "Completed");
			case USER_STATUS_ONHOLD: return "On-Hold";
			case USER_STATUS_DROPPED: return "Dropped";
			case USER_STATUS_PLAN_TO_WATCH: return "Plan to Watch";
			default: return "Watching";
		}
	}

	public void setUserLastUpdated(Timestamp userLastUpdated) {
		this.userLastUpdated = userLastUpdated;
	}
	
	public void setUserLastUpdated(String secondsStr) {
		this.userLastUpdated = new Timestamp(Long.parseLong(secondsStr) * 1000);
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
	
	public String getUserEpisodesProgress() {
		String progress;
		String watchedEpisodesStr = userEpisodesWatched == 0 ? "-" : "" + userEpisodesWatched;
		String totalEpisodesStr = episodes == 0 ? "-" : "" + episodes;
		
		progress = watchedEpisodesStr + "/" + totalEpisodesStr + " episodes";
		
		return progress;
	}
	
	public void setUserFansubGroup(String userFansubGroup) {
		this.userFansubGroup = userFansubGroup;
	}

	public String getUserFansubGroup() {
		return userFansubGroup;
	}

	public void setUserComments(String userComments) {
		this.userComments = userComments;
	}

	public String getUserComments() {
		return userComments;
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
	
	public void setUserStorageValue(double userStorageValue) {
		this.userStorageValue = userStorageValue;
	}

	public double getUserStorageValue() {
		return userStorageValue;
	}

	public void setUserDownloadedEpisodes(int userDownloadedEpisodes) {
		this.userDownloadedEpisodes = userDownloadedEpisodes;
	}

	public int getUserDownloadedEpisodes() {
		return userDownloadedEpisodes;
	}

	public void setUserTimesRewatched(int userTimesRewatched) {
		this.userTimesRewatched = userTimesRewatched;
	}
	
	public void incUserTimesRewatched() {
		++userTimesRewatched;
	}

	public int getUserTimesRewatched() {
		return userTimesRewatched;
	}

	public void setUserRewatchValue(int userRewatchValue) {
		this.userRewatchValue = userRewatchValue;
	}

	public int getUserRewatchValue() {
		return userRewatchValue;
	}

	public void setUserEnableDiscussion(boolean userEnableDiscussion) {
		this.userEnableDiscussion = userEnableDiscussion;
	}

	public boolean getUserEnableDiscussion() {
		return userEnableDiscussion;
	}

	public void setUserEnableRewatching(boolean userEnableRewatching) {
		this.userEnableRewatching = userEnableRewatching;
	}

	public boolean getUserEnableRewatching() {
		return userEnableRewatching;
	}
	
	public String getInfoUrlString() {
		return "http://myanimelist.net/anime/" + id;
	}
	
	public Anime copy() {
		Anime copy = new Anime();
		
		copy.aHashCode = aHashCode;
		copy.id = id;
		copy.title = title;
		copy.englishTitle = englishTitle;
		copy.synonyms = synonyms;
		copy.synopsis = synopsis;
		copy.type = type;
		copy.episodes = episodes;
		copy.score = score;
		copy.status = status;
		copy.start = start;
		copy.end = end;
		copy.imageUrl = imageUrl;
		copy.userListId = userListId;
		copy.userEpisodesWatched = userEpisodesWatched;
		copy.userStart = userStart;
		copy.userFinish = userFinish;
		copy.userScore = userScore;
		copy.previousUserStatus = previousUserStatus;
		copy.userStatus = userStatus;
		copy.userLastUpdated = userLastUpdated;
		copy.userTags = userTags;
		copy.userFansubGroup = userFansubGroup;
		copy.userComments = userComments;
		copy.userPriority = userPriority;
		copy.userStorageType = userStorageType;
		copy.userStorageValue = userStorageValue;
		copy.userDownloadedEpisodes = userDownloadedEpisodes;
		copy.userTimesRewatched = userTimesRewatched;
		copy.userRewatchValue = userRewatchValue;
		copy.userEnableDiscussion = userEnableDiscussion;
		copy.userEnableRewatching = userEnableRewatching;
		
		return copy;
	}
	
	public String toXML() {
		SimpleDateFormat sdFormatter = new SimpleDateFormat("MMddyyyy");
		String episode = "" + this.userEpisodesWatched;
		String status = "" + this.userStatus;
		String score = "" + this.userScore;
		String downloadedEpisodes = "" + this.userDownloadedEpisodes;
		String storageType = "" + this.userStorageType;
		String storageValue = "" + this.userStorageValue;
		String timesRewatched = "" + this.userTimesRewatched;
		String rewatchValue = "" + this.userRewatchValue;
		String dateStart = this.userStart == null ? "null" : sdFormatter.format(this.userStart);
		String dateFinish = this.userFinish == null ? "null" : sdFormatter.format(this.userFinish);
		String priority = "" + this.userPriority;
		String enableDiscussion = this.userEnableDiscussion ? "1" : "0";
		String enableRewatching = this.userEnableRewatching ? "1" : "0";
		String comments = this.userComments;
		String fansubGroup = this.userFansubGroup;
		String tags = this.userTags;
		
		String xmlStr = "<entry>" +
							"<episode>" + episode + "</episode>" +
							"<status>" + status + "</status>" +
							"<score>" + score + "</score>" +
							"<downloaded_episodes>" + downloadedEpisodes + "</downloaded_episodes>" +
							"<storage_type>" + storageType + "</storage_type>" +
							"<storage_value>" + storageValue + "</storage_value>" +
							"<times_rewatched>" + timesRewatched + "</times_rewatched>" +
							"<rewatch_value>" + rewatchValue + "</rewatch_value>" +
							"<date_start>" + dateStart + "</date_start>" +
							"<date_finish>" + dateFinish + "</date_finish>" +
							"<priority>" + priority + "</priority>" +
							"<enable_discussion>" + enableDiscussion + "</enable_discussion>" +
							"<enable_rewatching>" + enableRewatching + "</enable_rewatching>" +
							"<comments>" + comments + "</comments>" +
							"<fansub_group>" + fansubGroup + "</fansub_group>" +
							"<tags>" + tags + "</tags>" +
						"</entry>";
		
		return xmlStr;
	}
}
