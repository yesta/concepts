package concept.hierarchical;

import java.io.Serializable;
import java.util.List;

import concept.Concept;
import concept.ConceptImp;
import concept.ConceptUtil;
import concept.ConceptUtilImp;
import concept.TreeConcept;

public class TreeConceptUtilImp extends ConceptUtilImp implements ConceptUtil,
		Serializable {

	private int height;

	public TreeConceptUtilImp() {
		super();
	}

	@Override
	public void addConcept(String a_conceptName, String a_superConceptName) {
		final int index = my_concepts.size();
		if (a_superConceptName == null) {
			my_concepts.add(new TreeConcept(index, a_conceptName, true));
		} else {
			int super_id = findConcept(a_superConceptName).getId();
			if (super_id >= 0) {
				Concept sc = new TreeConcept(index, a_conceptName);
				sc.addSuperConcept(super_id);
				my_concepts.get(super_id).addChildConcept(sc.getId());
				my_concepts.add(sc);
				int dist = distanceToRoot(super_id);
				if (dist + 1 > height) {
					height = dist + 1;
				}
			} else {
				System.err.println(a_superConceptName
						+ " No super concept found!");
			}
		}
	}

	@Override
	public boolean isSuper(Concept the_super, Concept the_sub) {
		boolean result = false;
		int temp_sub_id = the_sub.getId();
		while (temp_sub_id >= 0 && temp_sub_id < my_concepts.size()) {
			// System.out.println(temp_sub_id+"=="+the_super);
			if (temp_sub_id == the_super.getId()) {
				result = true;
				break;
			} else if (my_concepts.get(temp_sub_id).isRoot()) {
				break;
			}
			temp_sub_id = (Integer) my_concepts.get(temp_sub_id)
					.getSuperConcepts();
		}
		return result;
	}


	@Override
	public int distanceToRoot(int a_concept_id) {
		if (my_concepts.get(a_concept_id).isRoot()) {
			return 0;
		} else {
			int dist = 1 + distanceToRoot((Integer)my_concepts.get(a_concept_id)
					.getSuperConcepts());
			return dist;
		}
	}

	@Override
	public double similarity(List<Integer> the_concepts,
			List<Integer> the_other_concets) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int findLeastSuperConcept(final int a_concept, final int b_concept) {
		int result = -1;
		if (a_concept == b_concept) {
			result = a_concept;
		} else if (((Integer)my_concepts.get(a_concept).getSuperConcepts()).intValue() == b_concept) {
			result = b_concept;
		} else if (((Integer)my_concepts.get(b_concept).getSuperConcepts()).intValue() == a_concept) {
			result = a_concept;
		} else {
			result = findLeastSuperConcept((Integer)my_concepts.get(a_concept)
					.getSuperConcepts(),(Integer) my_concepts.get(b_concept)
					.getSuperConcepts());
		}
		if (result == -1) {
			System.out.println(a_concept + " : " + b_concept);
		}
		return result;
	}

	/**
	 * the distance between the concept and its super concept
	 * 
	 * @param the_concept
	 * @param the_super_concept
	 * @return
	 */
	public int distance(final int the_concept, final int the_super_concept) {
		int dist = 0;
		if (the_concept == the_super_concept) {
			return dist;
		} else {
			dist = 1 + distance((Integer) my_concepts.get(the_concept)
					.getSuperConcepts(), the_super_concept);
			return dist;
		}
	}

	@Override
	public double similarity(int a_concept, int the_other_concept) {
		if (a_concept == the_other_concept) {
			return 1;
		} else {
			// System.out.println("similarity between: "+a_concept+", "+b_concept);
			final int lsc = findLeastSuperConcept(a_concept, the_other_concept);
			final int dist_a = distance(a_concept, lsc) + 1;
			final int dist_b = distance(the_other_concept, lsc) + 1;
			final int dist_lsc = distanceToRoot(lsc) + 1;
			// System.out.println("least super:" + index_lsc + " a: " + dist_a
			// + " b: " + dist_b + " lsc: " + dist_lsc);
			return (double) 2 * dist_lsc / (dist_a + dist_b + 2 * dist_lsc);

		}
	}

	@Override
	public void addConcept(String a_concept, String the_super_concept,
			String the_other_super_concept) {
		addConcept(a_concept, the_super_concept);
	}

	@Override
	public boolean shareCommonParent(int one_concept, int the_other_concept) {
		// TODO Auto-generated method stub
		return false;
	}
}
