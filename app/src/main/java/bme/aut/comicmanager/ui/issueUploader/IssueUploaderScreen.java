package bme.aut.comicmanager.ui.issueUploader;

/**
 * Created by i7 on 2016.05.20..
 */
public interface IssueUploaderScreen {
    void GoBackToParentScreen();
    long getComicId();
    void FillTextFields(String comicTitle);
}
