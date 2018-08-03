import java.util.Comparator;
import java.util.Random;

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

    public BinarySearchTree(Comparator<E> comp) {
        super(2, comp);
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

    public boolean contains(E data) {
        BinarySearchTreeNode cur;
        try {
            cur = (BinarySearchTreeNode) root;
        } catch (ClassCastException e) {
            cur = null;
        }

        while (cur != null) {
            int res = super.compare(data, cur.data);
            if (res > 0) {
                cur = cur.right();
            } else if (res < 0) {
                cur = cur.left();
            } else {
                return true;
            }
        }
        return false;
    }


    public void insert(E data) {
        BinarySearchTreeNode cur;
        try {
            cur = (BinarySearchTreeNode) root;
        } catch (ClassCastException e) {
            cur = null;
        }

        while (cur != null) {
            int res = super.compare(data, cur.data);
            if (res > 0) {
                BinarySearchTreeNode cursRight = cur.right();
                if (cursRight != null) {
                    cur = cursRight;
                } else {
                    BinarySearchTreeNode newNode = new BinarySearchTreeNode(data, cur);
                    cur.setRight(newNode);
                    ++super.size;
                }
            } else if (res < 0) {
                BinarySearchTreeNode cursLeft = cur.left();
                if (cursLeft != null) {
                    cur = cursLeft;
                } else {
                    BinarySearchTreeNode newNode = new BinarySearchTreeNode(data, cur);
                    cur.setLeft(newNode);
                    ++super.size;
                }
            } else { //node already exists in the tree
               ++cur.freq;
            }
        }
    }

    public void remove(E data) {
        BinarySearchTreeNode cur;
        try {
            cur = (BinarySearchTreeNode) root;
        } catch (ClassCastException e) {
            cur = null;
        }

        while (cur != null) {
            int res = super.compare(data, cur.data);
            if (res > 0) {
                cur = cur.right();
            } else if (res < 0) {
                cur = cur.left();
            } else { //found element to remove
               deleteEntry(cur);
            }
        }
        //if here, no field equal to data
    }

    protected void deleteEntry(TreeNode guy) {
        --super.size;
        if (super.size == 0) {
            root = null;
            return;
        }

        while (!guy.isLeafNode()) {
            BinarySearchTreeNode guysRight, guysLeft;
            try {
                guysRight = (BinarySearchTreeNode) guy;
            } catch (ClassCastException | NullPointerException e) {
                guysRight = null;
            }
            try {
                guysLeft = (BinarySearchTreeNode) guy;
            } catch (ClassCastException | NullPointerException e) {
                guysLeft = null;
            }

            if (guysRight != null && guysLeft != null)     {
                //choose via RNG which sibling with which to rotate
                Random random = new Random();
                int leftOrRight = random.nextInt(2); //random number, either 0 or 1
                if (leftOrRight == 0) {
                    rotate(guysLeft);
                } else {
                    rotate(guysRight);
                }
            } else if (guysRight != null) {
                rotate(guysRight);
            } else  {
                rotate(guysLeft);
            }
        }
        //now guy is a leaf, so delete
        BinarySearchTreeNode guysParent;
        try {
            guysParent = (BinarySearchTreeNode) guy.parent;
            //will have a parent because root case already handled at top
        } catch (ClassCastException | NullPointerException e) {
            guysParent = null;
        }

        if (guysParent != null) { //this is where the delete happens
            if (guy == guysParent.left()) {
                guysParent.setLeft(null);
            } else {
                guysParent.setRight(null);
            }
        }
    }
}
