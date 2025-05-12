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

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.tauceti.graph.BoundedShape;
import org.tauceti.graph.Circle;
import org.tauceti.graph.Definitions;
import org.tauceti.graph.Ellipse;
import org.tauceti.graph.Figure;
import org.tauceti.graph.Filter;
import org.tauceti.graph.FilterComponent;
import org.tauceti.graph.ForeignObject;
import org.tauceti.graph.Group;
import org.tauceti.graph.IFigure;
import org.tauceti.graph.ImageShape;
import org.tauceti.graph.Line;
import org.tauceti.graph.Path;
import org.tauceti.graph.Pattern;
import org.tauceti.graph.Polygon;
import org.tauceti.graph.Polyline;
import org.tauceti.graph.Rect;
import org.tauceti.graph.SVG;
import org.tauceti.graph.Text;
import org.tauceti.graph.Use;
import org.tauceti.graph.ViewBox;
import org.tauceti.graph.filter.FeBlend;
import org.tauceti.graph.filter.FeColorMatrix;
import org.tauceti.graph.filter.FeComponentTransfer;
import org.tauceti.graph.filter.FeComposite;
import org.tauceti.graph.filter.FeConvolveMatrix;
import org.tauceti.graph.filter.FeDiffuseLighting;
import org.tauceti.graph.filter.FeDisplacementMap;
import org.tauceti.graph.filter.FeDistantLight;
import org.tauceti.graph.filter.FeDropShadow;
import org.tauceti.graph.filter.FeFlood;
import org.tauceti.graph.filter.FeFunc;
import org.tauceti.graph.filter.FeGaussianBlur;
import org.tauceti.graph.filter.FeImage;
import org.tauceti.graph.filter.FeMerge;
import org.tauceti.graph.filter.FeMergeNode;
import org.tauceti.graph.filter.FeMorphology;
import org.tauceti.graph.filter.FeOffset;
import org.tauceti.graph.filter.FeSpecularLighting;
import org.tauceti.graph.filter.FeTile;
import org.tauceti.graph.filter.FeTurbulence;
import org.tauceti.graph.graphics.BackgroundAttribute;
import org.tauceti.graph.graphics.BorderAttribute;
import org.tauceti.graph.graphics.DisplayAttribute;
import org.tauceti.graph.graphics.FontAttribute;
import org.tauceti.graph.graphics.Point;
import org.tauceti.graph.graphics.Rectangle;
import org.tauceti.graph.graphics.Transform;
import org.tauceti.graph.graphics.TransformAttribute;
import org.tauceti.graph.graphics.VisibilityAttribute;

