package concept.hierarchical;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import concept.Concept;
import concept.ConceptUtil;
import concept.ConceptUtilImp;
import concept.LatticeConcept;

public class LatticeConceptUtilImp extends ConceptUtilImp implements
		ConceptUtil, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	public void addConcept(String a_concept, String the_super_concept) {
		int index = my_concepts.size();
		Concept super_con = null;
		if (the_super_concept !=null) {
			 super_con = findConcept(the_super_concept);
		}
		if (super_con == null) {
//			System.err.println("cannot find the super concept: "
//					+ the_super_concept);
			my_concepts.add(new LatticeConcept(index, a_concept, true));
		} else {
			my_concepts.add(new LatticeConcept(index, a_concept, super_con
					));
		}

	}

	@Override
	public double similarity(int a_concept, int the_other_concept) {
		if (a_concept == the_other_concept) {
			return 1;
		} else {
			// System.out.println("similarity between: "+a_concept+", "+b_concept);
			final int index_lsc = findLeastSuperConcept(a_concept, the_other_concept);
			if (index_lsc < 0) {
				System.out.println("no least super: "+a_concept + " and " + the_other_concept);
			}
			final int dist_a = distance(a_concept, index_lsc);
			final int dist_b = distance(the_other_concept, index_lsc);
			final int dist_lsc = distanceToRoot(index_lsc) + 1;
//			System.out.println("least super:" + index_lsc + " a: " + dist_a
//					+ " b: " + dist_b + " lsc: " + dist_lsc);
			return (double) 2 * dist_lsc / (dist_a + dist_b + 2 * dist_lsc);
		}
	}

	@Override
	public double similarity(List<Integer> the_concepts,
			List<Integer> the_other_concets) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * the distance to the root
	 * 
	 * @param the_concept_id
	 * @return
	 */
	public int distanceToRoot(final int the_concept) {
		if (my_concepts.get(the_concept).isRoot()) {
			return 0;
		} else {
			int min_dist = my_concepts.size();
			if (my_concepts.get(the_concept).getSuperConcepts() != null) {
				for (int sc : (Set<Integer>) my_concepts.get(the_concept).getSuperConcepts()) {
					if (distanceToRoot(sc) < min_dist) {
						min_dist = distanceToRoot(sc);
					}
				}
			}
			return min_dist + 1;
		}
	}

	public int shareCommon(final int a_concept, final int b_concept) {
		if (a_concept == b_concept) {
			return a_concept;
		} else if (my_concepts.get(a_concept).isRoot()) {
			return a_concept;
		} else if (my_concepts.get(b_concept).isRoot()) {
			return b_concept;
		} else if (isSuper(my_concepts.get(a_concept),
				my_concepts.get(b_concept))) {
			return b_concept;
		} else if (isSuper(my_concepts.get(b_concept),
				my_concepts.get(a_concept))) {
			return a_concept;
		} else {
			final Set<Integer> a_super = (Set<Integer>)my_concepts.get(a_concept)
					.getSuperConcepts();
			final Set<Integer> b_super = (Set<Integer>)my_concepts.get(b_concept)
					.getSuperConcepts();
			Set<Integer> intersection = interaction(a_super, b_super);
			if (intersection == null || intersection.isEmpty()) {
				return -1;
			} else {
				Iterator<Integer> iterator = intersection.iterator();
				int id = -1;
				while (iterator.hasNext()) {
					id = iterator.next();
					break;
				}
				return id;
			}
		}
	}

	private Set<Integer> interaction(final Set<Integer> a_set, final Set<Integer> b_set) {
		Set<Integer> result = new HashSet<Integer>();
		for(int i: a_set) {
			if (b_set.contains(i)) {
				result.add(i);
			}
		}
		return result;
	}
	public int findLeastSuperConcept(final int a_concept, final int b_concept) {
		int index = -1;
		if (a_concept == 0 || b_concept == 0) {
			return 0;
		} else if (a_concept == b_concept) {
			index = a_concept;
		} else if (my_concepts.get(a_concept).getSuperConcepts() != null
				&& isSuper(my_concepts.get(a_concept),
						my_concepts.get(b_concept))) {
			index = a_concept;
		} else if (my_concepts.get(b_concept).getSuperConcepts() != null
				&& isSuper(my_concepts.get(b_concept),
						my_concepts.get(a_concept))) {
			index = b_concept;
		} else {
//			System.out.println(" nothing is right a:"+my_concepts.get(a_concept).getSuperConcepts()+" and b: "+my_concepts.get(b_concept).getSuperConcepts());
			if (my_concepts.get(b_concept).getSuperConcepts() != null
					&& my_concepts.get(a_concept).getSuperConcepts() != null) {
				for (int as : (Set<Integer>)my_concepts.get(a_concept).getSuperConcepts()) {
					for (int bs :(Set<Integer>) my_concepts.get(b_concept)
							.getSuperConcepts()) {
						index = Math.max(Math.max(findLeastSuperConcept(a_concept, bs),findLeastSuperConcept(as, b_concept)), findLeastSuperConcept(as, bs));
						if (index >= 0) {
							return index;
						}
					}
				}
			}
		}
		return index;
	}
	/**
	 * is the first concept parent of the second concept
	 * @param one_concept
	 * @param the_other_concept
	 * @return
	 */
	public boolean isSuper(Concept one_concept, Concept the_other_concept) {
		if (one_concept.isSuper(the_other_concept.getId())) {
			return true;
		} else {
			boolean flag = false;
			if (one_concept != null
					&& the_other_concept.getSuperConcepts() != null) {
				for (int sc : (Set<Integer>) the_other_concept.getSuperConcepts()) {
					flag = flag
							| isSuper(one_concept, my_concepts.get(sc));
					if (flag)
						break;
				}
			}
			return flag;
		}
	}

	public int distance(final int the_concept, final int the_super_concept) {
		int dist = 0;
		if (the_concept == the_super_concept) {
			return dist;
		} else {
			int min_dist = my_concepts.size();
			if (my_concepts.get(the_concept).getSuperConcepts() != null) {
				for (int sc : (Set<Integer>)my_concepts.get(the_concept).getSuperConcepts()) {
					if (distance(sc, the_super_concept) < min_dist) {
						min_dist = distance(sc, the_super_concept);
					}
				}
			}
			return dist + min_dist + 1;
		}
	}

	@Override
	public void addConcept(String a_concept, String the_super_concept,
			String the_other_super_concept) {
		int index = my_concepts.size();
		Concept super_con1 = findConcept(the_super_concept);
		Concept super_con2 = findConcept(the_other_super_concept);
		Concept the_cp = new LatticeConcept(index, a_concept);
		if (super_con1 != null) {
			the_cp.addSuperConcept(super_con1.getId());
			super_con1.addChildConcept(index);
		} else {
			System.err.println("cannot find the super concept: "
					+ the_super_concept);
		}
		if (super_con2 != null) {
			the_cp.addSuperConcept(super_con2.getId());
			super_con1.addChildConcept(index);
		} else {
			System.err.println("cannot find the super concept: "
					+ the_other_super_concept);
		}
		my_concepts.add(the_cp);
	}

	@Override
	public boolean shareCommonParent(int one_concept, int the_other_concept) {
		boolean found = false;
		for (Concept n : my_concepts) {
			if (n.getChildConecpts() != null && !n.getChildConecpts().isEmpty()
					&& n.getChildConecpts().contains(one_concept)
					&& n.getChildConecpts().contains(the_other_concept)) {
				found = true;
				break;
			}
		}
		return found;
	}
}
