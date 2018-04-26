package config;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class GameConfig {

	private int width;
	private int height;
	private int windowSize;
	private int padding;
	private int windowup;
	private String title;
	private List<LayerConfig> LayersConfig;

	public GameConfig() throws Exception {

		// ����xml��ȡ��
		SAXReader rea = new SAXReader();

		// ��ȡxml�ļ�
		Document doc = rea.read("data/cfg.xml");

		// ��ø��ڵ�
		Element game = doc.getRootElement();
		Element frame = game.element("frame");

		// ���ô��ڲ���
		this.setUiConfig(frame);

		// ����ϵͳ����
		this.setSystemConfig(game.element("system"));

		// �������ݲ���
		this.setDateConfig(game.element("date"));
	}

	// ���ô���
	// frame�����ļ���������Ԫ��
	public void setUiConfig(Element frame) {

		this.width = Integer.parseInt(frame.attributeValue("width"));
		this.height = Integer.parseInt(frame.attributeValue("height"));

		this.windowSize = Integer.parseInt(frame.attributeValue("windowsize"));

		this.padding = Integer.parseInt(frame.attributeValue("padding"));
		// ��ȡ����θ�
		this.windowup = Integer.parseInt(frame.attributeValue("windowup"));
		// ��ȡ����

		this.title = frame.attributeValue("title");
		List<Element> layers = frame.elements("layer");

		LayersConfig = new ArrayList<LayerConfig>();
		for (Element layer : layers) {
			LayerConfig lc = new LayerConfig(layer.attributeValue("className"),
					Integer.parseInt(layer.attributeValue("x")),
					Integer.parseInt(layer.attributeValue("y")),
					Integer.parseInt(layer.attributeValue("w")),
					Integer.parseInt(layer.attributeValue("h")));

			LayersConfig.add(lc);

		}

	}

	private void setSystemConfig(Element frame) {
		// ϵͳ����
	}

	private void setDateConfig(Element frame) {
		// ���ݲ���
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getWindowSize() {
		return windowSize;
	}

	public int getWindowup() {
		return windowup;
	}

	public int getPadding() {
		return padding;
	}

	public String getTitle() {
		return title;
	}

	public List<LayerConfig> getLayersConfig() {
		return LayersConfig;
	}
}
