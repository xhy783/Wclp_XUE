package com.wclp.wooc.repository;

import com.wclp.wooc.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
