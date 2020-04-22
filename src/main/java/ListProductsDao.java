import java.util.ArrayList;
import java.util.List;

//this is our DAO for products
public class ListProductsDao implements Products {

    //list all the products, find a product by id, create a product
    private List<Product> products;

    //constructor for DAO class
    public ListProductsDao() {
        this.products = new ArrayList<>();
        Product hammer = new Product();
        hammer.setId(1);
        hammer.setTitle("A Bad Hammer");
        hammer.setPriceInCents(3000);
        hammer.setDescription("A bad hammer.");

        products.add(hammer);

        Product xbox = new Product();
        xbox.setId(2);
        xbox.setTitle("Xbox Series X");
        xbox.setPriceInCents(50000);
        xbox.setDescription("Your kids are gonna love it.");

        products.add(xbox);

        Product chiaPet = new Product();
        chiaPet.setId(3);
        chiaPet.setTitle("Chia pet");
        chiaPet.setPriceInCents(1500);
        chiaPet.setDescription("A useless product for people who kill plants and pets.");

        products.add(chiaPet);
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

    @Override
    public Product all() {
        return null;
    }
}
