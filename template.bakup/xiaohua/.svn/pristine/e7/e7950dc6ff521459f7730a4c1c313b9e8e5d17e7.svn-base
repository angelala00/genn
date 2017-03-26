package com.cjteam.xiao.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ChenLong on 14-3-3.
 */
public class ApplicationContextUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtils.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 根据名称获取一个对象.
     *
     * @param name
     * @return Object 一个以所给名字注册的bean的实例
     * @throws org.springframework.beans.BeansException
     */
    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    /**
     * 获取类型为requiredType的对象. 如果bean不能被类型转换，会抛出BeanNotOfRequiredTypeException.
     *
     * @param name
     *            bean注册名
     * @param requiredType
     *            返回对象类型
     * @return Object 返回requiredType类型对象
     * @throws org.springframework.beans.BeansException
     */
    public static Object getBean(String name, Class requiredType)
            throws BeansException {
        return applicationContext.getBean(name, requiredType);
    }

    /**
     * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true.
     *
     * @param name
     * @return boolean
     */
    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    /**
     * 判断以给定名字注册的bean定义是一个singleton还是一个prototype.
     * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常(NoSuchBeanDefinitionException).
     *
     * @param name
     * @return boolean
     * @throws org.springframework.beans.factory.NoSuchBeanDefinitionException
     */
    public static boolean isSingleton(String name)
            throws NoSuchBeanDefinitionException {
        return applicationContext.isSingleton(name);
    }

    /**
     * 获取给定名字的bean的类型.
     *
     * @param name
     * @return Class 注册对象的类型
     * @throws org.springframework.beans.factory.NoSuchBeanDefinitionException
     */
    public static Class getType(String name)
            throws NoSuchBeanDefinitionException {
        return applicationContext.getType(name);
    }

    /**
     * 根据Class类型返回由Spring托管的bean。 <br>
     * 如果出现多个，则默认返回找到的第一个非null的bean。<b>请谨慎使用本方法，确认参数clazz只有一个实现。</b>
     *
     * @param clazz
     * @author quanxiwei
     * @return
     */
    public static <T> T getType(Class<T> clazz) {
        Map map = applicationContext.getBeansOfType(clazz);
        if (map.size() == 0)
            return null;

        Set keys = map.keySet();
        for (Object object : keys) {
            Object obj = map.get(object);
            if (obj != null)
                return (T) obj;
        }
        return null;
    }

    public static <T> List<T> getBeansOfType(Class<T> clazz) {
        List<T> collection = new ArrayList<T>();
        Map map = ApplicationContextUtils.getApplicationContext()
                .getBeansOfType(clazz);
        Set keySet = map.keySet();
        for (Object key : keySet) {
            if (key == null)
                continue;

            Object listener = map.get(key);
            if (listener != null)
                collection.add((T) listener);
        }
        return collection;

    }

    /**
     * 如果给定的bean名字在bean定义中有别名，则返回这些别名.
     *
     * @param name
     * @return 别名数组
     * @throws org.springframework.beans.factory.NoSuchBeanDefinitionException
     */
    public static String[] getAliases(String name)
            throws NoSuchBeanDefinitionException {
        return applicationContext.getAliases(name);
    }
}
