package com.customermodule.www.view.sideNav;

import com.customermodule.www.model.entity.CustomerEntity;
import com.customermodule.www.view.Impl.customer.CustomerListView;
import com.customermodule.www.view.Impl.customer.CustomerView;
import com.profilemodule.www.model.entity.GroupEntity;
import com.profilemodule.www.model.entity.UserEntity;
import com.profilemodule.www.shared.i18n.CustomI18nProvider;
import com.profilemodule.www.view.Impl.group.GroupListView;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import jakarta.annotation.security.PermitAll;
import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;

@Component
@PermitAll
public class ContractorNav {
    private final static String TITLE = "Contractor";

//    private final Class<T> entity;
//    private final AccessAnnotationChecker accessChecker;
//    public ContractorNav(Class<T> entity, AccessAnnotationChecker accessChecker) {
//        this.entity = entity;
//        this.accessChecker = accessChecker;
//    }

    public static <T> SideNavItem initNav(Class<T> entity, AccessAnnotationChecker accessChecker) {

        SideNavItem navItem = new SideNavItem(TITLE);
        navItem.setPrefixComponent(VaadinIcon.PENCIL.create());


        RouteConfiguration configuration =
                RouteConfiguration.forApplicationScope();

        if(accessChecker.hasAccess(CustomerListView.class)) {

            configuration.setRoute(CustomerEntity.VIEW_ROUTE, CustomerListView.class, (Class<? extends RouterLayout>) entity);

                SideNavItem customerItem = new SideNavItem(CustomI18nProvider.getTranslationStatic(CustomerEntity.TITLE), CustomerListView.class,CustomerEntity.icon.create());

                customerItem.getElement().addEventListener("click", event -> {
                    final UI ui = UI.getCurrent();
                    ui.navigate(CustomerEntity.VIEW_ROUTE);
                });

                navItem.addItem(customerItem);
        }


        return navItem;
    }
}
