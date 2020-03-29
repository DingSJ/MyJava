package com.china.java8.inAction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * limit() skip() collect() filter() map() flatMap()
 * allMatch、anyMatch、noneMatch、findFirst和findAny
 * <p>
 * Optional<Dish> dish = menu.stream().filter(Dish::isVegetarian).findAny();
 * <p>
 * menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(d -> System.out.println(d.getName());
 * <p>
 * max min sum count
 * filter 中间 Stream<T> Predicate<T> T -> boolean
 * distinct 中间 (有状态无界)  Stream<T>
 * skip 中间 (有状态有界)  Stream<T> long
 * limit 中间 (有状态有界)   Stream<T> long
 * map 中间 Stream<R> Function<T, R> T -> R
 * flatMap 中间 Stream<R> Function<T, Stream<R>> T -> Stream<R>
 * sorted 中间 (有状态无界)  Stream<T> Comparator<T> (T, T) -> int
 * anyMatch 终端 boolean Predicate<T> T -> boolean
 * noneMatch 终端 boolean Predicate<T> T -> boolean
 * allMatch 终端 boolean Predicate<T> T -> boolean
 * findAny 终端 Optional<T>
 * findFirst 终端 Optional<T>
 * forEach 终端 void Consumer<T> T -> void
 * collect 终端 R Collector<T, A, R>
 * reduce 终端 (有状态有界)
 * Optional<T> BinaryOperator<T> (T, T) -> T
 * count 终端 long
 */
public class MenuTest {
    public static void main(String[] args) {
        List<Dish> menuList = buildMenus();

        // limit() skip() collect() filter() map()
//        testFun1(menuList);

        // flatMap
//        testFun2(menuList);

        // anyMatch() noneMatch() allMatch、noneMatch、findFirst和findAny
//        testFun3(menuList);

        // max min sum count
        testFun4(menuList);

    }

    private static void testFun4(List<Dish> menuList) {
        Optional<Dish> present = menuList.stream().max((o1, o2) -> o1.calories - o2.calories);
        System.out.println(present.get());
        Optional<Dish> present2 = menuList.stream().max(Comparator.comparingInt(o -> o.calories));
        System.out.println(present2.get());
        Optional<Dish> present3 = menuList.stream().max(Comparator.comparingInt(Dish::getCalories));
        System.out.println(present3.get());

        Optional<Dish> presentMin = menuList.stream().min(Comparator.comparingInt(Dish::getCalories));
        System.out.println(presentMin.get());

        long count = menuList.stream().count();
        System.out.println(count);

        Integer reduce = menuList.stream().map(Dish::getCalories).reduce(0, Integer::sum);
        System.out.println(reduce);

        Optional<Integer> reduce1 = menuList.stream().map(Dish::getCalories).reduce(Integer::sum);
        System.out.println(reduce1.get());

        Optional<Integer> reduce2 = menuList.stream().map(Dish::getCalories).reduce(Integer::min);
        System.out.println(reduce2.get());

        Optional<Integer> reduce3 = menuList.stream().map(Dish::getCalories).reduce(Integer::max);
        System.out.println(reduce3.get());

        /*
            pork-800-MEAT
            pork-800-MEAT
            pork-800-MEAT
            season fruit-120-OTHER
            9
            4200
            4200
            120
            800
        */
    }

    private static void testFun3(List<Dish> menuList) {
        Optional<Dish> present = menuList.stream().filter(menu -> menu.calories > 500).findAny();
        System.out.println(present.get());
        Optional<Dish> first = menuList.stream().filter(menu -> menu.calories > 500).findFirst();
        System.out.println(first.get());

        boolean anyMatch = menuList.stream().anyMatch(menu -> menu.calories > 700);
        System.out.println(anyMatch);

        boolean allMatch = menuList.stream().allMatch(menu -> menu.calories > 700);
        System.out.println(allMatch);

        boolean noneMatch = menuList.stream().noneMatch(menu -> menu.calories > 700);
        System.out.println(noneMatch);

        /*
        pork
        pork
        true
        false
        false
        */
        Optional<Dish> dish = menuList.stream().filter(Dish::isVegetarian).findAny();
        System.out.println(dish.get());

        menuList.stream().filter(Dish::isVegetarian).findAny().ifPresent(d -> System.out.println(d.getName()));
        menuList.stream().filter(Dish::isVegetarian).findAny().ifPresent(System.out::println);
    }

    private static void testFun2(List<Dish> menuList) {
        String[] hw = {"Hello", "World"};
        List<String> collect = Arrays.stream(hw).map(ch -> ch.split(""))
                .flatMap(arr -> Arrays.stream(arr))
                .map(String::toLowerCase)
                .distinct()
                .collect(toList());
        System.out.println(collect);
    }

    private static void testFun1(List<Dish> menuList) {
        List<Integer> collect = menuList.stream().map(Dish::getCalories).filter(valories -> valories > 500).collect(toList());
        List<Integer> collect1 = menuList.stream().map(Dish::getCalories).filter(valories -> valories > 500).skip(1).limit(2).collect(toList());
        System.out.println(collect);
        System.out.println(collect1);
    }

    private static List<Dish> buildMenus() {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
        return menu;
    }

    static class Dish {
        private final String name;
        private final boolean vegetarian;
        private final int calories;
        private final Type type;

        public Dish(String name, boolean vegetarian, int calories, Type type) {
            this.name = name;
            this.vegetarian = vegetarian;
            this.calories = calories;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public boolean isVegetarian() {
            return vegetarian;
        }

        public int getCalories() {
            return calories;
        }

        public Type getType() {
            return type;
        }

        @Override
        public String toString() {
            return name + "-" + calories + "-" + type;
        }

        public enum Type {MEAT, FISH, OTHER}
    }
}
