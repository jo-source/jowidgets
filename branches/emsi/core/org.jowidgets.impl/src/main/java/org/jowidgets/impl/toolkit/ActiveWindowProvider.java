/*
 * Copyright (c) 2010, grossmann
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 * * Neither the name of the jo-widgets.org nor the
 *   names of its contributors may be used to endorse or promote products
 *   derived from this software without specific prior written permission.
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

package org.jowidgets.impl.toolkit;

import java.util.HashMap;
import java.util.Map;

import org.jowidgets.api.widgets.IWindowWidget;
import org.jowidgets.common.widgets.IWidget;
import org.jowidgets.common.widgets.factory.IGenericWidgetFactory;
import org.jowidgets.common.widgets.factory.IWidgetFactoryListener;
import org.jowidgets.spi.IWidgetsServiceProvider;

public class ActiveWindowProvider {

	private final IWidgetsServiceProvider widgetsServiceProvider;
	private final Map<Object, IWindowWidget> uiReferenceToWindow;

	public ActiveWindowProvider(
		final IGenericWidgetFactory genericWidgetFactory,
		final IWidgetsServiceProvider widgetsServiceProvider) {

		this.uiReferenceToWindow = new HashMap<Object, IWindowWidget>();
		this.widgetsServiceProvider = widgetsServiceProvider;

		genericWidgetFactory.addWidgetFactoryListener(new IWidgetFactoryListener() {

			@Override
			public void widgetCreated(final IWidget widget) {
				if (widget instanceof IWindowWidget) {
					final IWindowWidget windowWidget = (IWindowWidget) widget;
					//If a WindowWidget wraps another WindowWidget it will be assumed, 
					//that the newer window wraps the previous window with the same ui reference.
					//From now, the wrapping window will returned for the active window
					uiReferenceToWindow.put(windowWidget.getUiReference(), windowWidget);
				}

			}
		});
	}

	public IWindowWidget getActiveWindow() {
		return uiReferenceToWindow.get(widgetsServiceProvider.getActiveWindowUiReference());
	}

}
