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
package info.magnolia.social.main;

import info.magnolia.commands.CommandsManager;
import info.magnolia.social.MediaHubSubAppDescriptor;
import info.magnolia.ui.api.app.SubAppContext;
import info.magnolia.ui.api.location.Location;
import info.magnolia.ui.dialog.formdialog.FormBuilder;
import info.magnolia.ui.form.definition.FormDefinition;
import info.magnolia.ui.framework.app.BaseSubApp;
import info.magnolia.ui.vaadin.form.FormViewReduced;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.data.Item;
import com.vaadin.data.util.PropertysetItem;

/**
 * info.magnolia.social.main.
 */
public class MediaHubMainSubApp extends BaseSubApp implements MediaHubMainSubAppView.Listener  {
    protected static final Logger log = LoggerFactory.getLogger(MediaHubMainSubApp.class);

    protected final FormViewReduced formView;
    protected final MediaHubMainSubAppView view;
    protected final FormBuilder builder;
    protected final FormDefinition formDefinition;
    protected final CommandsManager commandsManager;

    private Item item;
    private String name;

    @Inject
    protected MediaHubMainSubApp(final SubAppContext subAppContext, final FormViewReduced formView, final MediaHubMainSubAppView view,
                                 final FormBuilder builder, final CommandsManager commandsManager) {
        super(subAppContext, view);
        this.formView = formView;
        this.view = view;
        this.builder = builder;
        this.commandsManager = commandsManager;
        this.formDefinition = ((MediaHubSubAppDescriptor) subAppContext.getSubAppDescriptor()).getForm();
    }

    @Override
    protected void onSubAppStart() {
        item = new PropertysetItem();

        builder.buildReducedForm(formDefinition, formView, item, null);
        view.setFormView(formView);
        view.setListener(this);
    }

    @Override
    public MediaHubMainSubAppView getView() {
        return (MediaHubMainSubAppView)super.getView();
    }

    /**
     * Used to set the label on the tab.
     */
    @Override
    public String getCaption() {
        return name;
    }

    /**
     * In case there is a subApp instance running, here the decision is made whether this instance will handle the new {@link Location}.
     */
    @Override
    public boolean supportsLocation(Location location) {
        String newUser = location.getParameter();
        return getCurrentLocation().getParameter().equals(newUser);
    }

    @Override
    public void onActionTriggered() {
        formView.showValidation(true);
    }
}