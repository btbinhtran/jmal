package com.jmal.model;

import java.util.Comparator;

public class MangaTitleSort implements Comparator<Manga> {
	public int compare(Manga m1, Manga m2) {
		return m1.getTitleRaw().compareTo(m2.getTitleRaw());
	}
}
