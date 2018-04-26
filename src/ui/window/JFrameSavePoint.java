package ui.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.FrameUtil;
import control.GameControl;

public class JFrameSavePoint extends JFrame{
	
	private JButton jb1 = null;
	
	
	private JTextField txname = null;
	
	private JLabel pointJLabel = null;
	
	private JLabel errMsg = null;
	
	private GameControl gameControl = null;
	
	public JFrameSavePoint(GameControl gameControl){
		this.gameControl = gameControl;
		this.setTitle("保存记录");
		this.setSize(256, 128);
		this.setAlwaysOnTop(true);
		FrameUtil.setFrameCenter(this);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.creatCom();
		this.creatAction();
		
//		this.setDefaultCloseOperation(3);
//		this.setVisible(true);
	}
	//显示窗口
	public void show(int point){
		this.pointJLabel.setText("您的得分"+point);
		this.setVisible(true);
	}
	/**
	 * 创建事件监听
	 */
	private void creatAction() {
		this.jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String string = txname.getText();
				
				
				if(txname.getText().length() > 16 || string == null || "".equals(string)){
					errMsg.setText("名字过长请输入以上的名字");
				}else{
					setVisible(false);
					gameControl.savePoint(string);
				}
				
			}
		});
	}
	private void creatCom(){
		//创建北部面板    流失布局    左对齐
		JPanel northJPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		//创建标签 
		this.pointJLabel = new JLabel();
		//创建错误信息控件
		this.errMsg = new JLabel();
		this.errMsg.setForeground(Color.RED);
		//添加错误信息到北部面板
		
		northJPanel.add(errMsg);
		//标签  加入北部面板
		northJPanel.add(pointJLabel);
		//将背部面板  加入 窗口   边界布局   （北边）
		this.add(northJPanel,BorderLayout.NORTH);
		//创建中间面板      流失布局    左对齐
		JPanel center = new JPanel(new FlowLayout(FlowLayout.LEFT));
		//创建文本空间
		this.txname = new JTextField(10);
		//创建  标签  并加入中间面板
		center.add(new JLabel("您的名字："));
		//将文本空间加入中间面板
		center.add(this.txname);
		//将中间面板  加入窗口   （中间）
		this.add(center,FlowLayout.CENTER);
		
		
		//创建确定按钮
		this.jb1 = new JButton("确定");
		//创建南部面板（流失布局）
		JPanel southJPanel = new JPanel(new FlowLayout(FlowLayout .CENTER));
		//按钮添加到南部面板
		southJPanel .add(jb1);
		//南部面板添加到主面板
		this.add(southJPanel,BorderLayout.SOUTH);
		
		
	}
//	public static void main(String[] args) {
//		new JFrameSavePoint();
//	}
}
