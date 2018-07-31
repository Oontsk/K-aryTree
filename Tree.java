public abstract class Tree<E> {

    protected abstract class TreeNode {
        protected E data;
        protected int freq;
        protected TreeNode parent;
        @SuppressWarnings("unchecked")
        protected TreeNode[] children = (TreeNode[]) new Object[numKids];

        protected TreeNode(E data, TreeNode parent) {
            this.data = data;
            this.parent = parent;
            this.freq = 1;
        }

        protected abstract TreeNode insert(E data);
        protected abstract TreeNode remove(E data);
        protected abstract boolean contains(E data);
    }

    protected int size, numKids;
    protected TreeNode root;

    protected Tree(int numKids) {
        this.size = 0;
        this.numKids = numKids;
    }

    public int size() {
        return size;
    }

    public abstract void insert(E data);
    public abstract void remove(E data);
    public abstract boolean contains(E data);

}