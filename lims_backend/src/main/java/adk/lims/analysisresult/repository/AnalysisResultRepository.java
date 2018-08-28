package adk.lims.analysisresult.repository;

import adk.lims.analysisresult.model.entity.AnalysisResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalysisResultRepository extends JpaRepository<AnalysisResult, Long>{
    List<AnalysisResult> findAllByPatient_Id(Long id);
}
