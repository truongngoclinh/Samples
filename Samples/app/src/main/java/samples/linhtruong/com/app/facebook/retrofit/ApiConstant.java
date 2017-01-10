package samples.linhtruong.com.app.facebook.retrofit;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 1/6/17 - 10:25.
 * @organization VED
 */

public class ApiConstant {

    public static final String BASE_URL = "https://graph.facebook.com/v2.8/";

    public interface ACCESS_TOKEN {
         String publish_action = "EAACEdEose0cBAPbv7OjZBSkBZCfpgl4rbOwOPXWxPxuKN8ZC5mweG7YOCpNDj4z3PS9dzacv0RhByInXQdZC2JhvpQgrhnNFlrPXm1mBkFTzgeGlYaDIQoQbK5Y0EK1ZCUJgew7UxsLZAXvUGIy6rzFEOdTuck4AdH8wcjw9Tzqy78XI1E2mGdZCdIfZBkA1pwQZD";
         String publich_action_and_user_photos = "EAACEdEose0cBAGNThEos7ZCgtkzCIzgnOhKrCv3WV3plAaEqEQGx9I1m3XGzA4C9NDpCDFm74uKT4Q3ZBiJbmgO341ZBfvzvQVcE4nudwCkYpxtDrBMZAnerazQiZCFgS2n9idyFEzkZCTGGQfOqBZBiuZBea3wtQADrcPzZAlyViB8jHn4pgULNulyDtZBkPPg5hUqmQEkQ5FxSbX3URWQAIQqa9JeTZCVZCTcZD";
    }

    public interface METHOD {
         String photo_upload = "photos.upload";
    }

    public interface FILE_UPLOAD {
         String file_type = "image/png";
         int file_length = 100;
    }

}
