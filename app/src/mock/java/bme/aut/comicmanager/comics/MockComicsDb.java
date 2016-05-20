package bme.aut.comicmanager.comics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mhidvegi on 2016.05.19..
 */
public class MockComicsDb {

    public List<Comic> comics = new ArrayList<Comic>();
    private static List<ComicIssueDetails> comicIssues= new ArrayList<ComicIssueDetails>();
    private static long lastComicId = 0;
    private static long lastIssueId = 0;
    private boolean isInitialized = false;

    public List<Comic> getComics(){
        if(!isInitialized){
            initializeMockComicServer();
        }

        return comics;
    }

    public void addComic(String title){
        comics.add(new Comic(lastComicId++, title));
    }

    public List<ComicIssue> getIssuesForId(long comicId){
        if(!isInitialized){
            initializeMockComicServer();
        }

        List<ComicIssue> issues = new ArrayList<>();

        for (ComicIssueDetails detail: comicIssues) {
            if(detail.getComicId() == comicId){
                ComicIssue iss = new ComicIssue(detail);
                issues.add(iss);
            }
        }
        return issues;
    }

    private void initializeMockComicServer(){
        Comic c0 = new Comic(lastComicId++, "Amazing Spider-Man");
        Comic c1 = new Comic(lastComicId++, "Thor");
        Comic c2 = new Comic(lastComicId++, "Captain America");
        comics.add(c0);
        comics.add(c1);
        comics.add(c2);

        ComicIssueDetails d0 = new ComicIssueDetails();
        d0.setComicId(c0.getComicId());
        d0.setIssueId(lastIssueId++);
        d0.setIssueNumber(0);
        d0.setTitle("Spider-Man");
        d0.setEditor("Stan Lee");
        d0.setPenciler("Steve Ditko");
        d0.setPublished("1963");
        d0.setWriter("Stan Lee");
        d0.setSummary("The origins of the Amazing Spider-Man");
        comicIssues.add(d0);

        ComicIssueDetails d1 = new ComicIssueDetails();
        d1.setComicId(c0.getComicId());
        d1.setIssueId(lastIssueId++);
        d1.setIssueNumber(1);
        d1.setTitle("Duel to the Death With The Vulture!");
        d1.setEditor("Stan Lee");
        d1.setPenciler("Steve Ditko");
        d1.setPublished("1963");
        d1.setWriter("Stan Lee");
        d1.setSummary("Spider-Man has to gather all his wits to stop the Vulture.");
        comicIssues.add(d1);

        ComicIssueDetails d2 = new ComicIssueDetails();
        d2.setComicId(c1.getComicId());
        d2.setIssueId(lastIssueId++);
        d2.setIssueNumber(126);
        d2.setTitle("Whom the Gods Would Destroy!");
        d2.setEditor("Stan Lee");
        d2.setPenciler("Jack Kirby");
        d2.setPublished("1966");
        d2.setWriter("Stan Lee");
        d2.setSummary("Thor and Hercules fight over Jane Foster, and Odin decides when to mete out punishment to Thor for disobeying his commands.");
        comicIssues.add(d2);

        ComicIssueDetails d3 = new ComicIssueDetails();
        d3.setComicId(c2.getComicId());
        d3.setIssueId(lastIssueId++);
        d3.setIssueNumber(100);
        d3.setTitle("This Monster Unmasked!");
        d3.setEditor("Stan Lee");
        d3.setPenciler("Jack Kirby");
        d3.setPublished("1968");
        d3.setWriter("Stan Lee");
        d3.setSummary("Captain America, Black Panther, and Agent 13 find themselves outnumbered in the lair of Baron Zemo, who is on the cusp of a plan to do heavy damage to the United States.");
        comicIssues.add(d3);

        isInitialized = true;
    }
}
