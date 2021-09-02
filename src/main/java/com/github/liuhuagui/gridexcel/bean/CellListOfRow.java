package com.github.liuhuagui.gridexcel.bean;

import java.util.List;

/**
 * A special list wrapper for handling @{@link ArrayIndexOutOfBoundsException} due to Missing Cells at the end of row.
 *
 * @author liuhuagui
 */
public class CellListOfRow<T> {
    private List<T> baseList;

    /**
     * returning the value instead of throwing @{@link ArrayIndexOutOfBoundsException}.
     */
    private T defaultValue;

    /**
     * row Number (zero based)
     */
    private int rowNumber = -1;

    public CellListOfRow(List<T> baseList, T defaultValue) {
        this.baseList = baseList;
        this.defaultValue = defaultValue;
    }

    public CellListOfRow(List<T> baseList, T defaultValue, int rowNumber) {
        this.baseList = baseList;
        this.defaultValue = defaultValue;
        this.rowNumber = rowNumber;
    }

    public T get(int index) {
        return index >= baseList.size() ? defaultValue : baseList.get(index);
    }

    public List<T> baseList() {
        return baseList;
    }

    public int size() {
        return baseList.size();
    }

    public int getRowNumber() {
        return rowNumber;
    }

}
