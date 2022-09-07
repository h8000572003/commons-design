package io.github.h800572003.factory;

/**
 * PIZZA 店
 */
public class PizzaStore {

    /**
     *
     * @param type 類型
     * @return
     */
    public IPizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new CheesePizza();
        } else if (type.equals("veggie")) {
            return new VeggiePizza();
        }else{
            return new CheesePizza();
        }
    }
}
