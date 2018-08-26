import java.util.Comparator;

public abstract class Tree<E> {

    protected abstract class TreeNode {
        protected E data;
        protected int freq = 1;
        protected TreeNode parent;
        protected Object[] children; //annoying generics

        protected TreeNode(E data, TreeNode parent) {
            this.data = data;
            this.parent = parent;
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

        //some trees handle insert, remove, contains recursively
        protected abstract TreeNode insert(E data);
        protected abstract TreeNode remove(E data);
        protected abstract boolean contains(E data);
        //throws UnsupportedOperationException if handled iteratively
        
        protected abstract String printTreeNode();
    }

    protected int numNodes = 0, numElements = 0;

    protected TreeNode root = null;

    protected Comparator<E> comp;

    protected Tree(Comparator<E> comp) {
    	this.comp = comp;
    }

    public int getNumNodes() {
        return numNodes;
    }

    public int getNumElements() {
        return numElements;
    }

    public boolean isEmpty() {
        return numNodes == 0;
    }

    @SuppressWarnings("unchecked")
	protected int compare(E one, E two) {
        if (comp == null) {
            Comparable<E> o;
            try {
                o = (Comparable<E>) one;
            } catch (ClassCastException | NullPointerException e) {
                o = null;
            }
            return o == null ? 0 : o.compareTo(two);
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
    protected abstract String printTree();
}