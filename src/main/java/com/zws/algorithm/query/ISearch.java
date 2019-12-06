package com.zws.algorithm.query;

import java.util.List;

/**
 * @author zhengws
 * @date 2019-10-27 10:44
 */
public interface ISearch {
    /**
     * 查找数组中该元素的索引，如果找到则返回该元素首次出现的索引，如果找不到，则返回-1
     * @param value
     * @return
     */
    int search(int value);


    /**
     * 查找数组中，该元素的所有索引，如果没找到，则返回空数组.
     * @param value
     * @return
     */
    List<Integer> serachAll(int value);
}
