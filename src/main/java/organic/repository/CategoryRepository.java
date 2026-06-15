package organic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import organic.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}