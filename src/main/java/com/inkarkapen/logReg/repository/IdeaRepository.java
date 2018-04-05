package com.inkarkapen.logReg.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.inkarkapen.logReg.models.Idea;

public interface IdeaRepository extends CrudRepository<Idea, Long>{
	List<Idea> findAll();
	List<Idea> findAllByOrderByLikesAsc();
	List<Idea> findAllByOrderByLikesDesc();
}
