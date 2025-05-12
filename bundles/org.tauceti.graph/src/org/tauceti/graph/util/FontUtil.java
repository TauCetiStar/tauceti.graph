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
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import org.tauceti.graph.graphics.Point;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class FontUtil {

	public static FontRenderContext renderContext = new FontRenderContext(new AffineTransform(), true, true);

	public static Point getTextSize(String text) {
		return getTextSize(text, "Tahoma", 12);
	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	public static Point getTextSize(String text, String fontName, int fontSize) {
		Font font = new Font(fontName, Font.PLAIN, fontSize);
		int width = (int) (font.getStringBounds(text, renderContext).getWidth());
		int height = (int) (font.getStringBounds(text, renderContext).getHeight());
		return new Point(width, height);
	}
}
