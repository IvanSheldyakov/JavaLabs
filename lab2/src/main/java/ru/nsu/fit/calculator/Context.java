package ru.nsu.fit.calculator;



import ru.nsu.fit.calculator.exceptions.EmptyStackException;
import ru.nsu.fit.calculator.exceptions.IncorrectSizeException;
import ru.nsu.fit.calculator.exceptions.ThereIsNoSuchKeyException;

import java.util.ArrayDeque;
import java.util.Deque;



import java.util.HashMap;
import java.util.NoSuchElementException;

public class Context {
    private final Deque<Double> stack = new ArrayDeque<>();
    private final HashMap<String, Double> constants = new HashMap<>();

    public Double pop() throws EmptyStackException{

        try {
            Double value = stack.pop();
            return value;
        } catch (NoSuchElementException e) {
            throw new EmptyStackException("stack is empty");
        }
    }

    public int getStackSize() {
        return stack.size();
    }

    public Double peek() {
        Double value = stack.peek();
        if (value == null) {
            throw new EmptyStackException("stack is empty");
        } else {
            return value;
        }
    }

    public void push(Double value) {
        stack.push(value);
    }

    public Double[] getElements(int size) {
        if (size > getStackSize()) {
            throw new IncorrectSizeException("size is too big");
        }
        Double[] values = new Double[size];
        for (int i = 0; i < size; i++) {
            values[i] = pop();
        }
        for (int i = size - 1; i >= 0; i--) {
            push(values[i]);
        }
        return values;
    }

    public Double getValueByKey(String key) throws ThereIsNoSuchKeyException {
        Double value = constants.get(key);
        if (value == null) {
            throw new ThereIsNoSuchKeyException(key + " doesn't exist");
        }
        return value;
    }

    public void setConstant(String key, Double value) {
        constants.put(key, value);
    }

    @Override
    public String toString() {
        return "Context{" +
                "stack=" + stack +
                ", constants=" + constants +
                '}';
    }
}
