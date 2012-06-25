 /**
 * Copyright (c) 2010-2012 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 */
package com.love320.label;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.love320.templateparser.factory.AppFactory;
import com.love320.templateparser.factory.Factory;
import com.love320.templateparser.factory.entity.LabelBean;

/** 
 * @ClassName: Start 
 * @Description: TODO
 * @author love320.com
 * @date 2012-6-24 下午06:06:55 
 *  
 */
public class Start extends JFrame implements ActionListener {

	private JButton btnOpen;
	private JButton btnClose;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDel;
	private JList labeList;
	private JScrollPane scrollPane;
	private String Pathstr;
	private LabelBeanDao labelBeanDao = new LabelBeanDao();

	public static void main(String[] args) {
		Start start = new Start();
	}
	
	protected void frameInit() {
		initcontrols();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnOpen) {
			addlistvalue();
		}
		if (e.getSource() == btnAdd) {
			InputFrame inputFrame = new InputFrame(labelBeanDao);
			inputFrame.setStart(this);
		}
		if (e.getSource() == btnEdit) {
			Object[] labeListItems = labeList.getSelectedValues();
			for(int i = 0 ; i < labeListItems.length ; i++){
				InputFrame inputFrame = new InputFrame(labeListItems[i].toString(),labelBeanDao);
				inputFrame.setStart(this);
			}
		}
		if(e.getSource() == btnDel){
			Object[] labeListItems = labeList.getSelectedValues();
			for(int i = 0 ; i < labeListItems.length ; i++){
				labelBeanDao.delete(labeListItems[i].toString());
			}
			listData();
		}
		if (e.getSource() == btnClose) {
			System.exit(0);
		}
	}
	
	private void initcontrols() {

		this.setTitle("标签信息管理(冰迪网络)");
		
		JPanel panel = new JPanel();
		panel.setSize(380,200);
		panel.setLayout(new FlowLayout());
		add(panel);
		
		btnOpen = new JButton("打开");
		btnOpen.addActionListener(this);
		panel.add(btnOpen);
		
		btnAdd = new JButton("添加");
		btnAdd.addActionListener(this);
		panel.add(btnAdd);
		
		btnEdit = new JButton("修改");
		btnEdit.addActionListener(this);
		panel.add(btnEdit);
		
		btnDel = new JButton("删除");
		btnDel.addActionListener(this);
		panel.add(btnDel);
		
		btnClose = new JButton("关闭");
		btnClose.addActionListener(this);
		panel.add(btnClose);
		
		labeList = new JList();
		labeList.setVisibleRowCount(6);
		labeList.setFixedCellHeight(20);
		labeList.setFixedCellWidth(317);
		scrollPane = new JScrollPane(labeList);
		panel.add(scrollPane);
		
		//居中
		Toolkit tk = Toolkit.getDefaultToolkit();
		setLocation((tk.getScreenSize().width - 380)/2,(tk.getScreenSize().height - 200)/2);
		setSize(380,200);
		
		setVisible(true);
	}
	
	private String Pathfile() {// 返回打开的目录
		JFileChooser jfc = new JFileChooser();
		if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			// 解释下这里,弹出个对话框,可以选择要上传的文件,如果选择了,就把选择的文件的绝对路径打印出来,有了绝对路径,通过JTextField的settext就能设置进去了,那个我没写
			Pathstr = jfc.getSelectedFile().getPath();
		}
		return Pathstr;
	}

	private void addlistvalue() {

		labelBeanDao.setConfigPath(Pathfile());

		listData();
	}
	
	//设置列表
	public void listData(){
		labeList.setListData(listTOStr(labelBeanDao.getAll()));
	}
	
	//释放资源
	public void closeOjbect(Object object){
		object = null;
	}
	
	//列表转数组
	private String[] listTOStr(List<LabelBean> list){
		String[] strS = new String[list.size()];
		for(int i = 0 ; i< list.size();i++){
			LabelBean labelBean = list.get(i);
			strS[i] = labelBean.getName();
		}
		
		return strS;
	}
	
}
