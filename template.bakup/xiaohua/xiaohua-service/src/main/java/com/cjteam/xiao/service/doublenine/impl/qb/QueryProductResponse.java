package com.cjteam.xiao.service.doublenine.impl.qb;

import com.cjteam.xiao.service.doublenine.DoubleNineRequest;
import com.cjteam.xiao.service.doublenine.DoubleNineResponse;
import com.cjteam.xiao.service.doublenine.model.DoubleNineProduct;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.Assert;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenLong
 * Date: 13-10-22
 */
@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class QueryProductResponse extends DoubleNineResponse {
    @XmlTransient
    private int count = 0;
    @XmlTransient
    private QueryProductRequest request;

    @XmlElement(name = "product")
    private List<DoubleNineProduct> products;

    public QueryProductResponse() throws JAXBException {
        products = new ArrayList<DoubleNineProduct>(10);
    }

    public int getCount() {
        return CollectionUtils.isEmpty(products) ? 0 : products.size();
    }

    public List<DoubleNineProduct> getProducts() {
        return products;
    }

    public void setProducts(List<DoubleNineProduct> products) {
        this.products = products;
        if (CollectionUtils.isNotEmpty(products))
            setCount(products.size());
    }

    private void setCount(int count) {
        this.count = count;
    }

    public QueryProductResponse addProduct(DoubleNineProduct product) {
        Assert.notNull(product);
        this.products.add(product);
        setCount(getCount() + 1);
        return this;
    }

    @Override
    public String toString() {
        if (CollectionUtils.isNotEmpty(getProducts())) {
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            sb.append("\tcount:").append("\"").append(getCount()).append("\"");
            sb.append(",").append("\n\t[\n");
            for (int index = 0; index < products.size(); index++) {
                sb.append("\t\t").append(products.get(index).toString());
                if (index < products.size() - 1)
                    sb.append(",");
                sb.append("\n");
            }
            sb.append("\t\t]").append("\n}\n");
            return sb.toString();
        }
        return null;
    }

    @Override
    public DoubleNineRequest getRequest() {
        return request;
    }
}
