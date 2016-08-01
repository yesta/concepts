package concept;

import java.util.HashSet;
import java.util.Set;

import java.io.Serializable;

public class TreeConcept extends ConceptImp implements Concept, Serializable {

	private Set<Integer> my_children;
	
	private int my_superConcepts;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TreeConcept(int an_id, String a_name) {
		super(an_id, a_name);
	}
	
	public TreeConcept(int an_id, String a_name, boolean the_isRoot) {
		super(an_id, a_name, the_isRoot);
	}

	@Override
	public void addSuperConcept(int the_concept) {
		my_superConcepts = the_concept;
	}
	

	public int getSuperConcept() {
		return my_superConcepts;
	}

	@Override
	public Integer getSuperConcepts() {
		// TODO Auto-generated method stub
		return my_superConcepts;
	}
	
}
