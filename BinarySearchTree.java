import java.util.Comparator;
import java.util.Random;

public class BinarySearchTree<E> extends Tree<E> {

    protected class BinarySearchTreeNode extends TreeNode {

        protected BinarySearchTreeNode(E data, TreeNode parent) {
            super(data, parent);
            super.children = new Object[2];
        }

        @SuppressWarnings("unchecked")
		protected BinarySearchTreeNode left() {
            try {
                return (BinarySearchTreeNode) children[0];
            } catch (ClassCastException | NullPointerException e) {
                return null;
            }
        }

        protected void setLeft(BinarySearchTreeNode setTo) {
        	children[0] = setTo;
        }

        @SuppressWarnings("unchecked")
		protected BinarySearchTreeNode right() {
            try {
                return (BinarySearchTreeNode) children[1];
            } catch (ClassCastException | NullPointerException e) {
                return null;
            }
        }

        protected void setRight(BinarySearchTreeNode setTo) {
            children[1] = setTo;
        }
        /*
        * Insert, remove, and contains handled
        * iteratively in this implementation of BST
        * */
        public TreeNode insert(E data) {
           throw new UnsupportedOperationException();
        }
        public TreeNode remove(E data) {
            throw new UnsupportedOperationException();
        }
        public boolean contains(E data) {
            throw new UnsupportedOperationException();
        }

        protected String printTreeNode() {
            return ""; //formatting handled in node superclass for empty string return
        }
    }
    
    public BinarySearchTree(Comparator<E> comp) {
        super(comp);
    }

    //shared rotation function for anything that extends BinarySearchTree
    @SuppressWarnings("unchecked")
	protected void rotate(BinarySearchTreeNode guy) {
        BinarySearchTreeNode par;
        try {
            par = (BinarySearchTreeNode) guy.parent;
        } catch (ClassCastException | NullPointerException e) {
            par = null;
        }

        if (par != null) {
            BinarySearchTreeNode parsLeft = par.left();
            if (parsLeft != null && parsLeft == guy) {
                BinarySearchTreeNode guysRight = guy.right();
                par.setLeft(guysRight);
                if (guysRight != null) {
                    guysRight.parent = par;
                }
                guy.setRight(par);
            } else { //parsRight == guy
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
            }

            par.parent = guy;
            guy.parent = giisan;

            if (par == root) {
                root = guy;
            }
        }
    }

    @SuppressWarnings("unchecked")
	public boolean contains(E data) {
        BinarySearchTreeNode cur;
        try {
            cur = (BinarySearchTreeNode) root;
        } catch (ClassCastException | NullPointerException e) {
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

    @SuppressWarnings("unchecked")
	public void insert(E data) {
        BinarySearchTreeNode cur;
        try {
            cur = (BinarySearchTreeNode) root;
        } catch (ClassCastException | NullPointerException e) {
            cur = null;
        }

        if (root == null) {
            root = new BinarySearchTreeNode(data, null);
            ++super.numNodes;
            ++super.numElements;
            return;
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
                    ++super.numNodes;
                    ++super.numElements;
                    return;
                }
            } else if (res < 0) {
                BinarySearchTreeNode cursLeft = cur.left();
                if (cursLeft != null) {
                    cur = cursLeft;
                } else {
                    BinarySearchTreeNode newNode = new BinarySearchTreeNode(data, cur);
                    cur.setLeft(newNode);
                    ++super.numNodes;
                    ++super.numElements;
                    return;
                }
            } else { //node already exists in the tree
               ++cur.freq;
               ++super.numElements;
               return;
            }
        }
    }

    @SuppressWarnings("unchecked")
	public void remove(E data) {
        BinarySearchTreeNode cur;
        try {
            cur = (BinarySearchTreeNode) root;
        } catch (ClassCastException | NullPointerException e) {
            cur = null;
        }

        while (cur != null) {
            int res = super.compare(data, cur.data);
            if (res > 0) {
                cur = cur.right();
            } else if (res < 0) {
                cur = cur.left();
            } else { //found element to remove, remove one of it
                --cur.freq;
                --super.numElements;
                if (cur.freq == 0) {
                    deleteEntry(cur);
                    return;
                }
            }
        }
        //if here, no field equal to data
    }

    @SuppressWarnings("unchecked")
	protected void deleteEntry(BinarySearchTreeNode guy) {
        --super.numNodes;
        if (super.numNodes == 0) {
            root = null;
            return;
        }
        
        BinarySearchTreeNode guysRight = guy.right(), guysLeft = guy.left();
        
        while (!(guysRight == null && guysLeft == null)) {
            if (guysRight != null && guysLeft != null)  {
                //choose via RNG which sibling with which to rotate
                Random random = new Random();
                switch (random.nextInt(2)) { //Random number 0 or 1
                case 0:
                	rotate(guysLeft);
                	break;
                default:
                	rotate(guysRight);
                }
            } else if (guysRight != null) {
                rotate(guysRight);
            } else  {
                rotate(guysLeft);
            }
            guysRight = guy.right();
            guysLeft = guy.left();
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

    @SuppressWarnings("unchecked")
	protected String printTree() {
        try {
        	return printTreeHelper((BinarySearchTreeNode) root, 0);
        } catch (ClassCastException | NullPointerException e) {
        	return "";
        }
    }

    protected String printTreeHelper(BinarySearchTreeNode cur, int depth) {
        String res = "";

        for (int i = 0; i < depth; ++i) {
           res += "  ";
        }
        
        if (cur == null) {
        	res += "<empty>\n";
        	return res;
        }

        res += cur.toString();
        
        BinarySearchTreeNode cursLeft = cur.left(), cursRight = cur.right();
        res += printTreeHelper(cursLeft, depth + 1);
        res += printTreeHelper(cursRight, depth + 1);
        
        return res;
    }
}