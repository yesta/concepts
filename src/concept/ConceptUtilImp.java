package concept;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class ConceptUtilImp implements ConceptUtil, Serializable {

	private static final long serialVersionUID = 1L;

	protected List<Concept> my_concepts;

	public ConceptUtilImp() {
		my_concepts = new ArrayList<Concept>();
	}

	@Override
	public List<Concept> getConcepts() {
		return my_concepts;
	}

	@Override
	public Concept findConcept(int a_concept_id) {
		if (a_concept_id < 0 || a_concept_id >= my_concepts.size()) {
			return null;
		} else {
			return my_concepts.get(a_concept_id);
		}
	}

	@Override
	public Concept findConcept(String a_concept_name) {
		Concept result = null;
		for (Concept hc : my_concepts) {
			if (hc.getName().toLowerCase().trim()
					.equals(a_concept_name.toLowerCase().trim())) {
				result = hc;
				break;
			}
		}
		if (result == null) {

			result = findConceptWithPartialName(a_concept_name);
			if (result == null) {
				System.out.println(a_concept_name + " not found");
			}
		}
		if (result == null)
			System.out.println(a_concept_name);
		return result;
	}

	private Concept findConceptWithPartialName(final String the_concept_name) {
		for (Concept hc : my_concepts) {
			if (the_concept_name.toLowerCase().contains(
					hc.getName().toLowerCase().trim())) {
				return hc;
			}
		}
		return null;
	}

}
