package se.axesslab.argupedia.service;

import java.util.*;

import se.axesslab.argupedia.domain.Argument;
import se.axesslab.argupedia.domain.Proposition;
import se.axesslab.argupedia.repository.ArgumentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArgumentService {

	@Autowired 
	ArgumentRepository argumentRepository;

	private Map<String, Object> toD3Format(Collection<Argument> arguments) {
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> relationships = new ArrayList<>();
		int lastAddedNodeIndex = -1;
		Iterator<Argument> result = arguments.iterator();
		while (result.hasNext()) {
			
			//Add argument
			Argument argument = result.next();
			Map<String, Object> argumentNode = map("title", argument.getTitle(), "label", "argument");
			nodes.add(argumentNode);
			lastAddedNodeIndex++;
			int argumentIndex = nodes.indexOf(argumentNode);
			
			//Add supported proposition and link to argument
			Map<String, Object> supportedPropositionNode = map("title", argument.getSupportedProposition().getTitle(), "label", "proposition");
			int supportedPropositionIndex = nodes.indexOf(supportedPropositionNode);
			if (supportedPropositionIndex == -1) {
				nodes.add(supportedPropositionNode);
				lastAddedNodeIndex++;
				supportedPropositionIndex = lastAddedNodeIndex;
			}
			relationships.add(map("source", argumentIndex, "target", supportedPropositionIndex));
			
			//Add required propositions and link to argument
			for (Proposition proposition : argument.getRequiredPropositions()) {
				Map<String, Object> propositionNode = map("title", proposition.getTitle(), "label", "proposition");
				int requiredPropositionIndex = nodes.indexOf(propositionNode);
				if (requiredPropositionIndex == -1) {
					nodes.add(propositionNode);
					lastAddedNodeIndex++;
					requiredPropositionIndex = lastAddedNodeIndex;
				}
				relationships.add(map("source", requiredPropositionIndex, "target", argumentIndex));
			}
		}
		return map("nodes", nodes, "links", relationships);
	}

	private Map<String, Object> map(String key1, Object value1, String key2, Object value2) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put(key1, value1);
		result.put(key2, value2);
		return result;
	}

	@Transactional(readOnly = true)
	public Map<String, Object>  graph(int limit) {
		Collection<Argument> result = argumentRepository.graph(limit);
		return toD3Format(result);
	}
}
