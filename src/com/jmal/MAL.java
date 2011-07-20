package com.jmal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import com.jmal.util.Base64;
import com.jmal.util.Base64DecoderException;
import com.jmal.util.IOUtils;
import com.jmal.util.StringUtils;
import com.jmal.model.Anime;
import com.jmal.model.Manga;
import com.jmal.model.User;

import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.util.Xml;

public class MAL {
	// Timeouts
	private static final int SOCKET_TIMEOUT = 5000;
	private static final int CONNECT_TIMEOUT = 3000;
	
	// Root tag
	private static final String MYANIMELIST = "myanimelist";

	// User info tags
	private static final String MYINFO = "myinfo";
	private static final String USER_ID = "user_id";
	private static final String USER_NAME = "user_name";
	private static final String USER_WATCHING = "user_watching";
	private static final String USER_COMPLETED = "user_completed";
	private static final String USER_ONHOLD = "user_onhold";
	private static final String USER_DROPPED = "user_dropped";
	private static final String USER_PLANTOWATCH = "user_plantowatch";
	private static final String USER_DAYS_SPENT_WATCHING = "user_days_spent_watching";
	
	// Anime and Manga info tags
	private static final String ANIME = "anime";
	private static final String SERIES_ANIMEDB_ID = "series_animedb_id";
	private static final String SERIES_TITLE = "series_title";
	private static final String SERIES_SYNONYMS = "series_synonyms";
	private static final String SERIES_TYPE = "series_type";
	private static final String SERIES_EPISODES = "series_episodes";
	private static final String SERIES_STATUS = "series_status";
	private static final String SERIES_START = "series_start";
	private static final String SERIES_END = "series_end";
	private static final String SERIES_IMAGE = "series_image";
	
	// Your Anime and Manga info like how much you have watched, the score you gave, etc...
	private static final String MY_ID = "my_id";
	private static final String MY_WATCHED_EPISODES = "my_watched_episodes";
	private static final String MY_START_DATE = "my_start_date";
	private static final String MY_FINISH_DATE = "my_finish_date";
	private static final String MY_SCORE = "my_score";
	private static final String MY_STATUS = "my_status";
	private static final String MY_REWATCHING = "my_rewatching";
	private static final String MY_REWATCHING_EP = "my_rewatching_ep";
	private static final String MY_LAST_UPDATED = "my_last_updated";
	private static final String MY_TAGS = "my_tags";
	
	// More Manga User info tags
	private static final String USER_READING = "user_reading";
	private static final String USER_PLANTOREAD = "user_plantoread";
	private static final String USER_DAYS_SPENT_READING = "user_days_spent_watching";
	
	// More Manga info tags
	private static final String MANGA = "manga";
	private static final String SERIES_MANGADB_ID = "series_mangadb_id";
	private static final String SERIES_CHAPTERS = "series_chapters";
	private static final String SERIES_VOLUMES = "series_volumes";
	
	// Your Manga info like how much you have read, the score you gave, etc...
	private static final String MY_READ_CHAPTERS = "my_read_chapters";
	private static final String MY_READ_VOLUMES = "my_read_volumes";
	private static final String MY_REREADING = "my_rereadingg";
	private static final String MY_REREADING_CHAP = "my_rereading_chap";
	
	// Anime edit fields REGEX patterns
	private static final Pattern ANIME_FANSUB_GROUP_PATTERN = Pattern.compile("<input.*name=\"fansub_group\".*value=\"([^\"]*)\"[^>]*>");
	private static final Pattern ANIME_PRIORITY_PATTERN = Pattern.compile("<select.*?name=\"priority\"[^>]*>[\\s\\w\\W]*?<option\\svalue=\"(\\d+)\"\\sselected[^>]*>[\\s\\w\\W]*?</select>");
	private static final Pattern ANIME_STORAGE_TYPE_SELECT_PATTERN = Pattern.compile("<select.*?name=\"storage\"[^>]*>([\\s\\w\\W]*?)</select>");
	private static final Pattern ANIME_STORAGE_TYPE_PATTERN = Pattern.compile("<option\\svalue=\"(\\d+)\"\\sselected[^>]*>");
	private static final Pattern ANIME_STORAGE_VALUE_PATTERN = Pattern.compile("<input.*name=\"storageVal\".*value=\"([^\"]*)\"[^>]*>");
	private static final Pattern ANIME_DOWNLOADED_EPISODES_PATTERN = Pattern.compile("<input.*name=\"list_downloaded_eps\".*value=\"(\\d+)\"[^>]*>");
	private static final Pattern ANIME_COMMENTS_PATTERN = Pattern.compile("<textarea.*name=\"list_comments\"[^>]*>(.*)</textarea>");
	private static final Pattern ANIME_TIMES_REWATCHED_PATTERN = Pattern.compile("<input.*name=\"list_times_watched\".*value=\"(\\d+)\"[^>]*>");
	private static final Pattern ANIME_REWATCH_VALUE_SELECT_PATTERN = Pattern.compile("<select.*?name=\"list_rewatch_value\"[^>]*>([\\s\\w\\W]*?)</select>");
	private static final Pattern ANIME_REWATCH_VALUE_PATTERN = Pattern.compile("<option\\sselected\\svalue=\"(\\d+)\"[^>]*>");
	private static final Pattern ANIME_ENABLE_DISCUSSION_PATTERN = Pattern.compile("<select.*?name=\"discuss\"[^>]*>[\\s\\w\\W]*?<option\\svalue=\"(\\d+)\"\\sselected[^>]*>[\\s\\w\\W]*?</select>");
	
