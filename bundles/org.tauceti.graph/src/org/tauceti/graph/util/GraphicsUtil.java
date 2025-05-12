/*
 * Copyright (c) 2025 TauCeti.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * Contributors:
 *     Yang Yang - initial API and implementation
 */
package org.tauceti.graph.util;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import org.tauceti.graph.graphics.Point;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class GraphicsUtil {

	/**
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static double getAngle(Point source, Point target) {
		double angle = Math.toDegrees(Math.atan2(target.y - source.y, target.x - source.x));
		if (angle < 0) {
			angle += 360;
		}
		return angle;
	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	public static Rectangle2D getAwtTextBounds(String text) {
		if (text == null || text.isEmpty()) {
			return new Rectangle();
		}

		FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
		Font font = new Font("Tahoma", Font.PLAIN, 12);

		return font.getStringBounds(text, frc);
	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	public static int getAwtTextWidth(String text) {
		if (text == null || text.isEmpty()) {
			return 0;
		}

		boolean estimate = true;
		if (estimate) {
			return (int) (text.length() * 7);
		}

		// try {
		// Class<?> clazz = Class.forName("java.awt.Font");
		// if (clazz != null) {
		//
		// }
		// System.out.println();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// try {
		// System.loadLibrary("awt");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		Font font = new Font("Serif", Font.PLAIN, 12);
		FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);

		Rectangle2D bounds = font.getStringBounds(text, frc);
		return (int) bounds.getWidth();
	}

	// /**
	// *
	// * @param text
	// * @return
	// */
	// public static int getAwtTextHeight(String text) {
	// if (text == null || text.isEmpty()) {
	// return 0;
	// }
	//
	// FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
	// Font font = new Font("Tahoma", Font.PLAIN, 12);
	//
	// Rectangle2D bounds = font.getStringBounds(text, frc);
	// return (int) bounds.getHeight();
	// }

	public static int picaSize(String s) {
		if (s == null || s.isEmpty()) {
			return 0;
		}
		// the following characters are sorted by width in Arial font
		// String lookup = " .:,;'^`!|jl/\\i-()JfIt[]?{}sr*a\"ce_gFzLxkP+0123456789<=>~qvy$SbduEphonTBCXY#VRKZN%GUAHD@OQ&wmMW";
		// int result = 0;
		// for (int i = 0; i < s.length(); ++i) {
		// int c = lookup.indexOf(s.charAt(i));
		// result += (c < 0 ? 60 : c) * 7 + 200;
		// }
		// return result;

		return s.length() * 6;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		boolean test1 = false;
		if (test1) {
			Point p0 = new Point(0, 0);
			Point p1 = new Point(1, 0);
			Point p2 = new Point(0, 1);
			Point p3 = new Point(-1, 0);
			Point p4 = new Point(0, -1);
			double angle1 = p0.getAngle(p1);
			double angle2 = p0.getAngle(p2);
			double angle3 = p0.getAngle(p3);
			double angle4 = p0.getAngle(p4);
			System.out.println("angle1 = " + angle1);
			System.out.println("angle2 = " + angle2);
			System.out.println("angle3 = " + angle3);
			System.out.println("angle4 = " + angle4);
		}

		boolean test2 = false;
		if (test2) {
			GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
			Font[] allFonts = e.getAllFonts();

			System.out.println();
			System.out.println("All Fonts:");
			for (Font font : allFonts) {
				System.out.println(font);
			}
			System.out.println();
		}

		boolean test3 = true;
		if (test3) {
			String[] inputs = new String[] { "Hello World", "Pulp Fiction", "Create Folder", "Create File", "Plug-in Development" };
			for (String input : inputs) {
				Rectangle2D bounds = getAwtTextBounds(input);

				System.out.println(input + " -> (" + (int) bounds.getWidth() + ", " + (int) bounds.getHeight() + ")");
			}
			System.out.println();
		}

		boolean test4 = true;
		if (test4) {
			String[] inputs = new String[] { "Hello World", "Pulp Fiction", "Create Folder", "Create File", "Plug-in Development" };
			for (String input : inputs) {
				int width = picaSize(input);

				System.out.println(input + " -> (" + width + ", ?)");
			}
			System.out.println();
		}
	}
}