/**
 * Note: container element - An element which can have graphics elements and other container elements as child elements. Specifically: "a", "defs", "glyph", "g", "marker", "mask", "missing-glyph",
 * "pattern", "svg", "switch" and "symbol".
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class SVGStringWriter {

	public static String INDENT_STRING = "    ";
	public static String LINE_SEPARATOR = System.getProperty("line.separator");

	protected Figure shape;
	protected int indentLevel;

	/**
	 * 
	 * @param shape
	 */
	public SVGStringWriter(Figure shape) {
		this(shape, 0);
	}

	/**
	 * 
	 * @param shape
	 * @param indentLevel
	 */
	public SVGStringWriter(Figure shape, int indentLevel) {
		this.shape = shape;
		this.indentLevel = indentLevel;
	}

	/**
	 * @return
	 */
	public String toXml() {
		return switchToXML(shape);
	}

	public void write(OutputStream output) throws IOException {
		String stringContent = toXml();
		byte[] bytes = stringContent.getBytes(StandardCharsets.UTF_8);
		output.write(bytes);
	}

	/**
	 * 
	 * @param shape
	 * @return
	 */
	public String switchToXML(IFigure shape) {
		this.indentLevel++;
		try {
			String xmlStr = null;

			if (shape instanceof SVG) {
				xmlStr = svgToXML((SVG) shape);

			} else if (shape instanceof Definitions) {
				xmlStr = definitionsToXML((Definitions) shape);

			} else if (shape instanceof Pattern) {
				xmlStr = patternToXML((Pattern) shape);

			} else if (shape instanceof Use) {
				xmlStr = useToXML((Use) shape);

			} else if (shape instanceof Filter) {
				xmlStr = filterToXML((Filter) shape);

			} else if (shape instanceof FeBlend) {
				xmlStr = feBlendToXML((FeBlend) shape);

			} else if (shape instanceof FeColorMatrix) {
				xmlStr = feColorMatrixToXML((FeColorMatrix) shape);

			} else if (shape instanceof FeComponentTransfer) {
				xmlStr = feComponentTransferToXML((FeComponentTransfer) shape);

			} else if (shape instanceof FeComposite) {
				xmlStr = feCompositeToXML((FeComposite) shape);

			} else if (shape instanceof FeConvolveMatrix) {
				xmlStr = feConvolveMatrixToXML((FeConvolveMatrix) shape);

			} else if (shape instanceof FeDiffuseLighting) {
				xmlStr = feDiffuseLightingToXML((FeDiffuseLighting) shape);

			} else if (shape instanceof FeDisplacementMap) {
				xmlStr = feDisplacementMapToXML((FeDisplacementMap) shape);

			} else if (shape instanceof FeDropShadow) {
				xmlStr = feDropShadowToXML((FeDropShadow) shape);

			} else if (shape instanceof FeFlood) {
				xmlStr = feFloodToXML((FeFlood) shape);

			} else if (shape instanceof FeGaussianBlur) {
				xmlStr = feGaussianBlurToXML((FeGaussianBlur) shape);

			} else if (shape instanceof FeImage) {
				xmlStr = feImageToXML((FeImage) shape);

			} else if (shape instanceof FeMerge) {
				xmlStr = feMergeToXML((FeMerge) shape);

			} else if (shape instanceof FeMorphology) {
				xmlStr = feMorphologyToXML((FeMorphology) shape);

			} else if (shape instanceof FeOffset) {
				xmlStr = feOffsetToXML((FeOffset) shape);

			} else if (shape instanceof FeSpecularLighting) {
				xmlStr = feSpecularLightingToXML((FeSpecularLighting) shape);

			} else if (shape instanceof FeTile) {
				xmlStr = feTileToXML((FeTile) shape);

			} else if (shape instanceof FeTurbulence) {
				xmlStr = feTurbulenceToXML((FeTurbulence) shape);

			} else if (shape instanceof Text) {
				xmlStr = textToXML((Text) shape);

			} else if (shape instanceof Group) {
				xmlStr = groupToXML((Group) shape);

			} else if (shape instanceof Rect) {
				xmlStr = rectangleToXML((Rect) shape);

			} else if (shape instanceof Circle) {
				xmlStr = circleToXML((Circle) shape);

			} else if (shape instanceof Ellipse) {
				xmlStr = ellipseToXML((Ellipse) shape);

			} else if (shape instanceof Line) {
				xmlStr = lineToXML((Line) shape);

			} else if (shape instanceof Path) {
				xmlStr = pathToXML((Path) shape);

			} else if (shape instanceof Polyline) {
				xmlStr = polylineToXML((Polyline) shape);

			} else if (shape instanceof Polygon) {
				xmlStr = polygonToXML((Polygon) shape);

			} else if (shape instanceof ForeignObject) {
				xmlStr = foreignObjectToXML((ForeignObject) shape);

			} else if (shape instanceof ImageShape) {
				xmlStr = imageToXML((ImageShape) shape);

			} else {
				System.err.println("SVG shape is not supported " + shape != null ? "[" + shape.getClass().getName() + "] " + shape : "[null] null");
			}

			return xmlStr;

		} finally {
			this.indentLevel--;
		}
	}

	/**
	 * Convert SVG to xml
	 * 
	 * @param svg
	 * @return
	 */
	public String svgToXML(SVG svg) {
		String tagName = "svg";

		StringBuffer xmlStr = new StringBuffer();
		String indent = getIndent();

		appendStart(xmlStr, svg, indent, "svg");

		if (svg.getLevel() != null) {
			xmlStr.append(" level=\"").append(svg.getLevel().toString()).append("\"");
		}

		xmlStr.append(" version=\"").append(svg.getVersion()).append("\"");
		xmlStr.append(" xmlns=\"").append(svg.getXmlns()).append("\"");

		// NS Map
		for (Iterator<Entry<String, String>> itor = svg.getNamespaceMap().entrySet().iterator(); itor.hasNext();) {
			Entry<String, String> entry = itor.next();
			String prefix = entry.getKey();
			String namespace = entry.getValue();
			xmlStr.append(" xmlns:").append(prefix).append("=\"").append(namespace).append("\"");
		}

		xmlStr.append(" baseProfile=\"").append(svg.getBaseProfile()).append("\"");

		// visibility
		appendVisibility(xmlStr, svg);

		// xmlStr.append(" width=\"").append(svg.getWidth()).append("px\"");
		// xmlStr.append(" height=\"").append(svg.getHeight()).append("px\"");

		Rectangle bounds = svg.getBounds();
		if (bounds != null) {
			xmlStr.append(" x=\"").append(bounds.getX()).append("\"");
			xmlStr.append(" y=\"").append(bounds.getY()).append("\"");
			xmlStr.append(" width=\"").append(bounds.getWidth()).append("\"");
			xmlStr.append(" height=\"").append(bounds.getHeight()).append("\"");
		}

		ViewBox viewBox = svg.getViewBox();
		if (viewBox != null) {
			xmlStr.append(" viewBox=\"").append(viewBox.getViewBoxString()).append("\"");
		}

		appendFontAttributes(svg, xmlStr);

		if (svg.getChildren().isEmpty()) {
			// start tag end
			xmlStr.append("/>").append(LINE_SEPARATOR);
		} else {
			// start tag end
			xmlStr.append(">").append(LINE_SEPARATOR);

			List<Definitions> defss = new ArrayList<Definitions>();
			List<Use> uses = new ArrayList<Use>();

			for (IFigure child : svg.getChildren()) {
				if (child instanceof Definitions) {
					defss.add((Definitions) child);
				} else if (child instanceof Use) {
					uses.add((Use) child);
				}
			}

			// defs
			for (Definitions defs : defss) {
				String childXml = switchToXML(defs);
				if (childXml != null) {
					xmlStr.append(childXml);
				}
			}

			// children
			for (IFigure child : svg.getChildren()) {
				if (child instanceof Definitions || child instanceof Use) {
					continue;
				}
				String childXml = switchToXML(child);
				if (childXml != null) {
					xmlStr.append(childXml);
				}
			}

			// uses
			for (Use use : uses) {
				String childXml = switchToXML(use);
				if (childXml != null) {
					xmlStr.append(childXml);
				}
			}

			// end tag
			xmlStr.append(indent).append("</" + tagName + ">").append(LINE_SEPARATOR);
		}

		return xmlStr.toString();
	}

	/**
	 * Convert Definitions to xml
	 * 
	 * @param defs
	 * @return
	 */
	public String definitionsToXML(Definitions defs) {
		StringBuffer xmlStr = new StringBuffer();
		String indent = getIndent();
		appendStart(xmlStr, defs, indent, "defs");

		// visibility
		appendVisibility(xmlStr, defs);

		appendChildrenAndEnd(xmlStr, defs, indent, "defs");

		return xmlStr.toString();
	}

	/**
	 * Convert Use to xml
	 * 
	 * @param use
	 * @return
	 */
	public String useToXML(Use use) {
		StringBuffer xmlStr = new StringBuffer();
		String indent = getIndent();
		appendStart(xmlStr, use, indent, "use");

		// visibility
		appendVisibility(xmlStr, use);

		// xlink:href
		if (use.getShape() != null) {
			xmlStr.append(" xlink:href=\"#").append(use.getShape().getId()).append("\"");
		}

		// x, y
		if (use.getX() != -1) {
			xmlStr.append(" x=\"").append(use.getX()).append("\"");
		}
		if (use.getY() != -1) {
			xmlStr.append(" y=\"").append(use.getY()).append("\"");
		}

		appendBackgroundAttributes(use, xmlStr);
		appendBorderAttributes(use, xmlStr);
		appendTransformAttributes(use, xmlStr);
		appendChildrenAndEnd(xmlStr, use, indent, "use");

		return xmlStr.toString();
	}

	/**
	 * 
	 * @param pattern
	 * @return
	 */
	public String patternToXML(Pattern pattern) {
		StringBuffer xmlStr = new StringBuffer();
		String indent = getIndent();
		appendStart(xmlStr, pattern, indent, "pattern");

		// x, y, width, height
		appendBoundAttributes(pattern, xmlStr);

		// visibility
		appendVisibility(xmlStr, pattern);

		// pattern units
		if (pattern.getPatternUnits() != null) {
			xmlStr.append(" patternUnits=\"").append(pattern.getPatternUnits()).append("\"");
		}

		appendBackgroundAttributes(pattern, xmlStr);
		appendBorderAttributes(pattern, xmlStr);
		appendChildrenAndEnd(xmlStr, pattern, indent, "pattern");

		return xmlStr.toString();
	}

	/**
	 * @param filter
	 * @return
	 */
	protected String filterToXML(Filter filter) {
		StringBuffer xmlStr = new StringBuffer();
		String indent = getIndent();
		appendStart(xmlStr, filter, indent, "filter");

		// x, y, width, height
		if (filter.getX() != null && !filter.getX().isEmpty()) {
			xmlStr.append(" x=\"").append(filter.getX()).append("\"");
		}
		if (filter.getY() != null && !filter.getY().isEmpty()) {
			xmlStr.append(" y=\"").append(filter.getY()).append("\"");
		}
		if (filter.getWidth() != null && !filter.getWidth().isEmpty()) {
			xmlStr.append(" width=\"").append(filter.getWidth()).append("\"");
		}
		if (filter.getHeight() != null && !filter.getHeight().isEmpty()) {
			xmlStr.append(" height=\"").append(filter.getHeight()).append("\"");
		}

		// filterUnits, primitiveUnits, color-interpolation-filters
		if (filter.getFilterUnits() != null && !filter.getFilterUnits().isEmpty()) {
			xmlStr.append(" filterUnits=\"").append(filter.getFilterUnits()).append("\"");
		}
		if (filter.getPrimitiveUnits() != null && !filter.getPrimitiveUnits().isEmpty()) {
			xmlStr.append(" primitiveUnits=\"").append(filter.getPrimitiveUnits()).append("\"");
		}
		if (filter.getColorInterpolationFilters() != null && !filter.getColorInterpolationFilters().isEmpty()) {
			xmlStr.append(" color-interpolation-filters=\"").append(filter.getColorInterpolationFilters()).append("\"");
		}

		// appendBackgroundAttributes(filter, xmlStr);
		// appendBorderAttributes(filter, xmlStr);
		// appendTransformAttributes(filter, xmlStr);

		appendChildrenAndEnd(xmlStr, filter, indent, "filter");
		return xmlStr.toString();
	}

	/**
	 * 
	 * @param filterComponent
	 * @param xmlStr
	 */
	protected void attachFilterCommonAttributes(FilterComponent filterComponent, StringBuffer xmlStr) {
		if (filterComponent == null || xmlStr == null) {
			return;
		}

		// x, y, width, height
		if (filterComponent.getX() != null && !filterComponent.getX().isEmpty()) {
			xmlStr.append(" x=\"").append(filterComponent.getX()).append("\"");
		}
		if (filterComponent.getY() != null && !filterComponent.getY().isEmpty()) {
			xmlStr.append(" y=\"").append(filterComponent.getY()).append("\"");
		}
		if (filterComponent.getWidth() != null && !filterComponent.getWidth().isEmpty()) {
			xmlStr.append(" width=\"").append(filterComponent.getWidth()).append("\"");
		}
		if (filterComponent.getHeight() != null && !filterComponent.getHeight().isEmpty()) {
			xmlStr.append(" height=\"").append(filterComponent.getHeight()).append("\"");
		}

		// in, in2, result
		if (filterComponent.getIn() != null && !filterComponent.getIn().isEmpty()) {
			xmlStr.append(" in=\"").append(filterComponent.getIn()).append("\"");
		}
		if (filterComponent.getIn2() != null && !filterComponent.getIn2().isEmpty()) {
			xmlStr.append(" in2=\"").append(filterComponent.getIn2()).append("\"");
		}
		if (filterComponent.getResult() != null && !filterComponent.getResult().isEmpty()) {
			xmlStr.append(" result=\"").append(filterComponent.getResult()).append("\"");
		}
	}

	/**
	 * @param feBlend
	 * @return
	 */
	protected String feBlendToXML(FeBlend feBlend) {
		StringBuffer xmlStr = new StringBuffer();
		String indent = getIndent();
		appendStart(xmlStr, feBlend, indent, "feBlend");

		// x, y, width, height, in, in2, result
		attachFilterCommonAttributes(feBlend, xmlStr);

		// mode
		if (feBlend.getMode() != null && !feBlend.getMode().isEmpty()) {
			xmlStr.append(" mode=\"").append(feBlend.getMode()).append("\"");
		}

		appendChildrenAndEnd(xmlStr, feBlend, indent, "feBlend");
		return xmlStr.toString();
	}

	/**
	 * @param feColorMatrix
	 * @return
	 */
	protected String feColorMatrixToXML(FeColorMatrix feColorMatrix) {
		StringBuffer xmlStr = new StringBuffer();
		String indent = getIndent();
		appendStart(xmlStr, feColorMatrix, indent, "feColorMatrix");

		// x, y, width, height, in, in2, result
		attachFilterCommonAttributes(feColorMatrix, xmlStr);

		// type, value
		if (feColorMatrix.getType() != null && !feColorMatrix.getType().isEmpty()) {
			xmlStr.append(" type=\"").append(feColorMatrix.getType()).append("\"");
		}
		if (feColorMatrix.getValues() != null && !feColorMatrix.getValues().isEmpty()) {
			xmlStr.append(" values=\"").append(feColorMatrix.getValues()).append("\"");
		}

		appendChildrenAndEnd(xmlStr, feColorMatrix, indent, "feColorMatrix");
		return xmlStr.toString();
	}

	/**
	 * @param feComponentTransfer
	 * @return
	 */
	protected String feComponentTransferToXML(FeComponentTransfer feComponentTransfer) {
		StringBuffer xmlStr = new StringBuffer();
		String indent = getIndent();
		appendStart(xmlStr, feComponentTransfer, indent, "feComponentTransfer");

		// x, y, width, height, in, in2, result
		attachFilterCommonAttributes(feComponentTransfer, xmlStr);

		// funcs
		for (IFigure child : feComponentTransfer.getChildren()) {
			if (child instanceof FeFunc) {
				// FeFunc feFunc = (FeFunc) child;

				// Element feFuncElement = null;
				// {
				// // feFuncR or feFuncG or feFuncB or feFuncA
				// feFuncElement = createElement(feFunc, feFunc.getTagName());
				//
				// // type, tableValues
				// if (feFunc.getType() != null && !feFunc.getType().isEmpty()) {
				// feFuncElement.setAttribute("type", feFunc.getType());
				// }
				// if (feFunc.getTableValues() != null && !feFunc.getTableValues().isEmpty()) {
				// feFuncElement.setAttribute("tableValues", String.valueOf(feFunc.getTableValues()));
				// }
				// }
				//
				// if (feFuncElement != null) {
				// feComponentTransferElement.appendChild(feFuncElement);
				// }
			}
		}

		appendChildrenAndEnd(xmlStr, feComponentTransfer, indent, "feComponentTransfer");
		return xmlStr.toString();
	}

	/**
	 * @param feComposite
	 * @return
	 */
	protected String feCompositeToXML(FeComposite feComposite) {
		StringBuffer xmlStr = new StringBuffer();
		String indent = getIndent();
		appendStart(xmlStr, feComposite, indent, "feComposite");

		// x, y, width, height, in, in2, result
		attachFilterCommonAttributes(feComposite, xmlStr);

		// operator
		if (feComposite.getOperator() != null && !feComposite.getOperator().isEmpty()) {
			xmlStr.append(" operator=\"").append(feComposite.getOperator()).append("\"");
		}

		appendChildrenAndEnd(xmlStr, feComposite, indent, "feComposite");
		return xmlStr.toString();
	}

	/**
	 * @param feConvolveMatrix
	 * @return
	 */
	protected String feConvolveMatrixToXML(FeConvolveMatrix feConvolveMatrix) {
		StringBuffer xmlStr = new StringBuffer();
		String indent = getIndent();
		appendStart(xmlStr, feConvolveMatrix, indent, "feConvolveMatrix");

		// x, y, width, height, in, in2, result
		attachFilterCommonAttributes(feConvolveMatrix, xmlStr);

		// order, kernelMatrix
		if (feConvolveMatrix.getOrder() != null && !feConvolveMatrix.getOrder().isEmpty()) {
			xmlStr.append(" order=\"").append(feConvolveMatrix.getOrder()).append("\"");
		}
		if (feConvolveMatrix.getKernelMatrix() != null && !feConvolveMatrix.getKernelMatrix().isEmpty()) {
			xmlStr.append(" kernelMatrix=\"").append(feConvolveMatrix.getKernelMatrix()).append("\"");
		}

		// divisor, bias, targetX, targetY
		if (feConvolveMatrix.getDivisor() != null) {
			xmlStr.append(" divisor=\"").append(String.valueOf(feConvolveMatrix.getDivisor())).append("\"");
		}
		if (feConvolveMatrix.getBias() != null) {
			xmlStr.append(" bias=\"").append(String.valueOf(feConvolveMatrix.getBias())).append("\"");
		}
		if (feConvolveMatrix.getTargetX() != null) {
			xmlStr.append(" targetX=\"").append(String.valueOf(feConvolveMatrix.getTargetX())).append("\"");
		}
		if (feConvolveMatrix.getTargetY() != null) {
			xmlStr.append(" targetY=\"").append(String.valueOf(feConvolveMatrix.getTargetY())).append("\"");
		}

		// edgeMode, preserveAlpha
		if (feConvolveMatrix.getEdgeMode() != null && !feConvolveMatrix.getEdgeMode().isEmpty()) {
			xmlStr.append(" edgeMode=\"").append(feConvolveMatrix.getEdgeMode()).append("\"");
		}
		if (feConvolveMatrix.isPreserveAlpha() != null) {
			xmlStr.append(" preserveAlpha=\"").append(String.valueOf(feConvolveMatrix.isPreserveAlpha())).append("\"");
		}

		appendChildrenAndEnd(xmlStr, feConvolveMatrix, indent, "feConvolveMatrix");
		return xmlStr.toString();
	}

	/**
	 * @param feDiffuseLighting
	 * @return
	 */
	protected String feDiffuseLightingToXML(FeDiffuseLighting feDiffuseLighting) {
		StringBuffer xmlStr = new StringBuffer();
		String indent = getIndent();
		appendStart(xmlStr, feDiffuseLighting, indent, "feDiffuseLighting");

		// x, y, width, height, in, in2, result
		attachFilterCommonAttributes(feDiffuseLighting, xmlStr);

		// surfaceScale, diffuseConstant, lighting-color
		if (feDiffuseLighting.getSurfaceScale() != null) {
			xmlStr.append(" surfaceScale=\"").append(String.valueOf(feDiffuseLighting.getSurfaceScale())).append("\"");
		}
		if (feDiffuseLighting.getDiffuseConstant() != null) {
			xmlStr.append(" diffuseConstant=\"").append(String.valueOf(feDiffuseLighting.getDiffuseConstant())).append("\"");
		}
		if (feDiffuseLighting.getLightingColor() != null && !feDiffuseLighting.getLightingColor().isEmpty()) {
			xmlStr.append(" lighting-color=\"").append(feDiffuseLighting.getLightingColor()).append("\"");
		}

		// feDistantLight
		for (IFigure child : feDiffuseLighting.getChildren()) {
			if (child instanceof FeDistantLight) {
				// FeDistantLight feDistantLight = (FeDistantLight) child;

				// // feDistantLight
				// Element feDistangLightElement = createElement(feDistantLight, "feDistantLight");
				// // azimuth, elevation
				// if (feDistantLight.getAzimuth() != null) {
				// feDistangLightElement.setAttribute("azimuth", String.valueOf(feDistantLight.getAzimuth()));
				// }
				// if (feDistantLight.getElevation() != null) {
				// feDistangLightElement.setAttribute("elevation", String.valueOf(feDistantLight.getElevation()));
				// }
				// feDiffuseLightingElement.appendChild(feDistangLightElement);
			}
		}

		appendChildrenAndEnd(xmlStr, feDiffuseLighting, indent, "feDiffuseLighting");
		return xmlStr.toString();
	}

	/**
	 * @param feDisplacementMap
	 * @return
	 */
	protected String feDisplacementMapToXML(FeDisplacementMap feDisplacementMap) {
		StringBuffer xmlStr = new StringBuffer();
		String indent = getIndent();
		appendStart(xmlStr, feDisplacementMap, indent, "feDisplacementMap");

		// x, y, width, height, in, in2, result
		attachFilterCommonAttributes(feDisplacementMap, xmlStr);

		// scale, xChannelSelector, yChannelSelector
		if (feDisplacementMap.getScale() != null) {
			xmlStr.append(" scale=\"").append(String.valueOf(feDisplacementMap.getScale())).append("\"");
		}
		if (feDisplacementMap.getXChannelSelector() != null && !feDisplacementMap.getXChannelSelector().isEmpty()) {
			xmlStr.append(" xChannelSelector=\"").append(String.valueOf(feDisplacementMap.getXChannelSelector())).append("\"");
		}
		if (feDisplacementMap.getYChannelSelector() != null && !feDisplacementMap.getYChannelSelector().isEmpty()) {
			xmlStr.append(" yChannelSelector=\"").append(String.valueOf(feDisplacementMap.getYChannelSelector())).append("\"");
		}

		appendChildrenAndEnd(xmlStr, feDisplacementMap, indent, "feDisplacementMap");
		return xmlStr.toString();
	}

	/**
	 * @param feDropShadow
	 * @return
	 */
	protected String feDropShadowToXML(FeDropShadow feDropShadow) {
		StringBuffer xmlStr = new StringBuffer();
		String indent = getIndent();
		appendStart(xmlStr, feDropShadow, indent, "feDropShadow");

		// x, y, width, height, in, in2, result
		attachFilterCommonAttributes(feDropShadow, xmlStr);

		// dx, dy
		if (feDropShadow.getDx() != null) {
			xmlStr.append(" dx=\"").append(String.valueOf(feDropShadow.getDx())).append("\"");
		}
		if (feDropShadow.getDy() != null) {
			xmlStr.append(" dy=\"").append(String.valueOf(String.valueOf(feDropShadow.getDy()))).append("\"");
		}

		// stdDeviation, flood-color, flood-opacity
		if (feDropShadow.getStdDeviation() != null) {
			xmlStr.append(" stdDeviation=\"").append(String.valueOf(feDropShadow.getStdDeviation())).append("\"");
		}
		if (feDropShadow.getFloodColor() != null && !feDropShadow.getFloodColor().isEmpty()) {
			xmlStr.append(" flood-color=\"").append(feDropShadow.getFloodColor()).append("\"");
		}
		if (feDropShadow.getFloodOpacity() != null) {
			xmlStr.append(" flood-opacity=\"").append(String.valueOf(feDropShadow.getFloodOpacity())).append("\"");
		}

		appendChildrenAndEnd(xmlStr, feDropShadow, indent, "feDropShadow");
		return xmlStr.toString();
	}

	/**
	 * @param feFlood
	 * @return
	 */
	protected String feFloodToXML(FeFlood feFlood) {
		StringBuffer xmlStr = new StringBuffer();
		String indent = getIndent();
		appendStart(xmlStr, feFlood, indent, "feFlood");

		// x, y, width, height, in, in2, result
		attachFilterCommonAttributes(feFlood, xmlStr);

		// flood-color, flood-opacity
		if (feFlood.getFloodColor() != null && !feFlood.getFloodColor().isEmpty()) {
			xmlStr.append(" flood-color=\"").append(feFlood.getFloodColor()).append("\"");
		}
		if (feFlood.getFloodOpacity() != null) {
			xmlStr.append(" flood-opacity=\"").append(String.valueOf(feFlood.getFloodOpacity())).append("\"");
		}

		appendChildrenAndEnd(xmlStr, feFlood, indent, "feFlood");
		return xmlStr.toString();
	}

	/**
	 * @param feGaussianBlur
	 * @return
	 */
	protected String feGaussianBlurToXML(FeGaussianBlur feGaussianBlur) {
		StringBuffer xmlStr = new StringBuffer();
		String indent = getIndent();
		appendStart(xmlStr, feGaussianBlur, indent, "feGaussianBlur");

		// x, y, width, height, in, in2, result
		attachFilterCommonAttributes(feGaussianBlur, xmlStr);

		// stdDeviation, edgeMode, value
		if (feGaussianBlur.getStdDeviation() != null && !feGaussianBlur.getStdDeviation().isEmpty()) {
			xmlStr.append(" stdDeviation=\"").append(feGaussianBlur.getStdDeviation()).append("\"");
		}
		if (feGaussianBlur.getEdgeMode() != null && !feGaussianBlur.getEdgeMode().isEmpty()) {
			xmlStr.append(" edgeMode=\"").append(feGaussianBlur.getEdgeMode()).append("\"");
		}

		appendChildrenAndEnd(xmlStr, feGaussianBlur, indent, "feGaussianBlur");
		return xmlStr.toString();
	}

	/**
	 * @param feImage
	 * @return
	 */
	protected String feImageToXML(FeImage feImage) {
		StringBuffer xmlStr = new StringBuffer();
		String indent = getIndent();
		appendStart(xmlStr, feImage, indent, "feImage");

		// x, y, width, height, in, in2, result
		attachFilterCommonAttributes(feImage, xmlStr);

		// href, preserveAspectRatio, crossOrigin
		if (feImage.getHref() != null && !feImage.getHref().isEmpty()) {
			xmlStr.append(" href=\"").append(feImage.getHref()).append("\"");
		}
		if (feImage.getPreserveAspectRatio() != null && !feImage.getPreserveAspectRatio().isEmpty()) {
			xmlStr.append(" preserveAspectRatio=\"").append(feImage.getPreserveAspectRatio()).append("\"");
		}
		if (feImage.getCrossOrigin() != null && !feImage.getCrossOrigin().isEmpty()) {
			xmlStr.append(" crossOrigin=\"").append(feImage.getCrossOrigin()).append("\"");
		}

		appendChildrenAndEnd(xmlStr, feImage, indent, "feImage");
		return xmlStr.toString();
	}

	/**
	 * @param feMerge
	 * @return
	 */
	protected String feMergeToXML(FeMerge feMerge) {
		StringBuffer xmlStr = new StringBuffer();
		String indent = getIndent();
		appendStart(xmlStr, feMerge, indent, "feMerge");

		// x, y, width, height, in, in2, result
		attachFilterCommonAttributes(feMerge, xmlStr);

		// feMergeNode
		for (IFigure child : feMerge.getChildren()) {
			if (child instanceof FeMergeNode) {
				// FeMergeNode feMergeNode = (FeMergeNode) child;

				// // feMergeNode
				// Element feMergeNodeElement = createElement(feMergeNode, "feMergeNode");
				// // in
				// if (feMergeNode.getIn() != null && !feMergeNode.getIn().isEmpty()) {
				// feMergeNodeElement.setAttribute("in", feMergeNode.getIn());
				// }
				// feMergeElement.appendChild(feMergeNodeElement);
			}
		}

		appendChildrenAndEnd(xmlStr, feMerge, indent, "feMerge");
		return xmlStr.toString();
	}

	/**
	 * @param feMorphology
	 * @return
	 */
	protected String feMorphologyToXML(FeMorphology feMorphology) {
		StringBuffer xmlStr = new StringBuffer();
		String indent = getIndent();
		appendStart(xmlStr, feMorphology, indent, "feMorphology");

		// x, y, width, height, in, in2, result
		attachFilterCommonAttributes(feMorphology, xmlStr);

		// operator, radius
		if (feMorphology.getOperator() != null && !feMorphology.getOperator().isEmpty()) {
			xmlStr.append(" operator=\"").append(feMorphology.getOperator()).append("\"");
		}
		if (feMorphology.getRadius() != null && !feMorphology.getRadius().isEmpty()) {
			xmlStr.append(" radius=\"").append(feMorphology.getRadius()).append("\"");
		}

		appendChildrenAndEnd(xmlStr, feMorphology, indent, "feMorphology");
		return xmlStr.toString();
	}

	/**
	 * @param feOffset
	 * @return
	 */
	protected String feOffsetToXML(FeOffset feOffset) {
		StringBuffer xmlStr = new StringBuffer();
		String indent = getIndent();
		appendStart(xmlStr, feOffset, indent, "feOffset");

		// x, y, width, height, in, in2, result
		attachFilterCommonAttributes(feOffset, xmlStr);

		// dx, dy
		if (feOffset.getDx() != null) {
			xmlStr.append(" dx=\"").append(String.valueOf(feOffset.getDx())).append("\"");
		}
		if (feOffset.getDy() != null) {
			xmlStr.append(" dy=\"").append(String.valueOf(feOffset.getDy())).append("\"");
		}

		appendChildrenAndEnd(xmlStr, feOffset, indent, "feOffset");
		return xmlStr.toString();
	}

	/**
	 * @param feSpecularLighting
	 * @return
	 */
	protected String feSpecularLightingToXML(FeSpecularLighting feSpecularLighting) {
		StringBuffer xmlStr = new StringBuffer();
		String indent = getIndent();
		appendStart(xmlStr, feSpecularLighting, indent, "feSpecularLighting");

		// x, y, width, height, in, in2, result
		attachFilterCommonAttributes(feSpecularLighting, xmlStr);

		// surfaceScale, specularConstant, specularExponent, lighting-color
		if (feSpecularLighting.getSurfaceScale() != null) {
			xmlStr.append(" surfaceScale=\"").append(String.valueOf(feSpecularLighting.getSurfaceScale())).append("\"");
		}
		if (feSpecularLighting.getSpecularConstant() != null) {
			xmlStr.append(" specularConstant=\"").append(String.valueOf(feSpecularLighting.getSpecularConstant())).append("\"");
		}
		if (feSpecularLighting.getSpecularExponent() != null) {
			xmlStr.append(" specularExponent=\"").append(String.valueOf(feSpecularLighting)).append("\"");
		}
		if (feSpecularLighting.getLightingColor() != null && !feSpecularLighting.getLightingColor().isEmpty()) {
			xmlStr.append(" lighting-color=\"").append(feSpecularLighting.getLightingColor()).append("\"");
		}

		// feDistantLight
		for (IFigure child : feSpecularLighting.getChildren()) {
			if (child instanceof FeDistantLight) {
				// FeDistantLight feDistantLight = (FeDistantLight) child;

				// // feDistantLight
				// Element feDistangLightElement = createElement(feDistantLight, "feDistantLight");
				// // azimuth, elevation
				// if (feDistantLight.getAzimuth() != null) {
				// feDistangLightElement.setAttribute("azimuth", String.valueOf(feDistantLight.getAzimuth()));
				// }
				// if (feDistantLight.getElevation() != null) {
				// feDistangLightElement.setAttribute("elevation", String.valueOf(feDistantLight.getElevation()));
				// }
				// feSpecularLightingElement.appendChild(feDistangLightElement);
			}
		}

		appendChildrenAndEnd(xmlStr, feSpecularLighting, indent, "feSpecularLighting");
		return xmlStr.toString();
	}

	/**
	 * @param feTile
	 * @return
	 */
	protected String feTileToXML(FeTile feTile) {
		StringBuffer xmlStr = new StringBuffer();
		String indent = getIndent();
		appendStart(xmlStr, feTile, indent, "feTile");

		// x, y, width, height, in, in2, result
		attachFilterCommonAttributes(feTile, xmlStr);

		appendChildrenAndEnd(xmlStr, feTile, indent, "feTile");
		return xmlStr.toString();
	}

	/**
	 * @param feTurbulence
	 * @return
	 */
	protected String feTurbulenceToXML(FeTurbulence feTurbulence) {
		StringBuffer xmlStr = new StringBuffer();
		String indent = getIndent();
		appendStart(xmlStr, feTurbulence, indent, "feTurbulence");

		// x, y, width, height, in, in2, result
		attachFilterCommonAttributes(feTurbulence, xmlStr);

		// type, baseFrequency, numOctaves, seed, stitchTiles
		if (feTurbulence.getType() != null && !feTurbulence.getType().isEmpty()) {
			xmlStr.append(" type=\"").append(feTurbulence.getType()).append("\"");
		}
		if (feTurbulence.getBaseFrequency() != null && !feTurbulence.getBaseFrequency().isEmpty()) {
			xmlStr.append(" baseFrequency=\"").append(feTurbulence.getBaseFrequency()).append("\"");
		}
		if (feTurbulence.getNumOctaves() != null) {
			xmlStr.append(" numOctaves=\"").append(String.valueOf(feTurbulence.getNumOctaves())).append("\"");
		}
		if (feTurbulence.getSeed() != null) {
			xmlStr.append(" seed=\"").append(String.valueOf(feTurbulence.getSeed())).append("\"");
		}
		if (feTurbulence.getStitchTiles() != null && !feTurbulence.getStitchTiles().isEmpty()) {
			xmlStr.append(" stitchTiles=\"").append(feTurbulence.getStitchTiles()).append("\"");
		}

		appendChildrenAndEnd(xmlStr, feTurbulence, indent, "feTurbulence");
		return xmlStr.toString();
	}

	/**
	 * @param text
	 * @return
	 */
	public String textToXML(Text text) {
		StringBuffer xmlStr = new StringBuffer();

		String indent = getIndent();
		appendStart(xmlStr, text, indent, "text");

		// visibility
		appendVisibility(xmlStr, text);

		// top-left location
		xmlStr.append(" x=\"").append(text.getX()).append("\"");
		xmlStr.append(" y=\"").append(text.getY()).append("\"");

		// attributes to tweak the position of the text.
		if (text.getDx() != 0) {
			xmlStr.append(" dx=\"").append(text.getDx()).append("\"");
		}
		if (text.getDy() != 0) {
			xmlStr.append(" dy=\"").append(text.getDy()).append("\"");
		}

		// corner radius
		if (text.getTextAnchor() != null) {
			xmlStr.append(" text-anchor=\"").append(text.getTextAnchor()).append("\"");
		}

		appendBackgroundAttributes(text, xmlStr);
		appendBorderAttributes(text, xmlStr);
		appendTransformAttributes(text, xmlStr);

		// Text value and end tag
		xmlStr.append(">").append((text.getText() != null) ? text.getText() : "").append("</text>").append(LINE_SEPARATOR);

		return xmlStr.toString();
	}

	/**
	 * Convert Group to xml
	 * 
	 * @param group
	 * @return
	 */
	public String groupToXML(Group group) {
		StringBuffer xmlStr = new StringBuffer();

		String indent = getIndent();

		appendStart(xmlStr, group, indent, "g");

		// visibility
		appendVisibility(xmlStr, group);

		// top-left location
		xmlStr.append(" x=\"").append(group.getX()).append("\"");
		xmlStr.append(" y=\"").append(group.getY()).append("\"");

		// size
		xmlStr.append(" width=\"").append(group.getWidth()).append("\"");
		xmlStr.append(" height=\"").append(group.getHeight()).append("\"");

		appendBackgroundAttributes(group, xmlStr);
		appendBorderAttributes(group, xmlStr);
		appendTransformAttributes(group, xmlStr);

		appendChildrenAndEnd(xmlStr, group, indent, "g");

		return xmlStr.toString();
	}

	/**
	 * Convert Rectangle to xml
	 * 
	 * @param rect
	 * @return
	 */
	public String rectangleToXML(Rect rect) {
		StringBuffer xmlStr = new StringBuffer();

		String indent = getIndent();
		appendStart(xmlStr, rect, indent, "rect");

		// visibility
		appendVisibility(xmlStr, rect);

		// top-left location
		xmlStr.append(" x=\"").append(rect.getX()).append("\"");
		xmlStr.append(" y=\"").append(rect.getY()).append("\"");

		// size
		xmlStr.append(" width=\"").append(rect.getWidth()).append("\"");
		xmlStr.append(" height=\"").append(rect.getHeight()).append("\"");

		// corner radius
		if (rect.getRx() != -1) {
			xmlStr.append(" rx=\"").append(rect.getRx()).append("\"");
		}
		if (rect.getRy() != -1) {
			xmlStr.append(" ry=\"").append(rect.getRy()).append("\"");
		}

		appendBackgroundAttributes(rect, xmlStr);
		appendBorderAttributes(rect, xmlStr);
		appendChildrenAndEnd(xmlStr, rect, indent, "rect");

		return xmlStr.toString();
	}

	/**
	 * Convert Circle to xml
	 * 
	 * @param circle
	 * @return
	 */
	public String circleToXML(Circle circle) {
		StringBuffer xmlStr = new StringBuffer();

		String indent = getIndent();
		appendStart(xmlStr, circle, indent, "circle");

		// visibility
		appendVisibility(xmlStr, circle);

		// center location
		xmlStr.append(" cx=\"").append(circle.getCenterX()).append("\"");
		xmlStr.append(" cy=\"").append(circle.getCenterY()).append("\"");

		// radius
		xmlStr.append(" r=\"").append(circle.getRadius()).append("\"");

		appendBackgroundAttributes(circle, xmlStr);
		appendBorderAttributes(circle, xmlStr);
		appendChildrenAndEnd(xmlStr, circle, indent, "circle");

		return xmlStr.toString();
	}

	/**
	 * 
	 * @param ellipse
	 * @return
	 */
	public String ellipseToXML(Ellipse ellipse) {
		StringBuffer xmlStr = new StringBuffer();

		String indent = getIndent();
		appendStart(xmlStr, ellipse, indent, "ellipse");

		// visibility
		appendVisibility(xmlStr, ellipse);

		// center
		xmlStr.append(" cx=\"").append(ellipse.getCenterX()).append("\"");
		xmlStr.append(" cy=\"").append(ellipse.getCenterY()).append("\"");

		// horizontal radius and vertical radius
		xmlStr.append(" rx=\"").append(ellipse.getRadiusX()).append("\"");
		xmlStr.append(" ry=\"").append(ellipse.getRadiusY()).append("\"");

		appendBackgroundAttributes(ellipse, xmlStr);
		appendBorderAttributes(ellipse, xmlStr);
		appendChildrenAndEnd(xmlStr, ellipse, indent, "ellipse");

		return xmlStr.toString();
	}

	/**
	 * Convert Line to xml
	 * 
	 * @param line
	 * @return
	 */
	public String lineToXML(Line line) {
		StringBuffer xmlStr = new StringBuffer();

		String indent = getIndent();
		appendStart(xmlStr, line, indent, "line");

		// visibility
		appendVisibility(xmlStr, line);

		// start location
		xmlStr.append(" x1=\"").append(line.getX1()).append("\"");
		xmlStr.append(" y1=\"").append(line.getY1()).append("\"");

		// end location
		xmlStr.append(" x2=\"").append(line.getX2()).append("\"");
		xmlStr.append(" y2=\"").append(line.getY2()).append("\"");

		appendBorderAttributes(line, xmlStr);
		appendChildrenAndEnd(xmlStr, line, indent, "line");

		return xmlStr.toString();
	}

	/**
	 * Convert Path to xml
	 * 
	 * @param path
	 * @return
	 */
	public String pathToXML(Path path) {
		StringBuffer xmlStr = new StringBuffer();

		String indent = getIndent();
		appendStart(xmlStr, path, indent, "path");

		// visibility
		appendVisibility(xmlStr, path);

		// start location
		xmlStr.append(" d=\"").append(path.getD()).append("\"");

		appendBorderAttributes(path, xmlStr);
		appendChildrenAndEnd(xmlStr, path, indent, "path");

		return xmlStr.toString();
	}

	/**
	 * Convert Polyline to xml
	 * 
	 * @param polyline
	 * @return
	 */
	public String polylineToXML(Polyline polyline) {
		StringBuffer xmlStr = new StringBuffer();

		String indent = getIndent();
		appendStart(xmlStr, polyline, indent, "polyline");

		// visibility
		appendVisibility(xmlStr, polyline);

		// points
		if (polyline.getPoints() != null) {
			StringBuffer pointsValue = new StringBuffer();
			for (int i = 0; i < polyline.getPoints().size(); i++) {
				Point point = polyline.getPoints().get(i);
				if (i > 0) {
					pointsValue.append(" ");
				}
				pointsValue.append(point.getX()).append(",").append(point.getY());
			}
			xmlStr.append(" points=\"").append(pointsValue.toString()).append("\"");
		}

		appendBackgroundAttributes(polyline, xmlStr);
		appendBorderAttributes(polyline, xmlStr);
		appendChildrenAndEnd(xmlStr, polyline, indent, "polyline");

		return xmlStr.toString();
	}

	/**
	 * Convert Polygon to xml
	 * 
	 * @param polygon
	 * @return
	 */
	public String polygonToXML(Polygon polygon) {
		StringBuffer xmlStr = new StringBuffer();

		String indent = getIndent();
		appendStart(xmlStr, polygon, indent, "polygon");

		// visibility
		appendVisibility(xmlStr, polygon);

		// points
		if (polygon.getPoints() != null) {
			StringBuffer pointsValue = new StringBuffer();
			for (int i = 0; i < polygon.getPoints().size(); i++) {
				Point point = polygon.getPoints().get(i);
				if (i > 0) {
					pointsValue.append(" ");
				}
				pointsValue.append(point.getX()).append(",").append(point.getY());
			}
			xmlStr.append(" points=\"").append(pointsValue.toString()).append("\"");
		}

		appendBackgroundAttributes(polygon, xmlStr);
		appendBorderAttributes(polygon, xmlStr);
		appendChildrenAndEnd(xmlStr, polygon, indent, "polygon");

		return xmlStr.toString();
	}

	/**
	 * Convert ForeignObject to xml
	 * 
	 * @param foreignObject
	 * @return
	 */
	public String foreignObjectToXML(ForeignObject foreignObject) {
		StringBuffer xmlStr = new StringBuffer();

		String indent = getIndent();

		appendStart(xmlStr, foreignObject, indent, "foreignObject");

		// visibility
		appendVisibility(xmlStr, foreignObject);

		// top-left location
		xmlStr.append(" x=\"").append(foreignObject.getX()).append("\"");
		xmlStr.append(" y=\"").append(foreignObject.getY()).append("\"");

		// size
		xmlStr.append(" width=\"").append(foreignObject.getWidth()).append("\"");
		xmlStr.append(" height=\"").append(foreignObject.getHeight()).append("\"");

		appendBackgroundAttributes(foreignObject, xmlStr);
		appendBorderAttributes(foreignObject, xmlStr);
		appendChildrenAndEnd(xmlStr, foreignObject, indent, "foreignObject");

		return xmlStr.toString();
	}

	/**
	 * 
	 * @param imageShape
	 * @return
	 */
	public String imageToXML(ImageShape imageShape) {
		StringBuffer xmlStr = new StringBuffer();

		String indent = getIndent();
		appendStart(xmlStr, imageShape, indent, "image");

		// x, y, width, height
		appendBoundAttributes(imageShape, xmlStr);

		// xlink:href
		if (imageShape.getHref() != null) {
			// xmlStr.append(" xlink:href=\"").append(image.getHref()).append("\"");
			xmlStr.append(" href=\"").append(imageShape.getHref()).append("\"");
		}

		// preserveAspectRatio
		if (imageShape.getPreserveAspectRatio() != null) {
			xmlStr.append(" preserveAspectRatio=\"").append(imageShape.getPreserveAspectRatio()).append("\"");
		}

		appendBackgroundAttributes(imageShape, xmlStr);
		appendBorderAttributes(imageShape, xmlStr);
		appendChildrenAndEnd(xmlStr, imageShape, indent, "image");

		return xmlStr.toString();
	}

	/**
	 * 
	 * @param xmlStr
	 * @param shape
	 * @param indent
	 * @param tagName
	 */
	protected void appendStart(StringBuffer xmlStr, Figure shape, String indent, String tagName) {
		xmlStr.append(indent).append("<").append(tagName);

		if (shape.getId() != null) {
			xmlStr.append(" id=\"").append(shape.getId()).append("\"");
		}

		// if (shape.getType() != null) {
		// xmlStr.append(" type=\"").append(shape.getType()).append("\"");
		// }
		if (shape.getWidget() != null) {
			xmlStr.append(" widget=\"").append(shape.getWidget()).append("\"");

			if (shape.getWidgetStyle() > 0) {
				xmlStr.append(" widget-style=\"").append(shape.getWidgetStyle()).append("\"");
			}
		}

		Filter filter = shape.getFilter();
		if (filter != null) {
			String filterId = filter.getId();
			if (filterId != null && !filterId.isEmpty()) {
				xmlStr.append(" filter=\"").append("url(#" + filterId + ")").append("\"");
			}
		}
	}

	/**
	 * 
	 * @param boundedShape
	 * @param xmlStr
	 */
	protected void appendBoundAttributes(BoundedShape boundedShape, StringBuffer xmlStr) {
		// x, y, width, height
		xmlStr.append(" x=\"").append(boundedShape.getX()).append("\"");
		xmlStr.append(" y=\"").append(boundedShape.getY()).append("\"");
		xmlStr.append(" width=\"").append(boundedShape.getWidth()).append("\"");
		xmlStr.append(" height=\"").append(boundedShape.getHeight()).append("\"");
	}

	/**
	 * 
	 * @param xmlStr
	 * @param shape
	 */
	protected void appendVisibility(StringBuffer xmlStr, Figure shape) {
		/*
		 * display
		 * 
		 * Value: inline | block | list-item | run-in | compact | marker | table | inline-table | table-row-group | table-header-group | table-footer-group | table-row | table-column-group | table-column |
		 * table-cell | table-caption | none | inherit
		 * 
		 * Initial: inline
		 * 
		 * Applies to: "svg", "g", "switch", "a", "foreignObject", graphics elements (including the "text" element) and text sub-elements (i.e., "tspan", "tref", "altGlyph", "textPath")
		 * 
		 * Inherited: no
		 */
		if (shape instanceof DisplayAttribute && ((DisplayAttribute) shape).getDisplay() != null) {
			xmlStr.append(" display=\"").append(((DisplayAttribute) shape).getDisplay()).append("\"");
		}

		/*
		 * visibility
		 * 
		 * Value: visible | hidden | collapse | inherit
		 * 
		 * Initial: visible
		 * 
		 * Applies to: graphics elements (including the "text" element) and text sub-elements (i.e., "tspan", "tref", "altGlyph", "textPath" and "a")
		 * 
		 * Inherited: yes
		 */
		if (shape instanceof VisibilityAttribute && ((VisibilityAttribute) shape).getVisibility() != null) {
			xmlStr.append(" visibility=\"").append(((VisibilityAttribute) shape).getVisibility()).append("\"");
		}
	}

	/**
	 * 
	 * @param fontAttribute
	 * @param xmlStr
	 */
	protected void appendFontAttributes(FontAttribute fontAttribute, StringBuffer xmlStr) {
		// font name
		if (fontAttribute.getFontFamily() != null) {
			xmlStr.append(" font-family=\"").append(fontAttribute.getFontFamily()).append("\"");
		}

		// font size
		if (fontAttribute.getFontSize() > 0) {
			xmlStr.append(" font-size=\"").append(fontAttribute.getFontSize()).append("px\"");
		}
	}

	/**
	 * Append attributes for background color
	 * 
	 * @param bgAttribute
	 * @param xmlStr
	 */
	protected void appendBackgroundAttributes(BackgroundAttribute bgAttribute, StringBuffer xmlStr) {
		// background color
		if (bgAttribute.getFillColor() != null) {
			xmlStr.append(" fill=\"").append(bgAttribute.getFillColor()).append("\"");
		}

		if (bgAttribute.getPattern() != null) {
			xmlStr.append(" fill=\"url(#").append(bgAttribute.getPattern().getId()).append(")\"");
		}

		// rule for applying background color
		if (bgAttribute.getFillRule() != null) {
			xmlStr.append(" fill-rule=\"").append(bgAttribute.getFillRule()).append("\"");
		}

		// fill opacity
		if (bgAttribute.getFillOpacity() != -1) {
			xmlStr.append(" fill-opacity=\"").append(bgAttribute.getFillOpacity()).append("\"");
		}

		// opacity
		if (bgAttribute.getOpacity() != -1) {
			xmlStr.append(" opacity=\"").append(bgAttribute.getOpacity()).append("\"");
		}
	}

	/**
	 * Append attributes for border color (or line color)
	 * 
	 * @param borderAttribute
	 * @param xmlStr
	 */
	protected void appendBorderAttributes(BorderAttribute borderAttribute, StringBuffer xmlStr) {
		// border color
		if (borderAttribute.getStrokeColor() != null) {
			xmlStr.append(" stroke=\"").append(borderAttribute.getStrokeColor()).append("\"");
		}

		// border width
		if (borderAttribute.getStrokeWidth() >= 0) {
			xmlStr.append(" stroke-width=\"").append(borderAttribute.getStrokeWidth()).append("px\"");
		}

		// stroke opacity
		if (borderAttribute.getStrokeOpacity() != -1) {
			xmlStr.append(" stroke-opacity=\"").append(borderAttribute.getStrokeOpacity()).append("\"");
		}

		// border dash
		if (borderAttribute.getStrokeDasharray() != null) {
			xmlStr.append(" stroke-dasharray=\"").append(borderAttribute.getStrokeDasharray()).append("\"");
		}
	}

	/**
	 * 
	 * @param transformAttribute
	 * @param xmlStr
	 */
	protected void appendTransformAttributes(TransformAttribute transformAttribute, StringBuffer xmlStr) {
		Transform transform = transformAttribute.getTransform();
		if (transform != null) {
			StringBuffer transformSb = new StringBuffer();

			// matrix
			if (transform.getMatrix() != null) {
				transformSb.append(" matrix(").append(transform.getMatrix()).append(")");
			}

			// translate
			if (transform.getTranslateX() != 0 || transform.getTranslateY() != 0) {
				transformSb.append(" translate(").append(transform.getTranslateX() + "," + transform.getTranslateY()).append(")");
			}

			// rotate
			if (transform.getRotate() != 0) {
				transformSb.append(" rotate(").append(transform.getRotate()).append(")");
			}

			// scale
			if (transform.getScaleX() != 1 || transform.getScaleY() != 1) {
				if (transform.getScaleX() == transform.getScaleY()) {
					transformSb.append(" scale(").append(transform.getScaleX()).append(")");
				} else {
					transformSb.append(" scale(").append(transform.getScaleX() + "," + transform.getScaleY()).append(")");
				}
			}

			// skewX
			if (transform.getSkewX() != 0) {
				transformSb.append(" skewX(").append(transform.getSkewX()).append(")");
			}

			// skewY
			if (transform.getSkewY() != 0) {
				transformSb.append(" skewY(").append(transform.getSkewY()).append(")");
			}

			String transformValue = transformSb.toString();
			if (transformValue.startsWith(" ") && transformValue.length() > 1) {
				transformValue = transformValue.substring(1);
			}

			xmlStr.append(" transform=\"").append(transformValue).append("\"");
		}
	}

	/**
	 * 
	 * @param xmlStr
	 * @param shape
	 * @param indent
	 * @param tagName
	 */
	protected void appendChildrenAndEnd(StringBuffer xmlStr, Figure shape, String indent, String tagName) {
		if (shape instanceof ForeignObject) {
			ForeignObject foreignObject = (ForeignObject) shape;
			// start tag end
			xmlStr.append(">").append(LINE_SEPARATOR);

			// append html content
			String content = foreignObject.getContent();
			if (content != null) {
				xmlStr.append(indent).append(INDENT_STRING).append(content).append(LINE_SEPARATOR);
			}

			// end tag
			xmlStr.append(indent).append("</" + tagName + ">").append(LINE_SEPARATOR);

		} else {
			if (shape.getChildren().isEmpty()) {
				// start tag end
				xmlStr.append("/>").append(LINE_SEPARATOR);

			} else {
				// start tag end
				xmlStr.append(">").append(LINE_SEPARATOR);

				// children
				for (IFigure child : shape.getChildren()) {
					String childXml = switchToXML(child);
					if (childXml != null) {
						xmlStr.append(childXml);
					}
				}

				// end tag
				xmlStr.append(indent).append("</" + tagName + ">").append(LINE_SEPARATOR);
			}
		}
	}

	protected String getIndent() {
		StringBuffer indentString = new StringBuffer();
		for (int i = 0; i < this.indentLevel - 1; i++) {
			indentString.append(INDENT_STRING);
		}
		return indentString.toString();
	}
}
