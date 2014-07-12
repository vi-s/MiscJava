import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Vikram on 7/12/2014.
 *
 * Duck typing through use of reflection and a Proxy class
 */
public class DuckTyping {

    interface Walkable { void walk(); }
    interface Swimmable { void swim(); }
    interface Quackable { void quack(); }

    public static void main(String[] args){
        Duck duck = new Duck();
        Human human = new Human();

        getProxy(Walkable.class, duck).walk();
        getProxy(Swimmable.class, duck).swim();
        getProxy(Quackable.class, duck).quack();

        getProxy(Walkable.class, human).walk();
        getProxy(Swimmable.class, human).swim();
        getProxy(Quackable.class, human).quack();
    }

    /**
     *
     * @param interfaceClass The interface for the proxy class to implement, so that the
     *                       method we want to call on object 'o' can be called through the proxy
     *                       class instance
     * @param o object that we're attemping to call a method on
     * @param <T> The type of the interfaceClass param we're passing in. The proxy class instance ends up
     *           being the type T thereby utilizing polymorphism.
     * @return
     */

    @SuppressWarnings("unchecked")
    static <T> T getProxy(Class<T> interfaceClass, final Object o){
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[] {interfaceClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        try{
                            return o.getClass()
                                    .getMethod(method.getName(), method.getParameterTypes())
                                    .invoke(o, args);
                        } catch (NoSuchMethodException e){
                            throw new NoSuchMethodException(e.getMessage());
                        } catch (InvocationTargetException e){
                            throw e.getTargetException();
                        }
                    }
                });
    }
}

class Duck {
    public void walk() {System.out.println("Duck walking.");}
    public void swim() {System.out.println("Duck swimming.");}
    public void quack() {System.out.println("Duck quacking.");}
}

class Human {
    public void walk() {System.out.println("Human walking.");}
    public void swim() {System.out.println("Human swimming.");}
    public void quack() {System.out.println("Human quacking.");}
}