package com.jmal.model;

import java.util.ArrayList;
import java.util.Collections;

public class User {
	private int id;
	private String name;
	
	// Anime data
	private int animeWatching;
	private int animeCompleted;
	private int animeOnhold;
	private int animeDropped;
	private int animePlanToWatch;
	private double animeDaysSpentWatching;
	private ArrayList<Anime> animeList;
	private ArrayList<Anime> animeWatchingList;
	private ArrayList<Anime> animeCompletedList;
	private ArrayList<Anime> animeOnholdList;
	private ArrayList<Anime> animeDroppedList;
	private ArrayList<Anime> animePlanToWatchList;
	
	// Manga data
	private int mangaReading;
	private int mangaCompleted;
	private int mangaOnhold;
	private int mangaDropped;
	private int mangaPlanToRead;
	private double mangaDaysSpentReading;
	private ArrayList<Manga> mangaList;
	private ArrayList<Manga> mangaReadingList;
	private ArrayList<Manga> mangaCompletedList;
	private ArrayList<Manga> mangaOnholdList;
	private ArrayList<Manga> mangaDroppedList;
	private ArrayList<Manga> mangaPlanToReadList;
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAnimeEntries() {
		return animeList.size();
	}
	
	public void setAnimeWatching(int animeWatching) {
		this.animeWatching = animeWatching;
	}
	
	public int getAnimeWatching() {
		return animeWatching;
	}

	public void setAnimeCompleted(int animeCompleted) {
		this.animeCompleted = animeCompleted;
	}

	public int getAnimeCompleted() {
		return animeCompleted;
	}

	public void setAnimeOnhold(int animeOnhold) {
		this.animeOnhold = animeOnhold;
	}

	public int getAnimeOnhold() {
		return animeOnhold;
	}

	public void setAnimeDropped(int animeDropped) {
		this.animeDropped = animeDropped;
	}

	public int getAnimeDropped() {
		return animeDropped;
	}

	public void setAnimePlanToWatch(int animePlanToWatch) {
		this.animePlanToWatch = animePlanToWatch;
	}

	public int getAnimePlanToWatch() {
		return animePlanToWatch;
	}

	public void setAnimeDaysSpentWatching(double animeDaysSpentWatching) {
		this.animeDaysSpentWatching = animeDaysSpentWatching;
	}

	public double getAnimeDaysSpentWatching() {
		return animeDaysSpentWatching;
	}
	
	public int getMangaEntries() {
		return mangaList.size();
	}
	
	public void setMangaReading(int mangaReading) {
		this.mangaReading = mangaReading;
	}
	
	public int getMangaReading() {
		return mangaReading;
	}

	public void setMangaCompleted(int mangaCompleted) {
		this.mangaCompleted = mangaCompleted;
	}

	public int getMangaCompleted() {
		return mangaCompleted;
	}

	public void setMangaOnhold(int mangaOnhold) {
		this.mangaOnhold = mangaOnhold;
	}

	public int getMangaOnhold() {
		return mangaOnhold;
	}

	public void setMangaDropped(int mangaDropped) {
		this.mangaDropped = mangaDropped;
	}

	public int getMangaDropped() {
		return mangaDropped;
	}

	public void setMangaPlanToRead(int mangaPlanToRead) {
		this.mangaPlanToRead = mangaPlanToRead;
	}

	public int getMangaPlanToRead() {
		return mangaPlanToRead;
	}

	public void setMangaDaysSpentReading(double mangaDaysSpentReading) {
		this.mangaDaysSpentReading = mangaDaysSpentReading;
	}

	public double getMangaDaysSpentReading() {
		return mangaDaysSpentReading;
	}

	public void setAnimeList(ArrayList<Anime> animeList) {
		this.animeList = animeList;
	}

	public ArrayList<Anime> getAnimeList() {
		return animeList;
	}

