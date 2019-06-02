package se.axesslab.argupedia.repository;

import java.util.Collection;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import se.axesslab.argupedia.domain.Argument;

@RepositoryRestResource
public interface ArgumentRepository extends Neo4jRepository<Argument, Long> {

	Argument findByTitle(@Param("title") String title);
	
	Collection<Argument> findByTitleLike(@Param("title") String title);
	
	@Query("MATCH (p1:Proposition)<-[r1:SUPPORTS]-(a:Argument)<-[r:SUPPORTS]-(p:Proposition) RETURN p1,r1,a,r,p LIMIT {limit}")
	Collection<Argument> graph(@Param("limit") int limit);
}
