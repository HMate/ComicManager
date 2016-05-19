package bme.aut.comicmanager.comics;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;


@ApiModel(description = "")
public class Comic extends SugarRecord {

  @SerializedName("comic-id")
  private Long comicId = null;

  @SerializedName("title")
  private String title = null;

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
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
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
    Comic comic = (Comic) o;
    return Objects.equals(comicId, comic.comicId) &&
            Objects.equals(title, comic.title) &&
            Objects.equals(cover, comic.cover);
  }

  @Override
  public int hashCode() {
    return Objects.hash(comicId, title, cover);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Comic {\n");

    sb.append("    comicId: ").append(toIndentedString(comicId)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
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
