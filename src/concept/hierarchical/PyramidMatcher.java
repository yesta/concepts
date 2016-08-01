package concept.hierarchical;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import concept.Concept;
import concept.ConceptUtil;
import concept.ConceptUtilImp;

public class PyramidMatcher implements Serializable {
	private ConceptUtil my_concepts;

	private Concept my_root;

	public PyramidMatcher(final ConceptUtil the_nodes) {
		my_concepts = the_nodes;
		my_root = the_nodes.findConcept(0);
	}

	public PyramidMatcher(final ConceptUtil the_nodes, final Concept the_root) {
		my_concepts = the_nodes;
		my_root = the_root;
	}

	public double similarity(List<Integer> the_concepts,
			List<Integer> the_other_concets) {
		// int nodeId = findFinestNode(the_other_concets);
		// return getMatchDegree(the_concepts, the_other_concets, nodeId);
//		return getMatchDegreeSimilarity(the_concepts, the_other_concets);
		// similarity match
		 return getMatchDegreeOnSimilarity(the_concepts, the_other_concets);

		// original pmk match
//		 return getMatchDegreeEqual(the_concepts, the_other_concets);

		// return getMatchDegreePenalised(the_concepts, the_other_concets);
	}

	// private double simpleMatch(List<Integer> the_concepts,
	// List<Integer> the_other_concets) {
	//
	// }
	/**
	 * calculate how many nodes in the given set are the child of the given top
	 * node
	 * 
	 * @param the_top_node
	 * @param a_set
	 * @return
	 */
	private int histgrams(final int the_top_node, final List<Integer> a_set) {
		int result = 0;
		for (int the_id : a_set) {
			if (the_id == the_top_node || isChildOf(the_id, the_top_node)) {
				// if (the_id == the_top_no÷de) {
				result++;
			}
		}
		return result;
	}

	/**
	 * get the match degree of two sets.
	 * 
	 * @param a_set
	 *            : highlevel concepts
	 * @param b_set
	 *            lower level concepts
	 * @return
	 */
	public double getMatchDegreeEqual(final List<Integer> a_set,
			final List<Integer> b_set) {
		double[] result = new double[my_concepts.getConcepts().size()];
		for (Concept n : my_concepts.getConcepts()) {
			int the_current_depth = getDepth(n.getId());
			double factor = 1 / Math.pow(2, the_current_depth);
			// double factor = Math.pow(2, the_current_depth);
			result[n.getId()] = factor
					* Math.min(histgrams(n.getId(), a_set),
							histgrams(n.getId(), b_set));
			// System.out.println("at node "+n.getId()+": "+histgrams(n.getId(),
			// a_set)+", "+histgrams(n.getId(), b_set)+": "+result[n.getId()]);
		}
		double sum = 0;
		for (double md : result) {
			sum += md;
		}
		return sum;
	}

	private double similarityOnSet(final Concept n, final List<Integer> a_set) {
		double result = 0;
		for (int i : a_set) {
			if (n.getId() == i){
				result +=1;
			} else if (my_concepts.isSuper(my_concepts.findConcept(i), n)) {
				result += (double) my_concepts.similarity(n.getId(), i);
			}
		}
//		System.out.println("similarity on "+n.getId()+" and "+a_set+" : "+result);
		return result;
	}

	/**
	 * 
	 * @param a_set
	 * @param b_set
	 * @return
	 */
	public double getMatchDegreeSimilarity(final List<Integer> a_set,
			final List<Integer> b_set) {
		double[] result = new double[my_concepts.getConcepts().size()];
		for (Concept n : my_concepts.getConcepts()) {
			int the_current_depth = getDepth(n.getId());
			double factor = 1/ Math.pow(2, the_current_depth);
			// double factor = Math.pow(2, the_current_depth);
			result[n.getId()] = factor
					* Math.min(similarityOnSet(n, a_set),
							similarityOnSet(n, b_set));
//			System.out.println("penalised: "+result[n.getId()]);
			// System.out.println("at node "+n.getId()+": "+histgrams(n.getId(),
			// a_set)+", "+histgrams(n.getId(), b_set)+": "+result[n.getId()]);
		}
		double sum = 0;
		for (double md : result) {
			sum += md;
		}
		return sum;
	}

