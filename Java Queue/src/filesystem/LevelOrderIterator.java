package filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;

import structures.Queue;

/**
 * An iterator to perform a level order traversal of part of a 
 * filesystem. A level-order traversal is equivalent to a breadth-
 * first search.
 */
public class LevelOrderIterator extends FileIterator<File> {
	Queue<File> newQueue;
	/**
	 * Instantiate a new LevelOrderIterator, rooted at the rootNode.
	 * @param rootNode
	 * @throws FileNotFoundException if the rootNode does not exist
	 */
	public LevelOrderIterator(File rootNode) throws FileNotFoundException {
        	// TODO 1
			if(!rootNode.exists() || rootNode == null){
				throw new FileNotFoundException();
			}
			else {
				newQueue = new Queue<File>();
				Queue<File> otherQueue = new Queue<File>();
				newQueue.enqueue(rootNode);
				otherQueue.enqueue(rootNode);
				while(!otherQueue.isEmpty()){
					if(otherQueue.peek().isDirectory()){
					File[] files = otherQueue.peek().listFiles();
					Arrays.sort(files);
					for(int i = 0; i < files.length; i++){
						newQueue.enqueue(files[i]);
						if(files[i].isDirectory())
							otherQueue.enqueue(files[i]);
						}
					}
					otherQueue.dequeue();	
				}
			}
	}
	
	@Override
	public boolean hasNext() {
        	// TODO 2
            return !newQueue.isEmpty();
	}

	@Override
	public File next() throws NoSuchElementException {
        	// TODO 3
			if(newQueue.isEmpty()){
				throw new NoSuchElementException();
			}
			return newQueue.dequeue();
	}

	@Override
	public void remove() {
		// Leave this one alone.
		throw new UnsupportedOperationException();		
	}

}
