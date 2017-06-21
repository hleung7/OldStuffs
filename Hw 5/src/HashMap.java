import java.util.NoSuchElementException;
import java.util.Set;
import java.util.LinkedList;
import java.util.List;
import java.util.LinkedHashSet;

/**
 * Your implementation of HashMap.
 * @author Henry
 * @version 1.0
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {
    // Do not make any new instance variables.
    private MapEntry<K, V>[] backingArray;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code INITIAL_CAPACITY}.
     *
     * Use constructor chaining.
     */
    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        backingArray = (MapEntry<K, V>[]) new MapEntry[initialCapacity];
    }

    /**
     * If possible, given a table and a key, finds
     * the first possible location the key can be placed in.
     *
     * @param newTable table containing the key we need to find spot for
     * @param key key we want to find a spot for
     * @return if an index exists either +/- where it can be placed
     */

    private int getLocation(MapEntry<K, V>[] newTable, K key) {
        int index = getIndex(newTable, key);
        int start = index;
        int placeholder = -1;

        do {
            if (hasSpace(newTable, index)) {
                if (placeholder == -1) {
                    placeholder = index;
                }
                if (newTable[index] == null) {
                    return ((placeholder + 1) * -1);
                }
            } else if (newTable[index].getKey().equals(key)) {
                return index;
            }
            index = (index + 1) % newTable.length;
        } while (index != start);
        return ((placeholder + 1) * -1);
    }


    /**
     * Checks if the bucket has space at the indicated
     * slot or not. E.g. has it been removed/null?
     * @param newTable table containing the bucket we
     *                 check if it has space or not
     * @param bucket the index in the table we want to
     *               check if it's empty or not
     * @return true or false, e.g. can the bucket
     *               store another entry?
     */
    private boolean hasSpace(MapEntry<K, V>[] newTable, int bucket) {
        return (newTable[bucket] == null) || ((newTable[bucket].isRemoved()));
    }


    /**
     * Gets the index to place a key at.
     * @param newTable the table containing the key
     * @param key the key we need to find the index for
     * @return integer index where the key belongs at
     */
    private int getIndex(MapEntry<K, V>[] newTable, K key) {
        int index = key.hashCode();
        if (index < 0) {
            return (Math.abs(index) % newTable.length);
        }
        return index % newTable.length;
    }


    @Override
    public V put(K key, V value) {
        if ((key == null) || (value == null)) {
            throw new IllegalArgumentException("Keys/Values cannot be null.");
        }
        if ((((double) (size + 1) / backingArray.length)
                > (MAX_LOAD_FACTOR))) {
            resizeBackingArray(backingArray.length * 2 + 3);
        }


        if (!exceeds()) {
            MapEntry<K, V> storeEntry = new MapEntry<>(key, value);
            int index = getLocation(backingArray, key);
            if (index >= 0) {
                MapEntry<K, V> dataEntry = backingArray[index];
                backingArray[index] = storeEntry;
                return dataEntry.getValue();
            }
            backingArray[((index + 1) * -1)] = storeEntry;
            size = size + 1;

        }
        return null;
    }

    /**
     * public helper method to check if a Hashmap
     * has exceeded the .75 load factor cap.
     * @return boolean true or false
     */
    public boolean exceeds() {
        return ((((double) size / backingArray.length)
                - (MAX_LOAD_FACTOR)) >= 0);
    }


    @Override
    public V remove(K key) {

        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }

        int index = getLocation(backingArray, key);
        if (index < 0) {
            throw new NoSuchElementException("Index shouldn't be negative."
                    + "Key not found.");
        } else if (index == 0) {
            if (backingArray[index].getKey().equals(key)) {
                backingArray[index].setRemoved(true);
                size--;
                return backingArray[index].getValue();
            } else {
                throw new NoSuchElementException("Key not found.");
            }
        }
        backingArray[index].setRemoved(true);
        size = size - 1;
        return backingArray[index].getValue();
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
        int index = getLocation(backingArray, key);
        if (index < 0) {
            throw new NoSuchElementException("Key not found.");
        } else if (index == 0) {
            if (backingArray[index].getKey().equals(key)) {
                return backingArray[index].getValue();
            } else {
                throw new NoSuchElementException("Key does not exist.");
            }
        }
        return backingArray[index].getValue();
    }


    @Override
    public int count(V value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null.");
        }
        int number = 0;
        for (MapEntry<K, V> entry: backingArray) {
            if ((entry != null) && (!entry.isRemoved())) {
                if (value.equals((entry.getValue()))) {
                    number++;
                }
            }
        }
        return number;
    }


    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
        for (MapEntry<K, V> entry : backingArray) {
            if (entry != null && entry.getKey().equals(key)
                    && !entry.isRemoved()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        backingArray = (MapEntry<K, V>[]) new MapEntry[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<K> keySet() {
        Set<K> storeKey = new LinkedHashSet<>();
        for (MapEntry<K, V> entry : backingArray) {
            if ((entry != null) && (!entry.isRemoved())) {
                storeKey.add(entry.getKey());
            }
        }
        return storeKey;
    }

    @Override
    public List<V> values() {
        List<V> storeValue = new LinkedList<V>();
        for (MapEntry<K, V> entry: backingArray) {
            if ((entry != null) && (!entry.isRemoved())) {
                storeValue.add(entry.getValue());
            }
        }
        return storeValue;
    }


    @Override
    public void resizeBackingArray(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length cannot be negative.");
        } else if (length < size) {
            throw new IllegalArgumentException("Length cannot be less"
                    + "than the size of the map");
        } else {
            MapEntry<K, V>[] save =
                    (MapEntry<K, V>[]) new MapEntry[length];
            for (MapEntry<K, V> entry : backingArray) {
                if ((entry != null) && (!entry.isRemoved())) {
                    int index = getLocation(save, entry.getKey());
                    if (index >= 0) {
                        save[index] = entry;
                    }
                    save[(index + 1) * -1] = entry;
                }
            }
            backingArray = save;
        }
    }

    // DO NOT MODIFY OR USE CODE BEYOND THIS POINT.

    @Override
    public MapEntry<K, V>[] getArray() {
        return backingArray;
    }

}
