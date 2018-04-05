package com.inkarkapen.logReg.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.inkarkapen.logReg.models.Idea;
import com.inkarkapen.logReg.repository.IdeaRepository;

@Service
public class IdeaService {
	private static IdeaRepository ideaRepository;
	public IdeaService(IdeaRepository ideaRepository){
		this.ideaRepository = ideaRepository;
	}
	public Idea findById(Long id) {
		return ideaRepository.findById(id).orElse(null);
	}
	public List<Idea> findAll() {
		return ideaRepository.findAll();
	}
	public void saveIdea(Idea idea) {
		ideaRepository.save(idea);
	}
	public void update(Idea idea) {
		ideaRepository.save(idea);
	}
	public void delete(Long id) {
		ideaRepository.deleteById(id);
	}
	public List<Idea> findAllByOrderByLikesAsc(){
		return ideaRepository.findAllByOrderByLikesAsc();
	}
	public List<Idea> findAllByOrderByLikesDesc(){
		return ideaRepository.findAllByOrderByLikesDesc();
	}
}
