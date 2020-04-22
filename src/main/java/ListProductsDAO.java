import java.util.ArrayList;
import java.util.List;

//this is our DAO for products
public class ListProductsDAO implements Products {

    //list all the products, find a product by id, create a product
    private List<Product> products;

    //constructor for DAO class
    public ListProductsDAO() {
        this.products = new ArrayList<>();

    }

    //TODO: implement our interface requirements
    @Override
    public Product findById(long id) {
        //we want to return the "product" object for the ID passed in.  this will return the full row in our DB.
        //i.e. id | title | priceInCents | description  (the full row)
        return products.get((int) id - 1);
    }

    @Override
    public long createProduct(Product product) {

        // create a product and insert to our arrayList (eventually, the DB)
        //assign an ID
        product.setId(products.size() + 1); //same as auto increment.  always unique.
        products.add(product); //when we call the createProduct method we are sending in a Product object, this will add to the arrayList.
        return product.getId();
    }
}
