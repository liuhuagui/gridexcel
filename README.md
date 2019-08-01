
---
- **[快速使用](https://github.com/liuhuagui/gridexcel#%E5%BF%AB%E9%80%9F%E4%BD%BF%E7%94%A8)**
- **[GitHUb地址【https://github.com/liuhuagui/gridexcel】](https://github.com/liuhuagui/gridexcel)**
### Apache POI
在业务开发中我们经常会遇到Excel的导入导出，而 **Apache POI** 是Java开发者常用的API。
【[https://poi.apache.org/components/spreadsheet/index.html](https://poi.apache.org/components/spreadsheet/index.html)】
### GridExcel
> Universal solution for reading and writing simply Excel based on functional programming and POI EventModel

GridExcel是基于Java8函数式编程和POI EventModel实现的用于Excel简单读写的通用解决方案。

- 基于POI EventModel，在读写数据量非常大的Excel时，降低内存占用避免OOM与频繁FullGC
- 基于函数编程，支持关联对象等多种复杂情况的处理，学习成本低
- 支持流式API，使代码编写和理解更简单，更直观
### EventModel
什么是**EventModel**？在**POI FAQ**（常见问题解答）【[https://poi.apache.org/help/faq.html#faq-N100C2](https://poi.apache.org/help/faq.html#faq-N100C2)】官方给出解释：
> The SS eventmodel package is an API for reading Excel files without loading the whole spreadsheet into memory. It does require more knowledge on the part of the user, but reduces memory consumption by more than tenfold. It is based on the AWT event model in combination with SAX. If you need read-only access, this is the best way to do it.

SS eventmodel包是一个用于读取Excel文件而不将整个电子表格加载到内存中的API。 它确实需要用户掌握更多知识，但是将内存消耗减少了十倍以上。 它基于AWT（Abstract Window Toolkit）event model与SAX的结合。 如果您需要只读访问权限，这是最好的方式。
### 函数编程
说到函数编程，就不得不提**Lambda表达式**，如果对Java8中的Lambda不了解或理解不深刻，可以看下甲骨文官网给出的这篇文章，【[https://www.oracle.com/technetwork/articles/java/architect-lambdas-part1-2080972.html](https://www.oracle.com/technetwork/articles/java/architect-lambdas-part1-2080972.html)】，个人认为这是Java8 Lambda从入门到进阶最好的文章之一。

其中函数编程的目的就是实现`代码块传递`，即，将方法作为参数在方法间传递。为此，随着Java语言的发展，不断出现一些解决方案：
1. Java 1.0， 使用Abstract Window Toolkit (AWT)  EventModel来实现，但笨拙且不可行
2. Java 1.1，提出一系列“Listeners”
3. 后来使用`内部类`和`匿名内部类`来实现，但是大多数情况下，它们只是被用作事件处理。
4. 再后来发现更多地方将代`码块作为对象`(实际上是数据)不仅有用而且是必要的，但是Java中函数编程还是很笨拙，它需要成长。
5. 直到Java 1.7，Java引入了java.lang.invoke包，提供一 种新的动态确定目标方法的机制（可以不用再单纯依靠固化在虚拟机中的字节码调用指令），称为MethodHandle，模拟字节码的方法指针调用，类似于C/C++的**Function Pointer**（函数指针）并引入第5条方法调用的字节码指令**invokedynamic**。
6. 直到Java 1.8，基于Java 1.7提出的字节码指令**invokedynamic**，实现了Lamda技术，将函数作为参数在方法间传递，Java开始更好的支持函数式编程。
7. 用反射不是早就可以实现了吗？Reflection API 重量级，性能低。

注意： **5、6、7**参考《深入理解Java虚拟机》第2版，8.3.3 动态类型语言支持。

---
在POI的使用过程中，对大多数API User来说经常面临两个问题，这也是**GridExcel**致力解决的问题。
### 问题1. 仅使用简单的导入导出功能，但每次业务的数据对象结构不同，需要重新编写处理方法，很麻烦！
#### 解决方法
将Excel处理逻辑抽取出来，封装成工具类。
#### 封装条件
与大多数Java API一样，POI把更多的精力放在高级功能的处理上，比如Formula（公式）、Conditional Formatting（条件格式）、Zoom（缩放）等。对于仅仅做数据导入导出功能的API User，很少使用这些高级特性，这允许API用户对POI的使用进行简单的封装。
#### 封装方式
无论是读是写，我们都需要解决Excel中的Columns（列）与Java数据对象Fields（字段）的映射关系，将这种映射关系作为参数（Map对象HashMap或LinkedHashMap），传递给工具类。

对于Columns不难理解，它可以是有序的数字或字母，也可以是其它字符串用来作为首行，表示该列数据的含义。

对于Fields，它的处理需要兼容复杂情况，如下：

- 查询字段时出现异常
- 字段或单元格的值为null
- 该列的值可能对应关联对象、甚至是关联集合中的某个字段值
- 字段或单元格的值需要做特殊处理，例如`value == true?完成：失败；`

##### 反射
首先想到，也是大多数封装者都在使用的方式是就是**Reflection API**，从上文 **[函数编程](https://blog.csdn.net/qq_32331073/article/details/97650960#_15)** 章节我们了解到，反射重量级，会降低代码的性能，同时对复杂情况的处理支持性不够好。
##### 反射+注解
这种方式可以更好的支持复杂情况，但是反射依然会降低性能，同时注解对数据对象会造成代码侵入，而且对该工具类封装者的其他使用者无疑会增加学习成本。
##### 匿名内部类
这种方式也可以很好的支持复杂情况，但是使用匿名内部类的语法显然患有“垂直问题”(这意味着代码需要太多的线条来表达基本概念)，太过冗杂。~~至于性能，应该也不如直接传递函数来的快吧。~~ 
##### 函数接口（Lambda）
这种方式是基于第5条方法调用的字节码指令**invokeDynamic**实现的，直接传递函数代码块，很好的支持复杂情况，性能较高，代码编写更简单结构更加简洁，而且对数据对象代码零侵入。

当然如果你还没有使用Java1.8或更高版本，那么你可以参考**匿名内部类**或**反射+注解**，不过还是推荐**反射+注解**，[Alibaba/easyexcel【https://github.com/alibaba/easyexcel】](https://github.com/alibaba/easyexcel)对你来说会是不错的选择。

### 问题2. Excel导入或导出数据量比较大，造成`内存溢出`或`频繁的Full GC`，该如何解决？
#### 解决方法
- 读Excel —— eventmodel
- 写Excel —— streaming.SXSSFWorkbook
#### 原理
POI的使用对我们来说很常见，对下面两个概念应该并不陌生：
- HSSFWorkbook（处理97(-2007) 的.xls）
- XSSFWorkbook（处理2007 OOXML (.xlsx) ）

但是对于**eventmodel**和**streaming.SXSSFWorkbook**就很少接触了，它们是POI提供的专门用来解决内存占用问题的**low level API**（低级API），使用它们可以读写数据量非常大的Excel，同时可以避免`内存溢出`或`频繁的Full GC`。【https://poi.apache.org/components/spreadsheet/how-to.html】
- **eventmodel**，用来读Excel，并没有将Excel整个加载到内存中，而是允许用户从**InputStream**每读取一些信息，就交给**回调函数**或**监听器**，至于丢弃，存储还是怎么处理这些内容，都交由用户。
- **streaming.SXSSFWorkbook**，用来写Excel（是对XSSFWorkbook的封装，仅支持.xlsx），通过**滑动窗口**来实现，只在内存中保留滑动窗口允许存在的行数，超出的行Rows被写出到临时文件，当调用`write(OutputStream stream)`方法写出内容时，再直接从临时内存写出到目标**OutputStream**。**SXSSFWorkbook**的使用会产生一些局限性。
  - Only a limited number of rows are accessible at a point in time.
  - Sheet.clone() is not supported.
  - Formula evaluation is not supported
#### 解决途径
- https://github.com/liuhuagui/gridexcel
基于Java函数编程（Lambda），支持流式API，使用环境Java1.8或更高，学习成本：Lambda
- https://github.com/alibaba/easyexcel
基于反射+注解+监听器，使用环境Java1.6或以上，学习成本：模型注解

实际上POI官网已经给了用户使用示例，而上述两个工具都只是做了自己的封装实现，使用者只需要拿来用就好。
 
---
### 快速使用
```xml
<dependency>
    <groupId>com.github.liuhuagui</groupId>
    <artifactId>gridexcel</artifactId>
    <version>2.2</version>
</dependency>
```
#### GridExcel.java
GridExcel.java提供了多种静态方法，可以直接使用，具体式例可参考测试代码（提供了测试数据和测试文件）：
- https://github.com/liuhuagui/gridexcel/blob/master/src/test/java/ReadTest.java
- https://github.com/liuhuagui/gridexcel/blob/master/src/test/java/WriteTest.java
#### 流式API
```java
/**
  * 业务逻辑处理方式三选一：
  * 1.启用windowListener，并将业务逻辑放在该函数中。
  * 2.不启用windowListener，使用get()方法取回全部数据集合，做后续处理。
  * 3.readFunction函数，直接放在函数中处理 或 使用final or effective final的局部变量存放这写数据，做后续处理。
  * 注意：使用EventModel时readFunction函数的输入为每行的cell值集合List<String>。
  * @throws Exception
  */
 @Test
 public void readXlsxByEventModel() throws Exception {
     InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("2007.xlsx");
     GridExcel.readByEventModel(resourceAsStream,TradeOrder.class,ExcelType.XLSX)
             .window(2,ts -> System.out.println(JSON.toJSONString(ts)))//推荐在这里执行自己的业务逻辑
             .process(cs ->{
                 TradeOrder tradeOrder = new TradeOrder();
                 tradeOrder.setTradeOrderId(Long.valueOf(cs.get(0)));
                 Consultant consultant = new  Consultant();
                 consultant.setConsultantName(cs.get(3));
                 tradeOrder.setConsultant(consultant);
                 tradeOrder.setPaymentRatio(cs.get(16));
                 return tradeOrder;
             },1);
 }
 /**
  * 使用Streaming UserModel写出数据到Excel
  * @throws Exception
  */
 @Test
 public void writeExcelByStreaming() throws Exception {
     GridExcel.writeByStreaming(TradeOrder.class)
             .head(writeFunctionMap())//对象字段到Excel列的映射
             .createSheet()
             .process(MockData.data())//模拟数据。在这里设置业务数据集合。
             .write(FileUtils.openOutputStream(new File("/excel/test.xlsx")));
 }
```
#### ReadExcel
##### ReadExcelByUserModel
Use user model to read excel file. userModel ——
- **缺点**：内存消耗大，会将excel信息全部加载到内存再进行处理。
- **优点**：现成的API，使用和理解更简单。
- **使用场景**：可以处理数据量较小的Excel。
##### ReadExcelByEventModel
Use event model to read excel file. eventModel ——
- **缺点**：没有现成的API，使用和理解较为复杂，适合中高级程序员（GridExcel的目标之一就是让EventModel的使用变得简单）
- **优点**：非常小的内存占用，并没有在一开始就将所有内容加载到内存中，而是把主体内容的处理（存储，使用，丢弃）都交给了用户，用户可以自定义监听函数来处理这些内容。
- **使用场景**：可以处理较大数据量的Excel，避免OOM和频繁FullGC
#### WriteExcel
##### WriteExcelByUserModel
Use user model to write excel file. userModel ——
- **缺点**：会将产生的spreadsheets对象整个保存在内存中，所以write Excel的大小受到堆内存（Heap space）大小限制。
- **优点**：使用和理解更简单。
- **使用场景**：可以写出数据量较小的Excel。
##### WriteExcelByStreaming
Use API-compatible streaming extension of XSSF to write very large excel file. streaming userModel——
- **缺点**：
  - 仅支持XSSF；
  - Sheet.clone() is not supported；
  - Formula evaluation is not supported；
  - Only a limited number of rows are accessible at a point in time.
- **优点**：通过滑动窗口来实现，内存中只保留指定size of rows的内容，超出部分被写出到临时文件，write Excel的大小不再受到堆内存（Heap space）大小限制。
- **使用场景**：可以写出非常大的Excel。
#### Issues
在使用工具过程中出现问题，有功能添加或改动需求的可以向作者提Issue：https://github.com/liuhuagui/gridexcel/issues
- 比如说，想要增加对首行以外的行列做样式扩展



如有疑问可以联系作者：799600902@qq.com