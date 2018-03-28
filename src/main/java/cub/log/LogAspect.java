package cub.log;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class LogAspect {
    @AroundInvoke
    public Object logCall(InvocationContext context) throws Exception {
        System.out.println("@AroundInvoke Invoking method: " + context.getMethod());
        return context.proceed();
    }
}
