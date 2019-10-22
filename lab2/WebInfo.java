public class WebInfo {
    WebInfo(String url, String pageLimit, String productNameClass, String priceClass, String parametersClass, String imageClass) {
        this.url = url;
        this.pageLimit = pageLimit;
        this.productNameClass = productNameClass;
        this.priceClass = priceClass;
        this.parametersClass = parametersClass;
        this.imageClass = imageClass;
    }

    String url, pageLimit, productNameClass, priceClass, parametersClass, imageClass;


    public String getImageClass() {
        return imageClass;
    }

    public void setImageClass(String imageClass) {
        this.imageClass = imageClass;
    }

    public String getPageLimit() {
        return pageLimit;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProductNameClass() {
        return productNameClass;
    }

    public void setProductNameClass(String productNameClass) {
        this.productNameClass = productNameClass;
    }

    public String getPriceClass() {
        return priceClass;
    }

    public void setPriceClass(String priceClass) {
        this.priceClass = priceClass;
    }

    public String getParametersClass() {
        return parametersClass;
    }

    public void setParametersClass(String parametersClass) {
        this.parametersClass = parametersClass;
    }
}
