import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import com.jmal.model.Manga;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class MangaTest {
	private Manga manga;
	
	@Before
	public void setUp() throws Exception {
		this.manga = new Manga();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAndSetId() {
		final int id = 3;
		
		manga.setId(id);
		
		assertEquals(id, manga.getId());
	}

	@Test
	public void testGetAndSetTitle() {
		final String title = "My Cool Anime";
		
		manga.setTitle(title);
		
		assertEquals(title, manga.getTitleRaw());
	}

	@Test
	public void testGetAndSetEnglishTitle() {
		final String englishTitle = "My Eng Title";
		
		manga.setEnglishTitle(englishTitle);
		
		assertEquals(englishTitle, manga.getEnglishTitle());
	}

	@Test
	public void testGetAndSetSynonyms() {
		final String[] synonyms = {"blah", "cool"};
		
		manga.setSynonyms(synonyms);
		
		assertArrayEquals(synonyms, manga.getSynonyms());
	}

	@Test
	public void testGetAndSetSynopsis() {
		final String synopsis = "A Shinigami named Baishin who was sealed by Soul Society long ago is set free on Earth. On a fateful day Ichigo encounters him and has roughly half of his reiatsu drained.";
		
		manga.setSynopsis(synopsis);
		
		assertEquals(synopsis, manga.getSynopsis());
	}

	@Test
	public void testGetAndSetType() {
		final int type = 2;
		
		manga.setType(type);
		
		assertEquals(type, manga.getType());
	}
	
	@Test
	public void testSetTypeWithMyAnimeListTypeNumbers() {
		final int typeManga = 1;
		final int novel = 2;
		final int oneShot = 3;
		final int doujin = 4;
		final int manwha = 5;
		final int manhua = 6;
		final int oel = 7;
		final int exceptionNum = 22;
		
		manga.setType(typeManga);
		assertEquals("Manga", manga.getTypeString());
		
		manga.setType(novel);
		assertEquals("Novel", manga.getTypeString());
		
		manga.setType(oneShot);
		assertEquals("One Shot", manga.getTypeString());
		
		manga.setType(doujin);
		assertEquals("Doujin", manga.getTypeString());
		
		manga.setType(manwha);
		assertEquals("Manhwa", manga.getTypeString());
		
		manga.setType(manhua);
		assertEquals("Manhua", manga.getTypeString());
		
		manga.setType(oel);
		assertEquals("OEL", manga.getTypeString());
		
		manga.setType(exceptionNum);
		assertEquals("Manga", manga.getTypeString());
	}
	
	@Test
	public void testSetTypeWithMyAnimeListTypeString() {
		final String typeManga = "Manga";
		final String novel = "Novel";
		final String oneShot = "One Shot";
		final String doujin = "Doujin";
		final String manhwa = "Manhwa";
		final String manhua = "Manhua";
		final String oel = "OEL";
		final String exceptionString = "blah";
		
		manga.setType(typeManga);
		assertEquals(1, manga.getType());
		
		manga.setType(novel);
		assertEquals(2, manga.getType());
		
		manga.setType(oneShot);
		assertEquals(3, manga.getType());
		
		manga.setType(doujin);
		assertEquals(4, manga.getType());
		
		manga.setType(manhwa);
		assertEquals(5, manga.getType());
		
		manga.setType(manhua);
		assertEquals(6, manga.getType());
		
		manga.setType(oel);
		assertEquals(7, manga.getType());
		
		manga.setType(exceptionString);
		assertEquals(1, manga.getType());
	}

	@Test
	public void testGetAndSetChapters() {
		final int chapters = 33;
		
		manga.setChapters(chapters);
		
		assertEquals(chapters, manga.getChapters());
	}
	
	@Test
	public void testGetAndSetVolumes() {
		final int volumes = 22;
		
		manga.setVolumes(volumes);
		
		assertEquals(volumes, manga.getVolumes());
	}

	@Test
	public void testGetAndSetScore() {
		final double score = 3.42;
		
		manga.setScore(score);
		
		assertTrue(score == manga.getScore());
	}

	@Test
	public void testGetAndSetStatus() {
		final int airingStatus = 1;
		
		manga.setStatus(airingStatus);
		
		assertEquals(airingStatus, manga.getStatus());
	}
	
	@Test
	public void testSetStatusWithMyAnimeListStatusNumbers() {
		final int publishing = 1;
		final int finished = 2;
		final int notYetPublished = 3;
		final int exceptionNum = 23;
		
		manga.setStatus(publishing);
		assertEquals("Publishing", manga.getStatusString());
		
		manga.setStatus(finished);
		assertEquals("Finished", manga.getStatusString());
		
		manga.setStatus(notYetPublished);
		assertEquals("Not yet published", manga.getStatusString());
		
		manga.setStatus(exceptionNum);
		assertEquals("Finished", manga.getStatusString());
	}
	
	@Test
	public void testSetStatusWithMyAnimeListStatusString() {
		final String publishing = "Publishing";
		final String finished = "Finished";
		final String notYetPublished = "Not yet published";
		final String exceptionString = "blah";
		
		manga.setStatus(publishing);
		assertEquals(1, manga.getStatus());
		
		manga.setStatus(finished);
		assertEquals(2, manga.getStatus());
		
		manga.setStatus(notYetPublished);
		assertEquals(3, manga.getStatus());
		
		manga.setStatus(exceptionString);
		assertEquals(2, manga.getStatus());
	}

	@Test
	public void testGetAndSetStart() {
		final Date start = new Date(2010, 6, 22);
		
		manga.setStart(start);
		
		assertEquals(start, manga.getStart());
	}
	
	@Test
	public void testGetAndSetEnd() {
		final Date end = new Date(2010, 6, 22);
		
		manga.setEnd(end);
		
		assertEquals(end, manga.getEnd());
	}
	
	@Test
	public void testGetAndSetImageUrl() {
		final String imageUrl = "http://yayo.com";
		
		manga.setImageUrl(imageUrl);
		
		assertEquals(imageUrl, manga.getImageUrl());
	}

	@Test
	public void testGetAndSetUserListId() {
		final int userListId = 3;
		
		manga.setUserListId(userListId);
		
		assertEquals(userListId, manga.getUserListId());
	}
	
	@Test
	public void testGetAndSetUserChaptersRead() {
		final int userChaptersRead = 5;
		
		manga.setUserChaptersRead(userChaptersRead);
		
		assertEquals(userChaptersRead, manga.getUserChaptersRead());
	}

	@Test
	public void testGetAndSetUserStart() {
		final Date userStart = new Date(2010, 6, 22);
		
		manga.setUserStart(userStart);
		
		assertEquals(userStart, manga.getUserStart());
	}
	
	@Test
	public void testGetAndSetUserFinish() {
		final Date userFinish = new Date(2010, 6, 22);
		
		manga.setUserFinish(userFinish);
		
		assertEquals(userFinish, manga.getUserFinish());
	}

	@Test
	public void testGetAndSetUserScore() {
		final int userScore = 4;
		
		manga.setUserScore(userScore);
		
		assertEquals(userScore, manga.getUserScore());
	}

	@Test
	public void testGetAndSetUserStatus() {
		final int userWatchStatus = 1;
		
		manga.setUserStatus(userWatchStatus);
		
		assertEquals(userWatchStatus, manga.getUserStatus());
	}
	
	@Test
	public void testSetUserStatusWithMyAnimeListStatusNumbers() {
		final int reading = 1;
		final int completed = 2;
		final int onhold = 3;
		final int dropped = 4;
		final int planToRead = 6;
		final int exceptionNum = 11;
		
		manga.setUserStatus(reading);
		assertEquals("Reading", manga.getUserStatusString());
		
		manga.setUserStatus(completed);
		assertEquals("Completed", manga.getUserStatusString());
		
		manga.setUserStatus(onhold);
		assertEquals("On-Hold", manga.getUserStatusString());
		
		manga.setUserStatus(dropped);
		assertEquals("Dropped", manga.getUserStatusString());
		
		manga.setUserStatus(planToRead);
		assertEquals("Plan to Read", manga.getUserStatusString());
		
		manga.setUserStatus(exceptionNum);
		assertEquals("Reading", manga.getUserStatusString());
	}

	@Test
	public void testGetAndSetUserLastUpdated() {
		final BigInteger randomTimeInSeconds = new BigInteger("1280181484");
		final Timestamp userLastUpdated = new Timestamp(TimeUnit.SECONDS.toMillis(randomTimeInSeconds.longValue()));
		
		manga.setUserLastUpdated(userLastUpdated);
		
		assertEquals(userLastUpdated, manga.getUserLastUpdated());
	}

	@Test
	public void testGetAndSetUserLastUpdatedWithString() {
		final String randomTimeInSecondsStr = "1280181484";
		final BigInteger randomTimeInSeconds = new BigInteger(randomTimeInSecondsStr);
		final Timestamp expectedUserLastUpdated = new Timestamp(TimeUnit.SECONDS.toMillis(randomTimeInSeconds.longValue()));
		
		manga.setUserLastUpdated(randomTimeInSecondsStr);
		
		assertEquals(expectedUserLastUpdated, manga.getUserLastUpdated());
	}
	
	@Test
	public void testGetAndSetUserTags() {
		final String tags = "hello, cool, baseball";
		
		manga.setUserTags(tags);
		
		assertEquals(tags, manga.getUserTags());
	}
	
	@Test
	public void testToXML() {
		String expectedXML = "<entry><chapter>0</chapter><volume>0</volume><status>1</status><score>0</score><downloaded_chapters>0</downloaded_chapters><times_reread>0</times_reread><reread_value>0</reread_value><date_start>02022009</date_start><date_finish>null</date_finish><priority>0</priority><enable_discussion>0</enable_discussion><enable_rereading>0</enable_rereading><comments></comments><scan_group></scan_group><tags></tags><retail_volumes>0</retail_volumes></entry>";
		manga.setId(2);
		manga.setUserStatus(1);
		manga.setUserStart(Date.valueOf("2009-02-02"));
		
		assertEquals(expectedXML, manga.toXML());
	}
	
	@Test
	public void testIsCompletedWithUnknownChaptersAndVolumes() {
		manga.setChapters(0);
		manga.setVolumes(0);
		manga.setUserChaptersRead(2);
		manga.setUserVolumesRead(3);
		
		assertFalse(manga.isCompleted());
	}
	
	@Test
	public void testIsCompletedWithUnknownChapters() {
		manga.setChapters(0);
		manga.setVolumes(3);
		manga.setUserChaptersRead(4);
		manga.setUserVolumesRead(3);
		
		assertFalse(manga.isCompleted());
	}
	
	@Test
	public void testIsCompletedWithUnknownVolumes() {
		manga.setChapters(5);
		manga.setVolumes(0);
		manga.setUserChaptersRead(5);
		manga.setUserVolumesRead(9);
		
		assertFalse(manga.isCompleted());
	}
	
	@Test
	public void testIsCompleted() {
		manga.setChapters(8);
		manga.setVolumes(2);
		manga.setUserChaptersRead(8);
		manga.setUserVolumesRead(2);
		
		assertTrue(manga.isCompleted());
	}
	
	@Test
	public void testIsChaptersCompletedWithUnknown() {
		manga.setChapters(0);
		manga.setUserChaptersRead(3);
		
		assertFalse(manga.isChaptersCompleted());
	}
	
	@Test
	public void testIsChaptersCompleted() {
		manga.setChapters(3);
		manga.setUserChaptersRead(3);
		
		assertTrue(manga.isChaptersCompleted());
	}
	
	@Test
	public void testIsVolumesCompletedWithUnknown() {
		manga.setVolumes(0);
		manga.setUserVolumesRead(3);
		
		assertFalse(manga.isVolumesCompleted());
	}
	
	@Test
	public void testIsVolumesCompleted() {
		manga.setVolumes(3);
		manga.setUserVolumesRead(3);
		
		assertTrue(manga.isVolumesCompleted());
	}
	
	@Test
	public void testIsChaptersUnknown() {
		manga.setChapters(0);
		
		assertTrue(manga.isChaptersUnknown());
		manga.setChapters(3);
		assertFalse(manga.isChaptersUnknown());
	}
	
	@Test
	public void testIsVolumesUnknown() {
		manga.setVolumes(0);
		
		assertTrue(manga.isVolumesUnknown());
		manga.setVolumes(3);
		assertFalse(manga.isVolumesUnknown());
	}
	
	@Test
	public void testIncUserChaptersRead() {
		manga.setUserChaptersRead(3);
		manga.incUserChaptersRead();
		
		assertEquals(4, manga.getUserChaptersRead());
	}
	
	@Test
	public void testIncUserVolumesRead() {
		manga.setUserVolumesRead(4);
		manga.incUserVolumesRead();
		
		assertEquals(5, manga.getUserVolumesRead());
	}
	
	@Test
	public void testIncTimesReread() {
		manga.setUserTimesReread(2);
		manga.incUserTimesReread();
		
		assertEquals(3, manga.getUserTimesReread());
	}
	
	@Test
	public void testSetUserChaptersAndVolumesReadToCompleted() {
		manga.setChapters(4);
		manga.setVolumes(8);
		manga.setUserChaptersAndVolumesReadToCompleted();
		
		assertEquals(4, manga.getUserChaptersRead());
		assertEquals(8, manga.getUserVolumesRead());
	}
}
