package bme.aut.comicmanager.network;


import retrofit2.Call;
import retrofit2.http.*;

import okhttp3.RequestBody;

import bme.aut.comicmanager.comics.InlineResponse200;
import bme.aut.comicmanager.comics.Comic;
import bme.aut.comicmanager.comics.InlineResponse2001;
import bme.aut.comicmanager.comics.ComicIssue;
import bme.aut.comicmanager.comics.ComicIssueDetails;
import bme.aut.comicmanager.comics.InlineResponse2002;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ComicsApi {
  
  /**
   * 
   * Gets a list of `Comic` objects.
   * @param title The part of the title of a comic to search for.
   * @return Call<InlineResponse200>
   */
  
  @GET("comics")
  Call<InlineResponse200> comicsGet(
          @Query("title") String title
  );

  
  /**
   * 
   * Uploads a new comic series with the given information
   * @param comic 
   * @return Call<Void>
   */
  
  @POST("comics/new")
  Call<Void> comicsNewPost(
          @Body Comic comic
  );

  
  /**
   * 
   * Gets the uploaded issues of a comic
   * @param comicId 
   * @return Call<InlineResponse2001>
   */
  
  @GET("comics/{comic-id}")
  Call<InlineResponse2001> comicsComicIdGet(
          @Path("comic-id") Long comicId
  );

  
  /**
   * 
   * Update the information of the series
   * @param comicId 
   * @param comic 
   * @return Call<Void>
   */
  
  @POST("comics/{comic-id}")
  Call<Void> comicsComicIdPost(
          @Path("comic-id") Long comicId, @Body Comic comic
  );

  
  /**
   * 
   * Delete a comic from the server
   * @param comicId
   * @return Call<Void>
   */
  
  @DELETE("comics/{comic-id}")
  Call<Void> comicsComicIdDelete(
          @Path("comic-id") Long comicId
  );

  
  /**
   * 
   * Can search for issues by a query.
   * @param title The part of the title of an issue to search for.
   * @param creator The part of the name of somebody, who worked on an issue.
   * @param published The year of publish of an issue.
   * @return Call<InlineResponse2001>
   */
  
  @GET("issues")
  Call<InlineResponse2001> issuesGet(
          @Query("title") String title, @Query("creator") String creator, @Query("published") String published
  );

  
  /**
   * 
   * Uploads a new comic issue to a series
   * @param comic 
   * @return Call<Void>
   */
  
  @POST("issues/new")
  Call<Void> issuesNewPost(
          @Body ComicIssueDetails comic
  );

  
  /**
   * 
   * Gives back the details of a specific comic issue
   * @param issueId 
   * @return Call<InlineResponse2002>
   */
  
  @GET("issues/{issue-id}")
  Call<InlineResponse2002> issuesIssueIdGet(
          @Path("issue-id") Long issueId
  );

  
  /**
   * 
   * Update the information of an issue
   * @param issueId 
   * @param comic 
   * @return Call<Void>
   */
  
  @POST("issues/{issue-id}")
  Call<Void> issuesIssueIdPost(
          @Path("issue-id") Long issueId, @Body ComicIssueDetails comic
  );

  
  /**
   * 
   * Delete an issue from the server
   * @param issueId
   * @return Call<Void>
   */
  
  @DELETE("issues/{issue-id}")
  Call<Void> issuesIssueIdDelete(
          @Path("issue-id") Long issueId
  );

  
}
