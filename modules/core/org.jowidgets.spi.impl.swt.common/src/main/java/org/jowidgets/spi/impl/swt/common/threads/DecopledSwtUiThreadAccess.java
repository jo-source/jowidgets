/*
 * Copyright (c) 2016, grossmann
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
package org.jowidgets.spi.impl.swt.common.threads;

import org.eclipse.swt.widgets.Display;
import org.jowidgets.util.Assert;
import org.jowidgets.util.concurrent.SingleThreadAccess;

public class DecopledSwtUiThreadAccess implements ISwtUiThreadAccess {

    private Display display;

    private final SingleThreadAccess singleThreadAccess;

    public DecopledSwtUiThreadAccess() {
        this(null);
    }

    public DecopledSwtUiThreadAccess(final Display display) {
        this.display = display;
        this.singleThreadAccess = new SingleThreadAccess("Decoupled swt ui thread");
        singleThreadAccess.start();
    }

    @Override
    public void invokeLater(final Runnable runnable) {
        Assert.paramNotNull(runnable, "runnable");
        singleThreadAccess.invoke(new Runnable() {
            @Override
            public void run() {
                asyncExec(runnable);
            }
        });
    }

    private void asyncExec(final Runnable runnable) {
        getDisplay().asyncExec(runnable);
    }

    @Override
    public void invokeAndWait(final Runnable runnable) throws InterruptedException {
        Assert.paramNotNull(runnable, "runnable");
        getDisplay().syncExec(runnable);
    }

    @Override
    public boolean isUiThread() {
        final Display currentDisplay = Display.getCurrent();
        return currentDisplay != null && currentDisplay == getDisplay();
    }

    @Override
    public Display getDisplay() {
        if (display == null) {
            display = Display.getDefault();
        }
        return display;
    }

}
