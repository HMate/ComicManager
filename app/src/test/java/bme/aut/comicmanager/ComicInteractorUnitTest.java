package bme.aut.comicmanager;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.util.List;

import javax.inject.Inject;

import bme.aut.comicmanager.comics.Comic;
import bme.aut.comicmanager.comics.ComicIssue;
import bme.aut.comicmanager.comics.ComicIssueDetails;
import bme.aut.comicmanager.comics.ComicsInteractor;
import bme.aut.comicmanager.network.MockServerComicsDb;

import static org.junit.Assert.*;

/**
 * Created by i7 on 2016.05.22..
 */
@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ComicInteractorUnitTest {

    @Inject
    ComicsInteractor comicsInteractor;
    MockServerComicsDb comicsDb;

    @Before
    public void setup() throws Exception{
        TestHelper.setTestInjector();
        TestHelper.getInjector().inject(this);
        comicsDb = new MockServerComicsDb();
    }

    @Test
    public void getComicsNetworkTest(){
        comicsDb.initializeMockComicServer();
        if(BuildConfig.FLAVOR.equals("mock")){
            List<Comic> comics = comicsInteractor.getComics();
            assertEquals(comics.get(0), comicsDb.comics.get(0));
            assertEquals(comics.get(1), comicsDb.comics.get(1));
            assertEquals(comics.get(2), comicsDb.comics.get(2));
        }
    }

    @Test
    public void getComicByIdNetworkTest(){
        comicsDb.initializeMockComicServer();
        if(BuildConfig.FLAVOR.equals("mock")){
            List<Comic> comics = comicsInteractor.getComics();
            Comic refComic1 = comics.get(1);
            Comic testComic1 = comicsInteractor.getComic(refComic1.getComicId());
            assertEquals(refComic1, testComic1);
        }
    }

    @Test
    public void getWrongComicByIdNetworkTest(){
        comicsDb.initializeMockComicServer();
        if(BuildConfig.FLAVOR.equals("mock")){
            List<Comic> comics = comicsInteractor.getComics();
            Comic refComic = comics.get(0);
            Comic testComic = comicsInteractor.getComic(refComic.getComicId()+1);
            assertNotEquals(refComic, testComic);
        }
    }

    @Test
    public void addComicToEmptyNetworkTest(){
        comicsDb.initializeMockComicServer();
        if(BuildConfig.FLAVOR.equals("mock")){
            comicsDb.clearDb();
            String testTitle = "Test Title";
            comicsInteractor.addNewComic(testTitle);
            Comic testComic = comicsDb.getComics().get(0);
            assertEquals(testTitle, testComic.getTitle());
        }
    }

    @Test
    public void addComicNetworkTest(){
        comicsDb.initializeMockComicServer();
        if(BuildConfig.FLAVOR.equals("mock")){
            String testTitle = "Test Title";
            comicsInteractor.addNewComic(testTitle);
            List<Comic> testComics = comicsDb.getComics();

            boolean testPassed = false;
            for(Comic c : testComics){
                if(c.getTitle().equals(testTitle)){
                    testPassed = true;
                }
            }
            assertTrue(testPassed);
        }
    }

    @Test
    public void editComicCheckListNetworkTest(){
        comicsDb.initializeMockComicServer();
        if(BuildConfig.FLAVOR.equals("mock")){
            List<Comic> original = comicsDb.getComics();
            Comic comic = original.get(2);
            String originalTitle = comic.getTitle();
            String testTitle = originalTitle + " modified";

            comicsInteractor.editComic(comic.getComicId(), testTitle);

            List<Comic> testComics = comicsDb.getComics();
            boolean testPassed = false;
            for(Comic c : testComics){
                if(c.getTitle().equals(testTitle)){
                    testPassed = true;
                }
            }
            assertTrue(testPassed);

            testPassed = true;
            for(Comic c : testComics){
                if(c.getTitle().equals(originalTitle)){
                    testPassed = false;
                }
            }
            assertTrue(testPassed);
        }
    }

    @Test
    public void editComicCheckIdNetworkTest(){
        comicsDb.initializeMockComicServer();
        if(BuildConfig.FLAVOR.equals("mock")){
            List<Comic> original = comicsDb.getComics();
            Comic comic = original.get(2);
            String originalTitle = comic.getTitle();
            String testTitle = originalTitle + " modified";

            comicsInteractor.editComic(comic.getComicId(), testTitle);
            Comic test = comicsInteractor.getComic(comic.getComicId());

            assertEquals(comic.getComicId(), test.getComicId());
            assertEquals(testTitle, test.getTitle());
        }
    }

    @Test
    public void deleteComicCheckIdNetworkTest(){
        comicsDb.initializeMockComicServer();
        if(BuildConfig.FLAVOR.equals("mock")){
            List<Comic> original = comicsDb.getComics();
            Comic comic = original.get(2);

            comicsInteractor.deleteComic(comic.getComicId());
            Comic test = comicsInteractor.getComic(comic.getComicId());

            assertNotEquals(comic.getComicId(), test.getComicId());
            assertNotEquals(comic.getTitle(), test.getTitle());
        }
    }

    // Comic Issues



    @Test
    public void getIssuesNetworkTest(){
        comicsDb.initializeMockComicServer();
        if(BuildConfig.FLAVOR.equals("mock")){
            List<Comic> comics = comicsInteractor.getComics();
            Comic c = comics.get(0);
            List<ComicIssue> refIssues = comicsDb.getIssuesForId(c.getComicId());
            List<ComicIssue> issues = comicsInteractor.getIssuesForComic(c.getComicId());
            for(int i = 0; i < issues.size(); i++){
                assertEquals(refIssues.get(i), issues.get(i));
            }
        }
    }

    @Test
    public void getIssueByIdNetworkTest(){
        comicsDb.initializeMockComicServer();
        if(BuildConfig.FLAVOR.equals("mock")){
            List<ComicIssueDetails> issues = comicsDb.comicIssues;
            for(ComicIssueDetails i : issues) {
                ComicIssueDetails testDetail = comicsInteractor.getIssueDetails(i.getIssueId());
                assertEquals(i, testDetail);
            }
        }
    }

    @Test
    public void getWrongIssueByIdNetworkTest(){
        comicsDb.initializeMockComicServer();
        if(BuildConfig.FLAVOR.equals("mock")){
            List<ComicIssueDetails> issues = comicsDb.comicIssues;
            for(ComicIssueDetails i : issues) {
                ComicIssueDetails testDetail = comicsInteractor.getIssueDetails(i.getIssueId()+1);
                assertNotEquals(i, testDetail);
            }
        }
    }

    @Test
    public void addIssueToEmptyNetworkTest(){
        comicsDb.initializeMockComicServer();
        if(BuildConfig.FLAVOR.equals("mock")){
            comicsDb.clearDb();
            String testTitle = "Test Comic Title";
            comicsInteractor.addNewComic(testTitle);
            Comic testComic = comicsDb.getComics().get(0);

            int issueNumber = 1;
            String issueTitle = "TestinMan";
            comicsInteractor.addNewIssue(testComic.getComicId(), issueNumber, issueTitle, null, null, null, null);

            List<ComicIssueDetails> issues = comicsDb.comicIssues;
            assertEquals(issues.size(), 1);
            assertEquals(issues.get(0).getTitle(), issueTitle);
            assertEquals((int)issues.get(0).getIssueNumber(), issueNumber);

        }
    }

    @Test
    public void addIssueNetworkTest(){
        comicsDb.initializeMockComicServer();
        if(BuildConfig.FLAVOR.equals("mock")){
            long comicId = 2;
            int issueNumber = 1;
            String issueTitle = "TestinMan";
            comicsInteractor.addNewIssue(comicId, issueNumber, issueTitle, null, null, null, null);

            List<ComicIssueDetails> testIssues = comicsDb.comicIssues;
            boolean testPassed = false;
            for(ComicIssueDetails d : testIssues){
                if(d.getComicId() == comicId &&
                    d.getTitle().equals(issueTitle) &&
                    d.getIssueNumber().equals(issueNumber)){
                    testPassed = true;
                }
            }
            assertTrue(testPassed);
        }
    }

//    @Test
//    public void editComicCheckListNetworkTest(){
//        comicsDb.initializeMockComicServer();
//        if(BuildConfig.FLAVOR.equals("mock")){
//            List<Comic> original = comicsDb.getComics();
//            Comic comic = original.get(2);
//            String originalTitle = comic.getTitle();
//            String testTitle = originalTitle + " modified";
//
//            comicsInteractor.editComic(comic.getComicId(), testTitle);
//
//            List<Comic> testComics = comicsDb.getComics();
//            boolean testPassed = false;
//            for(Comic c : testComics){
//                if(c.getTitle().equals(testTitle)){
//                    testPassed = true;
//                }
//            }
//            assertTrue(testPassed);
//
//            testPassed = true;
//            for(Comic c : testComics){
//                if(c.getTitle().equals(originalTitle)){
//                    testPassed = false;
//                }
//            }
//            assertTrue(testPassed);
//        }
//    }
//
//    @Test
//    public void editComicCheckIdNetworkTest(){
//        comicsDb.initializeMockComicServer();
//        if(BuildConfig.FLAVOR.equals("mock")){
//            List<Comic> original = comicsDb.getComics();
//            Comic comic = original.get(2);
//            String originalTitle = comic.getTitle();
//            String testTitle = originalTitle + " modified";
//
//            comicsInteractor.editComic(comic.getComicId(), testTitle);
//            Comic test = comicsInteractor.getComic(comic.getComicId());
//
//            assertEquals(comic.getComicId(), test.getComicId());
//            assertEquals(testTitle, test.getTitle());
//        }
//    }
//
//    @Test
//    public void deleteComicCheckIdNetworkTest(){
//        comicsDb.initializeMockComicServer();
//        if(BuildConfig.FLAVOR.equals("mock")){
//            List<Comic> original = comicsDb.getComics();
//            Comic comic = original.get(2);
//
//            comicsInteractor.deleteComic(comic.getComicId());
//            Comic test = comicsInteractor.getComic(comic.getComicId());
//
//            assertNotEquals(comic.getComicId(), test.getComicId());
//            assertNotEquals(comic.getTitle(), test.getTitle());
//        }
//    }

}
