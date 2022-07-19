package edu.nwpu.edap.edapplugin.utils;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.ScrollEvent;

/**
 * 用于缩放的工具类
 * 
 * @author Wren
 * @date 2022年2月25日
 *
 */
public class ZoomHandler implements EventHandler<ScrollEvent> {
	
	private static final double MAX_SCALE = 3.0d;
	private static final double MIN_SCALE = .3d;
	
	private Node nodeToZoom;

	public ZoomHandler(Node nodeToZoom) {
		this.nodeToZoom = nodeToZoom;
	}

	@Override
	public void handle(ScrollEvent scrollEvent) {
		if (scrollEvent.isControlDown()) {
			final double scale = calculateScale(scrollEvent);
			nodeToZoom.setScaleX(scale);
			nodeToZoom.setScaleY(scale);
			scrollEvent.consume();
		}
	}

	private double calculateScale(ScrollEvent scrollEvent) {
		double scale = nodeToZoom.getScaleX() + scrollEvent.getDeltaY() / 100;

		if (scale <= MIN_SCALE) {
			scale = MIN_SCALE;
		} else if (scale >= MAX_SCALE) {
			scale = MAX_SCALE;
		}
		return scale;
	}
}
