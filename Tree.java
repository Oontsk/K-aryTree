public abstract class K-aryTree<E> {

    protected abstract class K-aryTreeNode {
        protected E data;
        protected K-aryTreeNode parent;
        protected K-aryTreeNode[] children;

        protected K-aryTreeNode(E data, K-aryTreeNode parent) {
            this.data = data;
            this.parent = parent;

        }


    }

    protected int size;
    protected K-a

}