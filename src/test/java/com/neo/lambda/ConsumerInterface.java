package com.neo.lambda;

/**
 * 功能描述
 *
 * @author zhangpengfei
 * @since 2020/6/23
 */
@FunctionalInterface
public interface ConsumerInterface<T> {
    public abstract void comsumer(T t);
}
