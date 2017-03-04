package com.serpics.smc.ui.dashboard;

import java.util.ArrayList;
import java.util.List;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.LineChartConfig;
import com.byteowls.vaadin.chartjs.config.PieChartConfig;
import com.byteowls.vaadin.chartjs.data.BarDataset;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.data.PieDataset;
import com.byteowls.vaadin.chartjs.options.InteractionMode;
import com.byteowls.vaadin.chartjs.options.scale.Axis;
import com.byteowls.vaadin.chartjs.options.scale.DefaultScale;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

public class Dashboard extends CustomComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 390824714842058865L;

	public Dashboard() {
		super();
		final VerticalLayout main = new VerticalLayout();
		main.setSizeFull();
		
		

		setCompositionRoot(main);
		
		addAttachListener(new AttachListener() {
			
			@Override
			public void attach(AttachEvent event) {
				
				main.addComponent(buildGraph());
				main.addComponent(buildPieChart());
				
			}
		});
		
		
	}

	

	public ChartJs buildGraph() {
		LineChartConfig config = new LineChartConfig();
		config.data().labels("January", "February", "March", "April", "May", "June", "July")
				.addDataset(new BarDataset().label("Order 1").backgroundColor("rgba(220,220,220,0.5)")).and().options()
				.responsive(true).title().display(true).text("Chart.js Line Chart - Stacked").and().tooltips()
				.mode(InteractionMode.DATASET).and().scales().add(Axis.X, new DefaultScale().stacked(true))
				.add(Axis.Y, new DefaultScale().stacked(true)).and().done();

		// add random data for demo
		List<String> labels = config.data().getLabels();
		for (Dataset<?, ?> ds : config.data().getDatasets()) {
			BarDataset lds = (BarDataset) ds;
			List<Double> data = new ArrayList<>();
			for (int i = 0; i < labels.size(); i++) {
				data.add((double) (Math.round(Math.random() * 100)));
			}
			lds.dataAsList(data);
		}

		ChartJs chart = new ChartJs(config);
		chart.setJsLoggingEnabled(true);
		chart.setWidth("100%");
		return chart;
	}

	public ChartJs buildPieChart() {
		PieChartConfig config = new PieChartConfig();
		config.data().labels("Red", "Green", "Yellow", "Grey", "Dark Grey")
				.addDataset(new PieDataset().label("Dataset 1")).and();

		config.options().responsive(true).title().display(true).text("Chart.js Single Pie Chart").and().animation()
				// .animateScale(true)
				.animateRotate(true).and().done();

		List<String> labels = config.data().getLabels();
		for (Dataset<?, ?> ds : config.data().getDatasets()) {
			PieDataset lds = (PieDataset) ds;
			List<Double> data = new ArrayList<>();
			List<String> colors = new ArrayList<>();
			for (int i = 0; i < labels.size(); i++) {
				data.add((double) (Math.round(Math.random() * 100)));
				colors.add(randomColor(0.7));
			}
			lds.backgroundColor(colors.toArray(new String[colors.size()]));
			lds.dataAsList(data);
		}

		ChartJs chart = new ChartJs(config);
		chart.setJsLoggingEnabled(true);
		chart.setWidth("100%");
		return chart;
	}

	private long randomColorFactor() {
		return Math.round(Math.random() * 255);
	}

	private String randomColor(double d) {
		return "rgba(" + randomColorFactor() + "," + randomColorFactor() + "," + randomColorFactor() + "," + d + ")";
	}
}
