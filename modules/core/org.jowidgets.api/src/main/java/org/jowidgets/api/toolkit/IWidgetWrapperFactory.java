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

package org.jowidgets.api.toolkit;

import org.jowidgets.api.widgets.IComposite;
import org.jowidgets.api.widgets.IFrame;

public interface IWidgetWrapperFactory {

    /**
     * Tests if the ui reference can be converted / wrapped to an IFrame
     * 
     * @param uiReference
     * @return True if convertible, false otherwise
     */
    boolean isConvertibleToFrame(Object uiReference);

    /**
     * Creates a IFrame from an ui-platform specific ui-reference
     * 
     * Remark: The created IFrame has no parent set
     * 
     * @param uiReference The ui-platform specific object that shall be wrapped to an
     *            IFrame (e.g. Window for Swing, Shell for swt).
     * 
     * @return The created IFrame
     * @throws IllegalArgumentException If the ui-reference could not be wrapped to an IFrame.
     */
    IFrame createFrame(Object uiReference);

    /**
     * Tests if the ui reference can be converted / wrapped to an IComposite
     * 
     * @param uiReference
     * @return True if convertible, false otherwise
     */
    boolean isConvertibleToComposite(Object uiReference);

    /**
     * Creates a IComposite from an ui-platform specific ui-reference
     * 
     * Remark: The created IComposite has no parent set
     * 
     * @param uiReference The ui-platform specific object that shall be wrapped to an
     *            IComposite (e.g. Container for Swing, Composite for swt).
     * @return The created ICompositeCommon
     * @throws IllegalArgumentException If the ui-reference could not be wrapped to an IComposite.
     */
    IComposite createComposite(Object uiReference);

}
