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

		// 创建xml读取器
		SAXReader rea = new SAXReader();

		// 读取xml文件
		Document doc = rea.read("data/cfg.xml");

		// 获得根节点
		Element game = doc.getRootElement();
		Element frame = game.element("frame");

		// 配置窗口参数
		this.setUiConfig(frame);

		// 配置系统参数
		this.setSystemConfig(game.element("system"));

		// 配置数据参数
		this.setDateConfig(game.element("date"));
	}

	// 配置窗口
	// frame配置文件窗口配置元素
	public void setUiConfig(Element frame) {

		this.width = Integer.parseInt(frame.attributeValue("width"));
		this.height = Integer.parseInt(frame.attributeValue("height"));

		this.windowSize = Integer.parseInt(frame.attributeValue("windowsize"));

		this.padding = Integer.parseInt(frame.attributeValue("padding"));
		// 获取窗体拔高
		this.windowup = Integer.parseInt(frame.attributeValue("windowup"));
		// 获取标题

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
		// 系统配置
	}

	private void setDateConfig(Element frame) {
		// 数据参数
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
