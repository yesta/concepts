package concept;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class LatticeConcept extends ConceptImp implements Concept, Serializable {

	private Set<Integer> my_parents;
	
	public LatticeConcept(int an_id, String a_name) {
		super(an_id, a_name);
	}

	public LatticeConcept(int an_id, String a_name, boolean the_isRoot) {
		super(an_id, a_name, the_isRoot);
	}
	
	public LatticeConcept(int an_id, String a_name, Concept the_superId) {
		super(an_id, a_name);
		addSuperConcept(the_superId.getId());
		the_superId.addChildConcept(an_id);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public Set<Integer> getSuperConcepts() {
		return my_parents;
	}

	@Override
	public void addSuperConcept(int the_concept) {
		if (my_parents == null) {
			my_parents = new HashSet<Integer>();
		}
		my_parents.add(the_concept);
	}
	
}
