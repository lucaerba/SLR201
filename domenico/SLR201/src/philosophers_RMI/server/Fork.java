package philosophers_RMI.server;

import philosophers_RMI.AbstractFork;
import philosophers_RMI.States;

import java.rmi.RemoteException;

public class Fork implements AbstractFork {
    private Fork leftFork;
    private Fork rightFork;
    protected int id;
    protected boolean deadlock;
    protected boolean foundAgreement;

    public Fork(int id){
        this.id = id;
        this.deadlock = false;
        this.foundAgreement = false;
    }

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
    @Override
    public States request_fork(int id, States state) throws RemoteException {
        deadlock = this.id == id;
        if(state == States.LEFT_ACQUIRED) {
            this.leftFork.foundAgreement = false;
            this.leftFork.deadlock = false;
            return States.COMPLETE;
        }
        if(state == States.LEFT_REFUSED) return States.RETRY;
        // From now on the state must be RETRY
        // This condition entails that if there is an already found agreement with the left fork it means that a philosopher is about
        // to ask for his right fork, therefore i must give him priority.
        // If the right fork has its deadlock variable set to true it means that it has to be considered occupied.
        if(this.leftFork.foundAgreement || this.rightFork.deadlock) return States.LEFT_REFUSED;

        // There is no problem with synchronization because even if another thread runs this code concurrently.
        // The agreement has been found with the right fork, whereas the other thread will see this as his right fork.
        // The behavior of the other thread will be only determined by this.deadlock variable.
        this.foundAgreement = true;
        return States.LEFT_ACQUIRED;
    }

    /**
     * Philosophers should use this method to eat.
     * It does nothing and is used only for completeness.
    */
    @Override
    public void eat() throws RemoteException, RuntimeException {
        // Consume
    }

    void setLeftFork(Fork leftFork) {
        this.leftFork = leftFork;
    }

    void setRightFork(Fork rightFork) {
        this.rightFork = rightFork;
    }

    
}
