package bme.aut.comicmanager.comics;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;


@ApiModel(description = "")
public class ComicIssueDetails extends SugarRecord {

  @SerializedName("comic-id")
  private Long comicId = null;

  @SerializedName("issue-id")
  private Long issueId = null;

  @SerializedName("title")
  private String title = null;

  @SerializedName("issue-number")
  private Integer issueNumber = null;

  @SerializedName("published")
  private String published = null;

  @SerializedName("editor")
  private String editor = null;

  @SerializedName("writer")
  private String writer = null;

  @SerializedName("penciler")
  private String penciler = null;

  @SerializedName("summary")
  private String summary = null;

  @SerializedName("cover")
  private CoverImage cover = null;



  /**
   **/
  @ApiModelProperty(value = "")
  public Long getComicId() {
    return comicId;
  }
  public void setComicId(Long comicId) {
    this.comicId = comicId;
  }


  /**
   **/
  @ApiModelProperty(value = "")
  public Long getIssueId() {
    return issueId;
  }
  public void setIssueId(Long issueId) {
    this.issueId = issueId;
  }


  /**
   **/
  @ApiModelProperty(value = "")
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }


  /**
   **/
  @ApiModelProperty(value = "")
  public Integer getIssueNumber() {
    return issueNumber;
  }
  public void setIssueNumber(Integer issueNumber) {
    this.issueNumber = issueNumber;
  }


  /**
   **/
  @ApiModelProperty(value = "")
  public String getPublished() {
    return published;
  }
  public void setPublished(String published) {
    this.published = published;
  }


  /**
   **/
  @ApiModelProperty(value = "")
  public String getEditor() {
    return editor;
  }
  public void setEditor(String editor) {
    this.editor = editor;
  }


  /**
   **/
  @ApiModelProperty(value = "")
  public String getWriter() {
    return writer;
  }
  public void setWriter(String writer) {
    this.writer = writer;
  }


  /**
   **/
  @ApiModelProperty(value = "")
  public String getPenciler() {
    return penciler;
  }
  public void setPenciler(String penciler) {
    this.penciler = penciler;
  }


  /**
   **/
  @ApiModelProperty(value = "")
  public String getSummary() {
    return summary;
  }
  public void setSummary(String summary) {
    this.summary = summary;
  }


  /**
   **/
  @ApiModelProperty(value = "")
  public CoverImage getCover() {
    return cover;
  }
  public void setCover(CoverImage cover) {
    this.cover = cover;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ComicIssueDetails comicIssueDetails = (ComicIssueDetails) o;
    return Objects.equals(comicId, comicIssueDetails.comicId) &&
            Objects.equals(issueId, comicIssueDetails.issueId) &&
            Objects.equals(title, comicIssueDetails.title) &&
            Objects.equals(issueNumber, comicIssueDetails.issueNumber) &&
            Objects.equals(published, comicIssueDetails.published) &&
            Objects.equals(editor, comicIssueDetails.editor) &&
            Objects.equals(writer, comicIssueDetails.writer) &&
            Objects.equals(penciler, comicIssueDetails.penciler) &&
            Objects.equals(summary, comicIssueDetails.summary) &&
            Objects.equals(cover, comicIssueDetails.cover);
  }

  @Override
  public int hashCode() {
    return Objects.hash(comicId, issueId, title, issueNumber, published, editor, writer, penciler, summary, cover);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ComicIssueDetails {\n");

    sb.append("    comicId: ").append(toIndentedString(comicId)).append("\n");
    sb.append("    issueId: ").append(toIndentedString(issueId)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    issueNumber: ").append(toIndentedString(issueNumber)).append("\n");
    sb.append("    published: ").append(toIndentedString(published)).append("\n");
    sb.append("    editor: ").append(toIndentedString(editor)).append("\n");
    sb.append("    writer: ").append(toIndentedString(writer)).append("\n");
    sb.append("    penciler: ").append(toIndentedString(penciler)).append("\n");
    sb.append("    summary: ").append(toIndentedString(summary)).append("\n");
    sb.append("    cover: ").append(toIndentedString(cover)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
