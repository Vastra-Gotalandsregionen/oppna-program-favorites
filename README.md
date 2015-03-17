# oppna-program-favorites #

The project oppna-program-favorites is an application that allows users to bookmark Liferay layouts and later revisit these pages.

The plugin is designed and implemented with the intention that the favorites portlet is to be used included in a Liferay theme. See section below "Include in theme".

![Favorites in a theme](https://bytebucket.org/monator/oppna-program-favorites/raw/30318d92b2255122465cde31c8608236e377291e/documentation/favorites-included-in-theme.png?token=840deb6414b2bc440a5628355be0073d8990eca8)

## Basic Features ##

User clicks the star icon in the portlet. This triggers an overlay displaying a link/button to bookmark the current page. Below the button a list is displayed with all the layouts bookmarked by the user in the current site.

If a site is already bookmarked the outlined start becomes filled with a solid background and the add link in the overlay changes to a remove link.

## Include in theme ##

The plugin is designed to be used by including the portlet in a Liferay theme. Below is outlined how including the portlet to the theme is done:

In init_custom.ftl the following sections are added:

~~~~
<#------ Favorites ----------------------------------------------------------------------------------------------------------------->

<#assign use_favorites_quickadd = true />

<#assign favorites_quickadd_portlet_id = "favoritesquickadd_WAR_oppnaprogramfavoritesportlet" />
~~~~

When including the portlet a Freemarker macro is used, which also is definied in init_custom.ftl:

~~~~
<#macro includePortlet portlet_id>

		${freeMarkerPortletPreferences.reset()}

		${freeMarkerPortletPreferences.setValue("portletSetupShowBorders","false")}

		${theme.runtime(portlet_id, "", freeMarkerPortletPreferences)}
		${freeMarkerPortletPreferences.reset()}
</#macro>
~~~~

When including the portlet in the theme using the freemarker macro, optionally extra markup can be added to allow more detailed styling and placement in theme as in this example:

~~~~
<#if use_favorites_quickadd >
  <div class="navbar-tools-item">
    <div id="favorites">
      <@includePortlet portlet_id=favorites_quickadd_portlet_id />
    </div>
  </div>
</#if>
~~~~

Styling for the favorites portlet can be found in the project [ Intra Theme ](https://bitbucket.org/monator/vgr-62-intra-theme). Specific styling is placed in the partial molecules/_favorites.scss.

## Requirements ##

The plugin creates a custom table in the Liferay database using JPA. In order for this to work, a database pool must be configured in tomcat as well as in the portlet.

### Configuring a database pool in tomcat ###

Edit liferay-bundle/tomcat-7.0.42/conf/server.xml

Inside <GlobalNamingResources> add:

~~~~
<GlobalNamingResources>
...

  <!-- Portal DB Pool -->
  <Resource acquireIncrement="1" auth="Container" description="Portal DB Connection" factory="org.apache.naming.factory.BeanFactory" idleConnectionTestPeriod="60" jdbcUrl="jdbc:postgresql://localhost:5432/lportal" maxPoolSize="20" minPoolSize="5" name="jdbc/PortalDbPool" password="lportal" testConnectionOnCheckin="true" type="com.mchange.v2.c3p0.ComboPooledDataSource" user="lportal"/>

...
</GlobalNamingResources>
~~~~

Configure the following options:

* jdbcUrl - the jdbcUrl to your database
* name - the name of the datapool - in this case PortalDbPool
* user - database user
* password - database password
* description - description of the resource

Also, add the Resource to contex.xml (may be added at the bottom before the </context> tag):

~~~~
<!-- VGR Portal DB Pool -->
<ResourceLink global="jdbc/PortalDbPool" name="jdbc/PortalDbPool" type="javax.sql.DataSource"/>
~~~~

### Configuring the database pool in the plugin ###

Edit core-bc/modules/composites/svc/src/main/resources/jpa-connector.xml and edit <bean id="dataSource".. with your datapool:

~~~~
<bean id="dataSource" class="org.springframework.jndi.JndiObjectFactoryBean">
  <property name="jndiName" value="java:comp/env/jdbc/PortalDbPool" />
</bean>
~~~~
