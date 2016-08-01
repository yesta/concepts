package concept;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class ConceptImp implements Concept, Serializable {

	private int my_id;
	private String my_name;
	private boolean isRoot;
	private Set<Integer> my_children;

	public ConceptImp(final int an_id, final String a_name) {
		my_id = an_id;
		my_name = a_name;
		isRoot = false;
	}

	public ConceptImp(final int an_id, final String a_name,
			final boolean the_isRoot) {
		my_id = an_id;
		my_name = a_name;
		isRoot = the_isRoot;
	}

	public int getId() {
		return my_id;
	}

	public void setId(final int an_id) {
		my_id = an_id;
	}

	public String getName() {
		return my_name;
	}

	public void setName(final String a_name) {
		my_name = a_name;
	}

	public String print() {
		return my_id + " " + my_name;
	}

	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	@Override
	public void addChildConcept(int the_concept) {
		if (my_children == null) {
			my_children = new HashSet<Integer>();
		}
		my_children.add(the_concept);
	}

	@Override
	public Set<Integer> getChildConecpts() {
		return my_children;
	}

	@Override
	public void setChildConcepts(Set<Integer> the_concepts) {
		my_children = the_concepts;
	}

	public boolean isSuper(final int a_conceptId) {
		if (my_children != null && my_children.contains(a_conceptId)) {
			return true;
		} else {
			return false;
		}
	}
}