	public void setAnimeWatchingList(ArrayList<Anime> animeWatchingList) {
		this.animeWatchingList = animeWatchingList;
	}

	public ArrayList<Anime> getAnimeWatchingList() {
		return animeWatchingList;
	}

	public void setAnimeCompletedList(ArrayList<Anime> animeCompletedList) {
		this.animeCompletedList = animeCompletedList;
	}

	public ArrayList<Anime> getAnimeCompletedList() {
		return animeCompletedList;
	}

	public void setAnimeOnholdList(ArrayList<Anime> animeOnholdList) {
		this.animeOnholdList = animeOnholdList;
	}

	public ArrayList<Anime> getAnimeOnholdList() {
		return animeOnholdList;
	}

	public void setAnimeDroppedList(ArrayList<Anime> animeDroppedList) {
		this.animeDroppedList = animeDroppedList;
	}

	public ArrayList<Anime> getAnimeDroppedList() {
		return animeDroppedList;
	}

	public void setAnimePlanToWatchList(ArrayList<Anime> animePlanToWatchList) {
		this.animePlanToWatchList = animePlanToWatchList;
	}

	public ArrayList<Anime> getAnimePlanToWatchList() {
		return animePlanToWatchList;
	}

	public void setMangaList(ArrayList<Manga> mangaList) {
		this.mangaList = mangaList;
	}

	public ArrayList<Manga> getMangaList() {
		return mangaList;
	}

	public void setMangaReadingList(ArrayList<Manga> mangaReadingList) {
		this.mangaReadingList = mangaReadingList;
	}

	public ArrayList<Manga> getMangaReadingList() {
		return mangaReadingList;
	}

	public void setMangaCompletedList(ArrayList<Manga> mangaCompletedList) {
		this.mangaCompletedList = mangaCompletedList;
	}

	public ArrayList<Manga> getMangaCompletedList() {
		return mangaCompletedList;
	}

	public void setMangaOnholdList(ArrayList<Manga> mangaOnholdList) {
		this.mangaOnholdList = mangaOnholdList;
	}

	public ArrayList<Manga> getMangaOnholdList() {
		return mangaOnholdList;
	}

	public void setMangaDroppedList(ArrayList<Manga> mangaDroppedList) {
		this.mangaDroppedList = mangaDroppedList;
	}

	public ArrayList<Manga> getMangaDroppedList() {
		return mangaDroppedList;
	}

	public void setMangaPlanToReadList(ArrayList<Manga> mangaPlanToReadList) {
		this.mangaPlanToReadList = mangaPlanToReadList;
	}

	public ArrayList<Manga> getMangaPlanToReadList() {
		return mangaPlanToReadList;
	}
	
	public ArrayList<Anime> mapAnimeList(final Anime anime) {
		final int userStatus = anime.getUserStatus();
		ArrayList<Anime> list = null;
		
		if(userStatus == Anime.USER_STATUS_WATCHING) {
			list = animeWatchingList;
		} else if(userStatus == Anime.USER_STATUS_COMPLETED) {
			if(anime.getUserEnableRewatching())
				list = animeWatchingList;
			else
				list = animeCompletedList;
		} else if(userStatus == Anime.USER_STATUS_ONHOLD) {
			list = animeOnholdList;
		} else if(userStatus == Anime.USER_STATUS_DROPPED) {
			list = animeDroppedList;
		} else if(userStatus == Anime.USER_STATUS_PLAN_TO_WATCH) {
			list = animePlanToWatchList;
		}
		return list;
	}
	
	public void addAnime(final Anime anime) {
		ArrayList<Anime> list = mapAnimeList(anime);
		
		list.add(anime);
		Collections.sort(list, new AnimeTitleSort());
		animeList.add(anime);
	}
	
