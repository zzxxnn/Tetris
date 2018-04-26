package ui.window;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import ui.Img;
import ui.Layer;
import config.ConfigFactory;
import config.GameConfig;
import config.LayerConfig;
import control.GameControl;
import control.PlayerControl;
import dto.GameDto;

@SuppressWarnings("serial")
public class JPanelGame extends JPanel {
	
	
	private JButton jbStart;
	
	private JButton jbSet;
	
	private GameControl gameControl = null;
	
	

	private List<Layer> layers = null;


	public JPanelGame(GameControl gameControl,GameDto dto) {
		//������Ϸ������
		this.gameControl = gameControl;
		//���ò��ֹ�����
		this.setLayout(null);
		// ��ʼ���齨
		this.iniComponent();
		// ��ʼ����
		this.initLayer(dto);
		//��װ���̼�����
		this.setGameControl(new PlayerControl(gameControl));

	}

	public void setGameControl(PlayerControl control) {
		this.addKeyListener(control);
	}

	private void iniComponent() {
		//��ʼ����ʼ��ť
		this.jbStart = new JButton(Img.START);
		jbStart.setBounds(820, 74, 105, 40);
		this.jbStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gameControl.start();
			}
		});
		this.add(jbStart);
		//��ʼ�����ð�ť
		this.jbSet = new JButton(Img.CONFIG);
		jbSet.setBounds(965, 74, 105, 40);
		this.jbSet.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gameControl.showUserConfig();
			}
		});
		this.add(jbSet);
	}
	/**
	 * ��ʾ��ҿ��ƴ���
	 */


	private void initLayer(GameDto dto) {
		try {
			// ��ȡ��Ϸ����
			GameConfig cfg = ConfigFactory.getGmaeConfig();
			// ��ò�����

			List<LayerConfig> layerscfg = cfg.getLayersConfig();

			// ������Ϸ������
			layers = new ArrayList<Layer>(layerscfg.size());

			// �������в����

			for (LayerConfig asdfg : layerscfg) {
				// ��������
				Class<?> c;
				c = Class.forName(asdfg.getClassName());
				// ��ù��캯��
				Constructor ctr = c.getConstructor(int.class, int.class,
						int.class, int.class);
				// ���ù��캯����������
				Layer l = (Layer) ctr.newInstance(asdfg.getX(), asdfg.getY(),
						asdfg.getW(), asdfg.getH());
				// ������Ϸ���ݶ���
				l.setDto(dto);
				// �ф�����layer����ļ���
				layers.add(l);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// lays = new Layer[]{
	// //Ӳ���� �ǳ����õĿ���ϰ��
	// //����Ҫ���������ֻ��ַ�������ɳ���������д�������ļ�
	// new LayerBackground(0,0,1162,660),
	// new LayerDateBase(40,32,334,279),
	// new LayerDisk(40,343,334,279),
	// new LayerGame(414,32,334,590),
	// new LayerButton(788,32,334,124),
	// new LayerNext(788,188,176,148),
	// new LayerLevel(964,188,158,148),
	// new LayerPoint(788,368,334,200)
	//
	// };

	@Override
	public void paintComponent(Graphics g) {
		// ���û��෽��
		super.paintComponent(g);
		// ѭ��ˢ����Ϸ����
		for (int i = 0; i < layers.size(); layers.get(i++).paint(g));
		// ˢ��������
		this.requestFocus();
	}
	public void buttonSwitch(boolean on){
		this.jbSet.setEnabled(on);
		this.jbStart.setEnabled(on);
		
		
	}
	
}
