package com.github.liuhuagui.gridexcel.eventmodel;

import com.github.liuhuagui.gridexcel.bean.CellListOfRow;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Use POI Event API to read excel，The advantage provided is that you can read a excel file with a relatively small memory footprint.
 *
 * @author KaiKang
 */
public abstract class EventModel {
    /**
     * the starting line number as a condition on enabling consumer function, 0-based
     */
    protected int startRow;
    /**
     * Each time a row of data is read, the function interface is called to execute the custom logic.
     */
    protected Consumer<CellListOfRow<String>> readConsumer;
    /**
     * Store record values that appear in a row of cells
     */
    protected List<String> rowCellValues;

    public EventModel(int startRow, Consumer<CellListOfRow<String>> readConsumer) {
        this.startRow = startRow;
        this.readConsumer = readConsumer;
        this.rowCellValues = new ArrayList<String>();
    }

    /**
     * Initiates the processing of the Excel file to Bean.
     *
     * @throws Exception
     */
    public abstract void process() throws Exception;

    /**
     * 启用消费函数的起始行
     *
     * @param startRow
     * @return
     */
    public EventModel startRow(int startRow) {
        this.startRow = startRow;
        return this;
    }

    /**
     * 设置消费函数。每读完一行记录（one row of records），调用该函数。
     *
     * @param readConsumer
     * @return
     */
    public EventModel readConsumer(Consumer<CellListOfRow<String>> readConsumer) {
        this.readConsumer = readConsumer;
        return this;
    }
}
