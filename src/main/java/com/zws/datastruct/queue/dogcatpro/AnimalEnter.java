package com.zws.datastruct.queue.dogcatpro;

/**
 * @author zhengws
 * @date 2019-10-24 16:14
 */
public class AnimalEnter {
    private Animal animal;
    private long count;

    public AnimalEnter(Animal animal, long count) {
        this.animal = animal;
        this.count = count;
    }

    public Animal getAnimal() {
        return animal;
    }
}
