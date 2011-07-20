import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import com.jmal.model.Anime;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class AnimeTest {

	private Anime anime;
	
	@Before
	public void setUp() throws Exception {
		this.anime = new Anime();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAndSetId() {
		final int id = 3;
		
		anime.setId(id);
		
		assertEquals(id, anime.getId());
	}

	@Test
	public void testGetAndSetTitleRaw() {
		final String title = "My Cool Anime";
		
		anime.setTitle(title);
		
		assertEquals(title, anime.getTitleRaw());
	}

	@Test
	public void testGetAndSetEnglishTitle() {
		final String englishTitle = "My Eng Title";
		
		anime.setEnglishTitle(englishTitle);
		
		assertEquals(englishTitle, anime.getEnglishTitle());
	}

	@Test
	public void testGetAndSetSynonyms() {
		final String[] synonyms = {"blah", "cool"};
		
		anime.setSynonyms(synonyms);
		
		assertArrayEquals(synonyms, anime.getSynonyms());
	}

	@Test
	public void testGetAndSetSynopsis() {
		final String synopsis = "A Shinigami named Baishin who was sealed by Soul Society long ago is set free on Earth. On a fateful day Ichigo encounters him and has roughly half of his reiatsu drained.";
		
		anime.setSynopsis(synopsis);
		
		assertEquals(synopsis, anime.getSynopsis());
	}

	@Test
	public void testGetAndSetType() {
		final int type = 2;
		
		anime.setType(type);
		
		assertEquals(type, anime.getType());
	}
	
	@Test
	public void testSetTypeWithMyAnimeListTypeNumbers() {
		final int tv = 1;
		final int ova = 2;
		final int movie = 3;
		final int special = 4;
		final int ona = 5;
		final int music = 6;
		final int exceptionNum = 22;
		
		anime.setType(tv);
		assertEquals("TV", anime.getTypeString());
		
		anime.setType(ova);
		assertEquals("OVA", anime.getTypeString());
		
		anime.setType(movie);
		assertEquals("Movie", anime.getTypeString());
		
		anime.setType(special);
		assertEquals("Special", anime.getTypeString());
		
		anime.setType(ona);
		assertEquals("ONA", anime.getTypeString());
		
		anime.setType(music);
		assertEquals("Music", anime.getTypeString());
		
		anime.setType(exceptionNum);
		assertEquals("TV", anime.getTypeString());
	}
	
	@Test
	public void testSetTypeWithMyAnimeListTypeString() {
		final String tv = "TV";
		final String ova = "OVA";
		final String movie = "Movie";
		final String special = "Special";
		final String ona = "ONA";
		final String music = "Music";
		final String exceptionString = "blah";
		
		anime.setType(tv);
		assertEquals(1, anime.getType());
		
		anime.setType(ova);
		assertEquals(2, anime.getType());
		
		anime.setType(movie);
		assertEquals(3, anime.getType());
		
		anime.setType(special);
		assertEquals(4, anime.getType());
		
		anime.setType(ona);
		assertEquals(5, anime.getType());
		
		anime.setType(music);
		assertEquals(6, anime.getType());
		
		anime.setType(exceptionString);
		assertEquals(1, anime.getType());
	}

	@Test
	public void testGetAndSetEpisodes() {
		final int episodes = 33;
		
		anime.setEpisodes(episodes);
		
		assertEquals(episodes, anime.getEpisodes());
	}

	@Test
	public void testGetAndSetScore() {
		final double score = 3.42;
		
		anime.setScore(score);
		
		assertTrue(score == anime.getScore());
	}

	@Test
	public void testGetAndSetStatus() {
		final int airingStatus = 1;
		
		anime.setStatus(airingStatus);
		
		assertEquals(airingStatus, anime.getStatus());
	}
	
	@Test
	public void testSetStatusWithMyAnimeListTypeNumbers() {
		final int currentlyAiring = 1;
		final int finishedAiring = 2;
		final int notYetAired = 3;
		final int exceptionNum = 23;
		
		anime.setStatus(currentlyAiring);
		assertEquals("Currently Airing", anime.getStatusString());
		
		anime.setStatus(finishedAiring);
		assertEquals("Finished Airing", anime.getStatusString());
		
		anime.setStatus(notYetAired);
		assertEquals("Not yet aired", anime.getStatusString());
		
		anime.setStatus(exceptionNum);
		assertEquals("Finished Airing", anime.getStatusString());
	}
	
	@Test
	public void testSetStatusWithMyAnimeListTypeString() {
		final String currentlyAiring = "Currently Airing";
		final String finishedAiring = "Finished Airing";
		final String notYetAired = "Not yet aired";
		final String exceptionString = "blah";
		
		anime.setStatus(currentlyAiring);
		assertEquals(1, anime.getStatus());
		
		anime.setStatus(finishedAiring);
		assertEquals(2, anime.getStatus());
		
		anime.setStatus(notYetAired);
		assertEquals(3, anime.getStatus());
		
		anime.setStatus(exceptionString);
		assertEquals(2, anime.getStatus());
	}

	@Test
	public void testGetAndSetStart() {
		final Date start = new Date(2010, 6, 22);
		
		anime.setStart(start);
		
		assertEquals(start, anime.getStart());
	}
	
	@Test
	public void testGetAndSetEnd() {
		final Date end = new Date(2010, 6, 22);
		
		anime.setEnd(end);
		
		assertEquals(end, anime.getEnd());
	}
	
	@Test
	public void testGetAndSetImageUrl() {
		final String imageUrl = "http://yayo.com";
		
		anime.setImageUrl(imageUrl);
		
		assertEquals(imageUrl, anime.getImageUrl());
	}

	@Test
	public void testGetAndSetUserListId() {
		final int userListId = 3;
		
		anime.setUserListId(userListId);
		
		assertEquals(userListId, anime.getUserListId());
	}

	@Test
	public void testGetAndSetUserWatchedEpisodes() {
		final int userWatchedEpisodes = 5;
		
		anime.setUserEpisodesWatched(userWatchedEpisodes);
		
		assertEquals(userWatchedEpisodes, anime.getUserEpisodesWatched());
	}

	@Test
	public void testGetAndSetUserStart() {
		final Date userStart = new Date(2010, 6, 22);
		
		anime.setUserStart(userStart);
		
		assertEquals(userStart, anime.getUserStart());
	}
	
	@Test
	public void testGetAndSetUserFinish() {
		final Date userFinish = new Date(2010, 6, 22);
		
		anime.setUserFinish(userFinish);
		
		assertEquals(userFinish, anime.getUserFinish());
	}

	@Test
	public void testGetAndSetUserScore() {
		final int userScore = 4;
		
		anime.setUserScore(userScore);
		
		assertEquals(userScore, anime.getUserScore());
	}

	@Test
	public void testGetAndSetUserStatus() {
		final int userWatchStatus = 1;
		
		anime.setUserStatus(userWatchStatus);
		
		assertEquals(userWatchStatus, anime.getUserStatus());
	}
	
	@Test
	public void testSetUserWatchStatusWithMyAnimeListWatchStatusNumbers() {
		final int watching = 1;
		final int completed = 2;
		final int onhold = 3;
		final int dropped = 4;
		final int planToWatch = 6;
		final int exceptionNum = 11;
		
		anime.setUserStatus(watching);
		assertEquals("Watching", anime.getUserStatusString());
		
		anime.setUserStatus(completed);
		assertEquals("Completed", anime.getUserStatusString());
		
		anime.setUserStatus(onhold);
		assertEquals("On-Hold", anime.getUserStatusString());
		
		anime.setUserStatus(dropped);
		assertEquals("Dropped", anime.getUserStatusString());
		
		anime.setUserStatus(planToWatch);
		assertEquals("Plan to Watch", anime.getUserStatusString());
		
		anime.setUserStatus(exceptionNum);
		assertEquals("Watching", anime.getUserStatusString());
	}

	@Test
	public void testGetAndSetUserLastUpdated() {
		final BigInteger randomTimeInSeconds = new BigInteger("1280181484");
		final Timestamp userLastUpdated = new Timestamp(TimeUnit.SECONDS.toMillis(randomTimeInSeconds.longValue()));
		
		anime.setUserLastUpdated(userLastUpdated);
		
		assertEquals(userLastUpdated, anime.getUserLastUpdated());
	}

	@Test
	public void testGetAndSetUserLastUpdatedWithString() {
		final String randomTimeInSecondsStr = "1280181484";
		final BigInteger randomTimeInSeconds = new BigInteger(randomTimeInSecondsStr);
		final Timestamp expectedUserLastUpdated = new Timestamp(TimeUnit.SECONDS.toMillis(randomTimeInSeconds.longValue()));
		
		anime.setUserLastUpdated(randomTimeInSecondsStr);
		
		assertEquals(expectedUserLastUpdated, anime.getUserLastUpdated());
	}
	
	@Test
	public void testGetAndSetUserTags() {
		final String tags = "hello, cool, baseball";
		
		anime.setUserTags(tags);
		
		assertEquals(tags, anime.getUserTags());
	}
	
	@Test
	public void testToXML() {
		final String expectedXML = "<entry><episode>0</episode><status>1</status><score>0</score><downloaded_episodes>0</downloaded_episodes><storage_type>0</storage_type><storage_value>0.0</storage_value><times_rewatched>0</times_rewatched><rewatch_value>0</rewatch_value><date_start>02022009</date_start><date_finish>null</date_finish><priority>0</priority><enable_discussion>0</enable_discussion><enable_rewatching>0</enable_rewatching><comments></comments><fansub_group></fansub_group><tags></tags></entry>";
		Anime testAnime = new Anime();
		testAnime.setId(2);
		testAnime.setUserStatus(1);
		testAnime.setUserStart(Date.valueOf("2009-02-02"));
		
		assertEquals(expectedXML, testAnime.toXML());
	}
}
