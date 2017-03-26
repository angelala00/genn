package controller;

import javapns.Push;

/**
 * User: wuyimin
 * Date: 14-1-13
 */
public class IOSClientMessageProvider {
    public static void main(String[] args) {
        String[] tokens = {"b17c27bd83469ae6819b600b36a000643ba9b6acc7ca1708263637d7dda8b86a"
                , "f1f10e6c36a835b91535cbad681013af49d9c49f322b3bc085a7d5efd7263e98"
                ,
                "a1de3ff9d6424738117792e89db71a094f9742d37c98569c601d508d183c03a3"};
        try {
            for (String token : tokens) {
                Push.alert("Hello World - false !", "D:\\message.p12", "", false, token);
                Push.alert("Hello World - true !", "D:\\message.p12", "", true, token);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
