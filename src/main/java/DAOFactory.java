//This DAO class will provide access to our Model data, but will not require the ListProductsDAO class directly. Instead we will reference the interface.

public class DAOFactory {
    private static Products productsDAO;

    public static Products getProductsDAO() {
        if (productsDAO == null){
            productsDAO = new ListProductsDAO(); //this is the only reference to the ListProductsDAO class.
        }
        // if it is not null return the productsDAO that already exists.
        return productsDAO;
    }
}
