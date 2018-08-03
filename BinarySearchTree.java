public class BinarySearchTree<E> extends Tree<E> {

    protected class BinarySearchTreeNode extends TreeNode {

        protected BinarySearchTree(E data, TreeNode parent) {
            super(data, parent);
        }

        protected BinarySearchTreeNode left() {
            try {
                return (BinarySearchTreeNode) super.children[0];
            } catch (ClassCastException | NullPointerException e) {
                return null;
            }
        }

        protected void setLeft(BinarySearchTreeNode setTo) {
            super.children[0] = setTo;
        }

        protected BinarySearchTreeNode right() {
            try {
                return (BinarySearchTreeNode) super.children[1];
            } catch (ClassCastException | NullPointerException e) {
                return null;
            }
        }

        protected void setRight(BinarySearchTreeNode setTo) {
            super.children[1] = setTo;
        }

        public TreeNode insert(E data) {
           throw new UnsupportedOperationException();
        }

        public TreeNode delete(E data) {
            throw new UnsupportedOperationException();
        }

        public boolean contains(E data) {
            throw new UnsupportedOperationException();
        }

    }


}
