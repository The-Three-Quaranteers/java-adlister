public interface Products {

    //this method will return an object of type "product" searched by id
    Product findById(long id);

    //this method will insert a "product" into our table, the return of this will be said products ID
    long createProduct(Product product);

    //could add extra method requirements to update/delete/etc.
    //i.e. deleteProduct(long id); {}
    //i.e. updateProduct(long id); {}


}
