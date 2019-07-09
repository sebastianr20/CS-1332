import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Your implementation of HashMap.
 * 
 * @author Juan Rodriguez
 * @userid jrodriguez325
 * @GTID 903379809
 * @version 1.0
 *
 * Collaborators: none
 *
 * Resources: none
 */
public class HashMap<K, V> {

    // DO NOT MODIFY OR ADD NEW GLOBAL/INSTANCE VARIABLES
    public static final int INITIAL_CAPACITY = 13;
    public static final double MAX_LOAD_FACTOR = 0.67;
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Creates a hash map with no entries. The backing array should have an
     * initial capacity of INITIAL_CAPACITY.
     *
     * Use constructor chaining.
     */
    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Creates a hash map with no entries. The backing array should have an
     * initial capacity of the initialCapacity parameter.
     *
     * You may assume the initialCapacity parameter will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        this.table = new MapEntry[initialCapacity];
        this.size = 0;
    }

    /**
     * Adds the given key-value pair to the HashMap.
     *
     * In the case of a collision, use linear probing as your resolution
     * strategy.

     * If you find a duplicate key, then replace the entry's value with the new
     * one passed in and return the old value.
     *
     * At the start of the method, you should check to see if the array would
     * violate the max load factor after adding the data (regardless of
     * duplicates). For example, let's say the array is of length 5 and the
     * current size is 3 (LF = 0.6). For this example, assume that no elements
     * are removed in between steps. If another entry is attempted to be added,
     * before doing anything else, you should check whether (3 + 1) / 5 = 0.8
     * is larger than the max LF. It is, so you would trigger a resize before
     * you even attempt to add the data or figure out if it's a duplicate. As a
     * warning, be careful about using integer division in the LF calculation!
     *
     * When regrowing, resize the length of the backing table to
     * 2 * old length + 1. You must use the resizeBackingTable method to do so.
     *
     * Return null if the key was not already in the map. If it was in the map,
     * return the old value associated with it.
     *
     * @param key key to add into the HashMap
     * @param value value to add into the HashMap
     * @throws IllegalArgumentException if key or value is null
     * @return null if the key was not already in the map. If it was in the
     * map, return the old value associated with it
     */
    public V put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        if (value == null) {
            throw new IllegalArgumentException("Value is null");
        }
        if (((double) ((size + 1)) / table.length) > MAX_LOAD_FACTOR) {
            resizeBackingTable((2 * table.length) + 1);
        }
        int keyHash = Math.abs(key.hashCode());
        int index = Math.abs(keyHash % table.length);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            size += 1;
            return null;
        } else if (!table[index].isRemoved()
                && table[index].getKey().equals(key)) {
            V temp = table[index].getValue();
            table[index] = new MapEntry<>(key, value);
            return temp;
        }
        int probes;
        int firstDEL = -1;
        boolean foundDEL = false;
        for (probes = 0; probes < table.length; probes++) {
            int indexNew = (index + probes) % table.length;
            if (table[indexNew] != null && table[indexNew].isRemoved()) {
                if (firstDEL == -1) {
                    firstDEL = indexNew;
                    foundDEL = true;
                    continue;
                }
            } else if (table[indexNew] != null
                    && !table[indexNew].isRemoved()
                    && table[indexNew].getKey().equals(key)) {
                V temp = table[indexNew].getValue();
                table[indexNew] = new MapEntry<>(key, value);
                return temp;
            } else if (table[indexNew] == null) {
                if (foundDEL) {
                    table[firstDEL] = new MapEntry<>(key, value);
                    size += 1;
                    return null;
                } else {
                    table[indexNew] = new MapEntry<>(key, value);
                    size += 1;
                    return null;
                }
            }
        }
        table[firstDEL] = new MapEntry<>(key, value);
        size += 1;
        return null;
    }

    /**
     * Resizes the backing table to the specified length.
     *
     * Disregard the load factor for this method. So, if the passed in length is
     * smaller than the current capacity, and this new length causes the table's
     * load factor to exceed MAX_LOAD_FACTOR, you should still resize the table
     * to the specified length and leave it at that capacity.
     *
     * You should iterate over the old table in order of increasing index and
     * add entries to the new table in the order in which they are traversed.
     *
     * Remember, you cannot just simply copy the entries over to the new array.
     * You will have to rehash all of the entries and add them to the new index
     * of the new table.
     *
     * Also, since resizing the backing table is working with the non-duplicate
     * data already in the table, you shouldn't need to explicitly check for
     * duplicates like the put method does. You also wouldn't want to carry
     * over removed entries either, so you can place the entry in the new table
     * as soon as you find a null spot while probing.
     *
     * @param length new length of the backing table
     * @throws IllegalArgumentException if length is non-positive or less than
     * the number of items in the hash map.
     */
    public void resizeBackingTable(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Cannot resize to negative.");
        }
        if (length < size) {
            throw new IllegalArgumentException("New size too small.");
        }
        MapEntry<K,V>[] temp = new MapEntry[length];
        for (MapEntry<K, V> entry: table) {
            if (entry != null && !entry.isRemoved()) {
                K key = entry.getKey();
                V value = entry.getValue();
                int keyHash = Math.abs(key.hashCode());
                int index = Math.abs(keyHash % temp.length);
                if (temp[index] == null) {
                    temp[index] = new MapEntry<>(key, value);
                }
                //int probes = 0;
                for (int probes = 1; probes < temp.length; probes++) {
                    int indexNew = (index + probes) % temp.length;
                    if (temp[indexNew] == null) {
                        temp[indexNew] = new MapEntry<>(key, value);
                    }
                }
            }
        }
        table = temp;
    }

    /**
     * Removes the entry with a matching key from the HashMap.
     *
     * @param key the key to remove
     * @throws IllegalArgumentException if key is null
     * @throws java.util.NoSuchElementException if the key does not exist
     * @return the value previously associated with the key
     */
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        int keyHash = Math.abs(key.hashCode());
        int index = keyHash % table.length;
        V temp;
        for (int probes = 0; probes < table.length; probes++) {
            int indexNew = (index + probes) % table.length;
            if (table[indexNew] != null
                    && !table[indexNew].isRemoved()
                    && table[indexNew].getKey().equals(key)) {
                temp = table[indexNew].getValue();
                table[indexNew].setRemoved(true);
                size -= 1;
                return temp;
            }
        }
        throw new java.util.NoSuchElementException("Key does not exist");
    }

    /**
     * Gets the value associated with the given key.
     *
     * @param key the key to search for
     * @throws IllegalArgumentException if key is null
     * @throws java.util.NoSuchElementException if the key is not in the map
     * @return the value associated with the given key
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Cannot search for null key.");
        } else if (!containsKey(key)) {
            throw new java.util.NoSuchElementException("Key does not exist.");
        }
        int keyHash = key.hashCode();
        int index = Math.abs(keyHash % table.length);
        for (int probes = 0; probes < table.length; probes++) {
            int indexNew = (index + probes) % table.length;
            if (table[indexNew].getKey().equals(key)) {
                return table[indexNew].getValue();
            }
        }
        return null;
    }

    /**
     * Returns whether or not the key is in the map.
     *
     * @param key the key to search for
     * @throws IllegalArgumentException if key is null
     * @return whether or not the key is in the map
     */
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Cannot search for null key.");
        }
        int keyHash = Math.abs(key.hashCode());
        int index = Math.abs(keyHash % table.length);
        for (int probes = 0; probes < table.length; probes++) {
            int indexNew = (index + probes) % table.length;
            if (table[indexNew] != null
                    && !table[indexNew].isRemoved()
                    && table[indexNew].getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a Set view of the keys contained in this map. The Set view is
     * used instead of a List view because keys are unique in a HashMap, which
     * is a property that elements of Sets also share.
     * 
     * Use java.util.HashSet.
     *
     * @return set of keys in this map
     */
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (MapEntry<K, V> entry: table) {
            if (entry == null) {
                continue;
            } else if (entry.isRemoved()) {
                continue;
            } else {
                set.add(entry.getKey());
            }
        }
        return set;
    }

    /**
     * Returns a List view of the values contained in this map.
     *
     * You should iterate over the table in order of increasing index.
     * Add entries to the List in the order in which they are traversed.
     * 
     * Use java.util.ArrayList or java.util.LinkedList.
     *
     * @return list of values in this map
     */
    public List<V> values() {
        List<V> list = new ArrayList<V>();
        for (MapEntry<K, V> entry: table) {
            if (entry == null) {
                continue;
            } else if (entry.isRemoved()) {
                continue;
            } else {
                list.add(entry.getValue());
            }
        }
        return list;
    }

    /**
     * Clears the table and resets it to a new table of length INITIAL_CAPACITY.
     */
    public void clear() {
        this.table  = new MapEntry[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Returns the size of the HashMap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return number of items in the HashMap
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
    
    /**
     * Returns the backing table of the HashMap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing table of the HashMap
     */
    public MapEntry<K, V>[] getTable() {
        // DO NOT MODIFY THIS METHOD!
        return table;
    }

}