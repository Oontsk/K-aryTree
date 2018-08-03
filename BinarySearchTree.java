public class BinarySearchTree<E> extends Tree<E> {

    protected class BinarySearchTreeNode extends TreeNode {

        protected BinarySearchTreeNode(E data, TreeNode parent) {
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

        public TreeNode remove(E data) {
            throw new UnsupportedOperationException();
        }

        public boolean contains(E data) {
            throw new UnsupportedOperationException();
        }

    }

    protected void rotate(BinarySearchTreeNode guy) {
        BinarySearchTreeNode par;
        try {
            par = (BinarySearchTreeNode) guy.parent;
        } catch (ClassCastException | NullPointerException e) {
            par = null;
        }

        if (par != null) {
            BinarySearchTreeNode parsLeft = par.left(),
                    parsRight = par.right();
            if (parsLeft != null && parsLeft == guy) {
                BinarySearchTreeNode guysRight = guy.right();
                par.setLeft(guysRight);
                if (guysRight != null) {
                    guysRight.parent = par;
                }
                guy.setRight(par);
            } else {
                BinarySearchTreeNode guysLeft = guy.left();
                par.setRight(guysLeft);
                if (guysLeft != null) {
                    guysLeft.parent = par;
                }
                guy.setLeft(par);
            }
            BinarySearchTreeNode giisan;
            try {
                giisan = (BinarySearchTreeNode) par.parent;
            } catch (ClassCastException | NullPointerException e) {
                giisan = null;
            }

            if (giisan != null) {
                BinarySearchTreeNode giisansLeft = giisan.left();
                if (giisansLeft != null && giisansLeft == par) {
                    giisan.setLeft(guy);
                } else {
                    giisan.setRight(guy);
                }
                guy.parent = giisan;
                par.parent = guy;
            }

            if (par == root) {
                root = guy;
            }
        }
    }

    public TreeNode insert(E data) {
        BinarySearchTreeNode cur;
        try {
            cur = (BinarySearchTreeNode) root;
        } catch (ClassCastException e) {
            cur = null;
        }

        if (cur != null) {
            int res = super.compare(data, cur.data);
            (if res > 0) {

            }

        }


    }


}
