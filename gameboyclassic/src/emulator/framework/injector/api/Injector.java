package emulator.framework.injector.api;

/**
 * Created with IntelliJ IDEA.
 * User: vicboma
 * Date: 14/05/14
 * Time: 20:03
 */
public interface Injector
{
	String POST_CONSTRUCTOR = "PostConstruct";
	String PRE_DESTROY = "PreDestroy";

	<T> T getInstance(Class<? extends T> _class);

	void destroy() throws Exception;

	void injectInto(Object instance) throws Exception ;

	<T> void mapValue(Class<T> _class, T instance);

	<T> void mapSingleton(Class<T> _classFrom, Class<? extends T> _classTo);

	<T> void mapLocal(Class<T> _classFrom, Class<? extends T> _classTo);

	Injector unmap(Class _class) ;

}
