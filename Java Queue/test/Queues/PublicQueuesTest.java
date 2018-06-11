package Queues;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import structures.Queue;


public class PublicQueuesTest {
	
	
	
	@Test
	public void testReversed(){
		Queue<Integer> intQueue = new Queue<Integer>();
		intQueue.enqueue(1);
		intQueue.enqueue(2);
		intQueue.enqueue(3);
		intQueue.enqueue(4);
		intQueue.enqueue(5);
		Queue<Integer> twoQueue = new Queue<Integer>();
		twoQueue.enqueue(5);
		twoQueue.enqueue(4);
		twoQueue.enqueue(3);
		twoQueue.enqueue(2);
		twoQueue.enqueue(1);
		intQueue.reversed();
		assertTrue(intQueue == twoQueue);
	}
}
