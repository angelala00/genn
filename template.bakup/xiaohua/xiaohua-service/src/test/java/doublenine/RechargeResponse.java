package doublenine;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringWriter;

/**
 * Created by ChenLong
 * Date: 13-9-17
 */

@XmlRootElement(name = "return")
@XmlAccessorType(XmlAccessType.FIELD)
public class RechargeResponse {
    @XmlElement(name = "result")
    private Integer result;
    @XmlElement(name = "sign")
    private String sign;

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        JAXBContext jc = null;
        try {
            jc = JAXBContext.newInstance(RechargeResponse.class);
            Marshaller marshaller = jc.createMarshaller();
            StringWriter writer = new StringWriter();
            marshaller.marshal(this, writer);
            return writer.toString();
        } catch (JAXBException e) {
        }
        return super.toString();
    }
}
