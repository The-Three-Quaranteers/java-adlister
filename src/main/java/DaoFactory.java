//This DAO class will provide access to our Model data, but will not require the ListProductsDAO class directly. Instead we will reference the interface.

public class DaoFactory {
    private static Products productsDao;

    public static Products getProductsDao() {
        if (productsDao == null){
            productsDao = new ListProductsDao(); //this is the only reference to the ListProductsDAO class.
        }
        // if it is not null return the productsDAO that already exists.
        return productsDao;
    }
}
