package com.china.designPattern.component;


/**
 * 组合模式主要包含如下几个角色：
 * <p>
 * 1.Component ：组合中的对象声明接口，在适当的情况下，实现所有类共有接口的默认行为。
 * 声明一个接口用于访问和管理Component子部件。
 * 2.Leaf：叶子对象。叶子结点没有子结点。
 * 3.Composite：容器对象，定义有枝节点行为，用来存储子部件，在Component接口中实现与子部件有关操作，
 * 如增加(add)和删除(remove)等。
 * <p>
 * <p>
 * 组合模式定义了如何将容器对象和叶子对象进行递归组合，使得客户在使用的过程中无须进行区分，
 * 可以对他们进行一致的处理。
 * 组合模式组合多个对象形成树形结构以表示“整体-部分”的结构层次。
 * 叶子对象和组合对象实现相同的接口。这就是组合模式能够将叶子节点和对象节点进行一致处理的原因。
 * <p>
 * <p>
 * <p>
 * 模式总结
 * <p>
 * 1、 组合模式用于将多个对象组合成树形结构以表示“整体-部分”的结构层次。
 * 组合模式对单个对象（叶子对象）和组合对象（容器对象）的使用具有一致性。
 * 2、 组合对象的关键在于它定义了一个抽象构建类，它既可表示叶子对象，
 * 也可表示容器对象，客户仅仅需要针对这个抽象构建进行编程，无须知道他是
 * 叶子对象还是容器对象，都是一致对待。
 * 3、 组合模式虽然能够非常好地处理层次结构，也使得客户端程序变得简单，
 * 但是它也使得设计变得更加抽象，而且也很难对容器中的构件类型进行限制，
 * 这会导致在增加新的构件时会产生一些问题。
 */
public class Client {
    public static void main(String[] args) {
        /**
         * 我们先建立一个这样的文件系统
         *                  总文件
         *
         *   a.txt    b.jpg                   c文件夹              
         *                      c_1.text  c_1.rmvb    c_1.jpg   
         *
         */
        //总文件夹
        Folder zwjj = new Folder("总文件夹");
        //向总文件夹中放入三个文件：1.txt、2.jpg、1文件夹
        TextFile aText = new TextFile("a.txt");
        ImagerFile bImager = new ImagerFile("b.jpg");
        Folder cFolder = new Folder("C文件夹");

        zwjj.add(aText);
        zwjj.add(bImager);
        zwjj.add(cFolder);

        //向C文件夹中添加文件：c_1.txt、c_1.rmvb、c_1.jpg 
        TextFile cText = new TextFile("c_1.txt");
        ImagerFile cImage = new ImagerFile("c_1.jpg");
        VideoFile cVideo = new VideoFile("c_1.rmvb");

        cFolder.add(cText);
        cFolder.add(cImage);
        cFolder.add(cVideo);

        //遍历C文件夹
        cFolder.display();
        //将c_1.txt删除
        cFolder.remove(cText);
        System.out.println("-----------------------");
        cFolder.display();
    }
}

/**
 * 这是文本文件，文件名：c_1.txt
 * 这是图像文件，文件名：c_1.jpg
 * 这是影像文件，文件名：c_1.rmvb
 * -----------------------
 * 这是图像文件，文件名：c_1.jpg
 * 这是影像文件，文件名：c_1.rmvb
 */