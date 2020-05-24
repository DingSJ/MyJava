package mqtest.thread2.threadlocal;

public class SystemContext {
    private static final ThreadLocal<Context> CONTEXT = new ThreadLocal(){
        @Override
        protected Object initialValue() {
            return new Context();
        }
    };
    private SystemContext(){}

    private static class ContextHolder{
        private static final SystemContext SYSTEM_CONTEXT = new SystemContext();
    }

    public static SystemContext getSystemContext(){
        return ContextHolder.SYSTEM_CONTEXT;
    }
    public Context getContext(){
        return CONTEXT.get();
    }
}
