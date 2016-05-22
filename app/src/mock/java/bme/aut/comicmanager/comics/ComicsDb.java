package bme.aut.comicmanager.comics;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by i7 on 2016.05.22..
 */
public class ComicsDb {

    public List<Comic> comics = new ArrayList<Comic>();
    public List<ComicIssueDetails> comicIssues= new ArrayList<ComicIssueDetails>();
    public long lastComicId = 0;
    public long lastIssueId = 0;
    public boolean isInitialized = false;

    public ComicsDb(){
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
            return;
        }

        Comic c0 = new Comic(lastComicId++, "Batman");
        Comic c2 = new Comic(lastComicId++, "Asterix");
        comics.add(c0);
        comics.add(c2);

        ComicIssueDetails d0 = new ComicIssueDetails();
        d0.setComicId(c0.getComicId());
        d0.setIssueId(lastIssueId++);
        d0.setIssueNumber(1);
        d0.setTitle("The legend of batman");
        d0.setEditor("Whitney Ellsworth");
        d0.setPenciler("Bob Kane");
        d0.setPublished("1940");
        d0.setWriter("Bill Finger");
        d0.setSummary("The origins of Batman");
        comicIssues.add(d0);

        ComicIssueDetails d1 = new ComicIssueDetails();
        d1.setComicId(c0.getComicId());
        d1.setIssueId(lastIssueId++);
        d1.setIssueNumber(2);
        d1.setTitle("The Joker Meets the Cat-Woman");
        d1.setEditor("Whitney Ellsworth");
        d1.setPenciler("Bob Kane");
        d1.setPublished("1940");
        d1.setWriter("Bill Finger");
        d1.setSummary("The joker survived his death-by-knife-wound. Now his back!");
        comicIssues.add(d1);

        ComicIssueDetails d3 = new ComicIssueDetails();
        d3.setComicId(c2.getComicId());
        d3.setIssueId(lastIssueId++);
        d3.setIssueNumber(1);
        d3.setTitle("Asterix the Gaul");
        d3.setEditor("René Goscinny");
        d3.setPenciler("Albert Uderzo");
        d3.setPublished("1961");
        d3.setWriter("René Goscinny");
        d3.setSummary("All Gaul is under Roman control, except a small village in Armorica.");
        comicIssues.add(d3);

        isInitialized = true;
    }
}
