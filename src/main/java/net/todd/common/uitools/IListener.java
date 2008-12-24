package net.todd.common.uitools;

public interface IListener {
	/**
	 * this contains the action that should occur when the event that this
	 * listener is waiting for happens
	 */
	void fireEvent();
}
