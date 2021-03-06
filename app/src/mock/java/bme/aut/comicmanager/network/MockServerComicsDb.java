package bme.aut.comicmanager.network;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import bme.aut.comicmanager.comics.Comic;
import bme.aut.comicmanager.comics.ComicIssue;
import bme.aut.comicmanager.comics.ComicIssueDetails;
import bme.aut.comicmanager.comics.ComicsDb;

/**
 * Created by mhidvegi on 2016.05.19..
 */
public class MockServerComicsDb implements ComicsDb{

    public static List<Comic> comics = new ArrayList<Comic>();
    public static List<ComicIssueDetails> comicIssues= new ArrayList<ComicIssueDetails>();
    public static long lastComicId = 0;
    public static long lastIssueId = 0;
    public static boolean isInitialized = false;

    public MockServerComicsDb(){
        initializeMockComicServer();
    }

    public void clearDb(){
        comics.clear();
        comicIssues.clear();
        lastComicId = 0;
        lastIssueId = 0;
        isInitialized = false;
    }

    public List<Comic> getComics(){
        return comics;
    }

    public List<ComicIssueDetails> getComicIssues(){
        return comicIssues;
    }

    public List<Comic> getComicsByQuery(String title){

        List<Comic> queriedComics = new ArrayList<>();
        if(EmptyString(title))
        {
            return queriedComics;
        }

        for (Comic c: this.comics) {
            String dTitle = c.getTitle();
            boolean giveBack = true;
            if(dTitle.contains(title)){
                queriedComics.add(c);
            }
        }
        return queriedComics;
    }

    public void addComic(String title){
        comics.add(new Comic(lastComicId++, title));
    }

    public void editComic(long comicId, String title){
        for(int i = 0; i < comics.size(); i++){
            Comic c = comics.get(i);
            if(c.getComicId() == comicId){
                c.setTitle(title);
            }
        }
    }

    public void deleteComic(long comicId){
        for(int i = 0; i < comics.size(); i++){
            Comic c = comics.get(i);
            if(c.getComicId() == comicId){
                comics.remove(i);
                break;
            }
        }
    }

    public List<ComicIssue> getIssuesForId(long comicId){

        List<ComicIssue> issues = new ArrayList<>();

        for (ComicIssueDetails detail: comicIssues) {
            if(detail.getComicId() == comicId){
                ComicIssue iss = new ComicIssue(detail);
                issues.add(iss);
            }
        }
        return issues;
    }

    public List<ComicIssue> getIssuesByQuery(String title, String creator, String published){

        List<ComicIssue> issues = new ArrayList<>();

        for (ComicIssueDetails detail: comicIssues) {
            String dTitle = detail.getTitle();
            String dEditor = makeNonNull(detail.getEditor());
            String dWriter = makeNonNull(detail.getWriter());
            String dPenciler = makeNonNull(detail.getPenciler());
            String dPublished = makeNonNull(detail.getPublished());
            boolean giveBack = true;
            if(!EmptyString(title) && !dTitle.contains(title)){
                giveBack = false;
            }
            if(!EmptyString(creator) && !(dEditor.contains(creator) || dWriter.contains(creator) || dPenciler.contains(creator))){
                giveBack = false;
            }
            if(!EmptyString(published) && !dPublished.contains(published)){
                giveBack = false;
            }

            if(giveBack){
                ComicIssue iss = new ComicIssue(detail);
                issues.add(iss);
            }
        }
        return issues;
    }

    String makeNonNull(String text){
        if(text == null)
            return "";
        return text;
    }

    boolean EmptyString(String text){
        boolean result = ("".equals(text) || text == null );
        return result;
    }

    public ComicIssueDetails getIssueDetails(long issueId){
        for(ComicIssueDetails details : comicIssues) {
            if(details.getIssueId() == issueId){
                return details;
            }
        }
        Log.d("mock comic db", "Couldn't find issue with id: " + issueId);
        return comicIssues.get(0);
    }

    public void addNewIssue(ComicIssueDetails details){
        details.setIssueId(lastIssueId++);
        comicIssues.add(details);
    }
    public void addNewIssue(long comicId, int issueNumber, String issueTitle,
                                   String published, String editorName,
                                   String writerName, String pencilerName){
        ComicIssueDetails details = new ComicIssueDetails();
        details.setComicId(comicId);
        details.setIssueId(lastIssueId++);
        details.setIssueNumber(issueNumber);
        details.setTitle(issueTitle);
        details.setEditor(editorName);
        details.setPenciler(pencilerName);
        details.setPublished(published);
        details.setWriter(writerName);
        details.setSummary("Placeholder");
        comicIssues.add(details);
    }

    public void editIssue(ComicIssueDetails details){
        for(int i = 0; i < comicIssues.size(); i++){
            ComicIssueDetails d = comicIssues.get(i);
            if(d.getIssueId() == details.getIssueId() && d.getComicId() == details.getComicId()){
                comicIssues.set(i, details);
                break;
            }
        }
    }

    public void deleteIssue(long issueId){
        for(int i = 0; i < comicIssues.size(); i++){
            ComicIssueDetails d = comicIssues.get(i);
            if(d.getIssueId() == issueId){
                comicIssues.remove(i);
                break;
            }
        }
    }

    public void initializeMockComicServer(){
        if(isInitialized) {
            clearDb();
        }

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

        ComicIssueDetails d4 = new ComicIssueDetails();
        d4.setComicId(c2.getComicId());
        d4.setIssueId(lastIssueId++);
        d4.setIssueNumber(101);
        d4.setTitle("This Monster Unmasked2!");
        d4.setEditor("Stan Lee");
        d4.setPenciler(null);
        d4.setPublished(null);
        d4.setWriter("Stan Lee");
        d4.setSummary("Captain America, Black Panther, and Age");
        comicIssues.add(d4);

        isInitialized = true;
    }
}
