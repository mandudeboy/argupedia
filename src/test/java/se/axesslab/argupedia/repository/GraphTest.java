package se.axesslab.argupedia.repository;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import se.axesslab.argupedia.domain.Argument;
import se.axesslab.argupedia.domain.Proposition;

/**
 * @author Joel Holmberg
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class GraphTest {

	private static final String MOCK_SUMMARY = "Mock summary";
	private static final String MOCK_ARGUMENT = "Mock argument";
	private static final String CHANGE_FOR_THE_BETTER_TITLE = "Change for the better";
	private static final String CHANGE_FOR_THE_BETTER_SUMMARY = "We should do things that make things better, "
			+ "and happiness is better than unhappiness so we should do things that increase happiness";

	@Autowired
	private Session session;

	@Autowired
	private ArgumentRepository argumentRepository;

	@Autowired
	private PropositionRepository propositionRepository;

	public GraphTest() {
	}

	@Before
	public void setUp() {

		Argument argument0 = new Argument(MOCK_ARGUMENT, MOCK_SUMMARY);
		argumentRepository.save(argument0);
		
		Proposition fact1 = new Proposition("Happiness is better than unhappiness");
		Proposition fact2 = new Proposition("We should do things that make things better and avoid doing things that make things worse");
		fact1.supportedBy(argument0);
		
		propositionRepository.save(fact1);
		propositionRepository.save(fact2);

		Argument argument1 = new Argument(CHANGE_FOR_THE_BETTER_TITLE, CHANGE_FOR_THE_BETTER_SUMMARY);
		argument1.assumes(fact1);
		argument1.assumes(fact2);
		
		argumentRepository.save(argument1);

		
		Proposition proposition1 = new Proposition("We should do things that increase happiness");
		proposition1.supportedBy(argument1);

		propositionRepository.save(proposition1);
	}

	@After
	public void tearDown() {
		session.purgeDatabase();
	}

	@Test
	public void testFindByTitle() {
		String title = CHANGE_FOR_THE_BETTER_TITLE;
		Argument result = argumentRepository.findByTitle(title);
		assertNotNull(result);
		assertEquals(CHANGE_FOR_THE_BETTER_SUMMARY, result.getSummary());
	}

	@Test
	public void testFindByTitleContaining() {
		String title = "*better*";
		Collection<Argument> result = argumentRepository.findByTitleLike(title);
		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	public void testGraph() {
		Collection<Argument> graph = argumentRepository.graph(5);

		assertEquals(1, graph.size());

		Argument argument = graph.iterator().next();

		assertEquals(2, argument.getRequiredPropositions().size());

		assertEquals(CHANGE_FOR_THE_BETTER_TITLE, argument.getTitle());
		assertEquals(MOCK_ARGUMENT, argument.getRequiredPropositions().iterator().next().getSupportingArguments().iterator().next().getTitle());
	}
}
