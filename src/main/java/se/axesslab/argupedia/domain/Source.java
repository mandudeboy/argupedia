
package se.axesslab.argupedia.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Source {

	@GraphId 
	private Long id;

	private String title;

	private Source() {
		// Empty constructor required as of Neo4j API 2.0.5
	};

	public Source(String title) {
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String toString() {
		return this.title;
	}
}