	// Manga edit fields REGEX patterns
	private static final Pattern MANGA_PRIORITY_PATTERN = Pattern.compile("<select.*?name=\"priority\".*?>[\\s\\w\\W]*?<option\\svalue=\"(\\d+)\"\\sselected.*?>[\\s\\w\\W]*?</select>");
	private static final Pattern MANGA_STORAGE_TYPE_SELECT_PATTERN = Pattern.compile("<select.*?name='storage_num'.*?>([\\s\\w\\W]*?)</select>");
	private static final Pattern MANGA_STORAGE_TYPE_PATTERN = Pattern.compile("<option\\svalue='(\\d+)'\\sselected.*?>");
	private static final Pattern MANGA_STORAGE_VALUE_PATTERN = Pattern.compile("<input.*?name='retail_volumes'.*?value='(\\d+)'.*?>");
	private static final Pattern MANGA_CHAPTERS_DOWNLOADED_PATTERN = Pattern.compile("<input.*?name=\"downloaded_chapters\".*value=\"(\\d+)\".*?>");
	private static final Pattern MANGA_TIMES_REREAD_PATTERN = Pattern.compile("<input.*?name=\"times_read\".*value=\"(\\d+)\".*?>");
	private static final Pattern MANGA_REREAD_VALUE_SELECT_PATTERN = Pattern.compile("<select.*?name=\"reread_value\".*?>([\\s\\w\\W]*?)</select>");
	private static final Pattern MANGA_REREAD_VALUE_PATTERN = Pattern.compile("<option\\sselected\\svalue=\"(\\d+)\".*?>");
	private static final Pattern MANGA_COMMENTS_PATTERN = Pattern.compile("<textarea.*?name=\"comments\".*?>(.*)</textarea>");
	private static final Pattern MANGA_ENABLE_DISCUSSION_PATTERN = Pattern.compile("<input.*?name=\"discuss\".*?checked.*?>");
	
	private final String name;
	private final String pass;
	private final String encodedAuthorization;
	private DefaultHttpClient httpClient;
	private BasicHttpContext localContext;
	
	public MAL(String username, String password) {
		this.name = username;
		this.pass = password;
		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, CONNECT_TIMEOUT);
		HttpConnectionParams.setSoTimeout(httpParams, SOCKET_TIMEOUT);
		this.httpClient = new DefaultHttpClient(httpParams);
		
		String userpassword = this.name + ":" + this.pass;
		encodedAuthorization = Base64.encode(userpassword.getBytes());
		
