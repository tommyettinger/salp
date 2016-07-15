package com.github.tommyettinger.ds;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Tensors are the construction in math that generalizes vectors and matrices; this one is generic.
 * Created by Tommy Ettinger on 7/15/2016.
 */
public class Tensor<T> implements Serializable {
    private static final long serialVersionUID = 0L;
    public ArrayList<T> data;
    public Shape shape;
    public T defaultValue = null;
    private Tensor()
    {
        data = null;
        shape = null;
    }
    public Tensor(Collection<T> data, Shape shape)
    {
        this.data = new ArrayList<>(data);
        this.shape = shape;
    }
    public Tensor(T[] data, Shape shape)
    {
        if(data == null)
            return;
        this.data = new ArrayList<>(data.length);
        Collections.addAll(this.data, data);
        this.shape = shape;
    }
    public Tensor(T[] data, Shape shape, T defaultValue)
    {
        if(data == null)
            return;
        this.data = new ArrayList<>(data.length);
        Collections.addAll(this.data, data);
        this.shape = shape;
        this.defaultValue = defaultValue;
    }
    public ArrayList<T> get(int... index)
    {
        if (shape == null)
        {
            return new ArrayList<>(0);
        }
        int[] indices = shape.at(index);
        ArrayList<T> found = new ArrayList<>(indices.length);
        for (int i = 0, idx; i < indices.length; i++) {
            idx = indices[i];
            if(idx >= 0 && idx < data.size())
                found.add(data.get(idx));
            else
                found.add(defaultValue);
        }
        return found;
    }
    public T[] getInto(T[] existing, int... index)
    {
        if (shape == null || existing == null)
        {
            return existing;
        }
        int[] indices = shape.at(index);
        for (int i = 0, idx; i < indices.length && i < existing.length; i++) {
            idx = indices[i];
            if(idx >= 0 && idx < data.size())
                existing[i] = data.get(idx);
            else
                existing[i] = defaultValue;
        }
        return existing;
    }
}
