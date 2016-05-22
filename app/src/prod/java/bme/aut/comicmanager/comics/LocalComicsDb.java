package bme.aut.comicmanager.comics;

import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by i7 on 2016.05.22..
 */
public class LocalComicsDb implements ComicsDb {
    public void clearDb(){
        Comic.deleteAll(Comic.class);
        ComicIssueDetails.deleteAll(ComicIssueDetails.class);
    }

    public List<Comic> getComics(){
        List<Comic> comics = Comic.listAll(Comic.class);
        return comics;
    }

    public List<Comic> getComicsByQuery(String title){
        String titleString = "%" + makeNonNull(title) + "%";
        List<Comic> comics = Comic.find(Comic.class, "title like ?", titleString);
        return comics;
    }

    public void addComic(String title){
        Comic c = new Comic();
        c.setTitle(title);
        c.save();
        c.setComicId(c.getId());
        c.save();
    }

    public void editComic(long comicId, String title){
        Comic c = Comic.findById(Comic.class, comicId);
        c.setTitle(title);
        c.save();
    }

    public void deleteComic(long comicId){
        Comic c = Comic.findById(Comic.class, comicId);
        boolean result = c.delete();
        int debug = 4;
    }

    public List<ComicIssue> getIssuesForId(long comicId){
        List<ComicIssueDetails> details = Select.from(ComicIssueDetails.class).where(Condition.prop("comic_id").eq(comicId)).list();
        List<ComicIssue> issues = new ArrayList<ComicIssue>();
        for (ComicIssueDetails d: details){
            issues.add(new ComicIssue(d));
        }
        return issues;
    }

    public List<ComicIssue> getIssuesByQuery(String title, String creator, String published){
        String titleString = "%" + makeNonNull(title) + "%";
        String creatorString = "%" + makeNonNull(creator) + "%";
        String publString = "%" + makeNonNull(published) + "%";

        List<ComicIssueDetails> details = ComicIssueDetails.find(ComicIssueDetails.class,
                "title like ? and (writer like ? or editor like ? or penciler like ?) and published like ?",
                titleString, creatorString, creatorString, creatorString, publString);
        List<ComicIssue> issues = new ArrayList<ComicIssue>();
        for (ComicIssueDetails d: details){
            issues.add(new ComicIssue(d));
        }
        return issues;
    }

    public ComicIssueDetails getIssueDetails(long issueId){
        ComicIssueDetails details = ComicIssueDetails.findById(ComicIssueDetails.class, issueId);
        return details;
    }


    public List<ComicIssueDetails> getComicIssues(){
        List<ComicIssueDetails> details = ComicIssueDetails.listAll(ComicIssueDetails.class);
        return details;
    }

    public void addNewIssue(ComicIssueDetails details){
        details.save();
        details.setIssueId(details.getId());
        details.save();
    }

    public void addNewIssue(long comicId, int issueNumber, String issueTitle,
                     String published, String editorName,
                     String writerName, String pencilerName){
        ComicIssueDetails details = new ComicIssueDetails();
        details.setComicId(comicId);
        details.setIssueNumber(issueNumber);
        details.setTitle(issueTitle);
        details.setPublished(published);
        details.setWriter(writerName);
        details.setEditor(editorName);
        details.setPenciler(pencilerName);
        addNewIssue(details);
    }

    public void editIssue(ComicIssueDetails details){
        details.setId(details.getIssueId());
        details.save();
    }

    public void deleteIssue(long issueId){
        ComicIssueDetails details = ComicIssueDetails.findById(ComicIssueDetails.class, issueId);
        details.delete();
    }

    String makeNonNull(String text){
        if(text == null)
            return "";
        return text;
    }

    public void initializeMockComicServer(){
        // Empty
    }
}
