package com.upc.TuCine.repository;
import com.upc.TuCine.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByUserId(Integer userId);
    List<Review> findByBusinessId(Integer businessId);

    List<Review> findByUserIdAndBusinessId(Integer userId, Integer businessId);
}
