import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import java.io.File;
import java.util.List;

public class RecommendationEngine {
    public static void main(String[] args) throws Exception {
        // Load the data file
        DataModel model = new FileDataModel(new File("user_preferences.csv"));

        // Define similarity method
        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

        // Define neighborhood (2 closest users)
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);

        // Create the recommender
        Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

        // Recommend 3 items for user 1
        List<RecommendedItem> recommendations = recommender.recommend(1, 3);

        // Print the recommendations
        for (RecommendedItem recommendation : recommendations) {
            System.out.println("Recommended Item: " + recommendation.getItemID() +
                               ", Value: " + recommendation.getValue());
        }
    }
}
