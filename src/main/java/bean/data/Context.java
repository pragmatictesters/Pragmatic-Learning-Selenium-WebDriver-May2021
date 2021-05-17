package bean.data;

/**
 * This is the test context class Which will be injected via pico using dependency injection.
 * All the other POJO's can be created as variables with in the class.
 */

public class Context {

    private BaseAldiUser baseAldiUser;
    private Products products;
    private Order order;

    /**
     * Other pojo's must be initialized here.
     */
    public Context() {
        this.baseAldiUser = new BaseAldiUser();
        this.products = new Products();
        this.order = new Order();
    }

    public BaseAldiUser getBaseAldiUser() {
        return baseAldiUser;
    }

    public void setBaseAldiUser(BaseAldiUser baseAldiUser) {
        this.baseAldiUser = baseAldiUser;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
