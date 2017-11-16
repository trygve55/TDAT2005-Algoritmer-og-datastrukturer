package com.trygve.oving9;

class RelationProperties {
    /*
     * Assuming that a two column array containing the relation and a one column          * array containing the set the relation is on is given in each method. 
     * No checks are performed.
     */

    public static boolean isReflexive(char[][] relation, char [] set){
		boolean[] isReflexive = new boolean[set.length];

		for (int i = 0;i < relation.length;i++) {
			if (relation[i][0] == relation[i][1]) {
				for (int j = 0; j < set.length;j++) {
					if (relation[i][0] == set[j]) {
						isReflexive[j] = true;
					}
				}
			}
		}

		for (int j = 0; j < isReflexive.length;j++) {
			if (!isReflexive[j]) return false;
		}

    	return true;
    }

    public static boolean isSymmetric(char[][] relation, char [] set){

        for (int i = 0;i < relation.length;i++) {
            if (relation[i][0] != relation[i][1]) {
                boolean isSymmetric = false;
                for (int j = 0; j < relation.length; j++) {
                    if (relation[i][0] == relation[j][1] && relation[i][1] == relation[j][0]) {
                        isSymmetric = true;
                    }
                }
                if (!isSymmetric) return false;
            }
        }

	    return true;
    }

    public static boolean isTransitive(char[][] relation, char [] set){

        boolean[][] graph = new boolean[set.length][set.length];

        for (int i = 0;i < relation.length;i++) {
            for (int j = 0;j < relation.length;j++) {
                if (relation[i][0] == relation[j][1]) {
                    int from = -1, to = -1;
                    for (int k = 0; k < set.length;k++) {
                        if (relation[i][0] == set[k]) from = k;
                        if (relation[i][1] == set[k]) to = k;
                    }
                    graph[from][to] = true;
                    break;
                }
            }
        }

        for (int i = 0;i < graph.length;i++) {
            for (int j = 0; j < graph.length;j++) {
                if (graph[i][j]) {
                    for (int k = 0; k < graph.length;k++) {
                        if (graph[j][k] && !graph[i][k]) {
                            System.out.println(i + " " + j + " " + k);
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public static boolean isAntiSymmetric(char[][] relation, char [] set){

        for (int i = 0;i < relation.length;i++) {
            for (int j = 0; j < relation.length;j++) {
                if (i != j && relation[i][0] == relation[j][1] && relation[i][1] == relation[j][0]) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean isEquivalenceRelation(char[][] relation, char [] set){
	    return (isReflexive(relation, set) && isSymmetric(relation, set) && isTransitive(relation, set));
    }

    public static boolean isPartialOrder(char[][] relation, char [] set){
        return (isReflexive(relation, set) && isAntiSymmetric(relation, set) && isTransitive(relation, set));
    }

    public static void main(String[] args) {
	char[] setA = {'a','x','r','m','2','0'};
	char[][] rel1 = {{'a','a'},{'r','a'},{'a','2'},{'x','x'},{'r','2'},{'r','r'},{'m','m'},{'2','r'},{'0','0'},{'a','r'},{'2','2'},{'2','a'}};
	char[][] rel2 = {{'a','x'},{'r','2'},{'0','0'},{'m','2'}};
	System.out.println("Rel1 is reflexive: " + isReflexive(rel1, setA));
	System.out.println("Rel2 is reflexive: " + isReflexive(rel2, setA));
	System.out.println("Rel1 is symmetric: " + isSymmetric(rel1, setA));
	System.out.println("Rel2 is symmetric: " + isSymmetric(rel2, setA));
	System.out.println("Rel1 is transitive: " + isTransitive(rel1, setA));
	System.out.println("Rel2 is transitive: " + isTransitive(rel2, setA));
	System.out.println("Rel1 is antisymmetric: " + isAntiSymmetric(rel1, setA));
	System.out.println("Rel2 is antisymmetric: " + isAntiSymmetric(rel2, setA));
	System.out.println("Rel1 is an equivalence relation: " + isEquivalenceRelation(rel1, setA));
	System.out.println("Rel2 is an equivalence relation: " + isEquivalenceRelation(rel2, setA));
	System.out.println("Rel1 is a partial order: " + isPartialOrder(rel1, setA));
	System.out.println("Rel2 is a partial order: " + isPartialOrder(rel2, setA));
	/* skal gi følgende utskrift:
	   Rel1 is reflexive: true
	   Rel2 is reflexive: false
	   Rel1 is symmetric: true
	   Rel2 is symmetric: false
	   Rel1 is transitive: true
	   Rel2 is transitive: true
	   Rel1 is antisymmetric: false
	   Rel2 is antisymmetric: true
	   Rel1 is an equivalence relation: true
	   Rel2 is an equivalence relation: false
	   Rel1 is a partial order: false
	   Rel2 is a partial order: false
	 */
    }


}