	/**
	 * 
	 * @param a_set
	 *            : high level
	 * @param b_set
	 *            : low level
	 * @return
	 */
	public double getMatchDegree(final List<Integer> a_set,
			final List<Integer> b_set) {
		// System.out.print("\n size: "+a_set.size()+", "+b_set.size());
		double md = 0;
		for (int hi : a_set) {
			for (int li : b_set) {
				if (hi == li) {
					md += 1;
				} else if (my_concepts.isSuper(my_concepts.findConcept(hi),
						my_concepts.findConcept(li))) {
					md += (double) 1
							/ Math.pow(2, Math.abs(getDepth(li) - getDepth(hi)));
					// System.out.print(" "+md);
				}
			}
		}
		md = (double) md / (a_set.size() * b_set.size());
		return md;
	}

	public double getMatchDegreeOnSimilarity(final List<Integer> a_set,
			final List<Integer> b_set) {
		// System.out.print("\n size: "+a_set.size()+", "+b_set.size());
		double md = 0;
		for (int li : b_set) {
			double max = 0;
			for (int hi : a_set) {
				if (hi == li) {
					max = 1;
				} else {
					double sim = (double) my_concepts.similarity(hi, li);
					if (sim > max) {
						max = sim;
					}
					// / Math.pow(2, Math.abs(getDepth(li) - getDepth(hi)));
					// System.out.print(" "+md);
				}
			}
			md += max;
		}
		md = (double) md / (b_set.size());
		return md;
	}

	/**
	 * penalised on unmatched
	 * 
	 * @param a_set
	 * @param b_set
	 * @return
	 */
	public double getMatchDegreePenalised(final List<Integer> a_set,
			final List<Integer> b_set) {
		double md = 0;
		int count = 0;
		for (int hi : a_set) {
			for (int li : b_set) {
				if (hi == li) {
					count++;
					md += 1;
				} else if (my_concepts.isSuper(my_concepts.findConcept(hi),
						my_concepts.findConcept(li))) {
					count++;
					md += (double) 1
							/ Math.pow(2, Math.abs(getDepth(li) - getDepth(hi)));
				}
			}
		}
		md = md * count / b_set.size();
		return md;

	}

	public double getMatchDegree(final List<Integer> a_set,
			final List<Integer> b_set, final int nodeId) {
		double md = getMatchDegreeEqual(a_set, b_set);
		// System.out.println(a_set + ", " + b_set + " : " + md
		// + " with starting node: " + nodeId);
		// int height = getDepth(nodeId);
		// System.out.println("update: " + height + ", " + md
		// * Math.pow(2, height));
		return md
				* Math.pow(2, Math.pow(
						my_concepts.similarity(nodeId, a_set.get(0)),
						a_set.size()));
	}

	private int findFinestNode(final List<Integer> a_list) {
		int nodeId = -1;
		int depth = Integer.MAX_VALUE;
		for (int nId : a_list) {
			int d = getDepth(nId);
			if (d < depth) {
				depth = d;
				nodeId = nId;
			}
		}
		return nodeId;
	}

	/**
	 * evaluate if the child node is a child of the parent node
	 * 
	 * @param the_child
	 * @param the_parent
	 * @return
	 */
	public boolean isChildOf(final int the_child, final int the_parent) {
		// boolean result = false;
		// if (!(the_child == the_parent
		// || my_concepts.getConcepts().get(the_parent).getChildConecpts() ==
		// null || my_concepts
		// .getConcepts().get(the_parent).getChildConecpts().isEmpty())) {
		// if (my_concepts.getConcepts().get(the_parent).getChildConecpts()
		// .contains(the_child)) {
		// result = true;
		// } else {
		// for (int the_child_id : my_concepts.getConcepts()
		// .get(the_parent).getChildConecpts()) {
		// result = isChildOf(the_child, the_child_id);
		// if (result) {
		// break;
		// }
		// }
		// }
		// }
		// return result;
		return my_concepts.isSuper(my_concepts.findConcept(the_parent),
				my_concepts.findConcept(the_child));
	}

	/**
	 * the depth from the current node to the leaf node
	 * 
	 * @param the_currentNode
	 * @return
	 */
	public int getDepth(final int the_currentNode) {
		int depth = 0;
		if (my_concepts.getConcepts().get(the_currentNode).getChildConecpts() != null
				&& !my_concepts.getConcepts().get(the_currentNode)
						.getChildConecpts().isEmpty()) {
			int max = 0;
			for (int the_child_id : my_concepts.getConcepts()
					.get(the_currentNode).getChildConecpts()) {
				int the_current_depth = getDepth(the_child_id);
				if (the_current_depth > max) {
					max = the_current_depth;
				}
			}
			depth = max + 1;
		}
		return depth;
	}

}
