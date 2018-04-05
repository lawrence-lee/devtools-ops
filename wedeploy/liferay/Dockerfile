FROM mdelapenya/liferay-portal:7-ce-ga5-oraclejdk

COPY portal-ext.properties $LIFERAY_HOME/portal-ext.properties

ADD deploy $LIFERAY_HOME/deploy
ADD osgi-configs $LIFERAY_HOME/osgi/configs