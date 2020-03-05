package com.github.liuhuagui.gridexcel.usermodel.read;

import com.github.liuhuagui.gridexcel.util.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Read excel file.采用控制窗口的方式读取Excel内容，当满足控制条件时执行自定义逻辑。
 *
 * @param <S> 需要处理的数据
 * @param <T> 返回的数据
 * @author KaiKang
 * @since JDK1.8
 */
public abstract class ReadExcel<S, T> {
    /**
     * Read excel rows to the list object.
     */
    protected List<T> list;

    /**
     * When the data is read from Excel, the size of the control window of 0 means no control, and N(n∈N+) means that the listener function is executed as long as the size of <code>list</code> reaches N.
     * <br><b>Default 0，不做窗口控制，不启用监听函数</b>
     */
    protected int windowSize;

    /**
     * The listen function of the Read operation. Used to execute custom logic, for example, to promote GC and reduce memory.<br>
     * 默认为空方法，表示什么都不做。
     */
    protected Consumer<List<T>> windowListener;

    public ReadExcel() {
        this.windowSize = 0;//不做窗口控制，不启用监听函数
        this.windowListener = ts -> {
        };//什么都不做
    }

    /**
     * 返回读入的Excel内容集合。如果监听函数被启用（windowSize设置为正整数），该方法将返回null。
     *
     * @return
     */
    public List<T> get() {
        return this.list;
    }

    /**
     * <ol>
     * <li>设置从Excel读入数据时的控制窗口大小。0表示不控制大小，n（n∈N+）表示只要<code>list</code>中元素个数达到n，调用监听函数。</li>
     * <li>设置窗口监听函数。The listen function of the Read operation. Used to execute custom logic, for example, to promote GC and reduce memory.</li>
     * <li>如果监听函数被启用（windowSize设置为正整数），{@link #get()} 将返回null。</li>
     * </ol>
     * <b>注意：该方法需要在 {@link #process(Function, int)} 之前调用</b>
     *
     * @param windowSize     默认0，不做窗口控制，不启用监听函数
     * @param windowListener 默认为空方法，表示什么都不做。
     * @return
     */
    public ReadExcel<S, T> window(int windowSize, Consumer<List<T>> windowListener) {
        this.windowSize = windowSize;
        this.windowListener = windowListener;
        return this;
    }

    /**
     * 处理读入的Excel数据流
     *
     * @param readFunction 读入Excel数据时需要调用的处理函数，表示如何处理 {@link S} 数据。输入是 {@link S}，返回值是{@link T}
     * @param startNum     起始行，0-based
     * @return
     * @throws IOException
     */
    public ReadExcel<S, T> process(Function<S, T> readFunction, int startNum) throws Exception {
        Assert.notNull(readFunction, "Parameter readFunction can't be null.");
        if (Objects.isNull(list)) {
            if (windowSize == 0)//不做大小控制
                list = new ArrayList<T>();
            else//使用窗口大小作为数组容量initialCapacity
                list = new ArrayList<T>(windowSize);
        }
        return null;
    }

    /**
     * 调用监听函数，执行自定义逻辑
     */
    protected void invokeListener() {
        //如果相等，windowSize一定不为0，所以windowSize=0，可以表示不启用监听函数。
        if (list.size() == windowSize) {
            //监听器执行自定义逻辑
            windowListener.accept(list);
            //重新初始化list，保证之前的数据被GC
            list = new ArrayList<T>(windowSize);
        }
    }

    /**
     * Fixed a bug in version 2.2 that prevented the windowListener from processing all rows
     * from excel when the total number of rows could not be divided exactly by the windowSize.<br>
     * <b>Note：</b>This method should be called at the end of the {@link #process(Function, int)} method
     */
    protected void clearList() {
        if (windowSize > 0 && list.size() > 0) {
            //监听器执行自定义逻辑
            windowListener.accept(list);
            //执行到这里，excel已经被全部读取，
            //将list赋值null（促使list在this失活前不可达，可提前回收这部分内存）
            list = null;
        }
    }

}