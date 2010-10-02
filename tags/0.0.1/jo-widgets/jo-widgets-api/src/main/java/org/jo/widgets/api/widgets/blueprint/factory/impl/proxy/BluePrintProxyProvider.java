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
package org.jo.widgets.api.widgets.blueprint.factory.impl.proxy;

import java.lang.reflect.Proxy;

import org.jo.widgets.api.widgets.IWidget;
import org.jo.widgets.api.widgets.blueprint.base.IBaseBluePrint;
import org.jo.widgets.api.widgets.blueprint.factory.impl.proxy.internal.BluePrintProxyInvovationHandler;
import org.jo.widgets.api.widgets.descriptor.base.IBaseWidgetDescriptor;
import org.jo.widgets.util.Assert;

public class BluePrintProxyProvider<BLUE_PRINT_TYPE extends IBaseBluePrint<?>> {

	private final BLUE_PRINT_TYPE proxy;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BluePrintProxyProvider(
			final Class<? extends IBaseBluePrint> bluePrintType,
			final Class<? extends IBaseWidgetDescriptor> widgetDescrType) {

		Assert.paramNotNull(bluePrintType, "bluePrintType");
		Assert.paramNotNull(widgetDescrType, "widgetDescrType");

		final BluePrintProxyInvovationHandler invocationHandler = new BluePrintProxyInvovationHandler();

		proxy = (BLUE_PRINT_TYPE) Proxy.newProxyInstance(
				bluePrintType.getClassLoader(), new Class[] { bluePrintType },
				invocationHandler);

		invocationHandler
				.initialize(
						proxy,
						(Class<? extends IBaseBluePrint<IBaseBluePrint<?>>>) bluePrintType,
						(Class<? extends IBaseWidgetDescriptor<? extends IWidget>>) widgetDescrType);

	}

	public BLUE_PRINT_TYPE getBluePrint() {
		return proxy;
	}

}
