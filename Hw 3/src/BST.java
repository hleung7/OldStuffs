import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Your implementation of a Binary Search Tree.
 *
 * @author Henry Leung
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty BST.
     * YOU DO NOT NEED TO IMPLEMENT THIS CONSTRUCTOR!
     */
    public BST() {

    }

    /**
     * Initializes the BST with the data in the Collection. The data in the BST
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("The data can't be null.");
        } else {
            for (T index: data) {
                if (index == null) {
                    throw new IllegalArgumentException("The data is null.");
                }
                add(index);
            }
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        } else if (root == null) {
            root = new BSTNode(data);
            size++;
        } else {
            add(data, root);
        }
    }

    /**
     * Recursive method to add to a BST.
     *
     * @param data the data we add
     * @param node the node to traverse
     */
    private void add(T data, BSTNode<T> node) {
        if (data.compareTo(node.getData()) < 0) {
            if (node.getLeft() == null) {
                node.setLeft(new BSTNode(data));
                size++;
            } else {
                add(data, node.getLeft());
            }
        } else if (data.compareTo(node.getData()) > 0) {
            if (node.getRight() == null) {
                node.setRight(new BSTNode(data));
                size++;
            } else {
                add(data, node.getRight());
            }
        }
    }
    @Override
    public T remove(T data) {
        T rem = null;

        if (data == null) {
            throw new IllegalArgumentException("Data can't be null");
        } else if (root == null) {
            throw new NoSuchElementException("Can't find the data.");
        } else {
            BSTNode<T> prev = root;
            rem = remove(data, root, prev);

            return rem;
        }
    }


    /**
     * Private recursive method to remove data in a BST.
     *
     * @param data we're trying to remove
     * @param node node we start from
     * @param prev the previous node
     * @return the removed data
     */
    private T remove(T data, BSTNode<T> node, BSTNode<T> prev) {
        BSTNode<T> rem = null;
        if (node == null) {
            throw new java.util.NoSuchElementException("Couldn't find data.");
        }

        if (data.compareTo(node.getData()) < 0) {
            return (remove(data, node.getLeft(), node));
        } else if (data.compareTo(node.getData()) > 0) {
            return (remove(data, node.getRight(), node));
        } else {
            rem = node;

            if ((node.getRight() == null) && (node.getLeft() == null)) {
                if (prev.getRight() == rem) {
                    prev.setRight(null);
                } else if (prev.getLeft() == rem) {
                    prev.setLeft(null);
                } else {
                    if (node == root) {
                        root = null;
                    } else {
                        node = null;
                    }
                }
                size--;
                return (rem.getData());
            } else if ((node.getRight() != null) && (node.getLeft() == null)) {
                if (prev.getRight() == rem) {
                    prev.setRight(node.getRight());
                } else if (prev.getLeft() == rem) {
                    prev.setLeft(node.getRight());
                } else {
                    root = node.getRight();
                }
                size--;
                return (rem.getData());
            } else if ((node.getRight() == null) && (node.getLeft() != null)) {
                if (prev.getRight() == rem) {
                    prev.setRight(node.getLeft());
                } else if (prev.getLeft() == rem) {
                    prev.setLeft(node.getLeft());
                } else {
                    root = node.getLeft();
                }
                size--;
                return (rem.getData());
            } else {
                if (node == root) {
                    node = getPredecessor(node);
                    if (node != null) {
                        root = node;
                    } else {
                        root = root.getLeft();
                    }
                } else {
                    BSTNode<T> tmp = getPredecessor(node);
                    tmp.setLeft(node.getLeft());
                }
                size--;
                return (rem.getData());
            }
        }
    }


    /**
     * A helper method to get predecessor
     * @param checkNode node the predecessor replaces
     * @return predecessor node
     */
    private BSTNode<T> getPredecessor(BSTNode<T> checkNode) {
        if (checkNode.getLeft() == null) {
            return checkNode.getRight();
        } else if (checkNode.getLeft().getRight() == null) {
            BSTNode<T> predecessor = checkNode.getLeft();
            checkNode.setLeft(checkNode.getLeft().getLeft());
            return predecessor;
        } else {
            BSTNode<T> curr = checkNode.getLeft();
            while (curr.getRight().getRight() != null) {
                curr = curr.getRight();
            }
            BSTNode<T> predecessor = curr.getRight();
            curr.setRight(curr.getRight().getLeft());
            return predecessor;
        }
    }


    @Override
    public T get(T data) {
        return get(data, root);
    }

    /**
     * Helper to get data from a BST
     * @throws java.lang.IllegalArgumentException for null data
     * @throws java.util.NoSuchElementException if we can't find
     * @param data data we're trying to get
     * @param checkNode node we currently compare with
     * @return the data we're looking for
     */
    private T get(T data, BSTNode<T> checkNode) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null.");
        }
        if (checkNode == null) {
            throw new NoSuchElementException("Data is not in tree.");
        }
        if (data.compareTo(checkNode.getData()) < 0) {
            return get(data, checkNode.getLeft());
        } else if (data.compareTo(checkNode.getData()) > 0) {
            return get(data, checkNode.getRight());
        } else {
            return checkNode.getData();
        }
    }

    @Override
    public boolean contains(T data) {
        return contains(data, root);
    }

    /**
     * Helper to find if the data is in the tree
     * @throws java.lang.IllegalArgumentException if data is null
     * @param data the data to find
     * @param checkNode the node we're comparing
     * @return boolean T yes or F no not in tree
     */
    private boolean contains(T data, BSTNode<T> checkNode) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null.");
        }
        boolean foundData = false;
        if (checkNode == null) {
            return foundData;
        }
        if (data.compareTo(checkNode.getData()) < 0) {
            return contains(data, checkNode.getLeft());
        } else if (data.compareTo(checkNode.getData()) > 0) {
            return contains(data, checkNode.getRight());
        } else {
            foundData = true;
            return foundData;
        }
    }


    @Override
    public T nextLargest(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Can't search for null data!");
        }
        return nextLargest(root, null, data);
    }

    /**
     * Private overloaded method to get the Successor of a BST.
     * @param curr current node we're on
     * @param data data
     * @param store a way to keep the data we need to return in nextLargest
     * @return the data stored in the successor node.
     */
    private T nextLargest(BSTNode<T> curr, T store, T data) {
        if (curr == null) {
            throw new IllegalArgumentException("Can't find the data.");
        }
        if (curr.getData().compareTo(data) > 0) {
            store = curr.getData();
            return nextLargest(curr.getLeft(), store, data);
        } else if (curr.getData().compareTo(data) < 0) {
            return nextLargest(curr.getRight(), store, data);
        }
        if (curr.getRight() != null) {
            store = minHelper(curr.getRight());
        }
        return store;
    }

    @Override
    public String toString() {
        return toString(root);
    }

    /**
     * Private toString helper method
     * @param curr current node we're on
     * @return string representation of the entire tree
     */
    private String toString(BSTNode<T> curr) {
        String output = "{";
        if (curr != null) {
            output = output + curr.getData() + ", ";
            output = output + toString(curr.getLeft());
            output = output + ", ";
            output = output + toString(curr.getRight());
        }
        output = output + "}";
        return output;
    }



    @Override
    public T getMax() {
        if (root == null) {
            return null;
        }
        return maxHelper(root);
    }
    /**
     * private helper method to get the max of a BST.
     * @param curr the node we're current on
     * @return max of a BST
     */
    private T maxHelper(BSTNode<T> curr) {
        if (curr.getRight() == null) {
            return curr.getData();
        }
        return maxHelper(curr.getRight());
    }
    @Override
    public T getMin() {
        if (root == null) {
            return null;
        }
        return minHelper(root);
    }
    /**
     * Private helper method to get the minimum of a BST.
     * @param curr node we start at
     * @return minimum node in the BST.
     */
    private T minHelper(BSTNode<T> curr) {
        if (curr.getLeft() == null) {
            return curr.getData();
        }
        return minHelper(curr.getLeft());
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> preorder() {
        return preorder(root);
    }

    /**
     * Helper method for the pre-order traversal
     * @param checkNode the starting node
     * @return the list of the pre-order traversal
     */
    private List<T> preorder(BSTNode<T> checkNode) {
        List<T> list = new ArrayList<T>();
        if (checkNode == null) {
            return list;
        }
        list.add(checkNode.getData());
        list.addAll(preorder(checkNode.getLeft()));
        list.addAll(preorder(checkNode.getRight()));
        return list;
    }

    @Override
    public List<T> postorder() {
        return postorder(root);
    }

    /**
     * Helper method for post-order traversal
     * @param checkNode the starting node
     * @return the list of the post-order traversal
     */
    private List<T> postorder(BSTNode<T> checkNode) {
        List<T> list = new ArrayList<T>();
        if (checkNode == null) {
            return list;
        }
        list.addAll(postorder(checkNode.getLeft()));
        list.addAll(postorder(checkNode.getRight()));
        list.add(checkNode.getData());
        return list;
    }

    @Override
    public List<T> inorder() {
        return inorder(root);

    }


    /**
     * Helper method for in-order traversal
     * @param checkNode the starting node
     * @return the list of the in-order traversal
     */
    private List<T> inorder(BSTNode<T> checkNode) {
        List<T> list = new ArrayList<T>();
        if (checkNode == null) {
            return list;
        }
        list.addAll(inorder(checkNode.getLeft()));
        list.add(checkNode.getData());
        list.addAll(inorder(checkNode.getRight()));
        return list;
    }


    @Override
    public List<T> levelorder() {
        List<BSTNode<T>> queue = new ArrayList<>();
        List<T> dataList = new ArrayList<T>();
        BSTNode<T> curr = root;
        if (curr == null) {
            return dataList;
        }
        queue.add(curr);
        while (!queue.isEmpty()) {
            curr = queue.get(0);
            if (curr.getLeft() != null) {
                queue.add(curr.getLeft());
            }
            if (curr.getRight() != null) {
                queue.add(curr.getRight());
            }
            dataList.add(queue.remove(0).getData());
        }
        return dataList;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Helper method for height of a BST.
     * @param checkNode the starting node
     * @return int height
     */
    private int height(BSTNode<T> checkNode) {
        if (checkNode == null) {
            return -1;
        }
        int heightLeft = height(checkNode.getLeft());
        int heightRight = height(checkNode.getRight());
        return Math.max(heightLeft, heightRight) + 1;
    }

    @Override
    public int height() {
        return height(root);
    }

    @Override
    public BSTNode<T> getRoot() {
        // DO NOT EDIT THIS METHOD!
        return root;
    }
}
