package bme.aut.comicmanager.comics;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;


@ApiModel(description = "")
public class CoverImage extends SugarRecord {
  
  @SerializedName("width")
  private Integer width = null;
  
  @SerializedName("height")
  private Integer height = null;
  
  @SerializedName("url")
  private String url = null;
  

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Integer getWidth() {
    return width;
  }
  public void setWidth(Integer width) {
    this.width = width;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Integer getHeight() {
    return height;
  }
  public void setHeight(Integer height) {
    this.height = height;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CoverImage coverImage = (CoverImage) o;
    return Objects.equals(width, coverImage.width) &&
        Objects.equals(height, coverImage.height) &&
        Objects.equals(url, coverImage.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(width, height, url);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CoverImage {\n");
    
    sb.append("    width: ").append(toIndentedString(width)).append("\n");
    sb.append("    height: ").append(toIndentedString(height)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
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
