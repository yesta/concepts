package concept;

import java.util.Set;

public interface Concept {

	//id 
	void setId(final int the_id);
	
	int getId();
	
	//name
	void setName(final String the_name);
	
	String getName();
	
	//super concept;
	Object getSuperConcepts();
	
	void addSuperConcept(final int the_concept);
	
//	void setSuperConcept(final Set<Integer> the_concepts);
	
	//root
	
	boolean isSuper(final int a_concept_id);
	
	boolean isRoot();
	
	void setRoot(final boolean isRoot);
	
	//children concept;
	
	void addChildConcept(final int the_concept);
	
	Set<Integer> getChildConecpts();
	
	void setChildConcepts(final Set<Integer> the_concepts);
	
	String print();
	
}
