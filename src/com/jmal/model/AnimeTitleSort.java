package com.jmal.model;

import java.util.Comparator;

public class AnimeTitleSort implements Comparator<Anime> {
	public int compare(Anime a1, Anime a2) {
		return a1.getTitleRaw().compareTo(a2.getTitleRaw());
	}
}
