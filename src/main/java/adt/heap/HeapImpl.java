package adt.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o menor sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 2 < 3),
 * essa heap deixa os elementos menores no topo. Essa comparação não é feita
 * diretamente com os elementos armazenados, mas sim usando um comparator. 
 * Dessa forma, dependendo do comparator, a heap pode funcionar como uma max-heap 
 * ou min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

	protected T[] heap;
	protected int index = -1;
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é
	 * mudar apenas o comparator e mandar reordenar a heap usando esse
	 * comparator. Assim os metodos da heap não precisam saber se vai funcionar
	 * como max-heap ou min-heap.
	 */
	protected Comparator<T> comparator;

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma
	 * min-heap. OU seja, voce deve considerar que a heap usa o comparator
	 * interno e se o comparator responde compare(x,y) < 0 entao o x eh menor
	 * e sobe na heap.
	 */
	@SuppressWarnings("unchecked")
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	// /////////////////// METODOS IMPLEMENTADOS
	private int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento
	 * indexado pela posicao i no vetor
	 */
	private int left(int i) {
		return (i * 2 + 1);
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento
	 * indexado pela posicao i no vetor
	 */
	private int right(int i) {
		return (i * 2 + 1) + 1;
	}
	
	private boolean isValidIndex(int number) {
		return number >= 0 && number < this.index;
	}
	
	private boolean isLeaf(int number) {
		return number > this.parent(this.index) && number <= this.index;
	}
	
	private int compareIndex(int parent, int left, int right) {
		int result;
        if (this.comparator.compare(this.heap[parent], this.heap[left]) == 1) {
            if (
            		this.isValidIndex(right) && 
            		this.comparator.compare(this.heap[parent], this.heap[right]) == -1
            	) {
            	result = right;
            } else {
            	result = parent;
            }
        } else {
                if (
                		this.isValidIndex(right) &&
                		this.comparator.compare(this.heap[left], this.heap[right]) == -1
                	) {
                    result = right;
                } else {
                	result = left;
                }
        }
        return result;
    }
	
	@Override
	public boolean isEmpty() {
		return (index == -1);
	}

	@Override
	public T[] toArray() {
		ArrayList<T> resp = new ArrayList<T>();
		for (int i = 0; i <= this.index; i++) {
			resp.add(this.heap[i]);
		}
		return (T[])resp.toArray(new Comparable[0]);
	}

	// ///////////// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode
	 * ser a raiz da heap ou de uma sub-heap. O heapify deve usar o comparator
	 * para subir os elementos na heap.
	 */
	private void heapify(int position) {
		if (!isLeaf(position) && this.isValidIndex(position)) {
				int toSwitch = this.compareIndex(position, this.left(position), this.right(position));
				
				if (toSwitch != position) {
	                Util.swap(this.heap, position, toSwitch);
	                heapify(toSwitch);
			}
		}
	}

	@Override
	public void insert(T element) {
		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}
		// /////////////////////////////////////////////////////////////////
		
		
		this.index += 1;
		this.heap[index] = element;
		
		int i = index;
		while (i > 0 && this.comparator.compare(this.heap[this.parent(i)], this.heap[i]) == -1) {
			T aux = this.heap[i];
	        this.heap[i] = this.heap[this.parent(i)];
	        this.heap[this.parent(i)] = aux;
	        i = this.parent(i);
		}
	}

	@Override
	public void buildHeap(T[] array) {
		this.heap = array;
		this.index = array.length - 1;
		for (int i = this.parent(this.index); i >= 0; i--)
            heapify(i);
			
	}

	@Override
	public T extractRootElement() {
		T root = null;
		if (!this.isEmpty()) {
			root = this.heap[0];
			this.heap[0] = this.heap[index];
			this.heap[index] = root;
			this.index -= 1;
			
			this.heapify(0);
		}
        return root;
	}

	@Override
	public T rootElement() {
		T root = null;
		if (!this.isEmpty()) {
			root = this.heap[0];
		}
		return root;
	}

	@Override
	public T[] heapsort(T[] array) {
		this.heap = array;
		this.buildHeap(array);
		while (this.size() > 0) {
			Util.swap(this.heap, 0, this.index);
			this.index--;
			this.heapify(0);
		}
		if (this.comparator instanceof ComparatorMinHeap) {
			int j = this.heap.length - 1;
			for (int i = 0; i < this.heap.length / 2; i++) {
				Util.swap(this.heap, i, j);
				j--;
			}
		}
		return this.heap;
	}

	@Override
	public int size() {
		return this.index + 1;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}

}
