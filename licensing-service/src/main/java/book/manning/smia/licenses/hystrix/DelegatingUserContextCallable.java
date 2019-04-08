package book.manning.smia.licenses.hystrix;

import book.manning.smia.licenses.utils.UserContext;
import book.manning.smia.licenses.utils.UserContextHolder;

import java.util.concurrent.Callable;

public final class DelegatingUserContextCallable<V> implements Callable<V> {
    private final Callable<V> delegate;
    private UserContext userContext;

    public DelegatingUserContextCallable(Callable<V> delegate, UserContext userContext) {
        this.delegate = delegate;
        this.userContext = userContext;
    }

    @Override
    public V call() throws Exception {
        UserContextHolder.setContext(userContext);
        try {
            return delegate.call();
        } finally {
            this.userContext = null;
        }
    }

    public static <V> Callable<V> create(Callable<V> delegate, UserContext userContext) {
        return new DelegatingUserContextCallable<>(delegate, userContext);
    }
}
