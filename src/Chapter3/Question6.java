package Chapter3;

import java.util.LinkedList;

/**
 * Animal Shelter: An animal shelter, which holds only dogs and cats, operates on a strictly "first in, first
 * out" basis. People must adopt either the "oldest" (based on arrival time) of all animals at the shelter,
 * or they can select whether they would prefer a dog or a cat (and will receive the oldest animal of
 * that type). They cannot select which specific animal they would like. Create the data structures to
 * maintain this system and implement operations such as enqueue, dequeueAny, dequeueDog,
 * and dequeueCat. You may use the built-in LinkedList data structure.
 */
class Question6 {

    private abstract class Animal {

        private int order;
        protected String name;

        public Animal(String name) {
            this.name = name;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getOrder() {
            return order;
        }

        public boolean isOlderThan(Animal animal) {
            return this.order < animal.getOrder();
        }

    }

    private class Dog extends Animal {
        public Dog(String name) {
            super(name);
        }
    }

    private class Cat extends Animal {
        public Cat(String name) {
            super(name);
        }
    }


    private class AnimalQueue {

        private LinkedList<Dog> dogs;
        private LinkedList<Cat> cats;
        private int order;

        public AnimalQueue() {
            dogs = new LinkedList<>();
            cats = new LinkedList<>();
            order = 0;
        }

        public void enqueue(Animal animal) {
            animal.setOrder(order);
            order++;
            if (animal instanceof Dog) {
                dogs.addLast((Dog) animal);
            } else if (animal instanceof Cat) {
                cats.addLast((Cat) animal);
            }
        }

        public Animal dequeueAny() {
            if (dogs.isEmpty()) {
                return dequeueCat();
            } else if (cats.isEmpty()) {
                return dequeueDog();
            } else {
                Dog dog = dogs.peek();
                Cat cat = cats.peek();
                if (dog.isOlderThan(cat)) {
                    return dequeueDog();
                } else {
                    return dequeueCat();
                }
            }
        }

        private Dog dequeueDog() {
            return dogs.pollFirst();
        }

        private Cat dequeueCat() {
            return cats.pollFirst();
        }

    }

}
