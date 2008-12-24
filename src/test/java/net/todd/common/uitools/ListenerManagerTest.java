package net.todd.common.uitools;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ListenerManagerTest {
	private int firstListenerFireCount;
	private int secondListenerFireCount;

	@Before
	public void setUp() {
		firstListenerFireCount = 0;
		secondListenerFireCount = 0;
	}

	@Test
	public void testListenerManagerUsingOneListenerFiringOnce() {
		ListenerManager manager = new ListenerManager();
		manager.addListener(new IListener() {
			public void fireEvent() {
				firstListenerFireCount++;
			}
		});
		
		assertEquals(0, firstListenerFireCount);
		manager.notifyListeners();
		assertEquals(1, firstListenerFireCount);
	}
	
	@Test
	public void testListenerManagerUsingOneListenerFiringMultipleTimes() {
		ListenerManager manager = new ListenerManager();
		manager.addListener(new IListener() {
			public void fireEvent() {
				firstListenerFireCount++;
			}
		});

		assertEquals(0, firstListenerFireCount);
		manager.notifyListeners();
		manager.notifyListeners();
		manager.notifyListeners();
		assertEquals(3, firstListenerFireCount);
	}
	
	@Test
	public void testListenerManagerUsingMultipleListenerFiringMultipleTimes() {
		ListenerManager manager = new ListenerManager();
		manager.addListener(new IListener() {
			public void fireEvent() {
				firstListenerFireCount++;
			}
		});

		assertEquals(0, firstListenerFireCount);
		manager.notifyListeners();
		assertEquals(1, firstListenerFireCount);

		manager.addListener(new IListener() {
			public void fireEvent() {
				secondListenerFireCount++;
			}
		});

		manager.notifyListeners();
		manager.notifyListeners();
		manager.notifyListeners();

		assertEquals(4, firstListenerFireCount);
		assertEquals(3, secondListenerFireCount);
	}
	
	@Test
	public void testListenerManagerUsingNoListeners() {
		ListenerManager manager = new ListenerManager();
		manager.notifyListeners();
	}
	
	@Test
	public void testListenerManagerAddingSameListenerMultipleTimes() {
		ListenerManager manager = new ListenerManager();

		IListener listener1 = new IListener() {
			public void fireEvent() {
				firstListenerFireCount++;
			}
		};

		manager.addListener(listener1);
		manager.addListener(listener1);

		assertEquals(0, firstListenerFireCount);
		manager.notifyListeners();
		manager.notifyListeners();
		manager.notifyListeners();
		assertEquals(3, firstListenerFireCount);
	}
}
