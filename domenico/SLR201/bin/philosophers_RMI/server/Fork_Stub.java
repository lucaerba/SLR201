// Stub class generated by rmic, do not edit.
// Contents subject to change without notice.

package philosophers_RMI.server;

public final class Fork_Stub
    extends java.rmi.server.RemoteStub
    implements philosophers_RMI.AbstractFork
{
    private static final long serialVersionUID = 2;
    
    private static java.lang.reflect.Method $method_eat_0;
    private static java.lang.reflect.Method $method_request_fork_1;
    
    static {
	try {
	    $method_eat_0 = philosophers_RMI.AbstractFork.class.getMethod("eat", new java.lang.Class[] {});
	    $method_request_fork_1 = philosophers_RMI.AbstractFork.class.getMethod("request_fork", new java.lang.Class[] {int.class, philosophers_RMI.States.class});
	} catch (java.lang.NoSuchMethodException e) {
	    throw new java.lang.NoSuchMethodError(
		"stub class initialization failed");
	}
    }
    
    // constructors
    public Fork_Stub(java.rmi.server.RemoteRef ref) {
	super(ref);
    }
    
    // methods from remote interfaces
    
    // implementation of eat()
    public void eat()
	throws java.lang.RuntimeException, java.rmi.RemoteException
    {
	try {
	    ref.invoke(this, $method_eat_0, null, -143305049311162956L);
	} catch (java.lang.RuntimeException e) {
	    throw e;
	} catch (java.rmi.RemoteException e) {
	    throw e;
	} catch (java.lang.Exception e) {
	    throw new java.rmi.UnexpectedException("undeclared checked exception", e);
	}
    }
    
    // implementation of request_fork(int, States)
    public philosophers_RMI.States request_fork(int $param_int_1, philosophers_RMI.States $param_States_2)
	throws java.rmi.RemoteException
    {
	try {
	    Object $result = ref.invoke(this, $method_request_fork_1, new java.lang.Object[] {new java.lang.Integer($param_int_1), $param_States_2}, -7251744790384490247L);
	    return ((philosophers_RMI.States) $result);
	} catch (java.lang.RuntimeException e) {
	    throw e;
	} catch (java.rmi.RemoteException e) {
	    throw e;
	} catch (java.lang.Exception e) {
	    throw new java.rmi.UnexpectedException("undeclared checked exception", e);
	}
    }
}