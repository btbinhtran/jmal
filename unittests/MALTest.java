import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import com.jmal.MAL;
import com.jmal.model.Anime;
import com.jmal.model.Manga;
import com.jmal.model.User;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class MALTest {
	private MAL mal;
	
	@Before
	public void setUp() throws Exception {
		mal = new MAL("rollatroll", "abc123");
	}
	
//	@Test
//	public void testOldPerformance() {
//		Anime anime = new Anime();
//		anime.setId(2232);
//		anime.setUserTags("bubble yay");
//		
//		for(int i = 0; i < 5; ++i) {
//			mal.deleteAnime(2232);
//			long startTime1 = System.currentTimeMillis();
//			mal.addAnimeOld(anime);
//			long stopTime1 = System.currentTimeMillis();
//			long runTime1 = stopTime1 - startTime1;
//			Log.e("benchmark", "Old: " + runTime1/1000.0);
//		}
//	}
//	
//	@Test
//	public void testNewPerformance() {
//		Anime anime = new Anime();
//		anime.setId(2232);
//		anime.setUserTags("bubble yay");
//		
//		for(int i = 0; i < 5; ++i) {
//			mal.deleteAnime(2232);
//			long startTime1 = System.currentTimeMillis();
//			mal.addAnime(anime);
//			long stopTime1 = System.currentTimeMillis();
//			long runTime1 = stopTime1 - startTime1;
//			Log.e("benchmark", "New: " + runTime1/1000.0);
//		}
//	}
	
	@Test
	public void testGetAnimeFormInfo() {
		Anime anime = new Anime();
		anime.setUserListId(23399261);
		anime.setUserStatus(Anime.USER_STATUS_COMPLETED);
		mal.authenticate();
		
		assertTrue(mal.getAnimeFormInfo(anime));
		assertEquals("Coalguys", anime.getUserFansubGroup());
		assertEquals(Anime.USER_PRIORITY_MEDIUM, anime.getUserPriority());
		assertEquals(Anime.USER_STORAGE_TYPE_EXTERNAL_HD, anime.getUserStorageType());
		assertTrue(33.00 == anime.getUserStorageValue());
		assertEquals(2, anime.getUserDownloadedEpisodes());
		assertEquals(1, anime.getUserTimesRewatched());
		assertEquals(Anime.USER_REWATCH_VALUE_MEDIUM, anime.getUserRewatchValue());
		assertEquals("Hi &gt;Everybody&lt;", anime.getUserComments());
		assertTrue(anime.getUserEnableDiscussion());
	}
	
	@Test
	public void testGetMangaFormInfo() {
		Manga manga = new Manga();
		manga.setUserListId(5805723);
		manga.setUserStatus(Manga.USER_STATUS_COMPLETED);
		mal.authenticate();
		
		assertTrue(mal.getMangaFormInfo(manga));
		assertEquals(Manga.USER_PRIORITY_HIGH, manga.getUserPriority());
		assertEquals(Manga.USER_STORAGE_TYPE_RETAIL_MANGA, manga.getUserStorageType());
		assertEquals(9, manga.getUserStorageValue());
		assertEquals(2, manga.getUserChaptersDownloaded());
		assertEquals(1, manga.getUserTimesReread());
		assertEquals(Manga.USER_REREAD_VALUE_VERY_HIGH, manga.getUserRereadValue());
		assertEquals("yay ranma", manga.getUserComments());
		assertTrue(manga.getUserEnableDiscussion());
	}
	
	@Test
	public void testAuthenticate() {
		MAL uAccount = new MAL("deedyddt", "abc123");
		
		assertTrue(uAccount.authenticate());
	}
	
	@Test
	public void testVerifyCredentials() {
		MAL badmalcreds = new MAL("blahblah", "blahblah");
		
		assertTrue(mal.verifyCredentials());
		assertFalse(badmalcreds.verifyCredentials());
	}
	
	@Test
	public void testAddAnime() {
		MAL uAccount = new MAL("deedyddt", "abc123");
		Anime anime = new Anime();
		
		uAccount.deleteAnime(2232);
		
		anime.setId(2232);
		anime.setUserStatus(1);
		anime.setUserTags("bubble yay");
		
		assertTrue(uAccount.addAnime(anime));
	}
	
	@Test
	public void testUpdateAnime() {
		MAL uAccount = new MAL("deedyddt", "abc123");
		Anime anime = new Anime();
		
		anime.setId(2232);
		anime.setUserTags("bubble yay");
		
		assertTrue(uAccount.updateAnime(anime));
	}
	
	@Test
	public void testDeleteAnime() {
		MAL dAccount = new MAL("deedyddt", "abc123");
		
		assertTrue(dAccount.deleteAnime(141234234));
	}
	
	@Test
	public void testGetUserAnimeListXML() throws IOException {		
		JSONObject testListData = mal.getUserAnimeListData("rollatroll");
		
		try {
			final ArrayList<Anime> testAnime = (ArrayList<Anime>) testListData.get("anime");
			final User testUser = (User) testListData.get("user");
			
			final long blueDragonLastUpdatedSeconds = 1280252631;
			final Timestamp expectedBlueDragonLastUpdated = new Timestamp(TimeUnit.SECONDS.toMillis(blueDragonLastUpdatedSeconds)); 
			final Anime blueDragon = testAnime.get(0);
			final String[] blueDragonSynonyms = blueDragon.getSynonyms();
			final Anime escaflowne = testAnime.get(1);
			final String[] escaflowneSynonyms = escaflowne.getSynonyms();
			
			// Test user rollatroll
			assertEquals(361346, testUser.getId());
			assertEquals("rollatroll", testUser.getName());
			assertEquals(1, testUser.getAnimeWatching());
			assertEquals(0, testUser.getAnimeCompleted());
			assertEquals(0, testUser.getAnimeOnhold());
			assertEquals(1, testUser.getAnimeDropped());
			assertEquals(0, testUser.getAnimePlanToWatch());
			assertTrue(4.48 == testUser.getAnimeDaysSpentWatching());
			
			// Test anime Blue Dragon
			assertEquals(2, testAnime.size());
			assertEquals(2142, blueDragon.getId());
			assertEquals("Blue Dragon", blueDragon.getTitle());
			
			// Test if the Blue Dragon Title which has 0 synonyms gives empty String array
			assertEquals(0, blueDragonSynonyms.length);
			
			assertEquals("TV", blueDragon.getTypeString());
			assertEquals(51, blueDragon.getEpisodes());
			assertEquals("Finished Airing", blueDragon.getStatusString());
			assertEquals(Date.valueOf("2008-03-22"), blueDragon.getEnd());
			assertEquals("http://cdn.myanimelist.net/images/anime/2/20597.jpg", blueDragon.getImageUrl());
			assertEquals(23384265, blueDragon.getUserListId());
			assertEquals(51, blueDragon.getUserEpisodesWatched());
			assertEquals(Date.valueOf("2010-02-03"), blueDragon.getUserStart());
			assertNull(blueDragon.getUserFinish());
			assertEquals(9, blueDragon.getUserScore());
			assertEquals("Dropped", blueDragon.getUserStatusString());
			assertEquals(expectedBlueDragonLastUpdated, blueDragon.getUserLastUpdated());
			assertEquals("blue''cool dragon", blueDragon.getUserTags());
			
			
			
			// Test anime Escaflowne
			assertEquals("Escaflowne", escaflowne.getTitle());
			assertEquals(Date.valueOf("2010-07-27"), escaflowne.getUserFinish());
			
			// Test if the Escaflowne Title synonyms are stored correctly
			assertEquals(3, escaflowneSynonyms.length);
			assertEquals("The Vision of Escaflowne", escaflowneSynonyms[0]);
			assertEquals("Tenkuu no Escaflowne", escaflowneSynonyms[1]);
			assertEquals("Vision of Escaflowne", escaflowneSynonyms[2]);
		} catch (JSONException e) {
			fail("Error grabbing anime data of JSON");
		}
	}
	
	@Test
	public void testSearchAnime() {
		ArrayList<Anime> testResults = mal.searchAnime("angelic layer");
		
		assertEquals(1, testResults.size());
		
		Anime firstResult = testResults.get(0);
		
		assertEquals(52, firstResult.getId());
		assertEquals("Angelic Layer", firstResult.getTitle());
		
		String[] firstResultSynonyms = firstResult.getSynonyms();
		
		// Test first anime synonyms
		assertEquals(2, firstResultSynonyms.length);
		assertEquals("Kidou Tenshi Angelic Layer", firstResultSynonyms[0]);
		assertEquals("Battle Doll Angelic Layer", firstResultSynonyms[1]);
		
		assertEquals(26, firstResult.getEpisodes());
		assertTrue(7.44 == firstResult.getScore());
		assertEquals("TV", firstResult.getTypeString());
		assertEquals("Finished Airing", firstResult.getStatusString());
		assertEquals(Date.valueOf("2001-04-01"), firstResult.getStart());
		assertEquals(Date.valueOf("2001-09-23"), firstResult.getEnd());
		
		final String expectedSynopsis = "12-year-old Misaki Suzuhara has just gotten involved in Angelic Layer, a battling game using electronic dolls called angels. Even as a newbie, Misaki shows advanced skills as she meets new friends and enters Angelic Layer tournaments to fight the greatest Angelic Layer champions of the nation."; 
		
		assertEquals(expectedSynopsis, firstResult.getSynopsis());
		assertEquals("http://cdn.myanimelist.net/images/anime/6/23379.jpg", firstResult.getImageUrl());
	}
	
	@Test
	public void testAddManga() {
		MAL uAccount = new MAL("deedyddt", "abc123");
		Manga manga = new Manga();
		
		uAccount.deleteManga(19089);
		
		manga.setId(19089);
		manga.setUserStatus(1);
		manga.setUserTags("add manga dog");
		
		assertTrue(uAccount.addManga(manga));
	}
	
	@Test
	public void testUpdateManga() {
		MAL uAccount = new MAL("deedyddt", "abc123");
		Manga manga = new Manga();
		
		manga.setId(8853);
		manga.setUserStatus(1);
		manga.setUserTags("hey funny money");
		
		assertTrue(uAccount.updateManga(manga));
	}
	
	@Test
	public void testDeleteManga() {
		MAL dAccount = new MAL("deedyddt", "abc123");
		
		assertTrue(dAccount.deleteManga(19089));
	}
	
	@Test
	public void testSearchManga() {
		ArrayList<Manga> testResults = mal.searchManga("yakitate");
		
		assertEquals(1, testResults.size());
		Manga firstResult = testResults.get(0);
		
		assertEquals(653, firstResult.getId());
		assertEquals("Yakitate!! Japan", firstResult.getTitle());
		assertEquals("Freshly-Baked Japan", firstResult.getEnglishTitle());
		
		String[] firstResultSynonyms = firstResult.getSynonyms();
		
		// Test first manga synonyms
		assertEquals(2, firstResultSynonyms.length);
		assertEquals("Fresh-Baked Japan", firstResultSynonyms[0]);
		assertEquals("Freshly-Baked Japan", firstResultSynonyms[1]);
		
		assertEquals(242, firstResult.getChapters());
		assertEquals(26, firstResult.getVolumes());
		assertTrue(8.02 == firstResult.getScore());
		assertEquals("Manga", firstResult.getTypeString());
		assertEquals("Finished", firstResult.getStatusString());
		assertEquals(Date.valueOf("2002-00-00"), firstResult.getStart());
		assertEquals(Date.valueOf("2007-01-10"), firstResult.getEnd());
		
		final String expectedSynopsis = "Azuma Kazuma isn't terribly clever, but he's got a good heart and great skill - at baking. Since childhood, he's been on a quest to create the perfect bread to represent Japan internationally. Now, he seeks to enter the famous bakery Pantasia, in hopes of reaching his goal. But plots abound... Source: ANN"; 
		
		assertEquals(expectedSynopsis, firstResult.getSynopsis());
		assertEquals("http://cdn.myanimelist.net/images/manga/4/28467.jpg", firstResult.getImageUrl());
	}
	
	@Test
	public void testGetUserMangaListXML() throws IOException {		
		JSONObject testListData = mal.getUserMangaListData("rollatroll");
		
		try {
			final ArrayList<Manga> testManga = (ArrayList<Manga>) testListData.get("manga");
			final User testUser = (User) testListData.get("user");
			
			final long dragonBallLastUpdatedSeconds = 1280794084;
			final Timestamp expectedDragonBallLastUpdated = new Timestamp(TimeUnit.SECONDS.toMillis(dragonBallLastUpdatedSeconds)); 
			final Manga dragonBall = testManga.get(0);
			final String[] dragonBallSynonyms = dragonBall.getSynonyms();
			
			// Test user rollatroll
			assertEquals(361346, testUser.getId());
			assertEquals("rollatroll", testUser.getName());
			assertEquals(1, testUser.getMangaReading());
			assertEquals(1, testUser.getMangaCompleted());
			assertEquals(0, testUser.getMangaOnhold());
			assertEquals(0, testUser.getMangaDropped());
			assertEquals(0, testUser.getMangaPlanToRead());
			assertTrue(4.63 == testUser.getMangaDaysSpentReading());
			
			assertEquals(2, testManga.size());
			// Test manga Dragon Ball
			assertEquals(42, dragonBall.getId());
			assertEquals("Dragon Ball", dragonBall.getTitle());
			
			// Test Dragon Ball synonyms
			assertEquals(3, dragonBallSynonyms.length);
			assertEquals("Dragonball", dragonBallSynonyms[0]);
			assertEquals("Dragon Ball Z", dragonBallSynonyms[1]);
			assertEquals("Dragonball Z", dragonBallSynonyms[2]);
			
			assertEquals("Manga", dragonBall.getTypeString());
			assertEquals(520, dragonBall.getChapters());
			assertEquals(42, dragonBall.getVolumes());
			assertEquals("Finished", dragonBall.getStatusString());
			assertEquals(Date.valueOf("1984-12-03"), dragonBall.getStart());
			assertEquals(Date.valueOf("1995-06-05"), dragonBall.getEnd());
			assertEquals("http://cdn.myanimelist.net/images/manga/2/24971.jpg", dragonBall.getImageUrl());
			assertEquals(5805711, dragonBall.getUserListId());
			assertEquals(20, dragonBall.getUserChaptersRead());
			assertEquals(2, dragonBall.getUserVolumesRead());
			assertEquals(Date.valueOf("2010-02-02"), dragonBall.getUserStart());
			assertNull(dragonBall.getUserFinish());
			assertEquals(7, dragonBall.getUserScore());
			assertEquals("Reading", dragonBall.getUserStatusString());
			assertEquals(expectedDragonBallLastUpdated, dragonBall.getUserLastUpdated());
			assertEquals("funny dragon", dragonBall.getUserTags());
		} catch (JSONException e) {
			fail("Error grabbing manga data of JSON");
		}
	}
}
