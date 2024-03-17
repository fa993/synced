package com.fa3.synced.collections.delegated;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DelegatedList<E> implements List<E> {

	protected List<E> inner;

	protected DelegatedList(List<E> inner) {
		this.inner = inner;
	}

	@Override
	public int size() {
		return inner.size();
	}

	@Override
	public boolean isEmpty() {
		return inner.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return inner.contains(o);
	}

	@Override
	public Iterator<E> iterator() {
		return inner.iterator();
	}

	@Override
	public Object[] toArray() {
		return inner.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return inner.toArray(a);
	}

	@Override
	public boolean add(E e) {
		return inner.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return inner.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return inner.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return inner.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		return inner.addAll(index, c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return inner.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return inner.retainAll(c);
	}

	@Override
	public void clear() {
		inner.clear();
	}

	@Override
	public E get(int index) {
		return inner.get(index);
	}

	@Override
	public E set(int index, E element) {
		return inner.set(index, element);
	}

	@Override
	public void add(int index, E element) {
		inner.add(index, element);
	}

	@Override
	public E remove(int index) {
		return inner.remove(index);
	}

	@Override
	public int indexOf(Object o) {
		return inner.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return inner.lastIndexOf(o);
	}

	@Override
	public ListIterator<E> listIterator() {
		return inner.listIterator();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return inner.listIterator(index);
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return inner.subList(fromIndex, toIndex);
	}

	@Override
	public boolean equals(Object o) {
		return inner.equals(o);
	}

	@Override
	public int hashCode() {
		return inner.hashCode();
	}
}
