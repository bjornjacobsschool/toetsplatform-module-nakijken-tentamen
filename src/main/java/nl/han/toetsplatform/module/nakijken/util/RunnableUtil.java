package nl.han.toetsplatform.module.nakijken.util;

import java.util.function.Consumer;

public class RunnableUtil {

    public static void runIfNotNull(Runnable runnable){
        if(runnable != null)
            runnable.run();
    }

    public static <T>  void runIfNotNull(Consumer<T> runnable, T val){
        if(runnable != null)
            runnable.accept(val);
    }
}
