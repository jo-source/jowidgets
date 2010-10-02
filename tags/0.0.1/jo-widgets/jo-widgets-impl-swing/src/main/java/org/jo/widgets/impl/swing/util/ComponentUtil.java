/*
 * Copyright (c) 2010, Michael Grossmann
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *   * Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *   * Neither the name of the jo-widgets.org nor the
 *     names of its contributors may be used to endorse or promote products
 *     derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL jo-widgets.org BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER 
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT 
 * LIABILITY, OR TORT(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY 
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH 
 * DAMAGE.
 */
package org.jo.widgets.impl.swing.util;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Point;

import javax.swing.JFrame;

public final class ComponentUtil {

	private ComponentUtil() {
		super();
	}

	public static JFrame getFrameAnchestor(final Component component) {
		if (component instanceof JFrame) {
			return (JFrame) component;
		} else if (component.getParent() != null) {
			return getFrameAnchestor(component.getParent());
		}
		return null;
	}

	public static Point getCenterLocation(final Frame parent,
			final Dialog dialog) {
		final int width = dialog.getWidth();
		final int height = dialog.getHeight();

		final Frame frame = ComponentUtil.getFrameAnchestor(parent);
		final int x = frame.getLocation().x;
		final int y = frame.getLocation().y;

		final int parentWidth = frame.getWidth();
		final int parentHeight = frame.getHeight();

		return new Point(x + parentWidth / 2 - width / 2, y + parentHeight / 2
				- height / 2);
	}

}
