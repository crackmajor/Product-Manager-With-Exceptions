public class ProductManager {

    private Product[] productCart = new Product[0];

    public Product[] findAll() {
        return productCart;
    }

    public void addProduct(Product product) {
        if (this.findById(product.id) == null) {
            int length = this.productCart.length + 1;
            Product[] tmpProduct = new Product[length];
            for (int i = 0; i < this.productCart.length; i++) {
                tmpProduct[i] = this.productCart[i];
            }
            int lastIndex = tmpProduct.length - 1;
            tmpProduct[lastIndex] = product;
            this.productCart = tmpProduct;
            return;
        } else {
            throw new AlreadyExistsException("Element with id: " + product.id + " already exist");
        }
    }

    public void removeById(int id) {
        if (this.findById(id) == null) {
            throw new NotFoundException("Element with id: " + id + " not found");
        } else {
            int length = productCart.length - 1;
            Product[] tmp = new Product[length];
            int index = 0;
            for (Product product1 : productCart) {
                if (product1.id != id) {
                    tmp[index] = product1;
                    index++;
                    productCart = tmp;
                }
            }
        }
    }

    public Product findById(int id) {
        for (Product product : findAll()) {
            if (product.id == id) {
                return product;
            }
        }
        return null;
    }

    public boolean matches(Product product, String search) {
        if (product.productName.contains(search)) {
            return true;
        } else {
            return false;
        }
    }

    public Product[] searchBy(String text) {
        Product[] result = new Product[0];
        for (Product product : findAll()) {
            if (matches(product, text)) {
                int length = result.length + 1;
                Product[] tmp = new Product[length];
                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                int lastIndex = tmp.length - 1;
                tmp[lastIndex] = product;
                result = tmp;
            }
        }
        return result;
    }
}