
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
public class Proposition {

	@GraphId 
	private Long id;

	private String title;

	@Relationship(type = "SUPPORTS", direction = Relationship.INCOMING)
	private Set<Argument> supportingArguments;
	
	@Relationship(type = "OPPOSES", direction = Relationship.INCOMING)
	private Set<Argument> opposingArguments;
	
	@Relationship(type = "ARGUES", direction = Relationship.INCOMING)
	private Set<Argument> arguments;
	
	private Proposition() {
		// Empty constructor required as of Neo4j API 2.0.5
	};

	public Proposition(String title) {
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

	public Set<Argument> getSupportingArguments() {
		return supportingArguments;
	}

	public Set<Argument> getOpposingArguments() {
		return opposingArguments;
	}

	public Set<Argument> getArguments() {
		return arguments;
	}

	public void supportedBy(Argument argument) {
		if (supportingArguments == null) {
			supportingArguments = new HashSet<>();
		}
		if (arguments == null) {
			arguments = new HashSet<>();
		}
		supportingArguments.add(argument);
		arguments.add(argument);
	}
	
	public void opposedBy(Argument argument) {
		if (opposingArguments == null) {
			opposingArguments = new HashSet<>();
		}
		if (arguments == null) {
			arguments = new HashSet<>();
		}
		opposingArguments.add(argument);
		arguments.add(argument);
	}

	public String toString() {

		return this.title + "'s arguments => "
				+ Optional.ofNullable(this.arguments).orElse(
						Collections.emptySet()).stream().map(
								argument -> argument.getTitle()).collect(Collectors.toList());
	}

}
