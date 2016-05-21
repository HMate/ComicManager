package bme.aut.comicmanager.ui.issueUploader;

/**
 * Created by i7 on 2016.05.20..
 */
public interface IssueUploaderScreen {
    void GoBackToParentScreen();
    void FillComicFields(String comicTitle);
    void FillIssueFields(String issueTitle, String issueNumber, String published, String editor, String writer, String penciler);
    void ShowError();
}
