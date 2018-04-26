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
		this.setTitle("�����¼");
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
	//��ʾ����
	public void show(int point){
		this.pointJLabel.setText("���ĵ÷�"+point);
		this.setVisible(true);
	}
	/**
	 * �����¼�����
	 */
	private void creatAction() {
		this.jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String string = txname.getText();
				
				
				if(txname.getText().length() > 16 || string == null || "".equals(string)){
					errMsg.setText("���ֹ������������ϵ�����");
				}else{
					setVisible(false);
					gameControl.savePoint(string);
				}
				
			}
		});
	}
	private void creatCom(){
		//�����������    ��ʧ����    �����
		JPanel northJPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		//������ǩ 
		this.pointJLabel = new JLabel();
		//����������Ϣ�ؼ�
		this.errMsg = new JLabel();
		this.errMsg.setForeground(Color.RED);
		//��Ӵ�����Ϣ���������
		
		northJPanel.add(errMsg);
		//��ǩ  ���뱱�����
		northJPanel.add(pointJLabel);
		//���������  ���� ����   �߽粼��   �����ߣ�
		this.add(northJPanel,BorderLayout.NORTH);
		//�����м����      ��ʧ����    �����
		JPanel center = new JPanel(new FlowLayout(FlowLayout.LEFT));
		//�����ı��ռ�
		this.txname = new JTextField(10);
		//����  ��ǩ  �������м����
		center.add(new JLabel("�������֣�"));
		//���ı��ռ�����м����
		center.add(this.txname);
		//���м����  ���봰��   ���м䣩
		this.add(center,FlowLayout.CENTER);
		
		
		//����ȷ����ť
		this.jb1 = new JButton("ȷ��");
		//�����ϲ���壨��ʧ���֣�
		JPanel southJPanel = new JPanel(new FlowLayout(FlowLayout .CENTER));
		//��ť��ӵ��ϲ����
		southJPanel .add(jb1);
		//�ϲ������ӵ������
		this.add(southJPanel,BorderLayout.SOUTH);
		
		
	}
//	public static void main(String[] args) {
//		new JFrameSavePoint();
//	}
}