		// Setup localContext as BasicAuth
		this.localContext = new BasicHttpContext();		
	}
	
	public static MAL initializeFromEncodedAuth(String encodedAuth) throws MALException {
		MAL mal = null;
		try {
			String raw = new String(Base64.decode(encodedAuth));
			String[] userpass = raw.split(":");
			mal = new MAL(userpass[0], userpass[1]);
			if(!mal.authenticate()) {
				throw new MALException("Unable to authenticate MAL user");
			}
		} catch (Base64DecoderException e) {
			throw new MALException(e);
		}
		return mal;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String encodedAuth() {
		return encodedAuthorization;
	}
	
	public boolean authenticate() throws MALException {
		HttpPost httpPost = new HttpPost("http://myanimelist.net/login.php");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		boolean valid = false;
		
		try {
			params.add(new BasicNameValuePair("username", this.name));
			params.add(new BasicNameValuePair("password", this.pass));
			params.add(new BasicNameValuePair("cookies", "1"));
			UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
			
			httpPost.setEntity(ent);
			
			HttpResponse response = this.httpClient.execute(httpPost, this.localContext);
			HttpUriRequest currentReq = (HttpUriRequest) localContext.getAttribute(ExecutionContext.HTTP_REQUEST);
			HttpHost currentHost = (HttpHost)  localContext.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
			String currentUrl = currentHost.toURI() + currentReq.getURI();			
			HttpEntity entity = response.getEntity();
			
			if(entity != null) {
				entity.consumeContent();
			}
			
			valid = currentUrl.equals("http://myanimelist.net/panel.php");
		} catch (Exception e) {
			throw new MALException(e);
		}
		return valid;
	}
	
	public boolean getAnimeFormInfo(Anime anime) throws MALException {
		HttpGet httpGet = new HttpGet("http://myanimelist.net/editlist.php?type=anime&hideLayout=true&id=" + anime.getUserListId());
		boolean valid = false;
		
		try {
			HttpResponse response = this.httpClient.execute(httpGet, this.localContext);
			HttpEntity entity = response.getEntity();
			final int statusCode = response.getStatusLine().getStatusCode();
			
			if(entity != null) {
				String content = IOUtils.inputStreamToString(entity.getContent());
				
				Matcher fansubGroupMatcher = ANIME_FANSUB_GROUP_PATTERN.matcher(content);
				Matcher priorityMatcher = ANIME_PRIORITY_PATTERN.matcher(content);
				Matcher downloadedEpisodesMatcher = ANIME_DOWNLOADED_EPISODES_PATTERN.matcher(content);
				Matcher storageTypeSelectMatcher = ANIME_STORAGE_TYPE_SELECT_PATTERN.matcher(content);
				Matcher commentsMatcher = ANIME_COMMENTS_PATTERN.matcher(content);
				Matcher enableDiscussionMatcher = ANIME_ENABLE_DISCUSSION_PATTERN.matcher(content);
				String userFansubGroup = "";
				int userPriority = 0;
				int userStorageType = 0;
				double userStorageValue = 0.0;
				int userDownloadedEpisodes = anime.getUserDownloadedEpisodes();
				int userTimesRewatched = anime.getUserTimesRewatched();
				int userRewatchValue = 0;
				String userComments = "";
				boolean userEnableDiscussion = false;
				
				if(fansubGroupMatcher.find()) {
					userFansubGroup = fansubGroupMatcher.group(1);
				}
				if(priorityMatcher.find()) {
					userPriority = Integer.parseInt(priorityMatcher.group(1));
				}
				if(downloadedEpisodesMatcher.find()) {
					userDownloadedEpisodes = Integer.parseInt(downloadedEpisodesMatcher.group(1));
				}
				if(commentsMatcher.find()) {
					userComments = commentsMatcher.group(1);
				}
				if(storageTypeSelectMatcher.find()) {
					Matcher storageTypeMatcher = ANIME_STORAGE_TYPE_PATTERN.matcher(storageTypeSelectMatcher.group(1));
					if(storageTypeMatcher.find()) {
						userStorageType = Integer.parseInt(storageTypeMatcher.group(1));
					}
				}
				if(enableDiscussionMatcher.find()) {
					userEnableDiscussion = enableDiscussionMatcher.group(1).equals("1");
				}
				
				// userStatus is equal to completed
				if(anime.getUserStatus() == Anime.USER_STATUS_COMPLETED) {
					Matcher timesRewatchedMatcher = ANIME_TIMES_REWATCHED_PATTERN.matcher(content);
					Matcher rewatchValueSelectMatcher = ANIME_REWATCH_VALUE_SELECT_PATTERN.matcher(content);
					
					if(userTimesRewatched <= 0 && timesRewatchedMatcher.find()) {
						userTimesRewatched = Integer.parseInt(timesRewatchedMatcher.group(1));
					}
					if(rewatchValueSelectMatcher.find()) {
						Matcher rewatchValueMatcher = ANIME_REWATCH_VALUE_PATTERN.matcher(rewatchValueSelectMatcher.group(1));
						if(rewatchValueMatcher.find()) {
							userRewatchValue = Integer.parseInt(rewatchValueMatcher.group(1));
						}
					}
				}
				
				if(userStorageType == 0) {
					userStorageType = Anime.USER_STORAGE_TYPE_NONE;
				} else {
					Matcher storageValueMatcher = ANIME_STORAGE_VALUE_PATTERN.matcher(content);
					if(storageValueMatcher.find()) {
						userStorageValue = Double.parseDouble(storageValueMatcher.group(1));
					}
				}
				anime.setUserFansubGroup(userFansubGroup);
				anime.setUserPriority(userPriority);
				anime.setUserStorageType(userStorageType);
				anime.setUserStorageValue(userStorageValue);
				anime.setUserDownloadedEpisodes(userDownloadedEpisodes);
				anime.setUserTimesRewatched(userTimesRewatched);
				anime.setUserRewatchValue(userRewatchValue);
				anime.setUserComments(userComments);
				anime.setUserEnableDiscussion(userEnableDiscussion);
				entity.consumeContent();
			}
			
			valid = statusCode == HttpStatus.SC_OK;
		} catch (Exception e) {
			throw new MALException(e);
		}
		return valid;
	}
	
	public boolean getMangaFormInfo(Manga manga) throws MALException {
		HttpGet httpGet = new HttpGet("http://myanimelist.net/panel.php?go=editmanga&hidenav=true&id=" + manga.getUserListId());
		boolean valid = false;
		
		try {
			HttpResponse response = this.httpClient.execute(httpGet, this.localContext);
			HttpEntity entity = response.getEntity();
			final int statusCode = response.getStatusLine().getStatusCode();
			
			if(entity != null) {
				String content = IOUtils.inputStreamToString(entity.getContent());
				
				Matcher priorityMatcher = MANGA_PRIORITY_PATTERN.matcher(content);
				Matcher storageTypeSelectMatcher = MANGA_STORAGE_TYPE_SELECT_PATTERN.matcher(content);
				Matcher storageValueMatcher = MANGA_STORAGE_VALUE_PATTERN.matcher(content);
				Matcher chaptersDownloadedMatcher = MANGA_CHAPTERS_DOWNLOADED_PATTERN.matcher(content);
				Matcher commentsMatcher = MANGA_COMMENTS_PATTERN.matcher(content);
				Matcher enableDiscussionMatcher = MANGA_ENABLE_DISCUSSION_PATTERN.matcher(content);
				
				int userPriority = Manga.USER_PRIORITY_LOW;
				int userStorageType = Manga.USER_STORAGE_TYPE_NONE;
				int userStorageValue = 0;
				int userChaptersDownloaded = manga.getUserChaptersDownloaded();
				int userTimesReread = manga.getUserTimesReread();
				int userRereadValue = 0;
				String userComments = "";
				boolean userEnableDiscussion = false;
				
				if(priorityMatcher.find()) {
					userPriority = Integer.parseInt(priorityMatcher.group(1));
				}
				if(storageTypeSelectMatcher.find()) {
					Matcher storageTypeMatcher = MANGA_STORAGE_TYPE_PATTERN.matcher(storageTypeSelectMatcher.group(1));
					if(storageTypeMatcher.find()) {
						userStorageType = Integer.parseInt(storageTypeMatcher.group(1));
					}
				}
				if(storageValueMatcher.find()) {
					userStorageValue = Integer.parseInt((storageValueMatcher.group(1)));
				}
				if(userChaptersDownloaded <= 0 && chaptersDownloadedMatcher.find()) {
					userChaptersDownloaded = Integer.parseInt(chaptersDownloadedMatcher.group(1));
				}
				// userStatus is equal to completed
				if(manga.getUserStatus() == Manga.USER_STATUS_COMPLETED) {
					Matcher timesRereadMatcher = MANGA_TIMES_REREAD_PATTERN.matcher(content);
					Matcher rereadValueSelectMatcher = MANGA_REREAD_VALUE_SELECT_PATTERN.matcher(content);
					
					if(userTimesReread <= 0 && timesRereadMatcher.find()) {
						userTimesReread = Integer.parseInt(timesRereadMatcher.group(1));
					}
					if(rereadValueSelectMatcher.find()) {
						Matcher rereadValueMatcher = MANGA_REREAD_VALUE_PATTERN.matcher(rereadValueSelectMatcher.group(1));
						if(rereadValueMatcher.find()) {
							userRereadValue = Integer.parseInt(rereadValueMatcher.group(1));
						}
					}
				}
				if(commentsMatcher.find()) {
					userComments = commentsMatcher.group(1);
				}
				userEnableDiscussion = enableDiscussionMatcher.find();
				
				
				manga.setUserPriority(userPriority);
				manga.setUserStorageType(userStorageType);
				manga.setUserStorageValue(userStorageValue);
				manga.setUserChaptersDownloaded(userChaptersDownloaded);
				manga.setUserTimesReread(userTimesReread);
				manga.setUserRereadValue(userRereadValue);
				manga.setUserComments(userComments);
				manga.setUserEnableDiscussion(userEnableDiscussion);
				entity.consumeContent();
			}
			
			valid = statusCode == HttpStatus.SC_OK;
		} catch (Exception e) {
			throw new MALException(e);
		}
		return valid;
	}
	
	public boolean verifyCredentials() throws MALException {
		boolean valid = false;
		
		try {
			URL url = new URL("http://myanimelist.net/api/account/verify_credentials.xml");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", "Basic "+ encodedAuthorization);
			con.setConnectTimeout(CONNECT_TIMEOUT);
			try {
				con.connect();
				valid = con.getResponseCode() == HttpURLConnection.HTTP_OK;
			} finally {
				if(con != null)
					con.disconnect();
			}
		} catch (Exception e) {
			throw new MALException(e);
		}
		return valid;
	}
	
	public boolean addAnime(Anime anime) throws MALException {
		boolean valid = false;
		
		try {
			URL url = new URL("http://myanimelist.net/api/animelist/add/" + anime.getId() + ".xml");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("Authorization", "Basic "+ encodedAuthorization);
			con.setConnectTimeout(CONNECT_TIMEOUT);
			String data = URLEncoder.encode(anime.toXML());
			OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
			writer.write("data=" + data);
			writer.close();
			
			valid = con.getResponseCode() == HttpURLConnection.HTTP_CREATED;
		} catch (Exception e) {
			throw new MALException(e);
		}
		
		return valid;
	}
	
	public boolean updateAnime(Anime anime) throws MALException {
		boolean valid = false;
		
		try {
			URL url = new URL("http://myanimelist.net/api/animelist/update/" + anime.getId() + ".xml");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("Authorization", "Basic "+ encodedAuthorization);
			con.setConnectTimeout(CONNECT_TIMEOUT);
			String data = URLEncoder.encode(anime.toXML());
			OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
			writer.write("data=" + data);
			writer.close();
			
			valid = con.getResponseCode() == HttpURLConnection.HTTP_OK;
		} catch (Exception e) {
			throw new MALException(e);
		}
		
		return valid;
	}
	
	public boolean deleteAnime(int id) throws MALException {
		boolean valid = false;
		
		try {
			URL url = new URL("http://myanimelist.net/api/animelist/delete/" + id + ".xml");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("DELETE");
			con.setRequestProperty("Authorization", "Basic "+ encodedAuthorization);
			con.setConnectTimeout(CONNECT_TIMEOUT);
			try {
				con.connect();
				valid = con.getResponseCode() == HttpURLConnection.HTTP_OK;
			} finally {
				if(con != null)
					con.disconnect();
			}
		} catch (Exception e) {
			throw new MALException(e);
		}
		
		return valid;
	}
	
	public ArrayList<Anime> searchAnime(String query) throws MALException {
		ArrayList<Anime> results = new ArrayList<Anime>();
		String encodedQuery = ""; 
		
		try {
			encodedQuery = URLEncoder.encode(query, "UTF-8");
			URL url = new URL("http://myanimelist.net/api/anime/search.xml?q=" + encodedQuery);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", "Basic "+ encodedAuthorization);
			con.setConnectTimeout(CONNECT_TIMEOUT);
			InputStream is = con.getInputStream();
			
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK)
				results = parseAnimeSearchResultsXML(is);
		} catch (Exception e) {
			throw new MALException(e);
		}
		
		return results;
	}
	
	public JSONObject getCurrentUserAnimeListData() throws MALException {
		return getUserAnimeListData(this.name);
	}
	
	public JSONObject getUserAnimeListData(String username) throws MALException {
		JSONObject animeListData = null;
		
		try {
			URL url = new URL("http://myanimelist.net/malappinfo.php?u=" + username + "&status=all&type=anime");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setConnectTimeout(CONNECT_TIMEOUT);
			InputStream is = con.getInputStream();
			
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				animeListData = parseUserAnimeListXML(is);
			} else {
				throw new MALException("getUserAnimeListData http response failed");
			}
		} catch (Exception e) {
			throw new MALException(e);
		}
					
		return animeListData;
	}
	
	public boolean addManga(Manga manga) throws MALException {
		boolean valid = false;
		
		try {
			URL url = new URL("http://myanimelist.net/api/mangalist/add/" + manga.getId() + ".xml");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("Authorization", "Basic "+ encodedAuthorization);
			con.setConnectTimeout(CONNECT_TIMEOUT);
			String data = URLEncoder.encode(manga.toXML());
			OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
			writer.write("data=" + data);
			writer.close();
			
			valid = con.getResponseCode() == HttpURLConnection.HTTP_CREATED;
		} catch (Exception e) {
			throw new MALException(e);
		}
		
		return valid;
	}
	
	public boolean updateManga(Manga manga) throws MALException {
		boolean valid = false;
		
		try {
			URL url = new URL("http://myanimelist.net/api/mangalist/update/" + manga.getId() + ".xml");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("Authorization", "Basic "+ encodedAuthorization);
			con.setConnectTimeout(CONNECT_TIMEOUT);
			String data = URLEncoder.encode(manga.toXML());
			OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
			writer.write("data=" + data);
			writer.close();
			
			valid = con.getResponseCode() == HttpURLConnection.HTTP_OK;
		} catch (Exception e) {
			throw new MALException(e);
		}
		
		return valid;
	}
	
	public boolean deleteManga(int id) throws MALException {
		boolean valid = false;
		
		try {
			URL url = new URL("http://myanimelist.net/api/mangalist/delete/" + id + ".xml");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("DELETE");
			con.setRequestProperty("Authorization", "Basic "+ encodedAuthorization);
			con.setConnectTimeout(CONNECT_TIMEOUT);
			try {
				con.connect();
				valid = con.getResponseCode() == HttpURLConnection.HTTP_OK;
			} finally {
				if(con != null)
					con.disconnect();
			}
		} catch (Exception e) {
			throw new MALException(e);
		}
		
		return valid;
	}
	
	public ArrayList<Manga> searchManga(String query) throws MALException {
		ArrayList<Manga> results = new ArrayList<Manga>();
		String encodedQuery = ""; 
		
		try {
			encodedQuery = URLEncoder.encode(query, "UTF-8");
			URL url = new URL("http://myanimelist.net/api/manga/search.xml?q=" + encodedQuery);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", "Basic "+ encodedAuthorization);
			con.setConnectTimeout(CONNECT_TIMEOUT);
			InputStream is = con.getInputStream();
			
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK)
				results = parseMangaSearchResultsXML(is);
		} catch (Exception e) {
			throw new MALException(e);
		}
		
		return results;
	}
	
	public JSONObject getCurrentUserMangaListData() throws MALException {
		return getUserMangaListData(this.name);
	}
	
	public JSONObject getUserMangaListData(String username) throws MALException {
		JSONObject mangaListData = null;
		
		try {
			URL url = new URL("http://myanimelist.net/malappinfo.php?u=" + username + "&status=all&type=manga");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setConnectTimeout(CONNECT_TIMEOUT);
			InputStream is = con.getInputStream();
			
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				mangaListData = parseUserMangaListXML(is);
			} else {
				throw new MALException("getUserMangaListData http response failed");
			}
		} catch (Exception e) {
			throw new MALException(e);
		}
					
		return mangaListData;
	}
	
	public JSONObject parseUserAnimeListXML(InputStream inputXML) throws MALException, IOException {
		// My storage variables
		final ArrayList<Anime> myAnime = new ArrayList<Anime>();
		final JSONObject animeListData = new JSONObject();
		final Anime currentAnime = new Anime();
		final User currentUser = new User();
		
		// XML nodes to detect
		RootElement root = new RootElement(MYANIMELIST);
		Element anime = root.getChild(ANIME);
		Element user = root.getChild(MYINFO);
		
		// Set parse events for myinfo node
		user.getChild(USER_ID).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentUser.setId(Integer.parseInt(body));
			}
		});
		user.getChild(USER_NAME).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentUser.setName(body);
			}
		});
		user.getChild(USER_WATCHING).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentUser.setAnimeWatching(Integer.parseInt(body));
			}
		});
		user.getChild(USER_COMPLETED).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentUser.setAnimeCompleted(Integer.parseInt(body));
			}
		});
		user.getChild(USER_ONHOLD).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentUser.setAnimeOnhold(Integer.parseInt(body));
			}
		});
		user.getChild(USER_DROPPED).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentUser.setAnimeDropped(Integer.parseInt(body));
			}
		});
		user.getChild(USER_PLANTOWATCH).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentUser.setAnimePlanToWatch(Integer.parseInt(body));
			}
		});
		user.getChild(USER_DAYS_SPENT_WATCHING).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentUser.setAnimeDaysSpentWatching(Double.parseDouble(body));
			}
		});
		
		// Set parse events for anime node
		anime.setEndElementListener(new EndElementListener() {
			public void end() {
				myAnime.add(currentAnime.copy());
			}
		});
		anime.getChild(SERIES_ANIMEDB_ID).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentAnime.setId(Integer.parseInt(body));
			}
		});
		anime.getChild(SERIES_TITLE).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentAnime.setTitle(body);
			}
		});
		anime.getChild(SERIES_SYNONYMS).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				if(body.equals("")) {
					currentAnime.setSynonyms(new String[0]);
				} else {
					currentAnime.setSynonyms(body.split(";\\s"));
				}
			}
		});
		anime.getChild(SERIES_TYPE).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentAnime.setType(Integer.parseInt(body));
			}
		});
		anime.getChild(SERIES_EPISODES).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentAnime.setEpisodes(Integer.parseInt(body));
			}
		});
		anime.getChild(SERIES_STATUS).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentAnime.setStatus(Integer.parseInt(body));
			}
		});
		anime.getChild(SERIES_START).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				if(body.equals("0000-00-00") == false) {
					currentAnime.setStart(Date.valueOf(body));
				}
			}
		});
		anime.getChild(SERIES_END).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				if(body.equals("0000-00-00") == false) {
					currentAnime.setEnd(Date.valueOf(body));
				}
			}
		});
		anime.getChild(SERIES_IMAGE).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentAnime.setImageUrl(body);
			}
		});
		anime.getChild(MY_ID).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentAnime.setUserListId(Integer.parseInt(body));
			}
		});
		anime.getChild(MY_WATCHED_EPISODES).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentAnime.setUserEpisodesWatched(Integer.parseInt(body));
			}
		});
		anime.getChild(MY_START_DATE).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				if(body.equals("0000-00-00") == false) {
					currentAnime.setUserStart(Date.valueOf(body));
				}
			}
		});
		anime.getChild(MY_FINISH_DATE).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				if(body.equals("0000-00-00") == false) {
					currentAnime.setUserFinish(Date.valueOf(body));
				}
			}
		});
		anime.getChild(MY_SCORE).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentAnime.setUserScore(Integer.parseInt(body));
			}
		});
		anime.getChild(MY_STATUS).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				final int userStatus = Integer.parseInt(body);
				currentAnime.setPreviousUserStatus(userStatus);
				currentAnime.setUserStatus(userStatus);
			}
		});
		anime.getChild(MY_REWATCHING).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				if(body.equals("1")) {
					currentAnime.setUserEnableRewatching(true);
				} else {
					currentAnime.setUserEnableRewatching(false);
				}
			}
		});
		anime.getChild(MY_LAST_UPDATED).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentAnime.setUserLastUpdated(body);
			}
		});
		anime.getChild(MY_TAGS).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentAnime.setUserTags(body);
			}
		});
		
		try {
			Xml.parse(inputXML, Xml.Encoding.UTF_8, root.getContentHandler());
			animeListData.put("user", currentUser);
			animeListData.put("anime", myAnime);
		} catch (Exception e) {
			throw new MALException(e);
		} finally {
			if(inputXML != null)
				inputXML.close();
		}
		
		return animeListData;
	}
	
	public JSONObject parseUserMangaListXML(InputStream inputXML) throws MALException, IOException {
		// My storage variables
		final ArrayList<Manga> myManga = new ArrayList<Manga>();
		final JSONObject mangaListData = new JSONObject();
		final Manga currentManga = new Manga();
		final User currentUser = new User();
		
		// XML nodes to detect
		RootElement root = new RootElement(MYANIMELIST);
		Element manga = root.getChild(MANGA);
		Element user = root.getChild(MYINFO);
		
		// Set parse events for myinfo node
		user.getChild(USER_ID).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentUser.setId(Integer.parseInt(body));
			}
		});
		user.getChild(USER_NAME).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentUser.setName(body);
			}
		});
		user.getChild(USER_READING).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentUser.setMangaReading(Integer.parseInt(body));
			}
		});
		user.getChild(USER_COMPLETED).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentUser.setMangaCompleted(Integer.parseInt(body));
			}
		});
		user.getChild(USER_ONHOLD).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentUser.setMangaOnhold(Integer.parseInt(body));
			}
		});
		user.getChild(USER_DROPPED).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentUser.setMangaDropped(Integer.parseInt(body));
			}
		});
		user.getChild(USER_PLANTOREAD).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentUser.setMangaPlanToRead(Integer.parseInt(body));
			}
		});
		user.getChild(USER_DAYS_SPENT_READING).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentUser.setMangaDaysSpentReading(Double.parseDouble(body));
			}
		});
		
		// Set parse events for manga node
		manga.setEndElementListener(new EndElementListener() {
			public void end() {
				myManga.add(currentManga.copy());
			}
		});
		manga.getChild(SERIES_MANGADB_ID).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentManga.setId(Integer.parseInt(body));
			}
		});
		manga.getChild(SERIES_TITLE).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentManga.setTitle(body);
			}
		});
		manga.getChild(SERIES_SYNONYMS).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				if(body.equals("")) {
					currentManga.setSynonyms(new String[0]);
				} else {
					currentManga.setSynonyms(body.split(";\\s"));
				}
			}
		});
		manga.getChild(SERIES_TYPE).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentManga.setType(Integer.parseInt(body));
			}
		});
		manga.getChild(SERIES_CHAPTERS).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentManga.setChapters(Integer.parseInt(body));
			}
		});
		manga.getChild(SERIES_VOLUMES).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentManga.setVolumes(Integer.parseInt(body));
			}
		});
		manga.getChild(SERIES_STATUS).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentManga.setStatus(Integer.parseInt(body));
			}
		});
		manga.getChild(SERIES_START).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				if(body.equals("0000-00-00") == false) {
					currentManga.setStart(Date.valueOf(body));
				}
			}
		});
		manga.getChild(SERIES_END).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				if(body.equals("0000-00-00") == false) {
					currentManga.setEnd(Date.valueOf(body));
				}
			}
		});
		manga.getChild(SERIES_IMAGE).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentManga.setImageUrl(body);
			}
		});
		manga.getChild(MY_ID).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentManga.setUserListId(Integer.parseInt(body));
			}
		});
		manga.getChild(MY_READ_CHAPTERS).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentManga.setUserChaptersRead(Integer.parseInt(body));
			}
		});
		manga.getChild(MY_READ_VOLUMES).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentManga.setUserVolumesRead(Integer.parseInt(body));
			}
		});
		manga.getChild(MY_START_DATE).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				if(body.equals("0000-00-00") == false) {
					currentManga.setUserStart(Date.valueOf(body));
				}
			}
		});
		manga.getChild(MY_FINISH_DATE).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				if(body.equals("0000-00-00") == false) {
					currentManga.setUserFinish(Date.valueOf(body));
				}
			}
		});
		manga.getChild(MY_SCORE).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentManga.setUserScore(Integer.parseInt(body));
			}
		});
		manga.getChild(MY_STATUS).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				final int userStatus = Integer.parseInt(body);
				currentManga.setPreviousUserStatus(userStatus);
				currentManga.setUserStatus(userStatus);
			}
		});
		manga.getChild(MY_REREADING).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				if(body.equals("1")) {
					currentManga.setUserEnableRereading(true);
				} else {
					currentManga.setUserEnableRereading(false);
				}
			}
		});
		manga.getChild(MY_LAST_UPDATED).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentManga.setUserLastUpdated(body);
			}
		});
		manga.getChild(MY_TAGS).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentManga.setUserTags(body);
			}
		});
		
		try {
			Xml.parse(inputXML, Xml.Encoding.UTF_8, root.getContentHandler());
			mangaListData.put("user", currentUser);
			mangaListData.put("manga", myManga);
		} catch (Exception e) {
			throw new MALException(e);
		} finally {
			if(inputXML != null)
				inputXML.close();
		}
		
		return mangaListData;
	}
	
	public ArrayList<Anime> parseAnimeSearchResultsXML(InputStream inputXML) throws MALException, IOException {
		// Root tag
		final String ANIME = "anime";
		
		// Entry info tags (aka Anime result info tags)
		final String ENTRY = "entry";
		final String ID = "id";
		final String TITLE = "title";
		final String ENGLISH = "english";
		final String SYNONYMS = "synonyms";
		final String EPISODES = "episodes";
		final String SCORE = "score";
		final String TYPE = "type";
		final String STATUS = "status";
		final String START_DATE = "start_date";
		final String END_DATE = "end_date";
		final String SYNOPSIS = "synopsis";
		final String IMAGE = "image";
		
		// My storage variables
		final ArrayList<Anime> results = new ArrayList<Anime>();
		final Anime currentAnime = new Anime();
		
		// XML nodes to detect
		RootElement root = new RootElement(ANIME);
		Element anime = root.getChild(ENTRY);
		
		anime.setEndElementListener(new EndElementListener() {
			public void end() {
				results.add(currentAnime.copy());
			}
		});
		anime.getChild(ID).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentAnime.setId(Integer.parseInt(body));
			}
		});
		anime.getChild(TITLE).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentAnime.setTitle(body);
			}
		});
		anime.getChild(ENGLISH).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentAnime.setEnglishTitle(body);
			}
		});
		anime.getChild(SYNONYMS).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				if(body.equals("")) {
					currentAnime.setSynonyms(new String[0]);
				} else {
					currentAnime.setSynonyms(body.split(";\\s"));
				}
			}
		});
		anime.getChild(EPISODES).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentAnime.setEpisodes(Integer.parseInt(body));
			}
		});
		anime.getChild(SCORE).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentAnime.setScore(Double.parseDouble(body));
			}
		});
		anime.getChild(TYPE).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentAnime.setType(body);
			}
		});
		anime.getChild(STATUS).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentAnime.setStatus(body);
			}
		});
		anime.getChild(START_DATE).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentAnime.setStart(Date.valueOf(body));
			}
		});
		anime.getChild(END_DATE).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentAnime.setEnd(Date.valueOf(body));
			}
		});
		anime.getChild(SYNOPSIS).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentAnime.setSynopsis(StringUtils.cleanWeirdMALSynopsisXML(body));
			}
		});
		anime.getChild(IMAGE).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentAnime.setImageUrl(body);
			}
		});
		
		try {
			String xmlStr = IOUtils.inputStreamToString(inputXML);
			xmlStr = xmlStr.replaceFirst("<[?]xml version=\"1.0\" encoding=\"utf-8\"[?]>", "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
			Xml.parse(xmlStr, root.getContentHandler());
		} catch (Exception e) {
			throw new MALException(e);
		} finally {
			if(inputXML != null)
				inputXML.close();
		}
		
		return results;
	}
	
	public ArrayList<Manga> parseMangaSearchResultsXML(InputStream inputXML) throws MALException, IOException {
		// Root tag
		final String MANGA = "manga";
		
		// Entry info tags (aka Manga result info tags)
		final String ENTRY = "entry";
		final String ID = "id";
		final String TITLE = "title";
		final String ENGLISH = "english";
		final String SYNONYMS = "synonyms";
		final String CHAPTERS = "chapters";
		final String VOLUMES = "volumes";
		final String SCORE = "score";
		final String TYPE = "type";
		final String STATUS = "status";
		final String START_DATE = "start_date";
		final String END_DATE = "end_date";
		final String SYNOPSIS = "synopsis";
		final String IMAGE = "image";
		
		// My storage variables
		final ArrayList<Manga> results = new ArrayList<Manga>();
		final Manga currentManga = new Manga();
		
		// XML nodes to detect
		RootElement root = new RootElement(MANGA);
		Element manga = root.getChild(ENTRY);
		
		manga.setEndElementListener(new EndElementListener() {
			public void end() {
				results.add(currentManga.copy());
			}
		});
		manga.getChild(ID).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentManga.setId(Integer.parseInt(body));
			}
		});
		manga.getChild(TITLE).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentManga.setTitle(body);
			}
		});
		manga.getChild(ENGLISH).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentManga.setEnglishTitle(body);
			}
		});
		manga.getChild(SYNONYMS).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				if(body.equals("")) {
					currentManga.setSynonyms(new String[0]);
				} else {
					currentManga.setSynonyms(body.split(";\\s"));
				}
			}
		});
		manga.getChild(CHAPTERS).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentManga.setChapters(Integer.parseInt(body));
			}
		});
		manga.getChild(VOLUMES).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentManga.setVolumes(Integer.parseInt(body));
			}
		});
		manga.getChild(SCORE).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentManga.setScore(Double.parseDouble(body));
			}
		});
		manga.getChild(TYPE).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentManga.setType(body);
			}
		});
		manga.getChild(STATUS).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentManga.setStatus(body);
			}
		});
		manga.getChild(START_DATE).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentManga.setStart(Date.valueOf(body));
			}
		});
		manga.getChild(END_DATE).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentManga.setEnd(Date.valueOf(body));
			}
		});
		manga.getChild(SYNOPSIS).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentManga.setSynopsis(StringUtils.cleanWeirdMALSynopsisXML(body));
			}
		});
		manga.getChild(IMAGE).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentManga.setImageUrl(body);
			}
		});
		
		try {
			String xmlStr = IOUtils.inputStreamToString(inputXML);
			xmlStr = xmlStr.replaceFirst("<[?]xml version=\"1.0\" encoding=\"utf-8\"[?]>", "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
	
			Xml.parse(xmlStr, root.getContentHandler());
		} catch (Exception e) {
			throw new MALException(e);
		} finally {
			if(inputXML != null)
				inputXML.close();
		}
		
		return results;
	}
}
