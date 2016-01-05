package com.libgdx.cookbook.chp05;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.libgdx.cookbook.help.BaseScreen;

/**
 * The XML parsing primer
 * ȷ��XML�ļ���UTF-8������libgdx�Ż�����Ľ���
 *
 */
public class XmlParsingSample extends BaseScreen {

    @Override
    public void show() {
        try {
            // ���Բο�InputProfile.java�ļ�������Ҳ��һ������XML�Ĳ�������
            XmlReader reader = new XmlReader();
            Element root = reader.parse(Gdx.files.internal("data/credits.xml")); // ����XML�ļ�Ҫ��һ��XmlReader��ʵ����Ȼ�����parse����
            //System.out.println(root);                                            // ���ص�����XML�ļ�
            //System.out.println("root name: " + root.getName()); // Credits
            //System.out.println("root test: " + root.getText()); // null
            System.out.println("child num: " + root.getChildCount());
            
            System.out.println("=========");
            System.out.println("Book data");
            System.out.println("=========");
            
            Element bookElement = root.getChildByName("Book"); // <Book year="2014" pages="300" >Libgdx Game Development Cookbook</Book>
            
            System.out.println("Name:" + bookElement.getName());
            System.out.println("Title: " + bookElement.getText());
            System.out.println("Year: " + bookElement.getInt("year")); // Attributes ͨ����������������
            System.out.println("Number of pages: " + bookElement.getInt("pages"));
            
            
//            <Authors>
//            <Author>David Saltares M��rquez</Author>
//            <Author>Alberto Cejas S��nchez</Author>
//            </Authors>
            Array<Element> authors = root.getChildrenByNameRecursively("Author");  // �ݹ����������Ļ�����null
            /** ����ķ���Ҳ�ܱ�����author��
            Element authorsElement = root.getChildByName("Authors");
            int childNum = authorsElement.getChildCount();
            for (int i = 0; i < childNum; i++) {
                System.out.println("  *  " + authorsElement.getChild(i).getText());
            }
            */
            
            System.out.println("Authors: ");
           
            for (Element author : authors) {
                System.out.println("  *  " + author.getText());
            }
            
            Array<Element> reviewers = root.getChildrenByNameRecursively("Reviewer");
            
            System.out.println("Reviewers: ");
            
            for (Element reviewer : reviewers) {
                System.out.println("  * " + reviewer.getText());
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Gdx.app.exit();
    }

}
