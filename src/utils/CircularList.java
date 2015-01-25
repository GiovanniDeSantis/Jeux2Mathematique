package utils;

import java.util.ArrayList;

public class CircularList<T> extends ArrayList<T> {
	private static final long serialVersionUID = 7229535031375379443L;

	@Override
    public T get(int index) {
        return super.get(index % size());
    }
}
