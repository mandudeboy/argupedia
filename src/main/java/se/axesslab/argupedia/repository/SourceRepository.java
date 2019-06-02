package se.axesslab.argupedia.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import se.axesslab.argupedia.domain.Source;

@RepositoryRestResource
public interface SourceRepository extends Neo4jRepository<Source, Long> {

	Source findByTitle(@Param("title") String title);
	
}
