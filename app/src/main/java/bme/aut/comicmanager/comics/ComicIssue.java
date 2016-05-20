package bme.aut.comicmanager.comics;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.google.gson.annotations.SerializedName;




@ApiModel(description = "")
public class ComicIssue   {

  @SerializedName("issue-id")
  private Long issueId = null;

  @SerializedName("title")
  private String title = null;

  @SerializedName("issue-number")
  private Integer issueNumber = null;

  @SerializedName("cover")
  private CoverImage cover = null;

  public ComicIssue(){}

  public ComicIssue(ComicIssueDetails detail){
    setTitle(detail.getTitle());
    setIssueId(detail.getIssueId());
    setIssueNumber(detail.getIssueNumber());
    setCover(detail.getCover());
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
    ComicIssue comicIssue = (ComicIssue) o;
    return Objects.equals(issueId, comicIssue.issueId) &&
            Objects.equals(title, comicIssue.title) &&
            Objects.equals(issueNumber, comicIssue.issueNumber) &&
            Objects.equals(cover, comicIssue.cover);
  }

  @Override
  public int hashCode() {
    return Objects.hash(issueId, title, issueNumber, cover);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ComicIssue {\n");

    sb.append("    issueId: ").append(toIndentedString(issueId)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    issueNumber: ").append(toIndentedString(issueNumber)).append("\n");
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
