package com.github.tommyettinger.ds;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Tensors are the construction in math that generalizes vectors and matrices; this one is generic.
 * Created by Tommy Ettinger on 7/15/2016.
 */
public class Tensor<T> implements Serializable {
    private static final long serialVersionUID = 0L;
    protected ArrayList<T> data;
    protected Shape shape;
    public T defaultValue = null;
    public boolean wrapBounds = false;
    private Tensor()
    {
        data = null;
        shape = null;
    }
    public Tensor(Collection<T> data, Shape shape)
    {
        if(data == null)
            this.data = new ArrayList<>();
        else
            this.data = new ArrayList<>(data);
        this.shape = shape;
    }
    public Tensor(Collection<T> data, Shape shape, T defaultValue)
    {
        if(data == null)
            this.data = new ArrayList<>();
        else
            this.data = new ArrayList<>(data);
        this.shape = shape;
        this.defaultValue = defaultValue;
    }

    public Tensor(Collection<T> data, Shape shape, boolean wrap)
    {
        if(data == null)
            this.data = new ArrayList<>();
        else
            this.data = new ArrayList<>(data);
        this.shape = shape;
        wrapBounds = wrap;
    }
    public Tensor(T[] data, Shape shape)
    {
        if(data == null)
            this.data = new ArrayList<>();
        else {
            this.data = new ArrayList<>(data.length);
            Collections.addAll(this.data, data);
        }
        this.shape = shape;
    }
    public Tensor(T[] data, Shape shape, T defaultValue)
    {
        if(data == null)
            this.data = new ArrayList<>();
        else {
            this.data = new ArrayList<>(data.length);
            Collections.addAll(this.data, data);
        }
        this.shape = shape;
        this.defaultValue = defaultValue;
    }
    public Tensor(T[] data, Shape shape, boolean wrap)
    {
        if(data == null)
            this.data = new ArrayList<>();
        else {
            this.data = new ArrayList<>(data.length);
            Collections.addAll(this.data, data);
        }
        this.shape = shape;
        wrapBounds = wrap;
    }
    public T at(int... index) {
        int idx = shape.at(index), sz = data.size();
        if (wrapBounds) {
            return data.get((idx % sz + sz) % sz);
        } else {
            if(idx >= 0 && idx < sz)
                return data.get(idx);
            return defaultValue;
        }
    }

    public List<T> fetch(int... index)
    {
        if (shape == null || index == null)
        {
            return new ArrayList<>(0);
        }
        int[] indices = shape.multipleAt(index);
        int sz = data.size();
        ArrayList<T> found = new ArrayList<>(indices.length);
        if(wrapBounds)
        {
            for (int i = 0; i < indices.length; i++) {
                found.add(data.get((indices[i] % sz + sz) % sz));
            }
        }
        else
        {
            for (int i = 0, idx; i < indices.length; i++) {
                idx = indices[i];
                if(idx >= 0 && idx < sz)
                    found.add(data.get(idx));
                else
                    found.add(defaultValue);
            }

        }
        return found;
    }
    public T[] fetchInto(T[] existing, int... index)
    {
        if (shape == null || index == null || existing == null)
        {
            return existing;
        }
        int[] indices = shape.multipleAt(index);
        int sz = data.size();
        if(wrapBounds)
        {
            for (int i = 0; i < indices.length && i < existing.length; i++) {
                existing[i] = data.get((indices[i] % sz + sz) % sz);
            }
        }
        else
        {
            for (int i = 0, idx; i < indices.length && i < existing.length; i++) {
                idx = indices[i];
                if(idx >= 0 && idx < sz)
                    existing[i] = data.get(idx);
                else
                    existing[i] = defaultValue;
            }
        }
        return existing;
    }

    public ArrayList<T> getData() {
        return data;
    }

    public void setData(ArrayList<T> data) {
        if(data != null)
            this.data = data;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        if(shape != null)
            this.shape = shape;
    }

    public T getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(T defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isWrapBounds() {
        return wrapBounds;
    }

    public void setWrapBounds(boolean wrapBounds) {
        this.wrapBounds = wrapBounds;
    }
    /*

    private ArrayList<T> loopingRepeat(ArrayList<T> basis, int length)
    {
        ArrayList<T> next = new ArrayList<>(basis);
        int start = next.size(), curr;
        for (curr = start; curr + start < length; curr += start)
        {
            next.addAll(basis);
        }
        next.addAll(basis.subList(0, length - curr));
        return next;
    }
     */
}
