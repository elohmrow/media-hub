/**
 * This file Copyright (c) 2017 Magnolia International
 * Ltd.  (http://www.magnolia-cms.com). All rights reserved.
 *
 *
 * This file is dual-licensed under both the Magnolia
 * Network Agreement and the GNU General Public License.
 * You may elect to use one or the other of these licenses.
 *
 * This file is distributed in the hope that it will be
 * useful, but AS-IS and WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE, TITLE, or NONINFRINGEMENT.
 * Redistribution, except as permitted by whichever of the GPL
 * or MNA you select, is prohibited.
 *
 * 1. For the GPL license (GPL), you can redistribute and/or
 * modify this file under the terms of the GNU General
 * Public License, Version 3, as published by the Free Software
 * Foundation.  You should have received a copy of the GNU
 * General Public License, Version 3 along with this program;
 * if not, write to the Free Software Foundation, Inc., 51
 * Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * 2. For the Magnolia Network Agreement (MNA), this file
 * and the accompanying materials are made available under the
 * terms of the MNA which accompanies this distribution, and
 * is available at http://www.magnolia-cms.com/mna.html
 *
 * Any modifications to this file must keep this entire header
 * intact.
 *
 */
package info.magnolia.social.config;

import info.magnolia.i18nsystem.SimpleTranslator;
import info.magnolia.ui.api.view.View;
import info.magnolia.ui.vaadin.layout.SmallAppLayout;

import javax.inject.Inject;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;

/**
 * info.magnolia.social.config.
 */
public class MediaHubConfigSubAppViewImpl implements MediaHubConfigSubAppView {
    protected final SimpleTranslator i18n;

    protected final SmallAppLayout content = new SmallAppLayout();
    protected final CssLayout inputSection = new CssLayout();
    protected final CssLayout buttonLayout = new CssLayout();

    protected Listener listener;

    @Inject
    public MediaHubConfigSubAppViewImpl(final SimpleTranslator i18n) {
        this.i18n = i18n;
    }

    @Override
    public Component asVaadinComponent() {
        return content;
    }

    @Override
    public void setListener(final Listener listener) {
        this.listener = listener;

        build();
    }

    @Override
    public void setFormView(final View formView) {
        inputSection.addComponent(formView.asVaadinComponent(), 0);
    }

    protected void build() {
        Button executeButton = new Button("do something");
        executeButton.addStyleName("v-button-smallapp");
        executeButton.addStyleName("commit");
        executeButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                listener.onActionTriggered();
            }
        });

        buttonLayout.addStyleName("v-csslayout-smallapp-actions");
        buttonLayout.addComponent(executeButton);

        inputSection.addComponent(buttonLayout);

        content.addSection(inputSection);
    }
}
