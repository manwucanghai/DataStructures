package com.zws.datastruct.queue.dogcatpro;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ●　用户可以调用add方法将cat类或dog类的实例放入队列中；
 * ●　用户可以调用pollAll方法，将队列中所有的实例按照进队列的先后顺序依次弹出；
 * ●　用户可以调用pollDog方法，将队列中dog类的实例按照进队列的先后顺序依次弹出；
 * ●　用户可以调用pollCat方法，将队列中cat类的实例按照进队列的先后顺序依次弹出；
 * ●　用户可以调用isEmpty方法，检查队列中是否还有dog或cat的实例；
 * ●　用户可以调用isDogEmpty方法，检查队列中是否有dog类的实例；
 * ●　用户可以调用isCatEmpty方法，检查队列中是否有cat类的实例。
 *
 * @author zhengws
 * @date 2019-10-24 16:13
 */
public class DogCatQueue {
    private Queue<AnimalEnter> dogQueue;
    private Queue<AnimalEnter> catQueue;
    private long count;

    public DogCatQueue() {
        this.dogQueue = new LinkedList<>();
        this.catQueue = new LinkedList<>();
    }


    public Animal add(Animal animal) {
        checkNullElement(animal);
        if ("dog".equals(animal.getType())) {
            dogQueue.add(new AnimalEnter(animal, ++count));
        } else {
            catQueue.add(new AnimalEnter(animal, ++count));
        }
        return animal;
    }

    public Dog pollDog() {
        if (isDogEmpty()) {
            throw new RuntimeException("Dog queue is empty");
        }
        return (Dog) dogQueue.poll().getAnimal();
    }

    public Cat pollCat() {
        if (isDogEmpty()) {
            throw new RuntimeException("Dog queue is empty");
        }
        return (Cat) catQueue.poll().getAnimal();
    }

    public boolean isEmpty() {
        return isDogEmpty() && isCatEmpty();
    }

    public boolean isDogEmpty() {
        return dogQueue.isEmpty();
    }

    public boolean isCatEmpty() {
        return catQueue.isEmpty();
    }

    private void checkNullElement(Animal animal) {
        if (animal == null) {
            throw new NullPointerException("添加元素不能为空");
        }
    }
}
