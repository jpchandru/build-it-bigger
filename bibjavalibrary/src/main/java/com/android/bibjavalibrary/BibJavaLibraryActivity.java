package com.android.bibjavalibrary;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by cj on 9/19/18.
 */

public class BibJavaLibraryActivity {

    ArrayList<String> jokes;
    private Random random;

    public BibJavaLibraryActivity() {
        random = new Random();
        jokes = new ArrayList<String>() {{
            add("Laugh Out Loudly");
            add("Laugh Out Really Loud");
            add("Comeon Snake, Laugh Out Really Loud");
        }};
    }

    public String getTestJoke() {
        return "LOL";
    }

    public String getRandomJokes() {
        int index = random.nextInt(jokes.size());
        String x = jokes.get(index);
        return x;
    }
}
