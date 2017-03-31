package me.huqiao.wss.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;

/**
 * 报表帮助工具类
 * 
 * @author NOVOTS
 * @version Version 1.0
 */
public class ReportHelper {

	/**
	 * 生成饼形图
	 * 
	 * @param dataset
	 *            数据集
	 * @param title
	 *            标题
	 * @return JFreeChart 饼状图
	 */
	public JFreeChart createPieChart(DefaultPieDataset dataset, String title) {
		StandardChartTheme standardChartTheme = new StandardChartTheme("cn");
		ChartFactory.setChartTheme(standardChartTheme);
		JFreeChart chart = ChartFactory.createPieChart("", dataset, true, true, false);
		chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		chart.setBackgroundPaint(Color.white);
		chart.setTitle(new TextTitle(title, new Font("宋体", 0, 14)));
		LegendTitle legend = chart.getLegend(0);
		legend.setItemFont(new Font("宋体", 0, 12));
		PiePlot pieplot = (PiePlot) chart.getPlot();
		pieplot.setLabelFont(new Font("宋体", 0, 12));
		pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}:{2}"));
		pieplot.setBackgroundPaint(Color.white);
		pieplot.setNoDataMessage("暂无数据");

		pieplot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}({1})"));
		return chart;
	}

	/**
	 * 生成柱状图
	 * 
	 * @param dataset
	 *            数据集
	 * @param title
	 *            标题
	 * @param xAxisTitle
	 *            x轴标题
	 * @param yAxisTitle
	 *            y轴标题
	 * @return JFreeChart 柱状图
	 */
	public JFreeChart createBarChart(DefaultCategoryDataset dataset, String title, String xAxisTitle, String yAxisTitle) {
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
		standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 12));
		standardChartTheme.setRegularFont(new Font("宋书", Font.PLAIN, 12));
		standardChartTheme.setLargeFont(new Font("宋书", Font.PLAIN, 12));
		ChartFactory.setChartTheme(standardChartTheme);
		JFreeChart chart = ChartFactory.createBarChart(title, xAxisTitle, yAxisTitle, dataset, PlotOrientation.VERTICAL, true, true, false);
		chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		chart.setTitle(new TextTitle(title, new Font("宋体", 0, 14)));
		LegendTitle legend = chart.getLegend(0);
		legend.setItemFont(new Font("宋体", 0, 12));
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setNoDataMessage("暂无数据");

		Number maximum = DatasetUtilities.findMaximumRangeValue(dataset);

		plot.setBackgroundPaint(Color.white);
		plot.setRangeGridlinePaint(Color.gray);

		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setNumberFormatOverride(new DecimalFormat("0"));
		rangeAxis.setRange(0, maximum.intValue() + 5);

		CategoryItemRenderer renderer = plot.getRenderer();
		DecimalFormat decimalformat1 = new DecimalFormat("0");// 数据点显示数据值的格式
		renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", decimalformat1));
		// 上面这句是设置数据项标签的生成器
		renderer.setItemLabelsVisible(true);// 设置项标签显示
		renderer.setBaseItemLabelsVisible(true);// 基本项标签显示
		return chart;
	}

	/**
	 * 生成曲线图
	 * 
	 * @param response
	 * @param dataset
	 * @param title
	 * @param xAxisTitle
	 * @param yAxisTitle
	 */
	/**
	 * 生成曲线图
	 * 
	 * @param dataset
	 *            数据集合
	 * @param title
	 *            标题
	 * @param xAxisTitle
	 *            x轴标题
	 * @param yAxisTitle
	 *            y轴标题
	 * @param isCombined
	 *            刻度
	 * @return JFreeChart 曲线图
	 */
	public JFreeChart createLineChart(DefaultCategoryDataset dataset, String title, String xAxisTitle, String yAxisTitle, boolean isCombined) {
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
		standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 12));
		standardChartTheme.setRegularFont(new Font("宋书", Font.PLAIN, 12));
		standardChartTheme.setLargeFont(new Font("宋书", Font.PLAIN, 12));
		ChartFactory.setChartTheme(standardChartTheme);
		JFreeChart chart = ChartFactory.createLineChart(title, xAxisTitle, yAxisTitle, dataset, PlotOrientation.VERTICAL, isCombined, true, false);

		chart.setBackgroundPaint(Color.white);

		chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		chart.setTitle(new TextTitle(title, new Font("宋体", 0, 14)));

		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.white);

		plot.setNoDataMessage("暂无数据");

		plot.setBackgroundPaint(Color.white);
		plot.setRangeGridlinePaint(Color.gray);

		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setNumberFormatOverride(new DecimalFormat("0.00%"));
		rangeAxis.setRange(0, 1);

		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
		renderer.setShapesVisible(true);
		renderer.setDrawOutlines(true);
		renderer.setUseFillPaint(true);
		if (!isCombined) {
			renderer.setPaint(Color.blue);
		}

		DecimalFormat decimalformat1 = new DecimalFormat("0.00%");// 数据点显示数据值的格式
		renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", decimalformat1));
		renderer.setItemLabelsVisible(true);// 设置项标签显示
		renderer.setBaseItemLabelsVisible(true);// 基本项标签显示

		return chart;
	}

	
	/**
	 * 写到response输出流中
	 * @param response HttpServletResponse对象
	 * @param chart 图表
	 * @param width 宽度
	 * @param height 高度
	 */
	public void writeChart(HttpServletResponse response, JFreeChart chart, int width, int height) {
		try {
			ChartUtilities.writeChartAsJPEG(response.getOutputStream(), chart, width, height);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 写到文件流中
	 * @param fos 输出流
	 * @param chart 图表
	 * @param width 宽度
	 * @param height 高度
	 */
	public void writeChart(FileOutputStream fos, JFreeChart chart, int width, int height) {
		try {
			ChartUtilities.writeChartAsJPEG(fos, chart, width, height);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
