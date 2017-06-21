import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Your implementation of an AVL Tree.
 *
 * @author Henry Leung
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> implements AVLInterface<T> {

    // Do not make any new instance variables.
    private AVLNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty AVL tree.
     */
    public AVL() {
    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("can't add null data");
        }
        for (T item : data) {
            if (item == null) {
                throw new IllegalArgumentException("can't add null data");
            }
            add(item);
        }
    }


    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        } else if (root == null) {
            root = new AVLNode(data);
            size++;
            root.setHeight(0);
            root.setBalanceFactor(0);
        } else {
            if (!(root.getData().equals(data))) {
                add(data, root);
                size++;
            }
        }
    }


    /**
     * Recursive method to add a node to an AVL.
     * If the value is already in the tree,
     * method does not add it to the tree.
     *
     * @param data the data to be added
     * @param node the node for traversal
     */
    private void add(T data, AVLNode<T> node) {
        if (data.compareTo(node.getData()) < 0) {
            if (node.getLeft() == null) {
                node.setLeft(new AVLNode(data));
                node.getLeft().setHeight(0);
                node.getLeft().setBalanceFactor(0);
                node.setHeight(height(node));
                node.setBalanceFactor(bFactor(node));
            } else {
                add(data, node.getLeft());
                node.setHeight(height(node));
                node.setBalanceFactor(bFactor(node));
            }
        } else if (data.compareTo(node.getData()) > 0) {
            if (node.getRight() == null) {
                node.setRight(new AVLNode(data));
                node.getRight().setHeight(0);
                node.getRight().setBalanceFactor(0);
                node.setHeight(height(node));
                node.setBalanceFactor(bFactor(node));
            } else {
                add(data, node.getRight());
                node.setHeight(height(node));
                node.setBalanceFactor(bFactor(node));
            }
        }
        balanceNode(node);
    }


    /**
     * Fixes an AVL tree and performs the appropriate rotations
     * based on balance factor.
     *
     * @param node the node we need to rotate about
     */
    private void balanceNode(AVLNode<T> node) {
        int balanceFactor = node.getBalanceFactor();
        if (balanceFactor < -1) {
            if (node.getRight().getBalanceFactor() == 1) {
                rightRotate(node.getRight());
                leftRotate(node);
            } else {
                leftRotate(node);
            }
        } else if (balanceFactor > 1) {
            if (node.getLeft().getBalanceFactor() == -1) {
                leftRotate(node.getLeft());
                rightRotate(node);
            } else {
                rightRotate(node);
            }
        }
    }

    /**
     * Performs a left rotation on the node.
     * Updates height and BF accordingly.
     *
     * @param node the node we are rotating about
     */
    private void leftRotate(AVLNode<T> node) {
        AVLNode<T> rightSubtree = node.getRight().getRight();
        AVLNode<T> leftSubtree = node.getRight().getLeft();
        T prev = node.getData();
        AVLNode<T> prevLeft = node.getLeft();
        AVLNode<T> prevRight = node.getRight();

        node.setLeft(prevRight);
        node.setRight(rightSubtree);
        prevRight.setRight(leftSubtree);
        prevRight.setLeft(prevLeft);
        node.setData(prevRight.getData());
        prevRight.setData(prev);

        node.setHeight(height(node));
        node.setBalanceFactor(bFactor(node));
        prevRight.setHeight(height(prevRight));
        prevRight.setBalanceFactor(bFactor(prevRight));
    }

    /**
     * Performs a right rotation about the node.
     * Updates height and BF accordingly.
     * @param node the node we are rotating about
     */
    private void rightRotate(AVLNode<T> node) {
        AVLNode<T> rightSubtree = node.getLeft().getRight();
        AVLNode<T> leftSubtree = node.getLeft().getLeft();
        T prev = node.getData();
        AVLNode<T> prevLeft = node.getLeft();
        AVLNode<T> prevRight = node.getRight();

        node.setLeft(leftSubtree);
        node.setRight(prevLeft);
        prevLeft.setRight(prevRight);
        prevLeft.setLeft(rightSubtree);
        node.setData(prevLeft.getData());
        prevLeft.setData(prev);

        node.setHeight(height(node));
        node.setBalanceFactor(bFactor(node));
        prevLeft.setHeight(height(prevLeft));
        prevLeft.setBalanceFactor(bFactor(prevLeft));
    }


    /**
     * Finds balance factor of a node.
     *
     * @param node the node we calculate BF
     * @return balance factor of the node
     */
    private int bFactor(AVLNode<T> node) {
        int lHeight = height(node.getLeft());
        int rHeight = height(node.getRight());
        return (lHeight - rHeight);
    }

    /**
     * rebalances a node
     * @param curr current node we're on
     * @return the fixed node
     */
    private AVLNode<T> fixBalance(AVLNode<T> curr) {
        int leftBalanceFactor = 0;
        int rightBalanceFactor = 0;
        if (curr.getLeft() == null) {
            leftBalanceFactor = 0;
        } else {
            leftBalanceFactor = curr.getLeft().getBalanceFactor();
        }
        if (curr.getRight() == null) {
            rightBalanceFactor = 0;
        } else {
            rightBalanceFactor = curr.getRight().getBalanceFactor();
        }
        // left heavy case
        if (curr.getBalanceFactor() > 1) {
            if (leftBalanceFactor >= 0) {
                curr = fixRight(curr);
            } else {
                curr.setLeft(fixLeft(curr.getLeft()));
                curr = fixRight(curr);
            }
            //right heavy case
        } else if (curr.getBalanceFactor() < -1) {
            if (rightBalanceFactor <= 0) {
                curr = fixLeft(curr);
            } else {
                curr.setRight(fixRight(curr.getRight()));
                curr = fixLeft(curr);
            }
        }
        return curr;
    }

    /**
     * rotates with the left child node
     * @param curr current node we're on
     * @return fixed node
     */
    private AVLNode<T> fixLeft(AVLNode<T> curr) {
        AVLNode<T> right = curr.getRight();
        curr.setRight(right.getLeft());
        right.setLeft(curr);
        fixHBF(curr);
        fixHBF(right);
        return right;
    }

    /**
     * rotates with the right child node
     * @param curr current node we're on
     * @return fixed node
     */
    private AVLNode<T> fixRight(AVLNode<T> curr) {
        AVLNode<T> left = curr.getLeft();
        curr.setLeft(left.getRight());
        left.setRight(curr);
        fixHBF(curr);
        fixHBF(left);
        return left;
    }

    /**
     * actively fixes a node's height and BF
     * @param curr node we fix height and BF for
     */
    private void fixHBF(AVLNode<T> curr) {
        int leftHeight = 0;
        int rightHeight = 0;
        if (curr.getRight() == null) {
            rightHeight = -1;
        } else {
            rightHeight = curr.getRight().getHeight();
        }
        if (curr.getLeft() == null) {
            leftHeight = -1;
        } else {
            leftHeight = curr.getLeft().getHeight();
        }
        curr.setHeight(Math.max(leftHeight, rightHeight) + 1);
        curr.setBalanceFactor(leftHeight - rightHeight);
    }


    //use empty node to keep track of data
    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        }
        if (root == null) {
            throw new NoSuchElementException("Data not found");
        }
        AVLNode<T> storeData = new AVLNode<T>(root.getData());
        root = remove(data, root, storeData);
        return storeData.getData();
    }

    /**
     * recursive helper funtion for removing
     * @param data data we find
     * @param curr current node
     * @param storeData empty node to store what we removed
     * @return Node we removed
     */
    private AVLNode<T> remove(T data, AVLNode<T> curr, AVLNode<T> storeData) {
        if (curr == null) {
            throw new NoSuchElementException("Can't find the data.");
        }
        //traverse the tree and find data
        if (curr.getData().compareTo(data) < 0) {
            curr.setRight(remove(data, curr.getRight(), storeData));
        } else if (curr.getData().compareTo(data) > 0) {
            curr.setLeft(remove(data, curr.getLeft(), storeData));
        } else if (curr.getLeft() == null) {
            storeData.setData(curr.getData());
            size--;
            return curr.getRight();
        } else if (curr.getRight() == null) {
            storeData.setData(curr.getData());
            size--;
            return curr.getLeft();
        } else {
            storeData.setData(curr.getData());
            curr.setRight(removeMinimum(curr.getRight(), curr));
            size--;
        }
        fixHBF(curr);
        if ((curr.getBalanceFactor() > 1) || (curr.getBalanceFactor() < 1)) {
            curr = fixBalance(curr);
        }
        return curr;
    }

    /**
     * finds the node containing the minimum of the tree.
     * @param curr current node we're on
     * @param min minimum element
     * @return Node containing the minimum element in the tree
     */
    private AVLNode<T> removeMinimum(AVLNode<T> curr, AVLNode<T> min) {
        if (curr.getLeft() == null) {
            min.setData(curr.getData());
            return curr.getRight();
        }
        curr.setLeft(removeMinimum(curr.getLeft(), min));
        fixHBF(curr);
        return curr;
    }

    /**
     *  Gets the successor of a node.
     * @param node the node we need to find the successor
     * @return succeessor node
     */
    private AVLNode<T> successor(AVLNode<T> node) {
        if (node.getLeft() == null) {
            return node;
        }
        return successor(node.getLeft());
    }


    @Override
    public List<T> findPathBetween(T start, T end) {
        if ((start == null) || (end == null)) {
            throw new IllegalArgumentException("Cant have null start/end/");
        }
        List<T> pathList = new LinkedList<T>();
        findPathBetween(root, start, end, pathList);
        return pathList;
    }

    /**
     * Recursive method to anneal root-start and start-end
     * resulting in the complete path between any two points.
     * @param curr current node
     * @param start starting node
     * @param end ending node
     * @param pathList list containing the path traversed
     */
    private void findPathBetween(AVLNode<T> curr, T start,
                                 T end, List<T> pathList) {
        if (curr == null) {
            throw new NoSuchElementException("Can't find data.");
        }
        T data = curr.getData();

        if (data.compareTo(start) == 0) {
            startToEnd(curr, end, pathList);
        } else if (data.compareTo(end) == 0) {
            rootToStart(curr, start, pathList);
        } else if ((data.compareTo(start) > 0) && (data.compareTo(end) > 0)) {
            findPathBetween(curr.getLeft(), start, end, pathList);
        } else if ((data.compareTo(start) < 0) && (data.compareTo(end) < 0)) {
            findPathBetween(curr.getRight(), start, end, pathList);
        } else if ((data.compareTo(start) > 0) && (data.compareTo(end) < 0)) {
            rootToStart(curr.getLeft(), start, pathList);
            startToEnd(curr, end, pathList);
        } else if ((data.compareTo(start) < 0) && (data.compareTo(end) > 0)) {
            rootToStart(curr.getRight(), start, pathList);
            startToEnd(curr, end, pathList);
        }
    }

    /**
     * Goes from the root to the first point
     * @param curr current node we're on
     * @param data data we try to find for first point
     * @param pathList contains the path we traversed
     */
    private void rootToStart(AVLNode<T> curr, T data, List<T> pathList) {
        if (curr == null) {
            throw new java.util.NoSuchElementException("Can't find data.");
        }
        if (curr.getData().compareTo(data) > 0) {
            rootToStart(curr.getLeft(), data, pathList);
        } else if (curr.getData().compareTo(data) < 0) {
            rootToStart(curr.getRight(), data, pathList);
        }
        pathList.add(curr.getData());
    }

    /**
     * goes from the first point to the end
     * @param curr current node we're on
     * @param data data we start from
     * @param pathList list containing the path we traversed
     */
    private void startToEnd(AVLNode<T> curr, T data, List<T> pathList) {
        if (curr == null) {
            throw new java.util.NoSuchElementException("Can't find data.");
        }
        pathList.add(curr.getData());
        if (curr.getData().compareTo(data) > 0) {
            startToEnd(curr.getLeft(), data, pathList);
        } else if (curr.getData().compareTo(data) < 0) {
            startToEnd(curr.getRight(), data, pathList);
        }
    }




    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null.");
        }
        return get(data, root).getData();
    }

    /**
     * Helper method to get a certain element
     * @param data data to "get"
     * @param newNode node currently examining
     * @return the object if we found it
     */
    private AVLNode<T> get(T data, AVLNode<T> newNode) {
        if (newNode == null) {
            throw new NoSuchElementException("Can't find what you seek");
        }
        if (data.equals(newNode.getData())) {
            return newNode;
        } else if (data.compareTo(newNode.getData()) > 0) {
            return get(data, newNode.getRight());
        } else {
            return get(data, newNode.getLeft());
        }
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data's null.");
        }
        return contains(data, root);
    }

    /**
     * helper for contains
     * @param data to check
     * @param newNode node that has the data
     * @return boolean: does it exist?
     */
    private boolean contains(T data, AVLNode<T> newNode) {
        if (newNode == null) {
            return false;
        }

        if (data.compareTo(newNode.getData()) > 0) {
            return contains(data, newNode.getRight());
        } else if (data.compareTo(newNode.getData()) < 0) {
            return contains(data, newNode.getLeft());
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        if (root == null) {
            return -1;
        } else {
            return root.getHeight();
        }
    }

    /**
     * Recursive method to get the height of a node.
     * @param node the node for traversal
     * @return net height
     */
    private int height(AVLNode<T> node) {
        if (node == null) {
            return -1;
        }

        int left = height(node.getLeft());
        int right = height(node.getRight());
        int maxHeight = Math.max(left, right) + 1;

        return maxHeight;
    }

    @Override
    public int depth(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data's null.");
        }
        return depth(data, root);
    }

    /**
     * Recursive method for depth
     * @param data data we pass in
     * @param node node we're currently on
     * @return integer depth of the node
     */
    private int depth(T data, AVLNode<T> node) {
        if (node == null) {
            throw new java.util.NoSuchElementException("Can't find such data.");
        }
        if (node.getData().compareTo(data) > 0) {
            return (depth(data, node.getLeft()) + 1);
        } else if (node.getData().compareTo(data) < 0) {
            return (depth(data, node.getRight()) + 1);
        }
        return 1;
    }


    // DO NOT MODIFY OR USE CODE BEYOND THIS POINT

    @Override
    public AVLNode<T> getRoot() {
        return root;
    }

    @Override
    public void setRoot(AVLNode<T> node) {
        this.root = node;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

}