	public void updateAnime(final Anime anime) {
		final int userStatus = anime.getUserStatus();
		if(userStatus != Anime.USER_STATUS_COMPLETED && userStatus == anime.getPreviousUserStatus()) {
			int index = animeList.indexOf(anime);
			animeList.set(index, anime);
		} else {
			removeAnime(anime);
			addAnime(anime);
		}
	}
	
	public void removeAnime(final Anime anime) {
		final int userStatus = anime.getUserStatus();
		
		if(userStatus == Anime.USER_STATUS_WATCHING) {
			animeWatchingList.remove(anime);
		} else if(userStatus == Anime.USER_STATUS_COMPLETED) {
			animeWatchingList.remove(anime);
			animeCompletedList.remove(anime);
		} else if(userStatus == Anime.USER_STATUS_ONHOLD) {
			animeOnholdList.remove(anime);
		} else if(userStatus == Anime.USER_STATUS_DROPPED) {
			animeDroppedList.remove(anime);
		} else if(userStatus == Anime.USER_STATUS_PLAN_TO_WATCH) {
			animePlanToWatchList.remove(anime);
		}
		animeList.remove(anime);
	}
	
	public ArrayList<Manga> mapMangaList(final Manga manga) {
		final int userStatus = manga.getUserStatus();
		ArrayList<Manga> list = null;
		
		if(userStatus == Manga.USER_STATUS_READING) {
			list = mangaReadingList;
		} else if(userStatus == Manga.USER_STATUS_COMPLETED) {
			if(manga.getUserEnableRereading())
				list = mangaReadingList;
			else
				list = mangaCompletedList;
		} else if(userStatus == Manga.USER_STATUS_ONHOLD) {
			list = mangaOnholdList;
		} else if(userStatus == Manga.USER_STATUS_DROPPED) {
			list = mangaDroppedList;
		} else if(userStatus == Manga.USER_STATUS_PLAN_TO_READ) {
			list = mangaPlanToReadList;
		}
		return list;
	}
	
	public void addManga(final Manga manga) {
		ArrayList<Manga> list = mapMangaList(manga);
		
		list.add(manga);
		Collections.sort(list, new MangaTitleSort());
		mangaList.add(manga);
	}
	
	public void updateManga(final Manga manga) {
		final int userStatus = manga.getUserStatus();
		if(userStatus != Manga.USER_STATUS_COMPLETED && userStatus == manga.getPreviousUserStatus()) {
			int index = mangaList.indexOf(manga);
			mangaList.set(index, manga);
		} else {
			removeManga(manga);
			addManga(manga);
		}
	}
	
	public void removeManga(final Manga manga) {
		final int userStatus = manga.getUserStatus();
		
		if(userStatus == Manga.USER_STATUS_READING) {
			mangaReadingList.remove(manga);
		} else if(userStatus == Manga.USER_STATUS_COMPLETED) {
			mangaReadingList.remove(manga);
			mangaCompletedList.remove(manga);
		} else if(userStatus == Manga.USER_STATUS_ONHOLD) {
			mangaOnholdList.remove(manga);
		} else if(userStatus == Manga.USER_STATUS_DROPPED) {
			mangaDroppedList.remove(manga);
		} else if(userStatus == Manga.USER_STATUS_PLAN_TO_READ) {
			mangaPlanToReadList.remove(manga);
		}
		mangaList.remove(manga);
	}
	
	public ArrayList<Anime> searchAnimeList(String query) {
		query = query.toLowerCase();
		ArrayList<Anime> results = new ArrayList<Anime>();
		
		for(Anime anime: animeList) {
			if(anime.getTitleRaw().toLowerCase().contains(query))
				results.add(anime);
		}
		
		return results;
	}
	
	public ArrayList<Manga> searchMangaList(String query) {
		query = query.toLowerCase();
		ArrayList<Manga> results = new ArrayList<Manga>();
		
		for(Manga manga: mangaList) {
			if(manga.getTitleRaw().toLowerCase().contains(query))
				results.add(manga);
		}
		
		return results;
	}
}
