package concept;

import java.util.List;

/**
 * 
 * @author juanye
 *
 * @param <T> type of concepts
 */
public interface ConceptUtil {

	List<Concept> getConcepts();
	/**
	 * check if the first concept is super to the second concept
	 * @param the_super
	 * @param the_sub
	 * @return
	 */
	boolean isSuper(Concept the_super, Concept the_sub);
	/**
	 * check if the first concept is sub concept to the second concept
	 * @param the_sub
	 * @param the_super
	 * @return
	 */
//	boolean isChildOf(int the_sub, int the_super);
	
	/**
	 * add the first concept into the list
	 * the first concept is a sub concept of the second concept
	 * @param a_concept
	 * @param the_super_concept
	 */
	void addConcept(String a_concept, String the_super_concept);
	
	void addConcept(String a_concept, String the_super_concept, String the_other_super_concept);
	
	int distanceToRoot(int a_concept_id);
	/**
	 * similarity measure between two concepts
	 * @param a_concept
	 * @param the_other_concept
	 * @method which method to use: "wu", "lec"
	 * @return
	 */
	double similarity(int a_concept, int the_other_concept);
	/**
	 * find the least super concept for the given two concepts
	 * @param a_concept
	 * @param the_other_concept
	 * @return
	 */
//	int findLeastSuperConcept(int a_concept, int the_other_concept);
	/**
	 * find the concept using the given concept id
	 * @param a_concept_id
	 * @return
	 */
	Concept findConcept(int a_concept_id);
	/**
	 * find the concept using the given concept name
	 * @param a_concept_name
	 * @return
	 */
	Concept findConcept(String a_concept_name);

//	int getHeight();
	/**
	 * get similarity between two lists of concepts
	 */
	double similarity(List<Integer> the_concepts, List<Integer> the_other_concets);
	
	boolean shareCommonParent(final int one_concept, final int the_other_concept);
}
