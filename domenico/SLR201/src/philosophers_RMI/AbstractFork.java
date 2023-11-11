package philosophers_RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AbstractFork extends Remote {
    /**
     * Philosophers should use this method to to request a fork to the server.
     * This works using a finite state machine and an intra-forks agreement.
     * @param id the id of the requestor.
     * @param state the state of the requestor.
     * @return state of the requesting philosopher:
     * <ul>
     *    <li> LEFT_ACQUIRED if the requestor was in RETRY state and an agreement with the right fork has been found.
     *    <li> LEFT_REFUSED if the requestor was in RETRY state and no agreement was found.
     *    <li> COMPLETE if the requestor was in LEFT_ACQUIRED state
     *    <li> RETRY if the requestor was in LEFT_REFUSED state
     * </ul>
     * */
    public States request_fork(int id, States state) throws RemoteException;

    /**
     * Philosophers should use this method to eat.
     * It does nothing and is used only for completeness.
    */
    public void eat() throws RemoteException, RuntimeException;
}
