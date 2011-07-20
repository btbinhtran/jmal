package com.jmal.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.util.Log;

public class IOUtils {
	public static String inputStreamToString(InputStream is) {
        InputStreamReader input = new InputStreamReader(is);
        final int CHARS_PER_PAGE = 5000; //counting spaces
        final char[] buffer = new char[CHARS_PER_PAGE];
        StringBuilder output = new StringBuilder(CHARS_PER_PAGE);
        try {
            for(int read = input.read(buffer, 0, buffer.length);
                    read != -1;
                    read = input.read(buffer, 0, buffer.length)) {
                output.append(buffer, 0, read);
            }
        } catch (IOException ignore) {
        	Log.e("IOUtils", "InputStream threw IOException");
        }

        return output.toString();
    }
}
