
package se.axesslab.argupedia.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Argument {

	@GraphId
	private Long id;

	private String title;
	
	private String summary;
	
	@Relationship(type = "SUPPORTS", direction = Relationship.INCOMING)
	private Set<Proposition> requiredPropositions;
	
	@Relationship(type = "SUPPORTS", direction = Relationship.OUTGOING)
	private Proposition supportedProposition;

	private Argument() {
		// Empty constructor required as of Neo4j API 2.0.5
	};

	public Argument(String title, String summary) {
		this.title = title;
		this.summary = summary;
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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public Set<Proposition> getRequiredPropositions() {
		return requiredPropositions;
	}
	
	public Proposition getSupportedProposition() {
		return supportedProposition;
	}
	
	public void assumes(Proposition proposition) {
		if (requiredPropositions == null) {
			requiredPropositions = new HashSet<>();
		}
		requiredPropositions.add(proposition);
	}

	public String toString() {

		return this.title + "'s required propositions => "
				+ Optional.ofNullable(this.requiredPropositions).orElse(
						Collections.emptySet()).stream().map(
								requiredProposition -> requiredProposition.getTitle()).collect(Collectors.toList());
	}


}
