package com.zws.datastruct.hashtable;

import java.util.*;

/**
 * @author zhengws
 * @date 2019-10-29 12:17
 */
public class HashTable<K, V> implements Map<K, V> {

    /**
     * 存放链表数据.
     */
    private transient Entry<?, ?>[] table;

    /**
     * 当前HashTable中元素个数.
     */
    private transient int size;

    /**
     * table元素的个数，即链表的条数。
     * 如果平均每条链表达到该值时，则进行扩容。
     */
    private int capacity;

    /**
     * 默认链表数.
     */
    private static int DEFAULT_SIZE = 10;

    public HashTable() {
        this(DEFAULT_SIZE);
    }

    public HashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
        this.capacity = Math.min(Integer.MAX_VALUE - 1, capacity);
        this.table = new Entry<?, ?>[capacity];
    }


    private static class Entry<K, V> implements Map.Entry<K, V> {
        /**
         * key的hash值.
         */
        int hash;
        final K key;
        V value;
        Entry<K, V> next;

        public Entry(int hash, K key, V value, Entry<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V value) {
            if (value == null) {
                throw new NullPointerException();
            }
            V oldVal = this.value;
            this.value = value;
            return oldVal;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public V get(Object key) {
        checkNullValue(key);
        if (isEmpty()) {
            return null;
        }
        int hash = key.hashCode();
        int index = getIndexByHash(hash);
        Entry<K, V> exist = findByKeyAndHash(key, hash, (Entry<K, V>) table[index]);
        if (exist != null) {
            return exist.value;
        }
        return null;
    }

    private int getIndexByHash(int hash) {
        return (hash & 0x7FFFFFFF) % this.capacity;
    }

    /**
     * 检查扩容
     */
    @SuppressWarnings("unchecked")
    private void checkDilatation() {
        //注意随着链表长度增长，会导致插入速度变慢，因为进行扩容的时候，需要一个个遍历迁移。
        if (this.size > (this.capacity << 2)) {
            //进行扩容操作,扩容2倍.
            this.capacity = Math.min(Integer.MAX_VALUE - 1, this.capacity << 1);
            Entry<?, ?>[] newTable = new Entry<?, ?>[this.capacity];
            Entry<K, V> tempEntry;
            for (int i = 0; i < table.length; i++) {
                for (Entry<K, V> entry = (Entry<K, V>) table[i]; entry != null; entry = tempEntry) {
                    tempEntry = entry.next;
                    int hash = entry.hash;
                    int index = getIndexByHash(hash);
                    entry.next = (Entry<K, V>) newTable[index];
                    newTable[index] = entry;
                }
            }
            this.table = newTable;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public V put(K key, V value) {
        checkNullValue(key);
        checkNullValue(value);
        checkDilatation();
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % this.capacity;
        Entry<K, V> entry = (Entry<K, V>) table[index];
        Entry<K, V> exist = findByKeyAndHash(key, hash, entry);
        if (exist != null) {
            V old = exist.value;
            exist.value = value;
            return old;
        }
        Entry<K, V> newEntry = new Entry<>(hash, key, value, entry);
        table[index] = newEntry;
        this.size++;
        return null;
    }

    /**
     * 根据key及hash值进行查找元素是否存在,存在的话返回该元素.
     *
     * @param key
     * @param hash
     * @param entry
     * @return
     */
    private Entry<K, V> findByKeyAndHash(Object key, int hash, Entry<K, V> entry) {
        for (Entry<K, V> e = entry; e != null; e = e.next) {
            if ((e.hash == hash) && (e.key.equals(key))) {
                return e;
            }
        }
        return null;
    }

    /**
     * 查找该节点的前一个节点。
     *
     * @param key
     * @param hash
     * @param entry
     * @return
     */
    private Entry<K, V> findPreEntryByKeyAndHash(Object key, int hash, Entry<K, V> entry) {
        for (Entry<K, V> e = entry; e.next != null; e = e.next) {
            if ((e.next.hash == hash) && (e.key.equals(key))) {
                return e;
            }
        }
        return null;
    }


    @Override
    @SuppressWarnings("unchecked")
    public V remove(Object key) {
        checkNullValue(key);
        if (isEmpty()) {
            return null;
        }
        int hash = key.hashCode();
        int index = getIndexByHash(hash);
        Entry<K, V> entry = (Entry<K, V>) this.table[index];
        V old = null;
        //如果第一个节点就找到，则删除掉该节点，将table的index指向该节点的下一个节点.
        if (entry.hash == hash && entry.key.equals(key)) {
            old = entry.value;
            this.table[index] = entry.next;
            entry.next = null;
        } else {
            Entry<K, V> preEntry = findPreEntryByKeyAndHash(key, hash, entry);
            if (preEntry != null) {
                entry = preEntry.next;
                preEntry.next = entry.next;
                entry.next = null;
                old = entry.value;
            }
        }
        return old;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            this.table[i] = null;
        }
        this.size = 0;
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return null;
    }

    private void checkNullValue(Object value) {
        if (value == null) {
            throw new NullPointerException();
        }
    }

    public Iterator<V> getIterator() {
        if (size == 0) {
            return Collections.emptyIterator();
        } else {
            return new Enumerator<>();
        }
    }

    private class Enumerator<T> implements Enumeration<T>, Iterator<T> {
        Entry<?, ?>[] table = HashTable.this.table;
        int index = table.length;
        Entry<?, ?> entry;
        Entry<?, ?> lastReturned;

        @Override
        public boolean hasMoreElements() {
            while (entry == null && index > 0) {
                entry = table[--index];
            }
            return entry != null;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T nextElement() {
            while (entry == null && index > 0) {
                entry = table[--index];
            }
            if (entry != null){
                lastReturned = entry;
                entry = entry.next;
                return (T) lastReturned.value;
            }
            throw new NoSuchElementException("HashTable Enumerator");
        }

        @Override
        public boolean hasNext() {
            return hasMoreElements();
        }

        @Override
        public T next() {
            return nextElement();
        }
    }
}
