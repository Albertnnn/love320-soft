 /**
 * Copyright (c) 2010-2012 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 */
package com.love320.label;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.love320.templateparser.factory.entity.LabelBean;

/** 
 * @ClassName: InputFrame 
 * @Description: TODO
 * @author love320.com
 * @date 2012-6-24 下午07:59:00 
 *  
 */
public class InputFrame extends JFrame implements ActionListener {
	
	private LabelBeanDao labelBeanDao;
	private Start start;
	
	private JButton btnSave;
	private JButton btnClose;
	
	private boolean status = false;
	private JTextField name;
	private JTextField parameters;
	private JTextArea template;
	private JTextField bean;
	private JTextField note;
	LabelBean labelBean = new LabelBean();
	
	InputFrame(LabelBeanDao labelBeanDao ){
		this.labelBeanDao = labelBeanDao;
		status = false;//添加状态
		
		labelBean.setName("添加标签");
		labelBean.setParameters("参数");
		labelBean.setTemplate("模板内容");
		labelBean.setBean("bean对象");
		labelBean.setNote("说明");
		
		initcontrols();
	}
	
	InputFrame(String name,LabelBeanDao labelBeanDao ){
		this.labelBeanDao = labelBeanDao;
		status = true;//修改状态
		labelBean = labelBeanDao.get(name);
		initcontrols();
	}
	
	private void initcontrols() {
		this.setTitle(labelBean.getName()+"-标签信息管理(冰迪网络)");
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		add(panel);
		
		btnSave = new JButton("保存");
		btnSave.addActionListener(this);
		panel.add(btnSave);
		
		btnClose = new JButton("关闭");
		btnClose.addActionListener(this);
		panel.add(btnClose);
		
		name = new JTextField(28);
		name.setText(labelBean.getName());
		panel.add(name);
		
		parameters = new JTextField(28);
		parameters.setText(labelBean.getParameters());
		panel.add(parameters);
		
		bean = new JTextField(28);
		bean.setText(labelBean.getBean());
		panel.add(bean);
		
		note = new JTextField(28);
		note.setText(labelBean.getNote());
		panel.add(note);
		
		template = new JTextArea(8,28);
		template.setText(labelBean.getTemplate());
		JScrollPane scrollPane = new JScrollPane(template);
		panel.add(scrollPane);
		
		setSize(380, 330);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSave) {
			save();//保存
			start.listData();//刷新列表
		}
		if (e.getSource() == btnClose) {
		}
		this.setVisible(false);
		start.closeOjbect(this);//释放资源
	}
	
	//保存
	private void save(){

		labelBean.setName(name.getText());
		labelBean.setParameters(parameters.getText());
		labelBean.setTemplate(template.getText());
		labelBean.setBean(bean.getText());
		labelBean.setNote(note.getText());
		
		if(status){//修改
			labelBeanDao.update(labelBean);
		}else{//添加
			labelBeanDao.add(labelBean);
			
		}
	}

	public void setStart(Start start) {
		this.start = start;
	}
	
	
}
