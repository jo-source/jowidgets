/*
 * Copyright (c) 2011, Benjamin Marstaller
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

package org.jowidgets.api.test;

import junit.framework.JUnit4TestAdapter;

import org.jowidgets.api.test.blueprint.DummyBluePrintFactory;
import org.jowidgets.api.test.blueprint.IHierarchy2nd3BluePrint;
import org.jowidgets.api.test.blueprint.defaults.Hierarchy1stDefaults;
import org.jowidgets.api.test.blueprint.defaults.Hierarchy2nd1Defaults;
import org.jowidgets.api.test.blueprint.defaults.Hierarchy2nd2Defaults;
import org.jowidgets.api.test.blueprint.defaults.Hierarchy2nd3Defaults;
import org.jowidgets.api.test.blueprint.defaults.Hierarchy3rdDefaults;
import org.jowidgets.api.test.blueprint.defaults.Hierarchy4thDefaults;
import org.jowidgets.api.test.blueprint.defaults.registry.DummyDefaultsInitializerRegistry;
import org.jowidgets.api.toolkit.Toolkit;
import org.jowidgets.api.widgets.blueprint.defaults.IDefaultsInitializerRegistry;
import org.jowidgets.api.widgets.blueprint.factory.IBluePrintFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public final class BluePrintFactoryAnnotationsTest {

	private static final IBluePrintFactory BLUE_PRINT_FACTORY = Toolkit.getBluePrintFactory();
	private DummyBluePrintFactory dummyBluePrintFactory;

	private Hierarchy1stDefaults hierarchy1stDefault;
	private Hierarchy2nd1Defaults hierarchy2nd1Default;
	private Hierarchy2nd2Defaults hierarchy2nd2Default;
	private Hierarchy3rdDefaults hierarchy3rdDefault;
	private Hierarchy4thDefaults hierarchy4thDefault;
	private IDefaultsInitializerRegistry defaultsInitializerRegistry;
	private Hierarchy2nd3Defaults hierarchy2nd3Default;

	@Before
	public void setUp() {
		defaultsInitializerRegistry = BLUE_PRINT_FACTORY.getDefaultsInitializerRegistry();

		hierarchy1stDefault = new Hierarchy1stDefaults();
		hierarchy3rdDefault = new Hierarchy3rdDefaults();
		hierarchy4thDefault = new Hierarchy4thDefaults();
		hierarchy2nd1Default = new Hierarchy2nd1Defaults();
		hierarchy2nd2Default = new Hierarchy2nd2Defaults();
		hierarchy2nd3Default = new Hierarchy2nd3Defaults();

		dummyBluePrintFactory = new DummyBluePrintFactory(BLUE_PRINT_FACTORY);
		dummyBluePrintFactory.setDefaultsInitializerRegistry(new DummyDefaultsInitializerRegistry(
			hierarchy1stDefault,
			hierarchy2nd1Default,
			hierarchy2nd2Default,
			hierarchy2nd3Default,
			hierarchy3rdDefault,
			hierarchy4thDefault));
	}

	@After
	public void tearDown() {
		dummyBluePrintFactory.setDefaultsInitializerRegistry(defaultsInitializerRegistry);
	}

	@Test
	public void testAnnotations() {
		final IHierarchy2nd3BluePrint hierarchy2nd3 = dummyBluePrintFactory.hierarchy2nd3();

		// overwritten with annotation-based DefaultInitializer -> see IHierarchy2nd3BluePrint
		Assert.assertEquals(666, hierarchy2nd3.getDefaultInitializerTestValue());

		// implementation of convenience-method injected via annotation -> see IHierarchy2nd3Convenience
		Assert.assertEquals(0, hierarchy2nd3.getConvenienceAnnotationCalled());
		hierarchy2nd3.introduce2nd3("");
		Assert.assertEquals(1, hierarchy2nd3.getConvenienceAnnotationCalled());
		hierarchy2nd3.introduce2nd3("");
		Assert.assertEquals(2, hierarchy2nd3.getConvenienceAnnotationCalled());
	}

	public static junit.framework.Test suite() {
		final JUnit4TestAdapter suite = new JUnit4TestAdapter(BluePrintFactoryAnnotationsTest.class);
		return suite;
	}

}
