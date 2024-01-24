package com.like.cooperation.board.adapter.out.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.like.cooperation.board.adapter.out.persistence.jpa.repository.BoardJpaRepository;
import com.like.cooperation.board.application.port.out.BoardCommandDbPort;
import com.like.cooperation.board.domain.Board;

@Repository
public class BoardDbAdapter implements BoardCommandDbPort {

	BoardJpaRepository repository;
	
	BoardDbAdapter(BoardJpaRepository repository) {
		this.repository = repository;
	}

	@Override
	public Optional<Board> select(Long id) {
		return this.repository.findById(id);
	}

	@Override
	public void save(Board entity) {				
		this.repository.save(entity);		
	}

	@Override
	public void delete(Long boardId) {
		this.repository.deleteById(boardId);		
	}

}
