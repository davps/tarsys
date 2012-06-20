package com.logicalnode.tarsys.repository;

import java.util.List;

public interface GenericDAO<T> {
	public T create(T item);
	public T update(T item);
	public void remove(T item);
	public void removeById(Long id);
	public void flush(T item);
	public T clear(T item);
	public T merge(T item);
	public long countEntities();
	public List<T> findAll();
	public T find(Long id);
}
