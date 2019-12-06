package com.zws.algorithm.mst;

import java.util.List;

/**
 * @author zhengws
 * @date 2019-11-17 16:42
 */
public interface IMinSpanTree<T> {

    /**
     * 获取最小生成树路径
     * @return
     */
    List<String> getMstPath(T start);
}
