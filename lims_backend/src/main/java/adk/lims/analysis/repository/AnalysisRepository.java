package adk.lims.analysis.repository;

import adk.lims.analysis.model.entity.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnalysisRepository extends JpaRepository<Analysis, Long>{

    @Query(value = "SELECT * FROM analyzes AS a WHERE a.id = :id", nativeQuery = true)
    Analysis getOne(@Param("id") Long id);
}
