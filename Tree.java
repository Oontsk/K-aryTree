import java.util.Comparator;

public abstract class Tree<E> {

    protected abstract class TreeNode {
        protected E data;
        protected int freq = 1;
        protected TreeNode parent;
        @SuppressWarnings("unchecked")
        protected TreeNode[] children;

        protected TreeNode(E data, TreeNode parent) {
            this.data = data;
            this.parent = parent;
            this.children = (TreeNode[]) new Object[numKids];
        }

        protected boolean isLeafNode() {
            for (int i = 0; i < numKids; ++i) {
                if (children[i] != null) {
                    return false;
                }
            }
            return true;
        }

        public String toString() {
            String res = "<" + data.toString() + ", fq: " +
                    ((Integer) freq).toString();
            String printRes = printTreeNode();
            if (!printRes.equals("")) {
                res += ", " + printRes;
            }
            res += ">\n";
            return res;
        }


        protected abstract TreeNode insert(E data);
        protected abstract TreeNode remove(E data);
        protected abstract boolean contains(E data);
        protected abstract String printTreeNode();
    }

    protected int numNodes = 0, numElements = 0, numKids;
    protected TreeNode root = null;
    protected Comparator<E> comp;

    protected Tree(int numKids, Comparator<E> comp) {
        this.numKids = numKids;
        this.comp = comp;
    }

    public int numNodes() {
        return numNodes;
    }

    public int numElements() {
        return numElements;
    }


    public boolean isEmpty() {
        return numNodes == 0;
    }

    protected int compare(E one, E two) {
        if (comp == null) {
            Comparable<E> o;
            try {
                o = (Comparable<E>) one;
            } catch (ClassCastException | NullPointerException e) {
                o = null;
            }

            if (o == null) {
                return 1;
            }
            return o.compareTo(two);
        }
        return comp.compare(one, two);
    }

    public String toString() {
        return "# Elements: " + ((Integer) numElements).toString() +
                ", # Nodes: " + ((Integer) numNodes).toString() +
                "\n" + printTree();
    }


    public abstract void insert(E data);
    public abstract void remove(E data);
    public abstract boolean contains(E data);
    protected abstract void deleteEntry(TreeNode guy);
    protected abstract String printTree();
}

